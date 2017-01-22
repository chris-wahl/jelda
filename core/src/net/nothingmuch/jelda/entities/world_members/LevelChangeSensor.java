package net.nothingmuch.jelda.entities.world_members;

import net.nothingmuch.jelda.managers.CameraManager;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 *
 */
public class LevelChangeSensor extends Sensor {
	
	private CameraManager cameraManager;
	private Level levelTarget0, levelTarget1;
	private Tile tile;
	
	public LevelChangeSensor( GameWorld gameWorld, Tile tile, Level levelTarget0, Level levelTarget1 ){
		super( gameWorld );
		this.cameraManager = gameWorld.getScreen().getCameraManager();
		this.tile = tile;
		setLevelTargets( levelTarget0, levelTarget1 );
	}
	
	public LevelChangeSensor( GameWorld gameWorld, Tile tile, Level levelTarget0, Level levelTarget1, boolean isActive ) {
		this( gameWorld, tile, levelTarget0, levelTarget1 );
		if( isActive ) activate();
	}
	
	public void setLevelTargets( Level target0, Level target1 ){
		levelTarget0 = target0;
		levelTarget1 = target1;
	}
	
	@Override
	public void trigger() {
		cameraManager.setTargetA( gameWorld.getLink() );
		cameraManager.setZoom( 0.5f );
	}
	
	@Override
	public void untrigger() {
		Level target = gameWorld.getLink().getPosition().dst( levelTarget0.getCamTarget() ) < gameWorld.getLink().getPosition().dst( levelTarget1.getCamTarget() ) ? levelTarget0 : levelTarget1;
		cameraManager.setZoom( 1 );
		cameraManager.setTargetA( target );
		gameWorld.setCurrentLevel( target );
	}
	
	@Override
	protected void makeSensorBody() {
		sensorBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, tile.pixelPosition.x + TILE_SIZE, tile.pixelPosition.y,
				2 * TILE_SIZE, TILE_SIZE, BIT_LINK_FEET, GINDEX_SENSOR );
	}
}
