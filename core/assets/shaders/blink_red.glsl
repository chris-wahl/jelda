#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform vec3 skinColor;
uniform sampler2D u_texture;

void main() {
    const vec3 skinColor = vec3( 252, 152, 56 ) / 255.0; // O.G. skin
    const vec3 clothesPink = vec3( 255, 0, 255 ) / 255.0; // O.G. PINK Clothes
    const vec3 clothesBrown = vec3( 200, 76, 12 ) / 255.0; // O.G. Brown Clothes/Sword

    vec4 texColor = texture2D( u_texture, v_texCoords );
    if( texColor.rgb == skinColor ) texColor.rgb = skinColor;  // skin color is unchanged
    if( texColor.rgb == clothesPink ) texColor.rgb = vec3( 216, 40, 0 ) / 255.0;     // Clothes turn red
    else if( texColor.rgb == clothesBrown ) texColor.rgb = vec3( 255, 255, 255 ) / 255.0; // Other clothes/hair turn white

    gl_FragColor = v_color * texColor;
}