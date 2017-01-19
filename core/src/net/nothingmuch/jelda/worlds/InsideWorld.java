package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.entities.world_members.DoorSensor;
import net.nothingmuch.jelda.entities.world_members.InsideLevel;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Created by christopher on 1/18/17.
 */
public class InsideWorld extends GameWorld {
	
	
	protected InsideLevel[][] levelGrid;
	
	public InsideWorld( GameScreen gameScreen, Link link ) {
		super( gameScreen, Constants.WorldType.INSIDE, link );
	}
	
	@Override
	protected void initGrid() {
		levelGrid = new InsideLevel[ worldType.N_X ][ worldType.N_Y ];
		
		for( int levelGridY = 0; levelGridY < worldType.N_Y; levelGridY++ ){
			for( int levelGridX = 0; levelGridX < worldType.N_X; levelGridX++ ){
				levelGrid[ levelGridX ][ levelGridY ] = new InsideLevel( this, levelGridX, levelGridY );
			}
		}
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
	public void exitWorld( DoorSensor.DoorSensorTarget sensorTarget ) {
		
	}
}
