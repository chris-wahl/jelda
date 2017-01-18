package net.nothingmuch.jelda.entities.world_members;

import net.nothingmuch.jelda.worlds.GameWorld;

/**
 * Created by christopher on 1/16/17.
 */
public class LevelSensor extends Sensor {
	
	public LevelSensor( GameWorld gameWorld ) {
		super( gameWorld );
	}
	
	public LevelSensor( GameWorld gameWorld, boolean isActive ) {
		super( gameWorld, isActive );
	}
	
	@Override
	public void trigger() {
		
	}
	
	@Override
	public void untrigger() {
		
	}
	
	@Override
	protected void makeSensorBody() {
		
	}
	// TODO: Develop class
}
