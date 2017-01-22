package net.nothingmuch.jelda.utilities;

import com.badlogic.gdx.utils.Array;

/**
 * Static constant variables
 */
public class Constants {
	
	public static final boolean DEBUG = true;
	
	/* PHYSICS CONSTANTS */
	/*
		* If group incidices are NOT of the same magnitude, or magnitude of 0, collision given by category & mash bits apply.
		*
		* If they ARE of the same magnitude:
		*      BOTH NEGATIVE, they will always IGNORE collision
		*      BOTH POSITIVE, they will ALWAYS collide
		*      OPPOSITE SIGN, fall back to collision rules
	*/
	public static final float PPM = 32f;
	public static final short BIT_NOCOLLISION = - 1,
			BIT_LINK = 1,
			BIT_TERRAIN = 1,
			BIT_LINK_FEET = 2,
			BIT_ITEM = 4,
			BIT_ENEMY = 8,
			BIT_SENSOR = 16;
	public static final short GINDEX_SENSOR = 0,
							  GINDEX_LINK = 0,
							  GINDEX_LINK_FEET = 1,
							  GINDEX_TERRAIN = 1;
	
	/* GRAPHICS CONSTANTS */
	public final static int WINDOW_WIDTH = 1600,
			WINDOW_HEIGHT = 900;
	private final static float ASPECT_RATIO = (float) WINDOW_WIDTH / (float) WINDOW_HEIGHT;
	public final static int WORLD_WIDTH = 480,
			WORLD_HEIGHT = (int) ( WORLD_WIDTH / ASPECT_RATIO );
	
	public final static int H_LINK = 16, W_LINK = 16;
	
	public enum CameraStyle {
		LERP_TO_TARGET,
		LERP_TO_TARGET_ZOOM,
		LERP_BETWEEN_TARGETS,
		LOCK_ON_TARGET,
		LOCK_BETWEEN_TARGETS
	}
	
	/* GAME LEVEL CONSTANTS */
	public final static int TILE_SIZE = 16;
	public static final int W_LEVEL = 16, W_LEVEL_TILE = W_LEVEL * TILE_SIZE,
			H_LEVEL = 11, H_LEVEL_TILE = H_LEVEL * TILE_SIZE;
	
	/* OVERWORLD TILE REFERENCES */
	public static final int DOOR_REF = Integer.parseInt( "12", 16 );
	public static final int BLANK_TILE_REF = Integer.parseInt( "18", 16 );
	public static final Array<Integer> LEVEL_CHANGE_TILES = new Array<Integer>( false,
			new Integer[] { Integer.parseInt( "02", 16 ), Integer.parseInt( "0e", 16 ), Integer.parseInt( "14", 16 ), Integer.parseInt( "1a", 16 ), Integer.parseInt( "20", 16 ) },
			0, 5 );
	
	public enum WorldType {
		OVERWORLD( 16, 8 ),
		INSIDE( 16, 8 ),
		DUNGEON_0( 0, 0 );
		
		public final int N_X, N_Y;
		
		WorldType( int n_x, int n_y ) {
			this.N_X = n_x;
			this.N_Y = n_y;
		}
	}
	
	public enum Direction {
		RIGHT( 0 ), UP( 90 ), LEFT( 180 ), DOWN( 270 ),;
		private final float angle;
		
		Direction( float angle ) {
			this.angle = angle;
		}
		
		public static float getAngle( Direction d ) {
			return d.angle;
		}
		
		public static Direction getDirection( float angle ) {
			angle %= 360;
			for( Direction a : values() ) {
				if( a.angle == angle ) return a;
			}
			return null;
		}
		
		public static Direction opposite( Direction d ) {
			return values()[ ( d.ordinal() + 2 ) % values().length ];
		}
		
		public static Direction next( Direction d ) {
			return values()[ ( d.ordinal() + 1 ) % values().length ];
		}
		
		public float getAngle() {
			return getAngle( this );
		}
		
		public Direction opposite() {
			return opposite( this );
		}
		
		public Direction next() {
			return next( this );
		}
		
	}
	
	public enum ShaderColor {
		DEFAULT, DARK, RED, BLUE;
		
		public static ShaderColor next( ShaderColor shaderColor ) {
			return values()[ ( shaderColor.ordinal() + 1 ) % values().length ];
		}
		
		public ShaderColor next() {
			return next( this );
		}
	}
	
	public enum LinkColor {
		GREEN, RED, BLUE;
		
		public static LinkColor next( LinkColor linkColor ) {
			return values()[ ( linkColor.ordinal() + 1 ) % values().length ];
		}
		
		public LinkColor next() {
			return next( this );
		}
	}
	
}
