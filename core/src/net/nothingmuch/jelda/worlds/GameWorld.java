package net.nothingmuch.jelda.worlds;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.Level;
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
		this.link = new Link( this );
		this.rayHandler = new RayHandler( world );
		rayHandler.setAmbientLight( 100f / 255, 100f / 255, 100f / 255, 100f / 255 );
		initGrid();
	}
	
	protected void initGrid(){
		levelGrid = new Level[ worldType.N_X ][ worldType.N_Y ];
		
		for( int y = 0; y < worldType.N_Y; y++ ){
			for( int x = 0; x < worldType.N_X; x++ ){
				levelGrid[ x ][ y ] = new Level( world, x, y );
			}
		}
	}
	
	public void destroy( Body body ){
		toBeDestoryed.add( body );
	}
	
	protected void destroyBodies(){
		for( Body b : toBeDestoryed ){
			world.destroyBody( b );
		}
		toBeDestoryed.clear();
	}
	
	public void update( float delta ){
		world.step( 1 / 60f, 6, 2 );
		doUpdate( delta );
		rayHandler.update();
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		doDraw( spriteBatch, runTime );
		rayHandler.render();
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
}
