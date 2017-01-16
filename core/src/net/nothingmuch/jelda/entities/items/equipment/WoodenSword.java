package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.managers.GameWorldManager;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Wooden Sword class
 */
public class WoodenSword extends Sword {
	public WoodenSword( GameWorldManager worldManager, Link link ) {
		super( worldManager, link, "WOODEN_SWORD", AssetManager.SWORD_SPRITE, AssetManager.LINK_COLOR_SHADERS.get( Constants.LinkColor.GREEN ) );
		this.damage = 10;
	}
}
