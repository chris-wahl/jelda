package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.GameWorldManager;
import net.nothingmuch.jelda.screens.WorldScreen;

import static net.nothingmuch.jelda.utilities.Constants.CameraStyle;
import static net.nothingmuch.jelda.utilities.Constants.WorldType;

/**
 * Overworld-specific handler class
 */
public class Overworld extends GameWorld {
	
	public Overworld( WorldScreen worldScreen, GameWorldManager worldManager, Link link ) {
		super( worldScreen, worldManager, WorldType.OVERWORLD, link );
		
		worldScreen.getCameraManager().setTargetA( levelGrid[ 7 ][ 0 ] );
		worldScreen.getCameraManager().setCameraStyle( CameraStyle.LERP_TO_TARGET_ZOOM );
	}
	
	public Overworld( WorldScreen worldScreen, GameWorldManager worldManager, Link link, boolean startInWorld ) {
		this( worldScreen, worldManager, link );
		
		sandbox();
		if( startInWorld ) link.setInGameWorld( this, levelGrid[ 7 ][ 0 ] );
		linkInWorld = startInWorld;
	}
	
	private void sandbox(){
		//new LevelChangeSensor( this, levelGrid[ 7 ][ 0 ].getGrid( 8, 10 ), levelGrid[ 7 ][ 0 ], levelGrid[ 8 ][ 0 ], true, true );
		for( Level[] levelRow : levelGrid ){
			for( Level level : levelRow ){
				if( level.getLevelGridX() == 7 && level.getLevelGridY() == 0 ){
					System.out.print( "Test" );
				}
				level.setLevelSensors();
			}
		}
	}
	
	@Override
	public void doUpdate( float delta ) {
		link.update( delta );
		loadLevels();
		exitCheck();
	}
	
	private void exitCheck() {
		if( ! worldChange ) return;
		if( linkInWorld ) {
			link.setPosition( exitPosition );
			world.step( 0, 0, 0 );
			linkInWorld = false;
		}
		if( link.inExitAnimation() ) return;
		link.resetExitAnimation();
		exitWorld();
	}
	
	public void loadLevels() {
		for( int x = currentLevel.getLevelGridX() - 1; x < currentLevel.getLevelGridX() + 2; x++ ){
			for( int y = currentLevel.getLevelGridY() - 1; y < currentLevel.getLevelGridY() + 2; y++ ){
				if( x > - 1 && x < worldType.N_X && y > - 1 && y < worldType.N_Y && ! levelGrid[ x ][ y ].isLoaded() ) {
					levelGrid[ x ][ y ].load();
				}
			}
		}
	}
	
	public void enterWorld( int levelGridX, int levelGridY, boolean useLastPos ) {
		super.enterWorld( levelGridX, levelGridY );
		if( useLastPos ) link.setPosition( exitPosition );
	}
	
	@Override
	public void doDraw( SpriteBatch spriteBatch, float runTime ) {
		link.draw( spriteBatch, runTime );
	}
	
	@Override
	public void setCurrentLevel( Level level ) {
		super.setCurrentLevel( level );
	}
	
	@Override
	public void setExitWorld( DoorSensorTarget sensorTarget, Vector2 position ) {
		link.doExitAnimation();
		exitPosition.set( position );
		exitTarget = sensorTarget;
		worldChange = true;
		
	}
	
	@Override
	public void exitWorld() {
		link.resetExitAnimation();
		worldChange = false;
		worldManager.changeWorld( exitTarget );
	}
}
