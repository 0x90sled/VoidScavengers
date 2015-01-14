package com.mikefdorst.voidscavengers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mikefdorst.voidscavengers.game.VoidScavengers;
import com.mikefdorst.voidscavengers.util.application.MenuBar;
import com.mikefdorst.voidscavengers.util.reference.Ref;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Ref.application.name;
		config.width = Ref.window.width;
		config.height = Ref.window.height;
		MenuBar.setApplicationName(Ref.application.name);
		new LwjglApplication(new VoidScavengers(), config);
	}
}
