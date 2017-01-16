package net.nothingmuch.jelda.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.nothingmuch.jelda.JeldaGame;

import static net.nothingmuch.jelda.utilities.Constants.WINDOW_HEIGHT;
import static net.nothingmuch.jelda.utilities.Constants.WINDOW_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final float aspect_ratio = 16f / 9f;
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Jelda";
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		
		new LwjglApplication(new JeldaGame(), config);
	}
}
