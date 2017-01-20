package net.nothingmuch.jelda.entities.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.nothingmuch.jelda.managers.AssetManager;
import net.nothingmuch.jelda.utilities.interfaces.Spawnable;
import net.nothingmuch.jelda.worlds.GameWorld;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static net.nothingmuch.jelda.utilities.Constants.*;

/**
 * Created by christopher on 1/16/17.
 */
public class Link extends Character {
	/* Movement Variables */
	private HashMap<Direction, Boolean> movement = new HashMap<Direction, Boolean>();
	private Vector2 moveVec = new Vector2( 0, 0 );
	private final float MAX_V = 5f;
	
	/* Physics world placeholders */
	LinkBody linkBody;
	Body footBox;
	
	/* Animations */
	boolean inExitAnim = false;
	
	/* Methods */
	public Link(){
		for( Direction d : Direction.values() ){
			movement.put( d, false );
		}
	}
	
	protected void createBody(){
		
	}
	
	public void setInGameWorld( GameWorld gameWorld ){
		this.gameWorld = gameWorld;
		this.linkBody = this.gameWorld.getLinkBody();
		
		if( !linkBody.isInit ) linkBody.createHitBox();
		
		this.characterBody = linkBody.hitBox;
		this.footBox = linkBody.footBox;
	}
	
	public void setInGameWorld( GameWorld gameWorld, Spawnable spawnPoint ){
		setInGameWorld( gameWorld );
		setPosition( spawnPoint );
	}
	public void setInGameWorld( GameWorld gameWorld, Vector2 position ){
		setInGameWorld( gameWorld );
		setPosition( position );
	}
	public void setInGameWorld( GameWorld gameWorld, float pixelX, float pixelY ){
		setInGameWorld( gameWorld );
		setPosition( pixelX, pixelY );
	}
	
	@Override
	public void update( float delta ) {
		updateMovement();
		super.update( delta );
	}
	
	private void updateMovement(){
		moveVec.x = 0;
		moveVec.y = 0;
		
		if( movement.get( Direction.LEFT ) ) moveVec.x--;
		if( movement.get( Direction.RIGHT ) ) moveVec.x++;
		if( movement.get( Direction.UP ) ) moveVec.y++;
		if( movement.get( Direction.DOWN )) moveVec.y--;
		
		moveVec.scl( MAX_V );
		linkBody.setVelocity( moveVec );
	}
	
	@Override
	public void draw( SpriteBatch spriteBatch, float runTime ) {
		
		lastFrame = new Sprite( AssetManager.LINK_WALK_ANIMATIONS.get( Direction.DOWN ).getKeyFrame( runTime ) );
		lastFrame.setCenter( getPosition().x, getPosition().y );
		lastFrame.draw( spriteBatch );
		super.draw( spriteBatch, runTime );
	}
	
	public void setMove( Direction direction, boolean b ) {
		movement.put( direction, b );
	}
	
	public void setDefaultShader( ShaderProgram shaderProgram ) {
		// TODO: Fill in shaders
	}
	
	public Vector2 getWeaponCenter() {
		// TODO: Establish pixelspace weapon center
		return getPosition();
	}
	
	public boolean isBlinking(){
		// TODO: Develop blinking
		return false;
	}
	
	public void resetShader( SpriteBatch spriteBatch ) {
		// TODO: Develop shaders
	}
	
	public Vector2 getShieldCenter() {
		// TODO: Establish pixelspace shield center
		return new Vector2( 0, 0 );
	}
	
	@Override
	public void setB2DPosition( float x, float y ) {
		super.setB2DPosition( x, y );
		footBox.setTransform( x, y - H_LINK / 4f / PPM, 0 );
	}
	
	public void doExitAnimation() {
		inExitAnim = true;
		
		/* Testing Code */
		new Timer().schedule( new TimerTask() {
			@Override
			public void run() {
				resetExitAnimation();
			}
		}, 3000 );
	}
	
	public void resetExitAnimation(){
		inExitAnim = false;
	}
	
	public boolean inExitAnimation() {
		return inExitAnim;
	}
}
