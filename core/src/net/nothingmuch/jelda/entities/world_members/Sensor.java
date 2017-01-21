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
	protected boolean sensorMade;
	
	public Sensor( GameWorld gameWorld ){
		this.gameWorld = gameWorld;
		this.isActive = false;
		this.sensorBody = null;
	}
	
	public Sensor( GameWorld gameWorld, boolean isActive ){
		this( gameWorld );
		this.isActive = isActive;
		this.sensorMade = isActive;
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public void activate(){
		isActive = true;
		if( !sensorMade ) {
			makeSensorBody();
			sensorMade = true;
		}
	}
	
	public void deactivate(){
		isActive = false;
	}
	
	public void destroy(){
		gameWorld.destroy( sensorBody );
		sensorMade = false;
	}
	
	protected abstract void makeSensorBody();
}
