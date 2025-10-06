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
        vec3 EffectAge; // in seconds

        mat4 ProjectionMatrix;
        mat4 PositionMatrix;
        mat4 ModelViewMatrix;
        mat4 InverseTransformMatrix; // equivelant to inverse(proj mat * model view mat)

        vec4 CameraRot;
};

uniform sampler2D InSampler;
uniform sampler2D InDepthSampler;

in vec2 texCoord;
out vec4 fragColor;

// Minecraft never calls any OpenGL functions related to setting up the depth ranges
// and these are normally hardcoded, so just hardcoding them here aswell
float near = 0.1;
float far  = 1000.0;

float _FOV = 70.0;

float linearizeDepth(float depth)
{
        float z = depth * 2.0 - 1.0;
        return (near * far) / (far + near - z * (far - near));
}

vec4 desaturate(vec4 color, float factor)
{
        vec3 lum = vec3(0.299, 0.587, 0.114);
        vec3 gray = vec3(dot(lum, color.rgb));
        return vec4(mix(color.rgb, gray, factor), color.a);
}

float cubicOut(float t) {
        float f = t - 1.0;
        return f * f * f + 1.0;
}

float bubbleSize = 64.0; // radius
float bubbleTime = 1.4; // seconds
float bubbleEdge = 1.0; // in blocks

void main() {
        vec4 tex = texture(InSampler, texCoord);
        float depth = linearizeDepth(texture(InDepthSampler, texCoord).r);
        float dist = length(vec3(1., (2.*texCoord - 1.) * vec2(ScreenSize.x/ScreenSize.y,1.) * tan(radians(_FOV / 2.))) * depth);

        float inBubble = mix(0.0, bubbleSize, cubicOut(clamp(EffectAge.x/bubbleTime, 0.0, 1.0)));

        if (dist > inBubble)
        {
                fragColor = tex;
                return;
        }
        else {
                if (dist < inBubble && dist > (inBubble - bubbleEdge))
                {
                        fragColor = vec4((vec3(1.0) - tex.rgb), tex.a);
                        return;
                }

                fragColor = desaturate(tex, 1.0);
        }
}