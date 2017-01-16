package net.nothingmuch.jelda.entities.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;

import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Created by christopher on 1/16/17.
 */
public abstract class Item implements Drawable {
	
	protected Body itemBox;
	
	
	protected void spawn( Vector2 spawnPoint ){
		spawn( spawnPoint.x, spawnPoint.y );
	}
	
	protected void spawn( float x, float y ){
		itemBox.setTransform( x / PPM, y / PPM, 0 );
		spawn();
	}
	
	protected void spawn(){
		makeBody();
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		
	}
	
	protected abstract void makeBody();
}
