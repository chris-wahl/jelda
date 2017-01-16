package net.nothingmuch.jelda.entities.items.equipment;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import net.nothingmuch.jelda.entities.characters.Link;

/**
 * Created by christopher on 1/8/17.
 */
public abstract class SwingWeapon extends Weapon {
	
	protected final Sprite WEAPON_SPRITE;
	protected final ShaderProgram DEFAULT_SHADER;
	protected final boolean IS_SHADABLE;
	
	public SwingWeapon( Link link, final String id, final Sprite weaponSprite ){
		this( link, id, weaponSprite, null, false );
	}
	
	public SwingWeapon( Link link, final String id, final Sprite weaponSprite, final ShaderProgram defaultShader, boolean isShadable ) {
		super( link, id );
		this.WEAPON_SPRITE = weaponSprite;
		this.DEFAULT_SHADER = defaultShader;
		this.IS_SHADABLE = isShadable;
	}
	
	public void draw( SpriteBatch spriteBatch ){
		if( !link.isBlinking() ) spriteBatch.setShader( DEFAULT_SHADER );
		WEAPON_SPRITE.setRotation( directionFacing.getAngle() + 90 );
		WEAPON_SPRITE.setCenter( link.getWeaponCenter().x + drawOffsetX(), link.getWeaponCenter().y + drawOffsetY() );
		WEAPON_SPRITE.draw( spriteBatch );
		link.resetShader( spriteBatch );
	}
	
	protected abstract float drawOffsetX();
	protected abstract float drawOffsetY();
	public abstract void swing();
	public abstract void stopSwinging();
}
