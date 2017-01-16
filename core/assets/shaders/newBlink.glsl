#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

uniform int j;


const vec3 white = vec3( 1, 1, 1 );
const vec3 lightBlue = vec3( 92, 148, 252 ) / 255.0;
const vec3 darkBlue = vec3( 0, 0, 168.0 / 255.0 );
const vec3 red = vec3( 216, 40, 0 ) / 255.0;
const vec3 darkSkin = vec3( 0, 128, 136 ) / 255.0;
const vec3 black = vec3( 0, 0, 0 );

const vec3 skinColor = vec3( 252, 152, 56 ) / 255.0; // O.G. skin
const vec3 clothesGreen = vec3( 128, 208, 16 ) / 255.0; // O.G. Green Clothes
const vec3 clothesBrown = vec3( 200, 76, 12 ) / 255.0; // O.G. Brown Clothes/Sword

void main() {
    int k = j;
    vec4 texColor = texture2D( u_texture, v_texCoords );
    if( j == 0 ) {
        if( texColor.rgb == skinColor ) texColor.rgb = skinColor;
    }
    if( j == 1 ){
        if( texColor.rgb == skinColor ) texColor.rgb = darkSkin;    // Change skin to dark
        else if( texColor.rgb == clothesGreen ) texColor.rgb = black;  // Clothes turn black
        else if( texColor.rgb == clothesBrown ) texColor.rgb = red;    // Other clothes turn re
    }
    else if( j == 2 ){
        if( texColor.rgb == skinColor )  texColor.rgb = lightBlue;       // Change skin to lightBlue
        else if( texColor.rgb == clothesGreen )  texColor.rgb = darkBlue;   // Clothes turn darkBlue
        else if( texColor.rgb == clothesGreen )  texColor.rgb = white;      // Other clothes/hair turn white
    }
    else if( j == 3 ){
        if( texColor.rgb == clothesGreen )  texColor.rgb = red;       // Clothes turn red
        else if( texColor.rgb == clothesBrown )  texColor.rgb = white;    // Other clothes/hair turn white
    }

    gl_FragColor = v_color * texColor;
}

