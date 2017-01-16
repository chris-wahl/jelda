package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.nothingmuch.jelda.utilities.CameraStyles;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Handles the rendering camera for the game screen
 */
public class CameraManager {
	
	private CameraStyle cameraStyle;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private float zoom;
	private Targetable targetA, targetB;
	
	public CameraManager(){
		this.camera = new OrthographicCamera( WORLD_WIDTH, WORLD_HEIGHT );
		this.camera.setToOrtho( false );
		this.viewport = new FitViewport( WORLD_WIDTH, WORLD_HEIGHT, this.camera );
		this.viewport.apply();
		
		this.zoom = 1f;
	}
	
	public void update( float delta ){
		switch( cameraStyle ) {
			case LERP_TO_TARGET:
				CameraStyles.lerpToTarget( camera, targetA.getCamTarget() );
				break;
			case LERP_TO_TARGET_ZOOM:
				CameraStyles.lerpToTargetZoom( camera, targetA.getCamTarget(), zoom );
				break;
			case LERP_BETWEEN_TARGETS:
				CameraStyles.lerpAverageBetweenTargets( camera, targetA.getCamTarget(), targetB.getCamTarget() );
				break;
			case LOCK_ON_TARGET:
				CameraStyles.lockOnTarget( camera, targetA.getCamTarget() );
				break;
			case LOCK_BETWEEN_TARGETS:
				CameraStyles.lockAverageBetweenTargets( camera, targetA.getCamTarget(), targetB.getCamTarget() );
				break;
		}
		updateCamera( delta );
	}
	
	public void setCameraStyle( CameraStyle cameraStyle ){
		this.cameraStyle = cameraStyle;
	}
	public void setTargetA( Targetable target ) {
		this.targetA = target;
	}
	public void setTargetB( Targetable target ){
		this.targetB = target;
	}
	
	public void setTargets( Targetable targetA, Targetable targetB ){
		this.targetA = targetA;
		this.targetB = targetB;
	}
	
	private void updateCamera( float delta ){
		camera.update();
	}
	
	public void setZoom( float zoom ){
		this.zoom = zoom;
	}
	
	public void resize( int width, int height ){
		viewport.update( width, height );
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
	
	public void ClearScreen(){
		ClearScreen( 0, 0, 0, 1 );
	}
	
	public void ClearScreen( float red, float green, float blue, float alpha){
		Gdx.gl.glClearColor(red, green, blue, alpha);
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT);
	}
}
