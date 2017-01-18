package net.nothingmuch.jelda.entities.world_members;

import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.WorldType;

/**
 * Door Sensor class
 *
 * Detect Link's presence in a doorway and trigger the appropriate world change
 */
public class DoorSensor extends Sensor {
	
	private Tile tile;
	
	private DoorSensorTarget sensorTarget;
	private boolean isActive;
	
	public DoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile ){
		super( gameWorld );
		this.sensorTarget = sensorTarget;
		this.tile = tile;
	}
	
	public DoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile, boolean isActive ){
		this( gameWorld, sensorTarget, tile );
		this.isActive = isActive;
	}
	
	@Override
	protected void makeSensorBody() {
		
	}
	
	@Override
	public void trigger() {
		if( isActive ){
			gameWorld.exitWorld( sensorTarget );
			deactivate();
		}
	}
	
	@Override
	public void untrigger() {
		activate();
	}

	
	public class DoorSensorTarget {
		public final WorldType targetWorldType;
		public final int targetLevelX, targetLevelY;
		
		public DoorSensorTarget( WorldType targetWorldType, int targetLevelX, int targetLevelY ) {
			this.targetWorldType = targetWorldType;
			this.targetLevelX = targetLevelX;
			this.targetLevelY = targetLevelY;
		}
		
	}
	
	
}
