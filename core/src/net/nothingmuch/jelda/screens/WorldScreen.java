package net.nothingmuch.jelda.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.managers.CameraManager;
import net.nothingmuch.jelda.managers.GameStateManager;
import net.nothingmuch.jelda.managers.GameWorldManager;

/**
 * Screen for managing when Link is in the GameWorld and moving around
 */
public class WorldScreen extends GameScreen {
	
	private GameWorldManager gameWorldManager;
	private SpriteBatch spriteBatch;
	
	public WorldScreen( CameraManager cameraManager ) {
		super( cameraManager );
		gameWorldManager = new GameWorldManager( this );
		spriteBatch = new SpriteBatch();
	}
	
	@Override
	public void update( float delta ) {
		gameWorldManager.update( delta );
		cameraManager.update( delta );
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void render( float delta ) {
		spriteBatch.setProjectionMatrix( cameraManager.getCamera().combined );
		gameWorldManager.draw( spriteBatch, GameStateManager.getRunTime() );
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		gameWorldManager.dispose();
		spriteBatch.dispose();
		
	}
	
	public GameWorldManager getWorldManager() {
		return gameWorldManager;
	}
}
