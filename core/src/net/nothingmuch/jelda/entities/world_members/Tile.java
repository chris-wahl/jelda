package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.BIT_NOCOLLISION;
import static net.nothingmuch.jelda.utilities.Constants.TILE_SIZE;

/**
 * Created by christopher on 1/16/17.
 */
public class Tile implements Drawable {
	// TODO: Develop class
	
	protected Body tileBody;
	protected boolean isLoaded = false;
	
	protected Vector2 pixelPosition;
	private GameWorld gameWorld;
	
	public Tile( GameWorld gameWorld, float pixelCenterX, float pixelCenterY ) {
		this.pixelPosition = new Vector2( pixelCenterX, pixelCenterY );
		this.gameWorld = gameWorld;
	}
	
	public Tile( GameWorld gameWorld, Vector2 pixelCenter ){
		this( gameWorld, pixelCenter.x, pixelCenter.y );
	}
	
	public void load(){
		if( isLoaded ) return;
		tileBody = BodyBuilder.createSensorRect( gameWorld.getWorld(), this, pixelPosition.x, pixelPosition.y, TILE_SIZE, TILE_SIZE, BIT_NOCOLLISION, BIT_NOCOLLISION );
		isLoaded = true;
	}
	
	public void unload(){
		if( !isLoaded ) return;
		gameWorld.destroy( tileBody );
		isLoaded = false;
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		
	}
}
