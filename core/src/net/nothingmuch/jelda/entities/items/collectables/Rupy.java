package net.nothingmuch.jelda.entities.items.collectables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.entities.items.Item;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.BIT_LINK;
import static net.nothingmuch.jelda.utilities.Constants.GINDEX_SENSOR;

/**
 * Rupy item class, overrides .draw as it is animated
 */
public class Rupy extends Item {
	
	private final static Animation<Sprite> RupieAnimation = AssetManager.RUPIE_ANIMATION;
	
	public Rupy( GameWorld gameWorld, float pixelCenterX, float pixelCenterY ) {
		super( gameWorld, "ITEM_RUPIE", AssetManager.BLANK_SPRITE, 10, 18 );
		spawn( pixelCenterX, pixelCenterY );
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ){
		Sprite frame = RupieAnimation.getKeyFrame( runTime );
		frame.setCenter( getCamTarget().x, getCamTarget().y );
		frame.draw( spriteBatch );
	}
	
	@Override
	protected void makeBody() {
		itemBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, pixelPosition.x, pixelPosition.y, pixelWidth, pixelHeight, BIT_LINK, GINDEX_SENSOR );
	}
	
	@Override
	public void onCollect() {
		Gdx.app.log( "Rupy", "Increment link's money count" );
		despawn();
	}
}
