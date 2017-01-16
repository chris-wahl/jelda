package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/7/17.
 */
public class AssetManager {
	
	// TODO: Make NPCs for caves
	
	public final static HashMap<Direction, Animation<Sprite>> BLANK_ANIMATIONS = load_blank_animation();
	/* Shaders */
	public final static HashMap<ShaderColor, ShaderProgram> BLINK_SHADERS = load_blink_shaders();
	public final static HashMap<LinkColor, ShaderProgram> LINK_COLOR_SHADERS = load_link_color_shaders();
	private static final float FRAME_DURATION = 1 / 8f;
	public final static Animation<Sprite> LINK_EXIT_ANIMATION = load_link_exit_animation();
	public final static Animation<Sprite> LINK_ENTER_ANIMATION = load_link_enter_animation();
	public final static Animation<Sprite> FLAME_ANIMATION = load_flame();
	/* Player Sprites */
	private static final TextureAtlas LINK_ATLAS = new TextureAtlas( Gdx.files.internal( "sprites/link.atlas" ) );
	public final static HashMap<Direction, Animation<Sprite>> LINK_WALK_ANIMATIONS = load_link_walk_animations();
	public final static HashMap<Direction, Sprite> LINK_SWING_SPRITES = load_link_swing_sprites();
	/* Weapon Sprites */
	private final static TextureAtlas WEAPON_ATLAS = new TextureAtlas( Gdx.files.internal( "sprites/weapon.atlas" ) );
	public final static Sprite BLANK_SPRITE = load_blank_sprite();
	public final static HashMap<Direction, Animation<Sprite>> SHIELD_ANIMATIONS = load_basic_shield_animations();
	public final static HashMap<Direction, Animation<Sprite>> BIG_SHIELD_ANIMATIONS = load_big_shield_animations();
	public final static Sprite SWORD_SPRITE = load_sword();
	/* Item Sprites */
	private final static TextureAtlas PICKUP_ATLAS = new TextureAtlas( Gdx.files.internal( "sprites/pickups.atlas" ) );
	public final static Animation<Sprite> RUPIE_ANIMATION = load_rupie();
	
	public static void load() {
		if( DEBUG ) Gdx.app.log( "AssetLoader", "load() called.  All final static values initialized." );
	}
	
	//-----------------//
	private static HashMap<Direction, Animation<Sprite>> load_link_walk_animations() {
		HashMap<Direction, Animation<Sprite>> linkAnimations = new HashMap<Direction, Animation<Sprite>>();
		
		
		/* Walk UP animation is just the same sprite reflected around the vertical WEAPON_ATLAS */
		Sprite frame0 = LINK_ATLAS.createSprite( "walkUP" );
		Sprite frame1 = new Sprite( frame0 );
		frame0.setOriginCenter();
		frame1.setOriginCenter();
		frame1.flip( true, false );
		
		linkAnimations.put( Direction.UP, new Animation<Sprite>( FRAME_DURATION, frame0, frame1 ) );
		
		/* Walk DOWN Animation operates the same way */
		frame0 = LINK_ATLAS.createSprite( "walkDOWN" );
		frame1 = new Sprite( frame0 );
		frame0.setOriginCenter();
		frame1.setOriginCenter();
		frame1.flip( true, false );
		linkAnimations.put( Direction.DOWN, new Animation<Sprite>( FRAME_DURATION, frame0, frame1 ) );
		
		/* Walk LEFT is the default pixelPosition for the two sideways walking frames */
		frame0 = LINK_ATLAS.createSprite( "walkSIDE", 0 );
		frame1 = LINK_ATLAS.createSprite( "walkSIDE", 1 );
		frame0.setOriginCenter();
		frame1.setOriginCenter();
		linkAnimations.put( Direction.LEFT, new Animation<Sprite>( FRAME_DURATION, frame0, frame1 ) );
		
		/* Walk RIGHT is the LEFT frames, but flipped */
		frame0 = new Sprite( frame0 );
		frame1 = new Sprite( frame1 );
		frame0.flip( true, false );
		frame1.flip( true, false );
		frame0.setOriginCenter();
		frame1.setOriginCenter();
		linkAnimations.put( Direction.RIGHT, new Animation<Sprite>( FRAME_DURATION, frame0, frame1 ) );
		for( Direction d : Direction.values() ) {
			linkAnimations.get( d ).setPlayMode( Animation.PlayMode.LOOP );
		}
		return linkAnimations;
	}
	
	private static HashMap<Direction, Sprite> load_link_swing_sprites() {
		HashMap<Direction, Sprite> swingSprites = new HashMap<Direction, Sprite>();
		
		Sprite frame = LINK_ATLAS.createSprite( "swingUP" );
		frame.setOriginCenter();
		
		swingSprites.put( Direction.UP, frame );
		
		frame = LINK_ATLAS.createSprite( "swingDOWN" );
		frame.setOriginCenter();
		
		swingSprites.put( Direction.DOWN, frame );
		
		frame = LINK_ATLAS.createSprite( "swingSIDE" );
		frame.setOriginCenter();
		
		swingSprites.put( Direction.LEFT, frame );
		
		frame = LINK_ATLAS.createSprite( "swingSIDE" );
		frame.flip( true, false );
		frame.setOriginCenter();
		
		swingSprites.put( Direction.RIGHT, frame );
		
		return swingSprites;
	}
	
	private static Animation<Sprite> load_link_exit_animation() {
		Sprite[] exitSprites = new Sprite[ 16 ];
		Texture exitTex = new Texture( Gdx.files.internal( "sprites/linkExit.png" ) );
		
		int n = 0;
		for( int row = 0; row < 4; row++ ) {
			for( int col = 0; col < 4; col++ ) {
				exitSprites[ n ] = new Sprite( new TextureRegion( exitTex, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE ) );
				n++;
			}
		}
		
		return new Animation<Sprite>( FRAME_DURATION, exitSprites );
	}
	
	private static Animation<Sprite> load_link_enter_animation() {
		Sprite[] enterSprites = new Sprite[ 16 ];
		Texture enterTex = new Texture( Gdx.files.internal( "sprites/linkEnter.png" ) );
		
		int n = 0;
		for( int row = 0; row < 4; row++ ) {
			for( int col = 0; col < 4; col++ ) {
				enterSprites[ n ] = new Sprite( new TextureRegion( enterTex, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE ) );
				n++;
			}
		}
		
		return new Animation<Sprite>( FRAME_DURATION, enterSprites );
	}
	
	//---------------//
	private static Sprite load_blank_sprite() {
		return WEAPON_ATLAS.createSprite( "BLANKsprite" );
	}
	
	private static HashMap<Direction, Animation<Sprite>> load_basic_shield_animations() {
		HashMap<Direction, Animation<Sprite>> shieldAnimations = new HashMap<Direction, Animation<Sprite>>();
		
		/*TextureRegion tUD = WEAPON_ATLAS.findRegion( "shieldDOWN" );
		Sprite f0 = new Sprite( tUD, - 1, 0, 8, 7 );
		Sprite f1 = new Sprite( tUD, 0, 0, 8, 7 );*/
		Sprite f0 = WEAPON_ATLAS.createSprite( "shieldDOWN", 0 );
		f0.setOriginCenter();
		Sprite f1 = WEAPON_ATLAS.createSprite( "shieldDOWN", 1 );
		f1.setOriginCenter();
		
		shieldAnimations.put( Direction.DOWN, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		shieldAnimations.put( Direction.UP, new Animation<Sprite>( FRAME_DURATION, BLANK_SPRITE ) );
		
		/*TextureRegion tSide = WEAPON_ATLAS.findRegion( "shieldSIDE" );
		f0 = new Sprite( tSide, 0, 0, 2, 8 );
		f1 = new Sprite( tSide, -1, -2, 2, 8 );*/
		
		f0 = WEAPON_ATLAS.createSprite( "shieldSIDE", 0 );
		f0.setOriginCenter();
		f1 = WEAPON_ATLAS.createSprite( "shieldSIDE", 1 );
		f1.setOriginCenter();
		
		shieldAnimations.put( Direction.LEFT, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		
		f0 = new Sprite( f0 );
		f1 = new Sprite( f1 );
		f0.flip( true, false );
		f1.flip( true, false );
		
		shieldAnimations.put( Direction.RIGHT, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		
		for( Direction d : Direction.values() ) {
			shieldAnimations.get( d ).setPlayMode( Animation.PlayMode.LOOP );
		}
		
		return shieldAnimations;
	}

	private static HashMap<Direction, Animation<Sprite>> load_big_shield_animations() {
		HashMap<Direction, Animation<Sprite>> shieldAnimations = new HashMap<Direction, Animation<Sprite>>();
		
		Sprite f0 = WEAPON_ATLAS.createSprite( "BIGshieldDOWN", 0 );
		f0.setOriginCenter();
		Sprite f1 = WEAPON_ATLAS.createSprite( "BIGshieldDOWN", 1 );
		f1.setOriginCenter();
		
		shieldAnimations.put( Direction.DOWN, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		shieldAnimations.put( Direction.UP, new Animation<Sprite>( FRAME_DURATION, BLANK_SPRITE ) );
		
		f0 = WEAPON_ATLAS.createSprite( "BIGshieldSIDE", 0 );
		f0.setOriginCenter();
		f1 = WEAPON_ATLAS.createSprite( "BIGshieldSIDE", 1 );
		f1.setOriginCenter();
		
		shieldAnimations.put( Direction.LEFT, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		
		f0 = new Sprite( f0 );
		f1 = new Sprite( f1 );
		f0.flip( true, false );
		f1.flip( true, false );
		
		shieldAnimations.put( Direction.RIGHT, new Animation<Sprite>( FRAME_DURATION, f0, f1 ) );
		
		for( Direction d : Direction.values() ) {
			shieldAnimations.get( d ).setPlayMode( Animation.PlayMode.LOOP );
		}
		
		return shieldAnimations;
	}
	
	private static HashMap<Direction, Animation<Sprite>> load_blank_animation() {
		HashMap<Direction, Animation<Sprite>> blankAnim = new HashMap<Direction, Animation<Sprite>>();
		for( Direction d : Direction.values() ) {
			blankAnim.put( d, new Animation<Sprite>( 1, BLANK_SPRITE ) );
		}
		return blankAnim;
	}
	
	private static Sprite load_sword() {
		Sprite sword = WEAPON_ATLAS.createSprite( "SwordDOWN", 0 );
		sword.setOriginCenter();
		return sword;
	}
	
	private static Animation<Sprite> load_rupie() {
		Animation<Sprite> a = new Animation<Sprite>( 1 / 4f, PICKUP_ATLAS.createSprite( "Rupy", 0 ), PICKUP_ATLAS.createSprite( "Rupy", 1 ) );
		a.setPlayMode( Animation.PlayMode.LOOP );
		return a;
	}

	private static Animation<Sprite> load_flame() {
		Sprite f0 = new Sprite( new Texture( Gdx.files.internal( "sprites/flame.png" ) ) );
		Sprite f1 = new Sprite( f0 );
		f1.flip( true, false );
		
		Animation<Sprite> flameAnimation = new Animation<Sprite>( FRAME_DURATION, f0, f1 );
		flameAnimation.setPlayMode( Animation.PlayMode.LOOP );
		return flameAnimation;
	}
	
	private static HashMap<ShaderColor, ShaderProgram> load_blink_shaders() {
		HashMap<ShaderColor, ShaderProgram> shaderPrograms = new HashMap<ShaderColor, ShaderProgram>();
		
		shaderPrograms.put( ShaderColor.DEFAULT, null );
		
		ShaderProgram program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/blink_dark.glsl" ) );
		shaderCompileCheck( ShaderColor.DARK.toString(), program );
		shaderPrograms.put( ShaderColor.DARK, program );
		
		program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/blink_blue.glsl" ) );
		shaderCompileCheck( ShaderColor.BLUE.toString(), program );
		shaderPrograms.put( ShaderColor.BLUE, program );
		
		program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/blink_red.glsl" ) );
		shaderCompileCheck( ShaderColor.RED.toString(), program );
		shaderPrograms.put( ShaderColor.RED, program );
		
		return shaderPrograms;
	}
	
	private static HashMap<LinkColor, ShaderProgram> load_link_color_shaders() {
		HashMap<LinkColor, ShaderProgram> shaderPrograms = new HashMap<LinkColor, ShaderProgram>();
		
		ShaderProgram program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/link_green.glsl" ) );
		shaderCompileCheck( LinkColor.GREEN.toString(), program );
		shaderPrograms.put( LinkColor.GREEN, program );
		
		program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/link_blue.glsl" ) );
		shaderCompileCheck( LinkColor.BLUE.toString(), program );
		shaderPrograms.put( LinkColor.BLUE, program );
		
		program = new ShaderProgram( Gdx.files.internal( "shaders/default.vert" ), Gdx.files.internal( "shaders/link_red.glsl" ) );
		shaderCompileCheck( LinkColor.RED.toString(), program );
		shaderPrograms.put( LinkColor.RED, program );
		
		return shaderPrograms;
	}
	
	private static void shaderCompileCheck( String shaderID, ShaderProgram program ) {
		if( ! program.isCompiled() ) {
			Gdx.app.log( shaderID, "Shader did not compile." );
			Gdx.app.log( " - Program log", program.getLog() );
		}
	}
	
	/* Clean up method */
	public static void dispose() {
		// Dump shaders
		for( ShaderProgram p : BLINK_SHADERS.values() ) {
			if( p == null ) continue;
			p.dispose();
		}
		for( ShaderProgram p : LINK_COLOR_SHADERS.values() ) {
			p.dispose();
		}
		
		WEAPON_ATLAS.dispose();
		LINK_ATLAS.dispose();
		PICKUP_ATLAS.dispose();
		
	}
	
}