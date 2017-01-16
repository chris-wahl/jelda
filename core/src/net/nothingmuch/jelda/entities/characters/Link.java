package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;
import net.nothingmuch.jelda.worlds.GameWorld;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/16/17.
 */
public class Link extends Character {
	/* Movement Variables */
	private HashMap<Direction, Boolean> movement = new HashMap<Direction, Boolean>();
	private Vector2 moveVec = new Vector2( 0, 0 );
	private final float MAX_V = 2f;
	
	/* Methods */
	public Link( GameWorld gameWorld, float pixelCenterX, float pixelCenterY ){
		super( gameWorld, pixelCenterX, pixelCenterY );
		for( Direction d : Direction.values() ){
			movement.put( d, false );
		}
	}
	
	public Link( GameWorld gameWorld, Vector2 pixelPosition ){
		this( gameWorld, pixelPosition.x, pixelPosition.y );
	}
	
	public Link( GameWorld gameworld, Targetable spawnTarget ){
		this( gameworld, spawnTarget.getCamTarget() );
	}
	
	protected void createBody(){
		characterBody = BodyBuilder.createRect( gameWorld.getWorld(), lastPosition.x, lastPosition.y, 16, 16, false, true,
				BIT_LINK, (short) ( BIT_TERRAIN | BIT_ENEMY | BIT_ITEM | BIT_SENSOR ), BIT_LINK );
		characterBody.getFixtureList().get( 0 ).setUserData( this );
	}
	
	@Override
	public void update( float delta ) {
		updateMovement();
		super.update( delta );
	}
	
	private void updateMovement(){
		moveVec.x = 0;
		moveVec.y = 0;
		
		if( movement.get( Direction.LEFT ) ) moveVec.x--;
		if( movement.get( Direction.RIGHT ) ) moveVec.x++;
		if( movement.get( Direction.UP ) ) moveVec.y++;
		if( movement.get( Direction.DOWN )) moveVec.y--;
		
		moveVec.scl( MAX_V );
		setB2DVelocity( moveVec );
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		lastFrame = new Sprite( AssetManager.LINK_WALK_ANIMATIONS.get( Direction.DOWN ).getKeyFrame( runTime ) );
		lastFrame.setCenter( getPosition().x, getPosition().y );
		lastFrame.draw( spriteBatch );
		super.draw( spriteBatch, runTime );
	}
	
	public void setMove( Direction direction, boolean b ) {
		movement.put( direction, b );
	}
	
	public void setDefaultShader( ShaderProgram shaderProgram ) {
		// TODO: Fill in shaders
	}
	
	public Vector2 getWeaponCenter() {
		// TODO: Establish pixelspace weapon center
		return getPosition();
	}
	
	public boolean isBlinking(){
		// TODO: Develop blinking
		return false;
	}
	
	public void resetShader( SpriteBatch spriteBatch ) {
		// TODO: Develop shaders
	}
	
	public Vector2 getShieldCenter() {
		// TODO: Establish pixelspace shield center
		return new Vector2( 0, 0 );
	}
}
