package net.nothingmuch.jelda.worlds;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.characters.LinkBody;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.GameWorldManager;
import net.nothingmuch.jelda.managers.MapManager;
import net.nothingmuch.jelda.screens.WorldScreen;

import java.util.Iterator;

import static net.nothingmuch.jelda.utilities.Constants.W_LEVEL_TILE;
import static net.nothingmuch.jelda.utilities.Constants.WorldType;


/**
 * Created by christopher on 1/15/17.
 */
public abstract class GameWorld {
	
	protected final WorldType worldType;
	protected final MapManager mapManager;
	public boolean linkInWorld;
	protected GameWorldManager worldManager;
	protected World world;
	
	protected Array<Body> toBeDestoryed = new Array<Body>();
	
	protected WorldScreen gameScreen;
	protected RayHandler rayHandler;
	
	protected Level[][] levelGrid;
	protected Level currentLevel;
	protected Array<Level> loadedLevels = new Array<Level>();
	
	protected float PIXEL_DRAW_LIMIT = W_LEVEL_TILE;
	
	protected LinkBody linkBody;
	protected Link link;
	
	protected DoorSensorTarget exitTarget;
	protected Vector2 exitPosition = new Vector2();
	protected boolean worldChange = false;
	
	
	protected GameWorld( WorldScreen worldScreen, GameWorldManager worldManager, WorldType worldType, Link link ) {
		this.worldType = worldType;
		this.gameScreen = worldScreen;
		this.worldManager = worldManager;
		this.link = link;
		this.linkInWorld = false;
		
		this.mapManager = new MapManager( worldType );
		
		this.world = new World( new Vector2( 0, 0 ), false );
		this.world.setContactListener( new WorldContactListener( this ) );
		this.rayHandler = new RayHandler( world );
		float lvl = 200f;
		
		rayHandler.setAmbientLight( lvl / 255f );
		initGrid();
		
		linkBody = new LinkBody( this );
	}
	
	protected void initGrid() {
		levelGrid = new Level[ worldType.N_X ][ worldType.N_Y ];
		
		for( int levelGridY = 0; levelGridY < worldType.N_Y; levelGridY++ ) {
			for( int levelGridX = 0; levelGridX < worldType.N_X; levelGridX++ ) {
				levelGrid[ levelGridX ][ levelGridY ] = new Level( this, levelGridX, levelGridY );
			}
		}
	}
	
	public void destroy( Body body ) {
		toBeDestoryed.add( body );
	}
	
	private void destroyBodies() {
		for( Body b : toBeDestoryed ) {
			world.destroyBody( b );
		}
		toBeDestoryed.clear();
	}
	
	public void update( float delta ) {
		if( linkInWorld ) world.step( 1 / 60f, 6, 2 );
		doUpdate( delta );
		postupdate();
	}
	
	public void postupdate() {
		checkLoadedLevels();
		destroyBodies();
		rayHandler.update();
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		for( Level level : loadedLevels ) {
			level.draw( spriteBatch, runTime );
		}
		doDraw( spriteBatch, runTime );
	}
	
	public abstract void doUpdate( float delta );
	
	public abstract void doDraw( SpriteBatch spriteBatch, float runTime );
	
	public void enterWorld( int levelGridX, int levelGridY ) {
		this.link.setInGameWorld( this );
		setCurentLevel( levelGridX, levelGridY );
		linkInWorld = true;
	}
	
	public abstract void enterWorld( int levelGridX, int levelGridY, boolean useLastPos );
	
	public abstract void setExitWorld( DoorSensorTarget sensorTarget, Vector2 position );
	
	public abstract void exitWorld();
	
	public void addLoadedLevel( Level level ) {
		loadedLevels.add( level );
	}
	
	protected void checkLoadedLevels() {
		Iterator<Level> levelIterator = loadedLevels.iterator();
		Level reference;
		while(levelIterator.hasNext()) {
			reference = levelIterator.next();
			if( ! reference.isLoaded() ) {
				levelIterator.remove();
			} else if( reference.gridDist( currentLevel ) > 1 ) {
				reference.unload();
				levelIterator.remove();
			} /*else {
				reference.load_in_limit( link.getPosition(), PIXEL_DRAW_LIMIT );
			} */
		}
	}
	
	public void setCurrentLevel( Level level ){
		currentLevel = level;
		currentLevel.load();
		gameScreen.setCameraTarget( currentLevel);
	}
	
	public void setCurentLevel( int levelGridX, int levelGridY ) {
		setCurrentLevel( levelGrid[ levelGridX ][ levelGridY ] );
	}
	
	public Link getLink() {
		return link;
	}
	
	public World getWorld() {
		return world;
	}
	
	public RayHandler getRayHandler() {
		return rayHandler;
	}
	
	public void scrollWheel( float scrolled ) {
		gameScreen.getCameraManager().setZoom( gameScreen.getCameraManager().getZoom() + scrolled );
	}
	
	public void dispose() {
		world.dispose();
		rayHandler.dispose();
		mapManager.dispose();
	}
	
	public WorldType getWorldType() {
		return worldType;
	}
	
	public LinkBody getLinkBody() {
		return linkBody;
	}
	
	public MapManager getMapManager() {
		return mapManager;
	}
	
	public WorldScreen getScreen() {
		return gameScreen;
	}
	
	public Level getLevel( int levelGridX, int levelGridY ) {
		return levelGrid[ levelGridX ][ levelGridY ];
	}
}
