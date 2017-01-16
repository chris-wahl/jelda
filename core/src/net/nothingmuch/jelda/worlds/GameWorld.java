package net.nothingmuch.jelda.worlds;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.WorldContactListener;
import net.nothingmuch.jelda.screens.GameScreen;

import static net.nothingmuch.jelda.utilities.Constants.WorldType;

/**
 * Created by christopher on 1/15/17.
 */
public abstract class GameWorld {
	
	protected final WorldType worldType;
	protected World world;
	protected Array<Body> toBeDestoryed = new Array<Body>();
	protected RayHandler rayHandler;
	
	protected Level[][] levelGrid;
	
	protected GameScreen gameScreen;
	protected Link link;
	
	protected GameWorld( GameScreen gameScreen, WorldType worldType ){
		this.worldType = worldType;
		this.gameScreen = gameScreen;
		this.world = new World( new Vector2( 0, 0 ), false );
		this.world.setContactListener( new WorldContactListener( this ) );
		this.rayHandler = new RayHandler( world );
		float lvl = 200f;
		
		rayHandler.setAmbientLight( lvl / 255f );
		initGrid();
		
		this.link = new Link( this, levelGrid[ 0 ][ 0 ] );
	}
	
	protected void initGrid(){
		levelGrid = new Level[ worldType.N_X ][ worldType.N_Y ];
		
		for( int levelGridY = 0; levelGridY < worldType.N_Y; levelGridY++ ){
			for( int levelGridX = 0; levelGridX < worldType.N_X; levelGridX++ ){
				levelGrid[ levelGridX ][ levelGridY ] = new Level( this, levelGridX, levelGridY );
			}
		}
	}
	
	public void destroy( Body body ){
		toBeDestoryed.add( body );
	}
	private void destroyBodies(){
		for( Body b : toBeDestoryed ){
			world.destroyBody( b );
		}
		toBeDestoryed.clear();
	}
	
	public void update( float delta ){
		world.step( 1 / 60f, 6, 2 );
		doUpdate( delta );
	}
	public void postupdate(){
		destroyBodies();
		rayHandler.update();
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		doDraw( spriteBatch, runTime );
	}
	
	public abstract void doUpdate( float delta );
	public abstract void doDraw( SpriteBatch spriteBatch, float runTime );
	public abstract void enterWorld();
	public abstract void exitWorld();
	
	public Link getLink() {
		return link;
	}
	public World getWorld(){
		return world;
	}
	public RayHandler getRayHandler(){
		return rayHandler;
	}
	public void scrollWheel( float scrolled ){
		gameScreen.getCameraManager().setZoom( gameScreen.getCameraManager().getZoom() + scrolled );
	}
}
