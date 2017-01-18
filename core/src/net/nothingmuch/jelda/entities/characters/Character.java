package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
import net.nothingmuch.jelda.utilities.interfaces.Spawnable;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.Direction;
import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 *
 */
public abstract class Character implements Drawable, Targetable {
	
	/* World Fields */
	protected Body characterBody;
	protected GameWorld gameWorld;
	protected Vector2 lastPosition;
	
	/* Drawing Fields */
	protected Sprite lastFrame;
	
	/* Movement Fields */
	protected Direction directionFacing;
	protected boolean isMoving;
	
	/* Methods */
	public Character( GameWorld gameWorld, float pixelCenterX, float pixelCenterY ){
		this.gameWorld = gameWorld;
		this.lastPosition = new Vector2( pixelCenterX, pixelCenterY );
		createBody();
	}
	
	public void update( float delta ){
		isMoving = getPosition().equals( lastPosition );
		if( isMoving ){
			lastPosition.set( getPosition() );
		}
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		
	}
	
	
	protected abstract void createBody();
	
	public Direction getDirectionFacing(){
		return directionFacing;
	}
	
	@Override
	public Vector2 getCamTarget() {
		return getPosition();
	}
	
	public boolean isMoving(){
		return isMoving;
	}
	public void setPosition( Spawnable spawnPoint ){
		setPosition( spawnPoint.getSpawnPoint() );
	}
	public void setPosition( Vector2 position ){
		setPosition( position.x, position.y );
	}
	public void setPosition( float x, float y ){
		setB2DPosition( x / PPM, y / PPM );
	}
	public void setB2DPosition( Vector2 position ){
		setB2DPosition( position.x, position.y );
	}
	public void setB2DPosition( float x, float y ) {
		characterBody.setTransform( x, y, 0 );
	}
	public Vector2 getPosition(){
		return getB2DPosition().scl( PPM );
	}
	public Vector2 getB2DPosition(){
		return characterBody.getPosition();
	}
	
	public void setVelocity( Vector2 moveVec ) {
		setB2DVelocity( moveVec.x / PPM, moveVec.y / PPM );
	}
	public void setVelocity( float x, float y ){
		setB2DVelocity( x / PPM, y / PPM );
	}
	public void setB2DVelocity( Vector2 moveVec ){
		setB2DVelocity( moveVec.x, moveVec.y );
	}
	public void setB2DVelocity( float x, float y ){
		characterBody.setLinearVelocity( x, y );
	}
}
