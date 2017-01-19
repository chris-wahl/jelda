package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.TILE_SIZE;

/**
 *
 */
public class MapManager {
	
	public static final int DOOR_REF = Integer.parseInt( "12", 16 );
	public static final int BLANK_TILE_REF = Integer.parseInt( "18", 16 );
	private static HashMap<Integer, TextureRegion> tileTextures;
	private static Texture TILE_TEXTURE;
	private static int[][] MAP_GRID;
	private static boolean isLoaded = false;
	
	public static void load(){
		TILE_TEXTURE = new Texture( Gdx.files.internal( "levels/overworld/overworldtiles.png" ) );
		tileTextures = new HashMap<Integer, TextureRegion>();
		
		int n = 0;
		for( int y = 1; y < TILE_TEXTURE.getHeight(); y += 17 ){
			for( int x = 1; x < TILE_TEXTURE.getWidth(); x += 17 ){
				tileTextures.put( n, new TextureRegion( TILE_TEXTURE, x, y, TILE_SIZE, TILE_SIZE ) );
				n++;
			}
			n+=2;
		}
		
		tileTextures.put( DOOR_REF, tileTextures.get( BLANK_TILE_REF ) );
		
		/* Turn the file into a grid */
		String MAP_FILE = Gdx.files.internal( "levels/overworld/owtileblocks.lvl" ).readString();
		MAP_GRID = new int[ 256 ][ 88 ];
		
		String[] maprows = MAP_FILE.split( "\n" );
		int len = maprows.length;
		for( int y = 0; y < maprows.length; y++ ){
			String[] maptiles = maprows[ len - 1 - y ].split( " " );
			for( int x = 0; x < maptiles.length; x++ ){
				MAP_GRID[ x ][ y ] = Integer.parseInt( maptiles[ x ], 16 );
			}
		}
		isLoaded = true;
	}
	
	public static int tileRef( int mapGridX, int mapGridY ){
		if( !isLoaded ) load();
		return MAP_GRID[ mapGridX ][ mapGridY ];
	}
	
	public static TextureRegion textRef( int tileRef ){
		// TODO: Handle door sensors
		/*
		*
		* */
		return tileTextures.get( tileRef );
	}
	
	public static void dispose(){
		TILE_TEXTURE.dispose();
	}
}
