package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;

/**
 * Overhead equipable class
 */
public abstract class Equippable {
	
	protected boolean isEquipped;
	protected Link link;
	public final String id;
	
	protected Equippable( Link link, final String id ){
		this.link = link;
		this.id = id;
	}
	
	public abstract void equip();
	public abstract void unequip();
	public boolean isEquipped(){
		return isEquipped;
	}
}
