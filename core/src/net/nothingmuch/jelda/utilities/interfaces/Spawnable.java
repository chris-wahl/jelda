package net.nothingmuch.jelda.utilities.interfaces;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by christopher on 1/17/17.
 */
public interface Spawnable {
	
	
	/**
	 * Returns the spawn point in Pixelspace
	 *
	 * @return Pixelspace x and y the stored position.
	 */
	Vector2 getSpawnPoint();
}
