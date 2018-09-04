package com.leaptechjsc.game.happyfarm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.main.Farm;
import com.leaptechjsc.game.happyfarm.screen.DestopPayment;
import com.leaptechjsc.game.happyfarm.screen.Payment;

public class DesktopLauncher {
	public static void main(String[] argv) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Language.General.APP_NAME.getStr();
		config.width = 600;
		config.height = 320;
		config.useGL30 = true;


		new LwjglApplication(new Farm((Payment) new DestopPayment()), config);
	}
}
