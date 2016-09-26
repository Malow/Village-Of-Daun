package com.malow.villageofdaun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.malow.villageofdaun.VillageOfDaun;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Village of Daun";
		config.width = 1280;
		config.height = 720;
		
		new LwjglApplication(new VillageOfDaun(), config);
	}
}
