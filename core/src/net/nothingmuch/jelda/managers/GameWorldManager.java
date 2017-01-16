package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.worlds.GameWorld;
import net.nothingmuch.jelda.worlds.SANDBOX;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/15/17.
 */
public class GameWorldManager {
	
	private GameWorld SANDBOX;
	private GameWorld currentWorld;
	private Box2DDebugRenderer debugRenderer;
	private GameScreen gameScreen;
	
	public GameWorldManager( GameScreen gameScreen ) {
		this.gameScreen = gameScreen;
		Gdx.input.setInputProcessor( new WorldInputManager( this ) );
		
		debugRenderer = new Box2DDebugRenderer();
		
		SANDBOX = new SANDBOX( gameScreen, WorldType.OVERWORLD );
		currentWorld = SANDBOX;
	}
	
	public void update( float delta ){
		currentWorld.update( delta );
		currentWorld.postupdate();
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ){
		spriteBatch.begin();
		currentWorld.draw( spriteBatch, runTime );
		spriteBatch.end();
		
		debugRenderer.render( currentWorld.getWorld(), gameScreen.getCameraCombined().scl( PPM ) );
		currentWorld.getRayHandler().render();
	}
	
	public void sendMovement( Direction direction, boolean doMove ) {
		currentWorld.getLink().setMove( direction, doMove );
	}
	
	public World getWorld() {
		return currentWorld.getWorld();
	}
	
	public void destroy( Body body ){
		currentWorld.destroy( body );
	}
	
	public GameWorld getCurrentGameWorld() {
		return currentWorld;
	}
}
