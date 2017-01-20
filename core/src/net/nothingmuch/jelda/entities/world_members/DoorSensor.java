package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.Gdx;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Door Sensor class
 *
 * Detect Link's presence in a doorway and trigger the appropriate world change
 */
public class DoorSensor extends Sensor {
	
	protected Tile tile;
	private DoorSensorTarget sensorTarget;
	
	public DoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile ){
		super( gameWorld );
		this.sensorTarget = sensorTarget;
		this.tile = tile;
		this.isActive = false;
	}
	
	public DoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile, boolean isActive ){
		this( gameWorld, sensorTarget, tile );
		this.activate();
	}
	
	@Override
	protected void makeSensorBody() {
		sensorBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, tile.pixelPosition.x, tile.pixelPosition.y, TILE_SIZE, TILE_SIZE / 4f, BIT_LINK_FEET, GINDEX_SENSOR );
	}
	
	@Override
	public void trigger() {
		if( isActive ){
			Gdx.app.log( tile.pixelPosition + "", "DoorSensor triggered" );
			gameWorld.setExitWorld( sensorTarget, tile.pixelPosition.cpy() );
			deactivate();
		}
	}
	
	@Override
	public void untrigger() {
		activate();
	}

	
	public static class DoorSensorTarget {
		public final WorldType targetWorldType;
		public final int targetLevelX, targetLevelY;
		
		public DoorSensorTarget( WorldType targetWorldType, int targetLevelX, int targetLevelY ) {
			this.targetWorldType = targetWorldType;
			this.targetLevelX = targetLevelX;
			this.targetLevelY = targetLevelY;
		}
	}
	
	
}
