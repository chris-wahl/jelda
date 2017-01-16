package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.nothingmuch.jelda.JeldaGame;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.screens.WorldScreen;
import net.nothingmuch.jelda.utilities.Constants;

import java.util.Stack;

/**
 * Controls the current GameState, creating, calling update, render and disposal.
 */
public class GameStateManager {
	
	private static float runTime;
	private JeldaGame jeldaGame;
	
	private CameraManager cameraManager;
	
	
	private Stack<GameScreen> states;
	private State currentState;
	
	public GameStateManager( JeldaGame jeldaGame ){
		this.jeldaGame = jeldaGame;
		this.states = new Stack<GameScreen>();
		this.cameraManager = new CameraManager();
		this.cameraManager.setCameraStyle( Constants.CameraStyle.LERP_TO_TARGET );
		runTime = 0;
		
		this.currentState = State.SANDBOX;
		this.setState( currentState );
	}
	
	
	public static void resetRunTime(){
		runTime = 0;
	}
	
	public void update( float delta ){
		runTime += delta;
		if( Gdx.input.isKeyPressed( Input.Keys.Q ) ) {
			Gdx.app.exit();
		} else if( Gdx.input.isKeyJustPressed( Input.Keys.R ) ) {
			setState( currentState );
		}
		states.peek().update( delta );
		cameraManager.update( delta );
	}
	
	public void render( float delta ){
		cameraManager.ClearScreen();
		states.peek().render( delta );
	}
	
	public void resize( int width, int height ){
		cameraManager.resize( width, height );
		states.peek().resize( width, height );
	}
	
	public void dispose(){
		for( GameScreen gs : states ){
			gs.dispose();
		}
		states.clear();
	}
	
	public void setState( State state ){
		if( states.size() >= 1 ) states.pop().dispose();
		states.push( getState( state ) );
		resetRunTime();
	}
	
	private GameScreen getState( State state ){
		switch( state ) {
			case SANDBOX:
				return new WorldScreen( cameraManager );
			default:
				return null;
		}
	}
	
	public JeldaGame getGame() {
		return jeldaGame;
	}
	public static float getRunTime(){
		return runTime;
	}
	
	public enum State{
		SANDBOX
	}
}
