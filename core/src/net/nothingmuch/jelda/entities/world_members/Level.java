package net.nothingmuch.jelda.entities.world_members;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import net.nothingmuch.jelda.utilities.b2d.BodyBuilder;
import net.nothingmuch.jelda.utilities.interfaces.Targetable;

import static net.nothingmuch.jelda.utilities.Constants.*;
/**
 * TODO: Load tiles, set level sensors, set hitboxes, set lighting.  Reconsider building entire grid from that python zelda map
 */
public class Level implements Targetable {
	// TODO: Load tiles
	protected World world;
	protected Body levelCenter;
	
	public Level( World world, int levelGridX, int levelGridY ) {
		this.world = world;
		this.levelCenter = BodyBuilder.createSensorRect( world, levelGridX * W_LEVEL_TILE, levelGridY * H_LEVEL_TILE, 2f, 2f,
				BIT_NOCOLLISION, BIT_NOCOLLISION, BIT_NOCOLLISION );
	}
	
	@Override
	public Vector2 getCamTarget() {
		return null;
	}
}
