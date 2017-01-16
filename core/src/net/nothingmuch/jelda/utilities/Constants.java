package net.nothingmuch.jelda.utilities;

/**
 * Created by christopher on 1/15/17.
 */
public class Constants {
	
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
	public static final short BIT_NOCOLLISION = -1,
							  BIT_LINK = 1,
							  BIT_TERRAIN = 1,
							  BIT_LINK_FFET = 2,
							  BIT_ITEM = 4,
							  BIT_ENEMY = 8;
	
	/* GRAPHICS CONSTANTS */
	public final static int WINDOW_WIDTH = 1600,
		   				    WINDOW_HEIGHT = 900;
	private final static float ASPECT_RATIO = (float) WINDOW_WIDTH / (float) WINDOW_HEIGHT;
	public final static int WORLD_WIDTH = 800,
							WORLD_HEIGHT = (int) ( WORLD_WIDTH / ASPECT_RATIO );
	
	public enum CameraStyle{
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
	
	public enum WorldType{
		OVERWORLD( 16, 8 ),
		DUNGEON_0( 0, 0 );
		
		public final int N_X, N_Y;
		WorldType( int n_x, int n_y ){
			this.N_X = n_x;
			this.N_Y = n_y;
		}
	}
	
	public enum Direction{
		UP, DOWN, LEFT, RIGHT
	}
}
