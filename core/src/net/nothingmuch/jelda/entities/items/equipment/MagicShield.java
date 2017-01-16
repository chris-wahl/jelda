package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;


/**
 * The upgraded Magic shield
 */
public class MagicShield extends Shield {
	
	public MagicShield( Link link ) {
		super( "MAGIC_SHIELD", link, AssetManager.BIG_SHIELD_ANIMATIONS );
	}
	
	@Override
	protected float drawOffsetX() {
		switch( directionFacing ) {
			case DOWN:
				return 0.5f;
			case LEFT:
				return -.5f;
			case RIGHT:
				return 0.5f;
		}
		return 0;
	}
	
	@Override
	protected float drawOffsetY() {
		switch( directionFacing) {
			case LEFT:
				return -0.5f;
			case RIGHT:
				return -0.5f;
		}
		return 0;
	}
	
	@Override
	public boolean hit() {
		/* CALLED FROM LINK.HIT()
		*  - CHECK IF PROJECTILE
		*  - IF SO, CHECK IF TYPE IS BLOCKED
		*  AND RETURN TRUE - Link will call return; ELSE RETURN FALSE
	    *  AND LET LINK LOGIC FIGURE OUT WHAT TO TO
		*/
		return false;
	}
}
