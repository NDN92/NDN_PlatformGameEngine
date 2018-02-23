package de.ndn.game.platformer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ndn.game.platformer.PlatformerGame;
import de.ndn.game.platformer.constants.GameConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width 			= GameConstants.GAME_WIDTH;
		config.height 			= GameConstants.GAME_HEIGHT;
		config.backgroundFPS 	= GameConstants.TARGET_FPS;
		config.foregroundFPS 	= GameConstants.TARGET_FPS;

		new LwjglApplication(new PlatformerGame(), config);
	}
}
