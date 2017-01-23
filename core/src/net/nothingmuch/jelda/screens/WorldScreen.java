package net.nothingmuch.jelda.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.nothingmuch.jelda.managers.CameraManager;
import net.nothingmuch.jelda.managers.GameStateManager;
import net.nothingmuch.jelda.managers.GameWorldManager;

import static net.nothingmuch.jelda.utilities.Constants.GUI_HEIGHT;
import static net.nothingmuch.jelda.utilities.Constants.WORLD_WIDTH;

/**
 * Screen for managing when Link is in the GameWorld and moving around
 */
public class WorldScreen extends GameScreen {
	
	private GameWorldManager gameWorldManager;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	
	public WorldScreen( CameraManager cameraManager ) {
		super( cameraManager );
		gameWorldManager = new GameWorldManager( this );
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
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
		
		drawUI( delta );
		//if( DEBUG ) cameraManager.debugRender( gameWorldManager.getWorld() );
	}
	
	private void drawUI( float delta ){
		shapeRenderer.begin( ShapeRenderer.ShapeType.Filled );
		shapeRenderer.setColor( Color.BLACK );
		shapeRenderer.rect( 50, 50, WORLD_WIDTH, GUI_HEIGHT );
		shapeRenderer.end();
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
