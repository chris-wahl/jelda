package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.worlds.GameWorld;

import static net.nothingmuch.jelda.utilities.Constants.H_LEVEL_TILE;
import static net.nothingmuch.jelda.utilities.Constants.W_LEVEL_TILE;

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
				float x = ( (Level) otherObject ).getPosition().x / W_LEVEL_TILE;
				float y = ( (Level) otherObject ).getPosition().y / H_LEVEL_TILE;
				Gdx.app.log( "Level Contact", x + ", " + y );
			}
		}
	}
	
	@Override
	public void endContact( Contact contact ) {
		
	}
	
	@Override
	public void preSolve( Contact contact, Manifold oldManifold ) {
		
	}
	
	@Override
	public void postSolve( Contact contact, ContactImpulse impulse ) {
		
	}
	
	private boolean isPlayer( Contact contact ){
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
