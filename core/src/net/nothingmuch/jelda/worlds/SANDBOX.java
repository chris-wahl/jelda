package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.utilities.Constants;

import static net.nothingmuch.jelda.utilities.Constants.TILE_SIZE;

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
		for( Level[] levelRow : levelGrid ){
			for( Level level : levelRow ){
				level.load_in_limit( link.getPosition(), 16 * TILE_SIZE, 11 * TILE_SIZE );
			}
		}
		
	}
	
	@Override
	public void doDraw( SpriteBatch spriteBatch, float runTime ) {
		
		for( Level[] row : levelGrid ) {
			for( Level level : row ) {
				level.draw( spriteBatch, runTime );
			}
		}
		link.draw( spriteBatch, runTime );
	}
	
	@Override
	public void enterWorld() {
		
	}
	
	@Override
	public void exitWorld() {
		
	}
}
