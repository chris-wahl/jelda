package net.nothingmuch.jelda.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.worlds.GameWorld;
import net.nothingmuch.jelda.worlds.InsideWorld;
import net.nothingmuch.jelda.worlds.Overworld;

import static net.nothingmuch.jelda.utilities.Constants.Direction;
import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Created by christopher on 1/15/17.
 */
public class GameWorldManager {
		
	private Overworld overWorld;
	private InsideWorld insideWorld;
	private GameWorld currentWorld;
	
	private Link link;
	
	private Box2DDebugRenderer debugRenderer;
	private GameScreen gameScreen;
	
	public GameWorldManager( GameScreen gameScreen ) {
		this.gameScreen = gameScreen;
		Gdx.input.setInputProcessor( new WorldInputManager( this ) );
		
		debugRenderer = new Box2DDebugRenderer();
		
		link = new Link();
		
		overWorld = new Overworld( gameScreen, link, true );
		insideWorld = new InsideWorld( gameScreen, link );
		currentWorld = overWorld;
		
		link.setInGameWorld( currentWorld );
	}
	
	public void update( float delta ){
		
		currentWorld.update( delta );
		
		
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ){
		spriteBatch.begin();
		currentWorld.draw( spriteBatch, runTime );
		spriteBatch.end();
		debugRenderer.render( currentWorld.getWorld(), gameScreen.getCameraCombined().scl( PPM ) );
		
		currentWorld.getRayHandler().render();
		
	}
	
	public void sendMovement( Direction direction, boolean doMove ) {
		currentWorld.getLink().setMove( direction, doMove );
	}
	
	public void changeWorld( DoorSensorTarget sensorTarget ){
		switch( sensorTarget.targetWorldType ){
			case OVERWORLD:
				currentWorld = overWorld;
				break;
		}
		
		currentWorld.enterWorld( sensorTarget.targetLevelX, sensorTarget.targetLevelY );
		
	}
	
	public World getWorld() {
		return currentWorld.getWorld();
	}
	public GameWorld getCurrentGameWorld() {
		return currentWorld;
	}
	
	public void destroy( Body body ){
		currentWorld.destroy( body );
	}
	public void dispose() {
		currentWorld.dispose();
		debugRenderer.dispose();
	}
}
