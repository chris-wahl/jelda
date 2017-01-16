package net.nothingmuch.jelda.worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import net.nothingmuch.jelda.screens.GameScreen;
import net.nothingmuch.jelda.utilities.Constants;

import static net.nothingmuch.jelda.utilities.Constants.PPM;

/**
 * Created by christopher on 1/16/17.
 */
public class SANDBOX extends GameWorld {
	
	private Box2DDebugRenderer debugRenderer;
	
	public SANDBOX( GameScreen gameScreen, Constants.WorldType worldType ) {
		super( gameScreen, worldType );
		this.debugRenderer = new Box2DDebugRenderer();
		gameScreen.getCameraManager().setTargetA( link );
	}
	
	@Override
	public void doUpdate( float delta ) {
		world.step( 1 / 60f, 6, 2 );
		link.update( delta );
	}
	
	@Override
	public void doDraw( SpriteBatch spriteBatch, float runTime ) {
		debugRenderer.render( world, gameScreen.getCameraManager().getCamera().combined.scl( PPM ) );
	}
	
	@Override
	public void enterWorld() {
		
	}
	
	@Override
	public void exitWorld() {
		
	}
}
