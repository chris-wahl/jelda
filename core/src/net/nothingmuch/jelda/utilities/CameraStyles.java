package net.nothingmuch.jelda.utilities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Camera style static reference
 */
public class CameraStyles {
	
	public static void lockOnTarget( OrthographicCamera camera, Vector2 target ) {
		camera.position.set( target.x, target.y, 0 );
		camera.update();
	}
	
	public static void lerpToTarget( OrthographicCamera camera, float x, float y ){
		// camera position + ( target position - camera position ) * lerp/smoothing factor
		camera.position.x += ( x - camera.position.x ) * 0.1f;
		camera.position.y += ( y - camera.position.y ) * 0.1f;
		camera.update();
	}
	
	public static void lerpToTargetZoom( OrthographicCamera camera, Vector2 target, float zoom ) {
		lerpToTargetZoom( camera, target.x, target.y, zoom );
	}
	
	public static void lerpToTargetZoom( OrthographicCamera camera, float x, float y, float zoom ){
		camera.zoom += ( zoom - camera.zoom ) * 0.1f;
		lerpToTarget( camera, x, y );
	}
	
	public static void lerpToTarget( OrthographicCamera camera, Vector2 target ) {
		// camera position + ( target position - camera position ) * lerp/smoothing factor
		lerpToTarget( camera, target.x, target.y );
	}
	
	public static void lockAverageBetweenTargets( OrthographicCamera camera, Vector2 targetA, Vector2 targetB ) {
		Vector3 position = camera.position;
		position.x = ( targetA.x + targetB.x ) / 2f;
		position.y = ( targetA.y + targetB.y ) / 2f;
		camera.position.set( position );
		camera.update();
	}
	
	public static void lerpAverageBetweenTargets( OrthographicCamera camera, Vector2 targetA, Vector2 targetB ) {
		float avgX = ( targetA.x + targetB.x ) / 2f;
		float avgY = ( targetA.y + targetB.y ) / 2f;
		lerpToTarget( camera, new Vector2( avgX, avgY ) );
	}
	
}
