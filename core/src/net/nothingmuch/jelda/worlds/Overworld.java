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
	
	private boolean worldChange = false;
	protected DoorSensorTarget exitTarget;
	protected Vector2 exitPosition = new Vector2();
	
	public Overworld( WorldScreen worldScreen, GameWorldManager worldManager, Link link ) {
		super( worldScreen, worldManager, WorldType.OVERWORLD, link );

		worldScreen.getCameraManager().setTargetA( this.link );
		worldScreen.getCameraManager().setCameraStyle( CameraStyle.LERP_TO_TARGET_ZOOM );
	}
	
	public Overworld( WorldScreen worldScreen, GameWorldManager worldManager, Link link, boolean startInWorld ) {
		this( worldScreen, worldManager, link );
		
		if( startInWorld ) link.setInGameWorld( this, levelGrid[ 7 ][ 0 ] );
		linkInWorld = startInWorld;
	}
	
	@Override
	public void doUpdate( float delta ) {
		link.update( delta );
		loadLevels();
		exitCheck();
	}
	
	private void exitCheck() {
		if( !worldChange ) return;
		if( linkInWorld ) {
			link.setPosition( exitPosition );
			world.step( 0, 0, 0 );
			linkInWorld = false;
		}
		if( link.inExitAnimation() ) return;
		link.resetExitAnimation();
		exitWorld();
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
