package net.nothingmuch.jelda.entities.items.equipment;

import net.nothingmuch.jelda.entities.characters.Link;

import static net.nothingmuch.jelda.utilities.Constants.Direction;

/**
 * Created by christopher on 1/8/17.
 */
public abstract class Weapon extends Equippable {
	
	
	protected Direction directionFacing;
	protected int damage;
	
	public Weapon( Link link, String id ) {
		super( link, id );
		directionFacing = link.getDirectionFacing();
	}
	
	public void update(){
		this.directionFacing = link.getDirectionFacing();
	}
	
	@Override
	public void equip() {
		this.isEquipped = true;
	}
	
	@Override
	public void unequip() {
		this.isEquipped = false;
	}
}
