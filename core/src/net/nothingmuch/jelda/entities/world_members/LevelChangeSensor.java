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
	private int width;
	private boolean isVertical;
	
	public LevelChangeSensor( GameWorld gameWorld, Tile tile, int width, Level levelTarget0, Level levelTarget1, boolean isVertical ){
		super( gameWorld );
		this.cameraManager = gameWorld.getScreen().getCameraManager();
		this.tile = tile;
		this.width = width;
		this.isVertical = isVertical;
		setLevelTargets( levelTarget0, levelTarget1 );
	}
	
	public LevelChangeSensor( GameWorld gameWorld, Tile tile, int width, Level levelTarget0, Level levelTarget1, boolean isVertical, boolean isActive ) {
		this( gameWorld, tile, width, levelTarget0, levelTarget1, isVertical );
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
		float offset = ( width - 1 ) * TILE_SIZE / 2f;
		/*if( width == 1 ) offset = 0;
		else if ( width == 2 ) offset += TILE_SIZE / 2f;
		else if ( width == 3 ) offset += TILE_SIZE;
		else if ( width == 4 ) offset += 3 * TILE_SIZE / 2f;
		else if ( width == 5 ) offset += 4 * TILE_SIZE / 2f;*/
		
		if( isVertical ){
			sensorBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, tile.pixelPosition.x + offset, tile.pixelPosition.y + TILE_SIZE/ 2f,
					width * TILE_SIZE, 2 * TILE_SIZE, BIT_LINK_FEET, GINDEX_SENSOR );
		} else {
			sensorBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, tile.pixelPosition.x + TILE_SIZE /2f, tile.pixelPosition.y + offset,
					2 * TILE_SIZE, width * TILE_SIZE, BIT_LINK_FEET, GINDEX_SENSOR );
		}
	}
}
