#version 150

/* gives us:

   vec2 ScreenSize;
   float GlintAlpha;
   float GameTime;
   int MenuBlurRadius;
*/
// this makes my IDE freak out if uncommented because it thinks #moj_import is null
#moj_import <minecraft:globals.glsl>

layout(std140) uniform LimelightConfig {
        vec3 CameraPos;
        float WorldTime;

        mat4 ProjectionMatrix;
        mat4 PositionMatrix;
        mat4 ModelViewMatrix;
        mat4 InverseTransformMatrix; // equivelant to inverse(proj mat * model view mat)
};

uniform sampler2D InSampler;
uniform sampler2D InDepthSampler;

in vec2 texCoord;
out vec4 fragColor;

// Minecraft never calls any OpenGL functions related to setting up the depth ranges
// and these are normally hardcoded, so just hardcoding them here aswell
float near = 0.05;
float far  = 100.0;

float _FOV = 70.0;

float linearizeDepth(in float depth)
{
        float z = depth * 2.0 - 1.0;
        return (near * far) / (far + near - z * (far - near));
}

// 0 - 1 linear depth :D
float getNormalizedLinearDepth(in float rawDepth) {
        float linear = linearizeDepth(rawDepth);
        return (linear - near) / (far - near); // normalize to 0..1
}

// Screenspace -> Worldspace
vec4 s2w(in float depth, in vec2 uv) {
        vec3 ndc;

        // TODO: see if gl_FragCoord actually works in this context. gl_DepthRange doesn't, so gl_FragCoord might not.
        // ndc.xy = (2. * gl_FragCoord.xy - ScreenSize) / min(ScreenSize.x, ScreenSize.y);
        // this line uses texCoord instead of gl_FragCoord
        ndc.xy = (2. * uv.xy - ScreenSize) / min(ScreenSize.x, ScreenSize.y);

        ndc.z = (2. * depth - near - far) / (far - near);

        vec4 worldPos = vec4(ndc, 1.);
        worldPos = InverseTransformMatrix * worldPos;

        worldPos = vec4(worldPos.xyz / worldPos.w, worldPos.w);
        // for some reason it was inverted
        // worldPos.y = -worldPos.y;

        return worldPos;
}

void main() {
        vec4 tex = texture(InSampler, texCoord);
        float depth = texture(InDepthSampler, texCoord).r;
        depth = getNormalizedLinearDepth(depth);

        float aspect = ScreenSize.y / ScreenSize.x;
        float scalingFac = 1. / tan(_FOV / 2.);
        float lambda1 = far / (far - near);
        float lambda2 = -(far * near) / (far - near);

        mat4 proj = mat4(
                aspect * scalingFac, 0,          0,       0,
                0,                   scalingFac, 0,       0,
                0,                   0,          lambda1, lambda2,
                0,                   0,          1,       0
        );

        vec3 worldPos = s2w(depth, texCoord).xyz + CameraPos;
        //worldPos *= proj;

        vec4 col = vec4(worldPos, 1.0);

        if (texCoord.x < texCoord.y)
        {
                col = vec4(CameraPos - vec3(0.0, 1.62, 0.0), 1.0);
        }

        fragColor = mix(col, tex, clamp(distance(texCoord, vec2(0.5)) * 8.0, 0.0, 1.0));
}