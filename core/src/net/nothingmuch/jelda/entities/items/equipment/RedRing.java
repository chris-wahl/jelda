package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Red ring reduces damage by 75 percent.  Turns Link's clothes red.
 */
public class RedRing extends Ring {
	public RedRing( Link link ) {
		super( link, "RED_RING", AssetManager.LINK_COLOR_SHADERS.get( Constants.LinkColor.RED ), 0.25f );
	}
}
