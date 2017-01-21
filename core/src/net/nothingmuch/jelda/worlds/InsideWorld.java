package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor.DoorSensorTarget;
import net.nothingmuch.jelda.entities.world_members.InsideLevel;
import net.nothingmuch.jelda.entities.world_members.Level;
import net.nothingmuch.jelda.managers.GameWorldManager;
import net.nothingmuch.jelda.screens.WorldScreen;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Created by christopher on 1/18/17.
 */
public class InsideWorld extends GameWorld {
	
	private Level activeLevel;
	
	public InsideWorld( WorldScreen worldScreen, Link link, GameWorldManager worldManager ) {
		super( worldScreen, worldManager, Constants.WorldType.INSIDE, link );
	}
	
	@Override
	protected void initGrid() {
		levelGrid = new InsideLevel[ worldType.N_X ][ worldType.N_Y ];
		
		for( int levelGridY = 0; levelGridY < worldType.N_Y; levelGridY++ ){
			for( int levelGridX = 0; levelGridX < worldType.N_X; levelGridX++ ){
				levelGrid[ levelGridX ][ levelGridY ] = new InsideLevel( this, levelGridX, levelGridY );
			}
		}
		
		// TODO: Set InsideDoorSensors for exiting back to Overworld
	}
	
	@Override
	public void enterWorld( int levelGridX, int levelGridY ) {
		super.enterWorld( levelGridX, levelGridY );
		activeLevel = levelGrid[ levelGridX ][ levelGridY ];
		activeLevel.load();
	}
	
	@Override
	public void doUpdate( float delta ) {
		link.update( delta );
	}
	
	@Override
	public void doDraw( SpriteBatch spriteBatch, float runTime ) {
		link.draw( spriteBatch, runTime );
	}
	
	@Override
	public void setExitWorld( DoorSensorTarget sensorTarget, Vector2 tilePosition ) {
		
	}
	
	@Override
	public void exitWorld() {
		activeLevel.unload();
	}
}
