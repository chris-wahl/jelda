package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.interfaces.Triggerable;
import net.nothingmuch.jelda.worlds.GameWorld;

/**
 * Parent Sensor class
 */
public abstract class Sensor implements Triggerable {
	
	protected GameWorld gameWorld;
	protected Body sensorBody;
	protected boolean isActive;
	
	public Sensor( GameWorld gameWorld ){
		this.gameWorld = gameWorld;
		this.isActive = false;
	}
	
	public Sensor( GameWorld gameWorld, boolean isActive ){
		this( gameWorld );
		this.isActive = isActive;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void activate(){
		isActive = true;
		if( sensorBody != null ) makeSensorBody();
	}
	
	public void deactivate(){
		isActive = false;
		destroy();
	}
	
	public void destroy(){
		gameWorld.destroy( sensorBody );
	}
	
	protected abstract void makeSensorBody();
}
