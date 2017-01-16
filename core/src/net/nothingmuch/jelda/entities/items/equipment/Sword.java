package net.nothingmuch.jelda.entities.items.equipment;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.managers.GameWorldManager;

import static net.nothingmuch.darkzelda.utils.Constants.*;

/**
 * Parent abstract sword class
 */
public abstract class Sword extends SwingWeapon {
	
	private GameWorldManager worldManager;
	
	private Body swordHitbox;
	private BodyDef bodyDef;
	private PolygonShape shape;
	private FixtureDef fixtureDef;
	private Vector2 b2dOffset = new Vector2();
	
	private final int W_SWORD = 5,
					  H_SWORD = 16;
	
	public Sword( GameWorldManager worldManager, Link link, String id, Sprite swordSprite, ShaderProgram default_shader ) {
		super( link, id, swordSprite, default_shader, true );
		this.worldManager = worldManager;
		initBody();
	}
	
	private void initBody(){
		bodyDef = new BodyDef();
		shape = new PolygonShape();
		fixtureDef = new FixtureDef();
		
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.fixedRotation = false;
		
		fixtureDef.density = 1f;
		fixtureDef.filter.groupIndex = -1;
	}
	
	private void createHitbox(){
		b2dOffset();
		shape = new PolygonShape();
		switch( directionFacing ){
			case UP:
			case DOWN:
				shape.setAsBox( W_SWORD / 2f / PPM, H_SWORD / 2f / PPM );
				break;
			case LEFT:
			case RIGHT:
			default:
				shape.setAsBox( H_SWORD / 2f / PPM, W_SWORD / 2f / PPM );
		}
		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = BIT_LINK;
		fixtureDef.filter.maskBits = BIT_ENEMY;
		//fixtureDef.filter.groupIndex = GINDEX_LINK;
		bodyDef.position.set( link.getWeaponCenter().x  / PPM + b2dOffset.x, link.getWeaponCenter().y / PPM + b2dOffset.y );
		swordHitbox = worldManager.getWorld().createBody( bodyDef );
		swordHitbox.createFixture( fixtureDef ).setUserData( this );
		shape.dispose();
	}
	
	private Vector2 b2dOffset() {
		b2dOffset.set( 0, 0 );
		switch( directionFacing ) {
			case DOWN:
			case UP:
				b2dOffset.x = -0.5f;
				break;
			case LEFT:
			case RIGHT:
				b2dOffset.y = -0.5f;
		}
		return b2dOffset.scl( 1 / PPM );
	}
	
	@Override
	public void swing() {
		createHitbox();
	}
	
	@Override
	public void stopSwinging() {
		worldManager.addToBeDestroyed( swordHitbox );
	}
	
	@Override
	protected float drawOffsetX() {
		switch( directionFacing ) {
			case DOWN:
			case UP:
				return - 0.5f;
		}
		return 0;
	}
	
	@Override
	protected float drawOffsetY() {
		switch( directionFacing ) {
			case RIGHT:
			case LEFT:
				return - 0.5f;
		}
		return 0;
	}
}
