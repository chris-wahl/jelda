package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.BIT_NOCOLLISION;
import static net.nothingmuch.jelda.utilities.Constants.TILE_SIZE;

/**
 * Created by christopher on 1/16/17.
 */
public class Tile implements Drawable {
	// TODO: Determine tileref for Dungeon world transition
	
	protected boolean isLoaded = false,
					  hasSensor = false;
	protected final int tileReference;
	protected Body tileBody;
	protected boolean isCollidable;
	
	protected Vector2 pixelPosition;
	
	private Sensor sensor;
	private GameWorld gameWorld;
	private MapManager mapManager;
	
	public Tile( GameWorld gameWorld, final int tileReference, float pixelCenterX, float pixelCenterY ) {
		this.pixelPosition = new Vector2( pixelCenterX, pixelCenterY );
		this.tileReference = tileReference;
		this.gameWorld = gameWorld;
		this.mapManager = gameWorld.getMapManager();
		this.isCollidable = false;
	}
	
	public Tile( GameWorld gameWorld, int tileReference, Vector2 pixelCenter ){
		this( gameWorld, tileReference, pixelCenter.x, pixelCenter.y );
	}
	
	public void load(){
		if( isLoaded ) return;
		if( hasSensor ) sensor.activate();
		if( isCollidable ) {
			// TODO: Create a tile collider method, builds shape based on the tileReference given.  Put in BodyBuilder
			tileBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, pixelPosition.x, pixelPosition.y, TILE_SIZE, TILE_SIZE, BIT_NOCOLLISION, BIT_NOCOLLISION );
		}
		isLoaded = true;
	}
	
	public void unload(){
		if( !isLoaded ) return;
		if( hasSensor ) sensor.destroy();
		if( isCollidable ) gameWorld.destroy( tileBody );
		isLoaded = false;
	}
	
	public void setSensor( Sensor sensor ){
		this.sensor = sensor;
		this.hasSensor = true;
	}
	
	public boolean hasSensor(){
		return hasSensor;
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		if( !isLoaded ) return;
		spriteBatch.draw( mapManager.textRef( tileReference ), pixelPosition.x - TILE_SIZE / 2f, pixelPosition.y - TILE_SIZE / 2f );
	}
	
	/**
	 * Get Tile Texture Reference used by MapManager
	 */
	public int getTileReference() {
		return tileReference;
	}
}
