package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.managers.GameWorldManager;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Wooden Sword class
 */
public class WoodenSword extends Sword {
	public WoodenSword( GameWorldManager worldManager, Link link ) {
		super( worldManager, link, "WOODEN_SWORD", AssetLoader.SWORD_SPRITE, AssetLoader.LINK_COLOR_SHADERS.get( Constants.LinkColor.GREEN ) );
		this.damage = 10;
	}
}
