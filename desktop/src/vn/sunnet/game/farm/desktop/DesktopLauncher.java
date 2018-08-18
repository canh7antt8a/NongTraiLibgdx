package vn.sunnet.game.farm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
//import vn.sunnet.game.farm.MainActivity;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.screen.DestopPayment;
import vn.sunnet.game.farm.screen.Payment;

public class DesktopLauncher {
//	public static void main (String[] arg) {
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new MainActivity(), config);
//	}
public static void main(String[] argv) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.title = "Nông trại vui vẻ";
	config.width = 600;
	config.height = 320;
	config.useGL30 = true;


	new LwjglApplication(new Farm((Payment) new DestopPayment()), config);
}
}
