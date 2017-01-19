package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 *
 */
public class MapManager {
		
	private HashMap<Integer, TextureRegion> tileTextures;
	private Texture TILE_TEXTURE;
	private int[][] MAP_GRID;
	
	private final String TILE_PATH;
	private final String MAP_FILE;
	
	private final WorldType worldType;
	
	public MapManager( WorldType worldType ){
		this.worldType = worldType;
		switch( worldType ){
			default:
			case OVERWORLD:
				TILE_PATH = "levels/overworld/overworldtiles.png";
				MAP_FILE = "levels/overworld/owtileblocks.lvl";
				break;
			case INSIDE:
				TILE_PATH = "levels/inside/insidetiles.png";
				MAP_FILE = "levels/inside/intileblocks.lvl";
				break;
			case DUNGEON_0:
				TILE_PATH = "levels/dungeon/d0tiles.png";
				MAP_FILE = "levels/dungeon/d0tileblocks.lvl";
		}
		loadMap();
		loadTiles();
		
	}
	
	private void loadTiles() {
		TILE_TEXTURE = new Texture( TILE_PATH );
		tileTextures = new HashMap<Integer, TextureRegion>();
		
		int n = 0;
		for( int y = 1; y < TILE_TEXTURE.getHeight(); y += 17 ){
			for( int x = 1; x < TILE_TEXTURE.getWidth(); x += 17 ){
				tileTextures.put( n, new TextureRegion( TILE_TEXTURE, x, y, TILE_SIZE, TILE_SIZE ) );
				n++;
			}
			n+=2;
		}
		if( worldType == WorldType.OVERWORLD )
			tileTextures.put( DOOR_REF, tileTextures.get( BLANK_TILE_REF ) );
			// TODO: Setup Inside door tiles vs. Dungeon door tiles
	}
	private void loadMap(){
		String MAP_TEXT = Gdx.files.internal( MAP_FILE ).readString();
		MAP_GRID = new int[ 256 ][ 88 ];
		
		String[] maprows = MAP_TEXT.split( "\n" );
		int len = maprows.length;
		for( int y = 0; y < maprows.length; y++ ){
			String[] maptiles = maprows[ len - 1 - y ].split( " " );
			for( int x = 0; x < maptiles.length; x++ ){
				MAP_GRID[ x ][ y ] = Integer.parseInt( maptiles[ x ], 16 );
			}
		}

	}
	
	public int tileRef( int mapGridX, int mapGridY ){
		return MAP_GRID[ mapGridX ][ mapGridY ];
	}
	public TextureRegion textRef( int tileRef ){
		return tileTextures.get( tileRef );
	}
	public void dispose(){
		TILE_TEXTURE.dispose();
	}
}
