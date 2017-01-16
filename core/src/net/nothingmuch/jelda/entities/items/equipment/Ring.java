package net.nothingmuch.jelda.entities.items.equipment;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.nothingmuch.darkzelda.entities.characters.Link;
import net.nothingmuch.darkzelda.utils.Constants;
import net.nothingmuch.darkzelda.utils.assets.AssetLoader;

/**
 * Parent ring controller
 */
public abstract class Ring extends Equippable {
	
	private final ShaderProgram RING_SHADER;
	private final float DAMAGE_MULTIPLIER;
	
	public Ring( Link link, String id, final ShaderProgram ringShader, float damageMultiplier ) {
		super( link, id );
		this.RING_SHADER = ringShader;
		this.DAMAGE_MULTIPLIER = damageMultiplier;
	}
	
	public int reduceDamage( int damage ){
		return (int) ((float) damage * DAMAGE_MULTIPLIER);
	}
	
	@Override
	public void equip() {
		isEquipped = true;
		link.setDefaultShader( RING_SHADER );
	}
	
	@Override
	public void unequip() {
		isEquipped = false;
		link.setDefaultShader( AssetLoader.LINK_COLOR_SHADERS.get( Constants.LinkColor.GREEN ) );
	}
}
