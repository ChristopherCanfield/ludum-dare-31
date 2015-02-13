package com.divergentthoughtsgames.ld31.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.divergentthoughtsgames.ld31.GameApp;

public class DesktopLauncher {
	@SuppressWarnings("unused")
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 800;
		
		new LwjglApplication(new GameApp(), config);
	}
}
