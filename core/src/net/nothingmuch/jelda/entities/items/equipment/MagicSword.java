package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.managers.GameWorldManager;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Magic Sword class
 */
public class MagicSword extends Sword {
	public MagicSword( GameWorldManager worldManager, Link link ) {
		super( worldManager, link, "WHITE_SWORD", AssetLoader.SWORD_SPRITE, AssetLoader.BLINK_SHADERS.get( Constants.ShaderColor.RED ) );
		this.damage = 10;
	}
}
