#version 150

uniform sampler2D DiffuseSampler;
uniform vec2 InSize;
in vec2 texCoord;
out vec4 fragColor;

// Convert RGB to HSV
vec3 rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs((q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

// Convert HSV to RGB
vec3 hsv2rgb(vec3 c) {
    vec3 p = abs(fract(c.xxx + vec3(0.0, 1.0 / 3.0, 2.0 / 3.0)) * 6.0 - 3.0);
    return c.z * mix(vec3(1.0), clamp(p - 1.0, 0.0, 1.0), c.y);
}

void main() {
    vec4 color = texture(DiffuseSampler, texCoord);
    vec3 hsv = rgb2hsv(color.rgb);
    hsv.x = mod(hsv.x + 0.5, 1.0); // shift hue by 180°
    vec3 rgb = hsv2rgb(hsv);
    fragColor = vec4(rgb, color.a);
}
