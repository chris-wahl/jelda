package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.worlds.GameWorld;

/**
 * Contact listener for a GameWorld
 */
public class WorldContactListener implements ContactListener {
	
	private GameWorld gameWorld;
	
	public WorldContactListener( GameWorld gameWorld ) {
		this.gameWorld = gameWorld;
	}
	
	@Override
	public void beginContact( Contact contact ) {
		if( isPlayer( contact ) ){
			Pair pair = getObjects( contact );
			Link link = pair.link;
			Object otherObject = pair.otherObject;
			
			if( otherObject instanceof Level ){
				Gdx.app.log( "Level Contact", ( (Level) otherObject ).getLevelGridX() + ", " + ( (Level) otherObject ).getLevelGridY() );
			} else if ( otherObject instanceof DoorSensor ){
				( ( DoorSensor ) otherObject ).trigger();
			}
		}
	}
	
	@Override
	public void endContact( Contact contact ) {
		if( isPlayer( contact ) ){
			Pair pair = getObjects( contact );
			Link link = pair.link;
			Object otherObject = pair.otherObject;
			
			if( otherObject instanceof Level ){
				Gdx.app.log( "Level Contact", ( (Level) otherObject ).getLevelGridX() + ", " + ( (Level) otherObject ).getLevelGridY() );
			} else if ( otherObject instanceof DoorSensor ){
				( ( DoorSensor ) otherObject ).untrigger();
			}
		}
	}
	
	@Override
	public void preSolve( Contact contact, Manifold oldManifold ) {
		
	}
	
	@Override
	public void postSolve( Contact contact, ContactImpulse impulse ) {
		
	}
	
	private boolean isPlayer( Contact contact ){
		Fixture A = contact.getFixtureA();
		Fixture B = contact.getFixtureB();
		return ( contact.getFixtureA().getUserData() instanceof Link || contact.getFixtureB().getUserData() instanceof Link );
	}
	
	private Pair getObjects( Contact contact ){
		if( contact.getFixtureA().getUserData() instanceof Link ){
			return new Pair( (Link) contact.getFixtureA().getUserData(), contact.getFixtureB().getUserData() );
		}
		return new Pair( (Link) contact.getFixtureB().getUserData(), contact.getFixtureA().getUserData() );
	}
	
	private class Pair{
		final Link link;
		final Object otherObject;
		
		Pair( Link link, Object otherObject ){
			this.link = link;
			this.otherObject = otherObject;
		}
	}
}
