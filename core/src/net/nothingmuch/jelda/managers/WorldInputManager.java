package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.Direction;
import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Created by christopher on 1/16/17.
 */
public class WorldInputManager extends InputAdapter {
	
	private HashMap<Integer, Direction> MovementKeys = new HashMap<Integer, Direction>();
	private GameWorldManager gameWorldManager;
	
	public WorldInputManager( GameWorldManager gameWorldManager ){
		MovementKeys.put( Input.Keys.W, Direction.UP );
		MovementKeys.put( Input.Keys.S, Direction.DOWN );
		MovementKeys.put( Input.Keys.A, Direction.LEFT );
		MovementKeys.put( Input.Keys.D, Direction.RIGHT );
		
		this.gameWorldManager = gameWorldManager;
	}
	
	@Override
	public boolean keyDown( int keycode ) {
		if( MovementKeys.keySet().contains( keycode )){
			gameWorldManager.sendMovement( MovementKeys.get( keycode ), true );
		}
		return super.keyDown( keycode );
	}
	
	@Override
	public boolean keyUp( int keycode ) {
		if( MovementKeys.keySet().contains( keycode )){
			gameWorldManager.sendMovement( MovementKeys.get( keycode ), false );
		}
		return super.keyUp( keycode );
	}
	
	@Override
	public boolean scrolled( int amount ) {
		gameWorldManager.getCurrentGameWorld().scrollWheel( amount / PPM );
		
		return super.scrolled( amount );
	}
}
