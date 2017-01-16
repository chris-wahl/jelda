package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.worlds.GameWorld;
import net.nothingmuch.jelda.worlds.SANDBOX;

import static net.nothingmuch.jelda.utilities.Constants.Direction;
import static net.nothingmuch.jelda.utilities.Constants.WorldType;

/**
 * Created by christopher on 1/15/17.
 */
public class GameWorldManager {
	
	private GameWorld SANDBOX;
	private GameWorld currentWorld;
	
	public GameWorldManager( GameScreen gameScreen ) {
		Gdx.input.setInputProcessor( new LevelInputManager( this ) );
		
		SANDBOX = new SANDBOX( gameScreen, WorldType.OVERWORLD );
		currentWorld = SANDBOX;
	}
	
	public void update( float delta ){
		currentWorld.update( delta );
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ){
		currentWorld.draw( spriteBatch, runTime );
	}
	
	public void sendMovement( Direction direction, boolean doMove ) {
		currentWorld.getLink().setMove( direction, doMove );
	}
	
	public World getWorld() {
		return currentWorld.getWorld();
	}
}
