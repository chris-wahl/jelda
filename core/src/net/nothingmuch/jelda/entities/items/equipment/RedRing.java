package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Red ring reduces damage by 75 percent.  Turns Link's clothes red.
 */
public class RedRing extends Ring {
	public RedRing( Link link ) {
		super( link, "RED_RING", AssetLoader.LINK_COLOR_SHADERS.get( Constants.LinkColor.RED ), 0.25f );
	}
}
