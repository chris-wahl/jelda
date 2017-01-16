package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.utilities.interfaces.Drawable;
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
	protected Vector2 lastPos;
	
	/* Drawing Fields */
	protected Sprite lastFrame;
	
	/* Movement Fields */
	protected Direction directionFacing;
	
	/* Methods */
	public Character( GameWorld gameWorld ){
		this.gameWorld = gameWorld;
		createBody();
	}
	
	public void update( float delta ){
		
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
