package net.nothingmuch.jelda.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Matrix4;
import net.nothingmuch.jelda.managers.CameraManager;

/**
 * Created by christopher on 1/15/17.
 */
public abstract class GameScreen implements Screen {
	
	protected CameraManager cameraManager;
	
	public GameScreen( CameraManager cameraManager ){
		this.cameraManager = cameraManager;
	}
	
	
	public void resize( int width, int height ){
	}
	
	public abstract void update( float delta );
	
	@Override
	public abstract void show();
	
	@Override
	public abstract void render( float delta );
	
	@Override
	public abstract void pause();
	
	@Override
	public abstract void resume();
	
	@Override
	public abstract void hide();
	
	public CameraManager getCameraManager() {
		return cameraManager;
	}
	
	public Matrix4 getCameraCombined(){
		return cameraManager.getCamera().combined.cpy();
	}
}
