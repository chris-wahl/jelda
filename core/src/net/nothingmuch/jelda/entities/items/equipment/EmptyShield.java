package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;


/**
 * Created by christopher on 1/9/17.
 */
public class EmptyShield extends Shield {
	
	public EmptyShield( Link link ) {
		super( "EMPTY_SHIELD", link, AssetManager.BLANK_ANIMATIONS );
	}
	
	@Override
	protected float drawOffsetX() {
		return 0;
	}
	
	@Override
	protected float drawOffsetY() {
		return 0;
	}
	
	@Override
	public boolean hit() {
		return false;
	}
}
