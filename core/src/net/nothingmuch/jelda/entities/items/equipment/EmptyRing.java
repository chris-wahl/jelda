package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.Constants;


/**
 * Created by christopher on 1/9/17.
 */
public class EmptyRing extends Ring {
	public EmptyRing( Link link ) {
		super( link, "EMPTY_RING", AssetManager.LINK_COLOR_SHADERS.get( Constants.LinkColor.GREEN ), 1 );
	}
}

