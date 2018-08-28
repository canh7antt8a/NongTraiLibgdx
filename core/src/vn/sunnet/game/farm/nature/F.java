package vn.sunnet.game.farm.nature;

import vn.sunnet.game.farm.Actor.SButton;
import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.assets.Language;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public class F {

	public static final int viewportWidth = 1280, viewportHeight = 720;
	public static boolean isGuide;
	public static int coins, money;
	public static int nof_table, nof_employee;
	public static int level, nof_wgod;
	
	public static long lvexperience[] = {0, 212, 681, 1618, 2980, 5034, 7687, 11004, 16884, 23911, 34239,
		46230, 60000, 80782, 104228, 130490, 167207, 207845, 252594, 301646, 368941, 442125, 521438, 627486,
		741715, 864422, 1011373, 1203447, 1407743, 1624625, 1854458, 2154273, 2470958, 2804961, 3156732,
		3526722, 4006159, 4509179, 5036337, 5588189, 6311585, 7067325, 7856105, 8678620, 9535566, 10427638,
		11595637, 12809602, 14070410, 15378936, 16736056, 18231952, 20238230, 22313162, 24457882, 26673525,
		28961227, 32039576, 35214840, 38488498, 41862031, 46576535, 51430557, 56426104, 61565184, 66849805,
		72281973, 79867001, 87657988, 95657663, 103868752, 112293985, 120936088, 133643185, 146669085,
		160017698, 174287512, 188895686, 203846132, 219142763, 241479828, 264322270, 287675672, 311545619,
		335937694, 360857483, 399815530, 439598379, 480214579, 521672676, 563981217, 607148750, 651183821,
		718779313, 787706260, 857977528, 929605979, 1002604479, 1076985892, 1152763081
	};
	
	public static Button createButton(String name) {
		Texture texture = Assets.manager.get(name, Texture.class);
		TextureRegion[] tregion = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(tregion[0]), new TextureRegionDrawable(tregion[1]), 
				new TextureRegionDrawable(tregion[1]));
	}
	
	
	public static Button createButton_(String name) {
		Texture texture = Assets.manager.get(name, Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[1]), new TextureRegionDrawable(region[0]), 
				new TextureRegionDrawable(region[0]));
	}

//	public static SButton createButton(String name, String title, BitmapFont font) {
//		Texture texture = Assets.manager.get(name, Texture.class);
//		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
////		return new Button(new TextureRegionDrawable(region[1]), new TextureRegionDrawable(region[0]),
////				new TextureRegionDrawable(region[0]));
//
//		return  new SButton(new TextureRegionDrawable(region[1]), new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[0]), title, font);
//	}
	
	public static Texture[] flower, fruit;
	public static BitmapFont font;

    public static Language.LANGU language;
    public static String strFontNormal = "data/font/font_normal.fnt";
	public static void setLanguage(Language.LANGU lan){
		language = lan;
	}
	
	public static void load() {
		flower = new Texture[12];
		fruit = new Texture[12];		
		for(int i = 0; i < 12; i++) {
			flower[i] = Assets.manager.get("data/icon/" + SeedNature.flower[i] + ".png", Texture.class);
			fruit[i] = Assets.manager.get("data/icon/" + SeedNature.fruit[i] + ".png", Texture.class);
		}
		
		font = new BitmapFont(Gdx.files.internal("data/font/kho-chua.fnt"), false);
		font.getData().setScale(0.3f);

		//Load tai san nguoi dung
		coins = Data.getCoins();
		level = Data.getLevel();
		
		nof_table = Data.loadnofTable();
		nof_employee = Data.loadnofEmployee();		
		nof_wgod = Data.loadnofwGod();
	}
	
	public static long get_lvexperience(int lv) {
		long n;
		if(lv <= 99) {
			n = new Long(lvexperience[lv]);
		} else {
			n = new Long(lvexperience[99] + (lv - 99) * 200000000l);
		}
		
		return n;
	}
}