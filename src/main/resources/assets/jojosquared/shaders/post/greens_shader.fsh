#version 150

/* gives us:

   vec2 ScreenSize;
   float GlintAlpha;
   float GameTime;
   int MenuBlurRadius;
*/
#moj_import <minecraft:globals.glsl>

layout(std140) uniform TestShaderConfig {
vec3 CameraPos;
float WorldTime;
mat4 DragonDeezNuts; // proj mat
mat4 FCBarcelonaOfficial; // pos mat
        mat4 Postal2VR; // inverse proj mat
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

float linearizeDepth(float depth)
{
float z = depth * 2.0 - 1.0;
return (near * far) / (far + near - z * (far - near));
}

float getNormalizedLinearDepth(sampler2D depthTexture, vec2 uv, float near, float far) { // 0 - 1 linear depth :D
float rawDepth = texture(depthTexture, uv).r;
float linear = linearizeDepth(rawDepth);
return (linear - near) / (far - near); // normalize to 0..1
}

vec4 EyeDontKnow(in float depth) {
vec3 NoDickNovember;
NoDickNovember.xy = (2. * gl_FragCoord.xy - ScreenSize) / min(ScreenSize.x, ScreenSize.y);
NoDickNovember.z = (2. * depth - near - far) / (far - near);
vec4 SnipSnipSnip = vec4(NoDickNovember, 1.);
SnipSnipSnip = Postal2VR * SnipSnipSnip;
return vec4(SnipSnipSnip.xyz / SnipSnipSnip.w, SnipSnipSnip.w);
}


void main() {
float a = ScreenSize.y / ScreenSize.x;
float f = 1. / tan(_FOV / 2.);
float lambda1 = far / (far - near);
float lambda2 = -(far * near) / (far - near);

mat4 proj = mat4(
a * f, 0, 0, 0,
0, f, 0, 0,
0, 0, lambda1, lambda2,
0, 0, 1, 0
);

        float rawDepth = texture(InDepthSampler, texCoord).r;

vec4 col = texture(InSampler, texCoord);
vec4 pos = EyeDontKnow(rawDepth);

pos *= proj;

fragColor = vec4(vec3(rawDepth / far), 1.0);
}