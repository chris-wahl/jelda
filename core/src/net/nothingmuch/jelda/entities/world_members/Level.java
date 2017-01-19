package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
import net.nothingmuch.jelda.utilities.interfaces.Spawnable;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.*;
/**
 * TODO: set level sensors, set lighting.
 */
public class Level implements Targetable, Drawable, Spawnable {
	// TODO: Load Sensors
	
	public final String id;
	protected GameWorld gameWorld;
	protected MapManager mapManager;
	
	protected Body levelCenter;
	protected int levelGridX, levelGridY;
	protected Vector2 spawnPoint;
	
	protected Tile[][] tileGrid;
	protected boolean isLoaded = false;
	
	public Level( GameWorld gameWorld, int levelGridX, int levelGridY ) {
		this.gameWorld = gameWorld;
		this.mapManager = gameWorld.getMapManager();
		this.id = levelGridX + "x" + levelGridY;
		this.levelGridX = levelGridX;
		this.levelGridY = levelGridY;
		float levelX = levelGridX * W_LEVEL_TILE + W_LEVEL_TILE / 2f;
		float levelY = levelGridY * H_LEVEL_TILE + H_LEVEL_TILE / 2f;
		this.levelCenter = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, levelX, levelY, 2f, 2f, BIT_NOCOLLISION, BIT_SENSOR );
				
		tileGrid = new Tile[ W_LEVEL ][ H_LEVEL ];
		for( int x = 0; x < W_LEVEL; x++ ){
			for( int y = 0; y < H_LEVEL; y++ ){
				int mapGridX = levelGridX * W_LEVEL + x;
				int mapGridY = levelGridY * H_LEVEL + y;
				tileGrid[ x ][ y ] = new Tile( gameWorld, mapManager.tileRef( mapGridX, mapGridY ), toPixel( this, x, y ) );
				if( tileGrid[ x ][ y ].tileReference == DOOR_REF ){
					// TODO: Determine world to go to
					// Consider changing OVERWORLD map references to Integer.parseInt( "13", 16 )
					DoorSensorTarget sensorTarget = new DoorSensorTarget( WorldType.INSIDE, levelGridX, levelGridY );
					DoorSensor doorSensor = new DoorSensor( gameWorld, sensorTarget, tileGrid[ x ][ y ] );
					tileGrid[ x ][ y ].setSensor( doorSensor );
				}
				
			}
		}
	}
	
	public void load(){
		Gdx.app.log( id, "Loaded" );
		load_unload_loop( true );
		gameWorld.addLoadedLevel( this );
	}
	public void unload() {
		Gdx.app.log( id, "Unloaded" );
		load_unload_loop( false );
	}
	private void load_unload_loop( boolean load ){
		for( Tile[] tileRow : tileGrid ) {
			for( Tile t : tileRow ) {
				if( load ) t.load();
				else t.unload();
			}
		}
		isLoaded = load;
	}
	
	public void load_in_limit( Vector2 position, final float MAX_PIXEL_X, final float MAX_PIXEL_Y ){
		for( Tile[] tileRow : tileGrid ){
			for( Tile t : tileRow ) {
				if( ( Math.abs( t.pixelPosition.x - position.x ) > MAX_PIXEL_X ) || ( Math.abs( t.pixelPosition.y - position.y ) ) > MAX_PIXEL_Y ) {
					t.unload();
				} else {
					t.load();
				}
			}
		}
	}
	
	public void load_in_limit( Vector2 position, final float MAX_PIXEL_RADIUS ) {
		for( Tile[] tileRow : tileGrid ){
			for( Tile t : tileRow ) {
				if( position.dst( t.pixelPosition ) > MAX_PIXEL_RADIUS ) {
					t.unload();
				} else {
					t.load();
				}
			}
		}
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		for( int x = 0; x < W_LEVEL; x++ ){
			for( int y = 0; y < H_LEVEL; y++ ){
				try {
					tileGrid[ x ][ y ].draw( spriteBatch, runTime );
				} catch( Exception e ) {
					System.out.println( id + " -- " + x + ", " + y + ": " + tileGrid[ x ][ y ].tileReference );
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Gives the pixel x-coordinate at the center of the level tile
	 *
	 * @param level     Level element
	 * @param tileGridX Level's tilegrid X
	 * @return Pixel X position
	 */
	public static float toPixelX( Level level, int tileGridX ){
		return level.getPosition().x - 0.5f * W_LEVEL_TILE + ( tileGridX + 0.5f ) * TILE_SIZE;
	}
	/**
	 * Gives the pixel y-coordinate at the center of the level tile
	 *
	 * @param level     Level element
	 * @param tileGridY Level's tilegrid Y
	 * @return Pixel Y position
	 */
	public static float toPixelY( Level level, int tileGridY ){
		return level.getPosition().y - 0.5f * H_LEVEL_TILE + ( tileGridY + 0.5f ) * TILE_SIZE ;
	}
	/**
	 * The central pixelspace coordinates a given level's tile
	 *
	 * @param level     Level of interest
	 * @param tileGridX Level's tileGridX
	 * @param tileGridY Level's tileGridY
	 * @return Center pixelspace coordinates of Level's gridspace
	 */
	public static Vector2 toPixel( Level level, int tileGridX, int tileGridY ){
		return new Vector2( toPixelX( level, tileGridX ), toPixelY( level, tileGridY ) );
	}
	
	public static int toLevelGridX( float pixelX ){
		return (int) ( pixelX / W_LEVEL_TILE );
	}
	public static int toLevelGridY( float pixelY ){
		return (int) ( pixelY / H_LEVEL_TILE );
	}
	
	@Override
	public Vector2 getCamTarget() {
		return getPosition();
	}
	
	@Override
	public Vector2 getSpawnPoint() {
		if( spawnPoint == null ) return getPosition();
		return spawnPoint.cpy();
	}
	
	public void setSpawnPoint( int tileGridX, int tileGridY ){
		if( spawnPoint == null ) spawnPoint = new Vector2();
		spawnPoint.set( tileGrid[ tileGridX ][ tileGridY ].pixelPosition );
		
	}
	public void setSpawnPoint( Vector2 tileGrid ){
		setSpawnPoint( (int) tileGrid.x, (int) tileGrid.y );
	}
	
	public Vector2 getPosition(){
		return levelCenter.getPosition().scl( PPM );
	}
	
	public boolean isLoaded() {
		return isLoaded;
	}
	
	public int gridDist( Link link ) {
		int x = toLevelGridX( link.getPosition().x );
		int y = toLevelGridY( link.getPosition().y );
		return Math.max( Math.abs( levelGridX - toLevelGridX( link.getPosition().x ) ),
					     Math.abs( levelGridY - toLevelGridY( link.getPosition().y ) )) ;
		
	}
	
	public int getLevelGridX(){
		return levelGridX;
	}
	
	public int getLevelGridY(){
		return levelGridY;
	}
}
