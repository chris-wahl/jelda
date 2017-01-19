package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.screens.GameScreen;

import static net.nothingmuch.jelda.utilities.Constants.CameraStyle;
import static net.nothingmuch.jelda.utilities.Constants.WorldType;

/**
 * Overworld-specific handler class
 */
public class Overworld extends GameWorld {
	
	public Overworld( GameScreen gameScreen, Link link ) {
		super( gameScreen, WorldType.OVERWORLD, link );

		gameScreen.getCameraManager().setTargetA( this.link );
		gameScreen.getCameraManager().setCameraStyle( CameraStyle.LERP_TO_TARGET_ZOOM );
		MapManager.load();
	}
	
	public Overworld( GameScreen gameScreen, Link link, boolean setInWorld ) {
		this( gameScreen, link );
		
		if( setInWorld ) link.setInGameWorld( this, levelGrid[ 7 ][ 0 ] );
	}
	
	@Override
	public void doUpdate( float delta ) {
		link.update( delta );
		loadLevels();
	}
	
	public void loadLevels(){
		int linkLevelX = Level.toLevelGridX( link.getPosition().x );
		int linkLevelY = Level.toLevelGridY( link.getPosition().y );
		
		for( int x = -1; x < 2; x++ ){
			for( int y = -1; y < 2; y++ ){
				if( x + linkLevelX < 0 || x + linkLevelX >= worldType.N_X || y + linkLevelY < 0 || y + linkLevelY >= worldType.N_Y ) {
					continue;
				} else if( !levelGrid[ x + linkLevelX ][ y + linkLevelY ].isLoaded() ){
					levelGrid[ x + linkLevelX ][ y + linkLevelY ].load();
				}
			}
		}
	}
	
	@Override
	public void doDraw( SpriteBatch spriteBatch, float runTime ) {
		link.draw( spriteBatch, runTime );
	}
	
	@Override
	public void exitWorld( DoorSensorTarget sensorTarget ) {
		
	}
}
