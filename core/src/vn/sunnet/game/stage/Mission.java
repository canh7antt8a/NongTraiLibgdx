package vn.sunnet.game.stage;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.assets.Language;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Align;

public class Mission{
	
	private Stage stage;
	private Texture texture;
	private BitmapFont font;
	private Button close;
	private int cs, n, len, require[][];;
	private boolean gc = false;
	private String srequire[];
	
	public Mission(Stage sta) {
		stage = new Stage();
		stage.setViewport(sta.getViewport());
		texture = Assets.manager.get("data/texture/khung-nhiem-vu.png", Texture.class);
		font = Assets.manager.get("data/font/cua-hang.fnt", BitmapFont.class);
		close = F.createButton("data/texture/close.png");
//		close.setPosition(750, 450);
		close.setPosition(1050, 632);

		n = Data.loadsMarket(5);
		if(cs != 3) {
			srequire = Data.loadMarket(5).split(":");		
			len = srequire.length/3;
			require = new int[3][3];
			
			for(int i = 0; i < len; i++) {
				require[i][0] = Integer.parseInt(srequire[i*3]);
				require[i][1] = Integer.parseInt(srequire[i*3 + 1]);
				require[i][2] = Integer.parseInt(srequire[i*3 + 2]);
			}
		}
		
		stage.addActor(close);
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render(SpriteBatch batch) {
		batch.begin();
		batch.draw(texture, 100, 35);
		
		switch(cs) {
		case 0:
			if(n == 3) {
				font.draw(batch, Language.General.HOAN_THANH_NV.getStr(), 0, 625, 1280, Align.center, false);
			} else {
				font.draw(batch,  Language.General.NHIEM_VU.getStr(), 0, 645, 1280, Align.center, false);
				font.draw(batch,  Language.General.DAP_UNG_NC_LT_QG.getStr(), 0, 580, 1280, Align.center, false);
				
				for(int i = 0; i < len; i++) {
					int kind = (require[i][0] < 12) ? require[i][0] : require[i][0] - 12;
					if(require[i][0] < 12) {
						font.draw(batch, SeedNature.flname[kind] + ":  " + require[i][2] +"/" + require[i][1], 540, 440 - i * 70);
						batch.draw(F.flower[kind], 460, 380 - i * 65);
					}
					else {
						font.draw(batch, SeedNature.frname[kind] + ":  " + require[i][2] +"/" + require[i][1], 540, 440 - i * 70);
						batch.draw(F.fruit[kind], 460, 380 - i * 65);
					}
				}
			}
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		batch.end();
		
		stage.draw();
		update();
	}
	
	public void update() {
		if(close.isChecked()) {
			Audio.btnClick.play(Audio.soundVolume);
			gc = true;
		}
	}
	
	public boolean gc() {
		return gc;
	}
	
	public void dispose() {
		stage.dispose();
	}
}