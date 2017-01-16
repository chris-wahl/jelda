package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.utilities.assets.AssetLoader;

import static net.nothingmuch.jelda.utilities.Constants.Direction.DOWN;
import static net.nothingmuch.jelda.utilities.Constants.Direction.LEFT;

/**
 * Link's O.G. Wooden Shield
 */
public class WoodenShield extends Shield {
	
	public WoodenShield( Link link ) {
		super( "WOODEN_SHEILD", link, AssetLoader.SHIELD_ANIMATIONS );
	}
	
	@Override
	protected float drawOffsetX() {
		switch( directionFacing ) {
			case DOWN
				return 0;
			case LEFT:
				return 0;
		}
		return 0;
	}
	
	@Override
	protected float drawOffsetY() {
		switch( directionFacing ) {
			case DOWN:
				return -0.5f;
			case LEFT:
				return 0f;
		}
		return 0;
	}
	
	@Override
	public boolean hit() {
		/* CHECK IF PROJECTILE
		*  IF SO, CHECK IF TYPE IS BLOCKED
		*  ELSE CALL LINK.HIT() AND PASS RELEVANT INFO
		*/
		return false;
	}
}
