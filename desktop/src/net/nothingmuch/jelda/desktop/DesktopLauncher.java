package net.nothingmuch.jelda.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.nothingmuch.jelda.JeldaGame;

import static net.nothingmuch.jelda.utilities.Constants.WINDOW_HEIGHT;
import static net.nothingmuch.jelda.utilities.Constants.WINDOW_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Jelda";
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		
		new LwjglApplication(new JeldaGame(), config);
	}
}
