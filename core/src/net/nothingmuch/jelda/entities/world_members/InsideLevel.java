package net.nothingmuch.jelda.entities.world_members;

import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.H_LEVEL;
import static net.nothingmuch.jelda.utilities.Constants.W_LEVEL;

/**
 * Created by christopher on 1/18/17.
 */
public class InsideLevel extends Level {
	
	public InsideLevel( GameWorld gameWorld, int levelGridX, int levelGridY ) {
		super( gameWorld, levelGridX, levelGridY );
		loadInsideRoom();
	}
	
	private void loadInsideRoom(){
		tileGrid = new Tile[ W_LEVEL ][ H_LEVEL ];
		
		for( int y = 0; y < H_LEVEL; y++ ){
			tileGrid[ 0 ][ y ] = new Tile( gameWorld, 1, toPixel( this, 0, y ) );
			tileGrid[ 1 ][ y ] = new Tile( gameWorld, 1, toPixel( this, 1, y ) );
			tileGrid[ W_LEVEL - 1 ][ y ] = new Tile( gameWorld, 1, toPixel( this, W_LEVEL - 1, y ) );
			tileGrid[ W_LEVEL - 2 ][ y ] = new Tile( gameWorld, 1, toPixel( this, W_LEVEL - 2, y ) );
		}
		
		for( int x = 2; x < W_LEVEL - 2; x++ ){
			if( x != 7 && x != 8 ){
				tileGrid[ x ][ 0 ] = new Tile( gameWorld, 1, toPixel( this, x, 0 ) );
				tileGrid[ x ][ 1 ] = new Tile( gameWorld, 0, toPixel( this, x, 1 ) );
			} else {
				tileGrid[ x ][ 0 ] = new Tile( gameWorld, 2, toPixel( this, x, 0 ) );
				tileGrid[ x ][ 1 ] = new Tile( gameWorld, 2, toPixel( this, x, 1 ) );
			}
			tileGrid[ x ][ H_LEVEL - 1 ] = new Tile( gameWorld, 1, toPixel( this, x, H_LEVEL - 1 ) );
			tileGrid[ x ][ H_LEVEL - 2 ] = new Tile( gameWorld, 1, toPixel( this, x, H_LEVEL - 2 ) );
		}
		for( int x = 2; x < W_LEVEL - 2; x++ ){
			for( int y = 2; y < H_LEVEL - 2; y++ ){
				tileGrid[ x ][ y ] = new Tile( gameWorld, 2, toPixel( this, x, y ) );
			}
		}
		spawnPoint = toPixel( this, 7, 0 );
	}
}
