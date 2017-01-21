package net.nothingmuch.jelda.entities.world_members;

import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Exit sensor for Inside rooms - Expect that link will only be returning to the Overworld
 */
public class InsideDoorSensor extends DoorSensor {
	
	public InsideDoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile ) {
		super( gameWorld, sensorTarget, tile );
	}
	
	public InsideDoorSensor( GameWorld gameWorld, DoorSensorTarget sensorTarget, Tile tile, boolean isActive ) {
		super( gameWorld, sensorTarget, tile, isActive );
	}
	
	@Override
	protected void makeSensorBody() {
		sensorBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this,
				tile.pixelPosition.x + TILE_SIZE / 2f, tile.pixelPosition.y - TILE_SIZE / 1f, TILE_SIZE * 2, TILE_SIZE / 4f, BIT_LINK_FEET, GINDEX_SENSOR );
	}
}
