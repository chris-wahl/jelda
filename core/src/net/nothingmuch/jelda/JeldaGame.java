package net.nothingmuch.jelda;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import net.nothingmuch.jelda.managers.GameStateManager;

public class JeldaGame extends Game {

	private GameStateManager gameStateManager;
	private float delta;
	
	@Override
	public void create () {
		
		gameStateManager = new GameStateManager( this );
	}

	@Override
	public void render () {
		delta = Gdx.graphics.getDeltaTime();
		
		gameStateManager.update( delta );
		gameStateManager.render( delta );
	}
	
	@Override
	public void resize( int width, int height ) {
		gameStateManager.resize( width, height );
	}
	
	@Override
	public void dispose () {
		gameStateManager.dispose();
	}
}
