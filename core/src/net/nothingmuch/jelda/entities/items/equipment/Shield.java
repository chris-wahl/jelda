package net.nothingmuch.jelda.entities.items.equipment;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.nothingmuch.darkzelda.entities.characters.Link;

import java.util.HashMap;

import static net.nothingmuch.darkzelda.utils.Constants.Direction;

/**
 * Shield parent abstract class
 */
public abstract class Shield extends Equippable {
	
	protected Direction directionFacing;
	
	private final HashMap<Direction, Animation<Sprite>> shieldAnimations;
	private Sprite lastFrame;
	
	protected Shield( final String id, Link link, final HashMap<Direction, Animation<Sprite>> shieldAnimations ){
		super( link, id );
		
		this.shieldAnimations = shieldAnimations;
		this.directionFacing = link.getDirectionFacing();
		lastFrame = this.shieldAnimations.get( this.directionFacing ).getKeyFrame( 0 );
		lastFrame.setCenter(  link.getShieldCenter().x + drawOffsetX(), link.getShieldCenter().y + drawOffsetY() );
	}
	
	public void draw( SpriteBatch spriteBatch, float runTime ){
		if( link.isMoving() ) {
			lastFrame = shieldAnimations.get( directionFacing ).getKeyFrame( runTime );
		}
		lastFrame.setCenter(  link.getShieldCenter().x + drawOffsetX(), link.getShieldCenter().y + drawOffsetY() );
		lastFrame.draw( spriteBatch );
	}
	
	public void update() {
		directionFacing = link.getDirectionFacing();
	}
	
	@Override
	public void equip() {
		this.isEquipped = true;
	}
	
	@Override
	public void unequip() {
		this.isEquipped = false;
	}
	
	protected abstract float drawOffsetX();
	protected abstract float drawOffsetY();
	public abstract boolean hit();
}
