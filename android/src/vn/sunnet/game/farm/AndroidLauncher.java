package vn.sunnet.game.farm;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import vn.sunnet.game.farm.main.Farm;

public class AndroidLauncher extends AndroidPayment {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGLSurfaceView20API18 = true;
		initialize(new Farm(this), config);
	}
}
