package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Created by christopher on 1/16/17.
 */
public class SANDBOX extends GameWorld {
	
	public SANDBOX( GameScreen gameScreen, Constants.WorldType worldType ) {
		super( gameScreen, worldType );

		gameScreen.getCameraManager().setTargetA( link );
		gameScreen.getCameraManager().setCameraStyle( Constants.CameraStyle.LERP_TO_TARGET_ZOOM );
		MapManager.load();
	}
	
	@Override
	public void doUpdate( float delta ) {
		world.step( 1 / 60f, 6, 2 );
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
		
		/*for( Level[] row : levelGrid ) {
			for( Level level : row ) {
				level.draw( spriteBatch, runTime );
			}
		}*/
		link.draw( spriteBatch, runTime );
	}
	
	
	@Override
	public void enterWorld( DoorSensorTarget sensorTarget ) {
		
	}
	
	@Override
	public void exitWorld( DoorSensorTarget sensorTarget ) {
		
	}
}
