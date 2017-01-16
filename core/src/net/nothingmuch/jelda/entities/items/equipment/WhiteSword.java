package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.managers.GameWorldManager;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * WhiteSword class
 */
public class WhiteSword extends Sword {
	public WhiteSword( GameWorldManager worldManager, Link link ) {
		super( worldManager, link, "WHITE_SWORD", AssetLoader.SWORD_SPRITE, AssetLoader.BLINK_SHADERS.get( Constants.ShaderColor.BLUE ) );
		this.damage = 10;
	}
}
