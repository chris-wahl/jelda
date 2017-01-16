package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.math.Vector2;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.worlds.GameWorld;

import java.util.HashMap;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/16/17.
 */
public class Link extends Character {
	/* Movement Variables */
	private HashMap<Direction, Boolean> movement = new HashMap<Direction, Boolean>();
	private Vector2 moveVec = new Vector2( 0, 0 );
	private final float MAX_V = 2f;
	
	
	/* Methods */
	public Link( GameWorld gameWorld ){
		super( gameWorld );
		for( Direction d : Direction.values() ){
			movement.put( d, false );
		}
	}
	
	protected void createBody(){
		characterBody = BodyBuilder.createRect( gameWorld.getWorld(), 0, 0, 16, 16, false, true,
				BIT_LINK, (short) ( BIT_TERRAIN | BIT_ENEMY | BIT_ITEM ), BIT_LINK );

	}
	
	@Override
	public void update( float delta ) {
		updateMovement();
		super.update( delta );
	}
	
	private void updateMovement(){
		moveVec.x = 0;
		moveVec.y = 0;
		
		if( movement.get( Direction.LEFT ) ) moveVec.x--;
		if( movement.get( Direction.RIGHT ) ) moveVec.x++;
		if( movement.get( Direction.UP ) ) moveVec.y++;
		if( movement.get( Direction.DOWN )) moveVec.y--;
		
		moveVec.scl( MAX_V );
		setB2DVelocity( moveVec );
	}
	
	
	public void setMove( Direction direction, boolean b ) {
		movement.put( direction, b );
	}
	
	
}
