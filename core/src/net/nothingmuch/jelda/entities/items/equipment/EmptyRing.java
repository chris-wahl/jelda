package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Created by christopher on 1/9/17.
 */
public class EmptyRing extends Ring {
	public EmptyRing( Link link ) {
		super( link, "EMPTY_RING", AssetLoader.LINK_COLOR_SHADERS.get( Constants.LinkColor.GREEN ), 1 );
	}
}

