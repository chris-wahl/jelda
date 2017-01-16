package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Blue ring reduces damage by half.  Turns Link's clothes blue.
 */
public class BlueRing extends Ring {
	public BlueRing( Link link ) {
		super( link, "BLUE_RING", AssetLoader.LINK_COLOR_SHADERS.get( Constants.LinkColor.BLUE ), 0.5f );
	}
}

