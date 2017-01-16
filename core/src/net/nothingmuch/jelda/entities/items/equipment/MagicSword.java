package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.managers.GameWorldManager;
import net.nothingmuch.jelda.utilities.Constants;

/**
 * Magic Sword class
 */
public class MagicSword extends Sword {
	public MagicSword( GameWorldManager worldManager, Link link ) {
		super( worldManager, link, "WHITE_SWORD", AssetManager.SWORD_SPRITE, AssetManager.BLINK_SHADERS.get( Constants.ShaderColor.RED ) );
		this.damage = 10;
	}
}
