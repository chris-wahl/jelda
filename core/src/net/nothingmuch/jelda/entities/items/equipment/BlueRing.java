package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Blue ring reduces damage by half.  Turns Link's clothes blue.
 */
public class BlueRing extends Ring {
	public BlueRing( Link link ) {
		super( link, "BLUE_RING", AssetManager.LINK_COLOR_SHADERS.get( Constants.LinkColor.BLUE ), 0.5f );
	}
}

