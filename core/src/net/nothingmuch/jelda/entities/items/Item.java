package net.nothingmuch.jelda.entities.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Parent controlling class for spawnable items.
 */
public abstract class Item implements Drawable, Targetable {
	
	protected Body itemBody;
	protected GameWorld gameWorld;
	protected Sprite itemSprite;
	
	protected final String id;
	
	protected final float pixelWidth, pixelHeight;
	protected Vector2 pixelPosition = new Vector2();
	
	protected Item( GameWorld gameWorld, String id, Sprite itemSprite, float pixelWidth, float pixelHeight ){
		this.gameWorld = gameWorld;
		this.id = id;
		this.itemSprite = new Sprite( itemSprite );
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
	}
	
	protected Item( GameWorld gameWorld, String id, Sprite itemSprite, float pixelWidth, float pixelHeight, float pixelCenterX, float pixelCenterY ){
		this( gameWorld, id, itemSprite, pixelWidth, pixelHeight );
		spawn( pixelCenterX, pixelCenterY );
	}
	
	protected void spawn( Vector2 spawnPoint ){
		spawn( spawnPoint.x, spawnPoint.y );
	}
	
	protected void spawn( float pixelCenterX, float pixelCenterY ){
		pixelPosition.x = pixelCenterX;
		pixelPosition.y = pixelCenterY;
		itemBody.setTransform( pixelCenterX / PPM, pixelCenterY / PPM, 0 );
		makeBody();
	}
	
	protected void despawn(){
		gameWorld.destroy( itemBody );
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		
	}
	
	protected abstract void makeBody();
	protected abstract void onCollect();
	
	@Override
	public Vector2 getCamTarget() {
		return getPixelPosition();
	}
	
	public Vector2 getPixelPosition(){
		return itemBody.getPosition().scl( PPM );
	}
}
