package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/18/17.
 */
public class LinkBody {
	
	Body hitBox;
	Body footBox;
	boolean isInit = false;
	
	private GameWorld gameWorld;
	private Link link;
	
	public LinkBody( GameWorld gameWorld ){
		this.gameWorld = gameWorld;
		this.link = gameWorld.getLink();
	}
	
	void createHitBox(){
		Vector2 lastPosition = link.lastPosition == null ? new Vector2( 0, 0 ) : link.lastPosition;
		
		/* Init Link's hit box */
		BodyDef bodydDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodydDef.position.set( lastPosition.x / PPM, lastPosition.y / PPM );
		bodydDef.type = BodyDef.BodyType.DynamicBody;
		bodydDef.fixedRotation = true;
		shape.setAsBox( ( W_LINK - 2 ) / 2 / PPM, ( H_LINK - 2 ) / 2 / PPM );
		fixtureDef.density = 1f;
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = BIT_LINK;
		fixtureDef.filter.maskBits = (short) ( BIT_ENEMY | BIT_ITEM );
		fixtureDef.filter.groupIndex = GINDEX_LINK;
		
		hitBox = gameWorld.getWorld().createBody( bodydDef );
		hitBox.createFixture( fixtureDef ).setUserData( this.link );
		
		shape.dispose();
		shape = new PolygonShape();
		fixtureDef = new FixtureDef();
		
		float[] feetvectrices = new float[] { 0, 3,   -7, 0,  0, -3,   7, 0};
		for( int i = 0; i < feetvectrices.length; i++ ) {
			feetvectrices[ i ] /= PPM;
		}
		
		shape.set( feetvectrices );
		
		
		fixtureDef.density = 1f;
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = BIT_LINK_FEET;
		fixtureDef.filter.maskBits = (short) ( BIT_SENSOR | BIT_TERRAIN );
		fixtureDef.filter.groupIndex = GINDEX_LINK_FEET;
		fixtureDef.restitution = 0;
		
		footBox = gameWorld.getWorld().createBody( bodydDef );
		footBox.createFixture( fixtureDef ).setUserData( this.link );
		
		RevoluteJointDef jdef = new RevoluteJointDef();
		jdef.collideConnected = false;
		jdef.bodyA = hitBox;
		jdef.bodyB = footBox;
		jdef.upperAngle = 0;
		jdef.lowerAngle = 0;
		jdef.localAnchorB.y = ( H_LINK / 4f + 0.5f ) / PPM;
		
		gameWorld.getWorld().createJoint( jdef );
		
		shape.dispose();
		
		isInit = true;
		
	}
	
	void setVelocity( Vector2 velocity ) {
		hitBox.setLinearVelocity( velocity );
		footBox.setLinearVelocity( velocity );
	}
}
