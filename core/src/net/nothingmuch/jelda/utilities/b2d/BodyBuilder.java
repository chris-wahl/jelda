package net.nothingmuch.jelda.utilities.b2d;

import com.badlogic.gdx.physics.box2d.*;

import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Reference class to create bodies
 */
public class BodyBuilder {
	
	public static Body createRect( World world, float centerX, float centerY, float width, float height, boolean isStatic, boolean fixedRotation ){
		Body body;
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		
		bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = fixedRotation;
		bodyDef.position.set( centerX / PPM, centerY / PPM );
		
		shape.setAsBox( width / 2f / PPM, height / 2f / PPM );
		
		body = world.createBody( bodyDef ).createFixture( shape, 1f ).getBody();
		shape.dispose();
		return body;
	}
	
	public static Body createRect( World world, float centerX, float centerY, float width, float height, boolean isStatic, boolean fixedRotation,
	                               short cBits, short mBits, short gIndex ){
		Body body;
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodyDef.type = isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = fixedRotation;
		bodyDef.position.set( centerX / PPM, centerY / PPM );
		
		shape.setAsBox( width / 2f / PPM, height / 2f / PPM );
		
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.filter.categoryBits = cBits;
		fixtureDef.filter.maskBits = mBits;
		fixtureDef.filter.groupIndex = gIndex;
		
		body = world.createBody( bodyDef );
		body.createFixture( fixtureDef );
		
		shape.dispose();
		
		return body;
	}
	
	public static Body createSensorRect( World world, float centerX, float centerY, float width, float height,
	                                     short cBits, short mBits, short gIndex ){
		Body body;
		BodyDef bodyDef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.fixedRotation = true;
		bodyDef.position.set( centerX / PPM, centerY / PPM );
		
		shape.setAsBox( width / 2f / PPM, height / 2f / PPM );
		
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.filter.categoryBits = cBits;
		fixtureDef.filter.maskBits = mBits;
		fixtureDef.filter.groupIndex = gIndex;
		fixtureDef.isSensor = true;
		
		body = world.createBody( bodyDef );
		body.createFixture( fixtureDef );
		
		shape.dispose();
		
		return body;
	}
}
