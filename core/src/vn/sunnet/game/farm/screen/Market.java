package vn.sunnet.game.farm.screen;

import java.util.Random;

import vn.sunnet.game.farm.Actor.MyButton;
import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.assets.Language;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class Market extends BaseScreen {

	private static final int CLOSE = 0, OPEN = 1, SUCCESS = 3;
	public static final int array_lvUnlock[] = {49, 1, 15, 4, 9, 1, 22, 29, 38, 59, 70, 83};
	private static final int array_factor[] = {10, 8, 11, 14, 30, 12, 26, 31, 41, 15, 41, 46};
	private static final String array_market[] = {"chung-cu", "cong-vien", "cua-hang-cafe", "fastfood",
		"khach-san", "kho-luong-thuc", "nha-ga", "nha-hang-nhat", "truong-hoc", "van-phong",
		"vuon-hoa", "vuon-thu"};
	private static final String market_name[] = {
			Language.General.CHUNG_CU.getStr()
			,Language.General.CONG_VIEN.getStr()
			,Language.General.CUA_HANG_CAFE.getStr()
			,Language.General.CUA_HANG_FASTFOOD.getStr()
			,Language.General.KHACH_SAN.getStr()
			,Language.General.KHO_LUONG_THUC.getStr()
			,Language.General.NHA_GA.getStr()
			,Language.General.NHA_HANG_NHAT.getStr()
			,Language.General.TRUONG_HOC.getStr()
			,Language.General.VAN_PHONG.getStr()
			,Language.General.VUON_HOA.getStr()
			,Language.General.VUON_THU.getStr()};//{"Chung cư", "Công viên", "Cửa hàng Cafe",
//		"Cửa hàng Fastfood", "Khách sạn", "Kho lương thực", "Nhà ga", "Nhà hàng nhật", "Trường học",
//		"Văn phòng", "Vườn hoa", "Vườn thú"};

	private final int maxComodity = 3;
	private int market_cost, state, lvUnlock, nof_plot;
	private String srequire;
	private int require[][];
	private Random random;
	private static int[][] pos = {{45, 280}, {1050, 390}, {510, 500}, {1050, 160}, {245, 435}, {800, 420},
			{800, 230}, {575, 345}, {290, 310}, {320, 115}, {30, 80}, {575, 120}};
	private static int[][] fpos = {{70, 390}, {1075, 520}, {550, 600}, {1100, 290}, {290, 570}, {850, 580},
			{825, 360}, {620, 475}, {315, 420}, {345, 220}, {75, 190}, {600, 230}};

	private Farm farm;
//	private OrthographicCamera cam;
//	private SpriteBatch batch;
	private Stage /*stage,*/ confirm_stage, stage_other;
	private BitmapFont font1, font2, font3, fontButton;
	private Texture texture, if_frame, shadow, war_frame, textbox;
	private Button close, back, btn_money, btn_gold;//, btn_yes, btn_no;
	private Button market[];//, btnpause, btnresume;
	private MyButton btnMarket[], btnPause, btnResume, btnYes, btnNo;
	private String text = "";

	private int index, comodity, payType = -1;
	private boolean renderMarket, renderDialog;
	private boolean smarket, isMusic;
	private Texture[] flag;
	private TextureRegion icon1, icon2;
	private Image image;
	private int array_state[];
	private Group group;

	public Market(Farm farm) {
		super();
		this.farm = farm;
		random = new Random();
		flag = new Texture[3];
		require = new int[3][3];

		nof_plot = Data.getnofPlot();
		array_state = new int[12];
		for(int i = 0; i < 12; i++) {
			array_state[i] = Data.loadsMarket(i);
		}

//		cam = new OrthographicCamera(F.viewportWidth, F.viewportHeight);
//		cam.position.set(F.viewportWidth/2, F.viewportHeight/2, 0);
//		batch = new SpriteBatch();
//		stage = new Stage(F.viewportWidth, F.viewportHeight, false);
//		stage = new Stage();
		confirm_stage = new Stage();
		stage_other = new Stage();
		group = new Group();
		stage_other.addActor(group);
		stage_other.setViewport(viewport);

		texture = Assets.manager.get("data/shop/icon-gold.png", Texture.class);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		icon1 = region[0];
		texture = Assets.manager.get("data/shop/icon-xu.png", Texture.class);
		region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		icon2 = region[0];

		fontButton = Assets.manager.get("data/font/font_normal.fnt", BitmapFont.class);
		font1 = Assets.manager.get("data/font/cua-hang.fnt", BitmapFont.class);
		font2 = new BitmapFont(Gdx.files.internal("data/font/kho-chua.fnt"), false);
		font2.getData().setScale(0.4f);
		font3 = new BitmapFont(Gdx.files.internal("data/font/font.fnt"), false);

		texture = Assets.manager.get("data/market/map.jpg", Texture.class);
		shadow = Assets.manager.get("data/texture/shadow.png", Texture.class);
		if_frame = Assets.manager.get("data/market/khung-thong-tin.png", Texture.class);
		war_frame = Assets.manager.get("data/market/thong-tin.png", Texture.class);
		textbox = Assets.manager.get("data/market/textbox.png", Texture.class);
		for(int i = 0; i < 3; i++) {
			flag[i] = Assets.manager.get("data/market/co-" + i + ".png", Texture.class);
		}

		TextureRegion tregion[] = new TextureRegion[4];
		market = new Button[12];
		btnMarket = new MyButton[12];
		float lbPosX[] = {-5,0,-20,-45,-15,-5,0,-30,-10,0,-20,40};
		float lbPosY[] = {-35,-35,-30,-30,-20,-40,-30,-40,-30,-30,-40,-30};
		for(int ii = 0; ii < 12; ii++) {
			final int i = ii;
			tregion = getTextureRegion("data/market/" + array_market[i] + ".png", 4);
			if(F.level >= array_lvUnlock[i]) {
				market[i] = createButton(tregion[0], tregion[1]);
				btnMarket[i] = new MyButton(tregion[0], market_name[i], lbPosX[i], lbPosY[i]) {
					@Override
					public void precessClicked() {
						market[i].setChecked(true);
					}
				};
				btnMarket[i].setColorText(Color.RED);
				btnMarket[i].setScaleFont(.8f);
			}else {
				market[i] = createButton(tregion[2], tregion[3]);
				btnMarket[i] = new MyButton(tregion[2],market_name[i], lbPosX[i], lbPosY[i]) {
					@Override
					public void precessClicked() {
						market[i].setChecked(true);
					}
				};
				btnMarket[i].setColorText(Color.GRAY);
				btnMarket[i].setScaleFont(.8f);
			}
//			market[i].setPosition(pos[i][0], pos[i][1]);
			btnMarket[i].setPosition(pos[i][0], pos[i][1]);
//			stage.addActor(market[i]);
			stage.addActor(btnMarket[i]);
		}

		close = F.createButton("data/texture/close.png");
		close.setPosition(1078, 620);
		back = F.createButton("data/texture/back.png");
		back.setPosition(1132, 580);

		btn_gold = F.createButton("data/shop/icon-gold.png");
		btn_gold.setPosition(250, 90);
		btn_money = F.createButton("data/shop/icon-xu.png");
		btn_money.setPosition(660, 90);

//		tregion = getTextureRegion("data/market/" + array_market[i] + ".png", 4);
//		btnpause = F.createButton("data/market/tam-dung-mua-ban.png");//, Language.General.TAM_DUNG_MUA_BAN.getStr(), fontButton);
//		btnpause.setPosition(520, 60);
//		btnresume = F.createButton("data/market/tiep-tuc-mua-ban.png");//, Language.General.TIEP_TUC_MUA_BAN.getStr(), fontButton);
//		btnresume.setPosition(520, 60);

		TextureRegion tregionP[] = getTextureRegion("data/market/tam-dung.png", 2);
		btnPause = new MyButton(tregionP[0], Language.General.TAM_DUNG_MUA_BAN.getStr()) {
			@Override
			public void precessClicked() {
//				if(btnpause.isChecked()) {
//						clicked_(btnpause);
						smarket = true;
						Data.savesMarket(index, 2);
						array_state[index] = 2;
						updateMstage(id);
//					}
			}
		};
		btnPause.setPosition(520, 60);
//		btnPause.setScaleFont();
btnPause.setColorText(Color.BLUE);

		TextureRegion tregionR[] = getTextureRegion("data/market/tam-dung.png", 2);
		btnResume = new MyButton(tregionR[0], Language.General.TIEP_TUC_MUA_BAN.getStr()) {
			@Override
			public void precessClicked() {
//				if(btnpause.isChecked()) {
//						clicked_(btnpause);
						smarket = false;
						Data.savesMarket(index, 1);
						array_state[index] = 1;
						updateMstage(id);
//					}
			}
		};
		btnResume.setPosition(520, 60);
		btnResume.setColorText(Color.BLUE);

		//					if(btnresume.isChecked()) {
//						clicked_(btnresume);
//						smarket = false;
//						Data.savesMarket(index, 1);
//						array_state[index] = 1;
//						updateMstage(id);
//					}

//		btn_yes = F.createButton("data/texture/icon-YES.png");
//		btn_yes.setPosition(450, 240);
//		btn_no = F.createButton("data/texture/icon-NO.png");
//		btn_no.setPosition(670, 240);

		Texture texture = Assets.manager.get("data/texture/nap-xu.png", Texture.class);
		tregion = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		btnYes = new MyButton(tregion[0], Language.General.YES.getStr()) {
			@Override
			public void precessClicked() {
				if(payType == 0) {
					if(F.coins >= market_cost) {
						state = OPEN;
						renderDialog = false;
						F.coins -= market_cost;

						Data.savesMarket(index, 1);
						array_state[index] = 1;
						updateMstage(id);
						Audio.sunlockM.play(Audio.soundVolume);
						Gdx.input.setInputProcessor(stage_other);
					} else {
						Audio.btnClick.play(Audio.soundVolume);
						Farm.payment.onMCDialog();
					}
				}

				if(payType == 1) {
					if(F.money >= market_cost/10) {
						state = OPEN;
						renderDialog = false;
						F.money -= market_cost/10;
						Data.savesMarket(index, 1);
						array_state[index] = 1;
						updateMstage(id);
						Audio.sunlockM.play(Audio.soundVolume);
						Gdx.input.setInputProcessor(stage_other);

						Farm.payment.Tracker(2, market_cost/10, market_name[index]); //Theo doi nguoi dung
					} else {
						Audio.btnClick.play(Audio.soundVolume);
						Farm.payment.onMXDialog();
					}
				}
			}
		};

		btnYes.setPosition(450, 240);
		btnNo = new MyButton(tregion[0], Language.General.NO.getStr()) {
			@Override
			public void precessClicked() {
				renderDialog = false;
				payType = -1;
				Gdx.input.setInputProcessor(stage_other);
			}
		};

		btnNo.setPosition(670, 240);

		stage.addActor(back);
		group.addActor(close);
		group.addActor(btn_money);
		group.addActor(btn_gold);

//		confirm_stage.addActor(btn_yes);
//		confirm_stage.addActor(btn_no);
		confirm_stage.addActor(btnYes);
		confirm_stage.addActor(btnNo);

		isMusic = Data.getisMusic();
		if(isMusic) {
			Audio.smarket.play();
		}

		texture = Assets.manager.get("data/shop/nuoc-than.png", Texture.class);
		TextureRegion wgod = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0][0];
		image = new Image(wgod);
		image.setScale(0.8f);
		image.setPosition(600, 40);
		Gdx.input.setInputProcessor(stage);
	}

	public TextureRegion[] getTextureRegion(String path, int nFrame) {
		Texture texture = Assets.manager.get(path, Texture.class);
		return TextureRegion.split(texture, texture.getWidth()/nFrame, texture.getHeight())[0];
	}

	public Button createButton(TextureRegion... tregion) {
		return new Button(new TextureRegionDrawable(tregion[0]), new TextureRegionDrawable(tregion[1]),
				new TextureRegionDrawable(tregion[1]));
	}
	@Override
	protected void draw(float delta){
		batch.draw(texture, 0, 0);
		batch.draw(icon1, 20, 600, 0, 0, 114, 108, 0.5f, 0.5f, 0);
		batch.draw(icon2, 20, 660, 0, 0, 125, 118, 0.5f, 0.5f, 0);
		font2.draw(batch, "" + F.money, 100, 735);
		font2.draw(batch, "" + F.coins, 100, 674);
		font3.draw(batch, "Level: " + F.level, 30, 600);
	}

	@Override
	protected  void update(float delta){
		if(renderMarket) renderMarket(index);
		else update_button();

		if(back.isChecked()) {
			clicked_(back);
			if(isMusic) {
				Audio.smarket.stop();
			}

			farm.setScreen(new PlayScreen(farm));
		}
	}
	@Override
	protected  void stageDraw(float delta){
		super.stageDraw(delta);
//		stage_other.draw();
		batch.begin();
		for(int i = 0; i < 12; i++) {
			int s = array_state[i];
			if(s == 1 || s == 2) {
				if(i == 5)
					batch.draw(flag[0], fpos[i][0], fpos[i][1]);
				else
					batch.draw(flag[s], fpos[i][0], fpos[i][1]);
			}
		}
		batch.end();
	}

	public void update_button() {
		for(int i = 0; i < 12; i++) {
			if(market[i].isChecked()) {
				clicked_(market[i]);
				renderMarket = true;

				index = i;
				lvUnlock = array_lvUnlock[i];
				state = array_state[i];

				if(F.level >= lvUnlock) {
					srequire = Data.loadMarket(i);
					if(srequire.equals("none")) {
						comodity = initialMarket(i);
						String s = "";
						for(int j = 0; j < comodity; j++) {
							if(j == comodity - 1) {
								s += require[j][0] + ":" + require[j][1] + ":0";
							}
							else {
								s += require[j][0] + ":" + require[j][1] + ":0" + ":";
							}
						}
						Data.saveMarket(i, s);
					} else {
						String[] s = srequire.split(":");
						if(state == 2) {
							state = 1;
							smarket = true;
						}

						updateMstage(i);
						int len = s.length;
						comodity = len/3;
						for(int j = 0; j < len; j++) {
							switch(j%3) {
							case 0:
								require[j/3][0] = Integer.parseInt(s[j]);
								break;
							case 1:
								require[j/3][1] = Integer.parseInt(s[j]);
								break;
							case 2:
								require[j/3][2] = Integer.parseInt(s[j]);
								break;
							}
						}

						if(state == 0) {
							for(int j = 0; j < comodity; j++) {
								int kind = (require[j][0] < 12) ? require[j][0] : require[j][0] - 12;
								if(require[j][0] < 12) {
									market_cost += SeedNature.getflowerValue(kind) * require[j][1];
								}
								else {
									market_cost += SeedNature.getfruitValue(kind) * require[j][1];
								}
							}
							market_cost /= 10;
						}
					}
				} else {
					group.removeActor(btn_money);
					group.removeActor(btn_money);
				}
				Gdx.input.setInputProcessor(stage_other);
			}
		}
	}

	public void renderMarket(int id) {
		batch.begin();
		batch.draw(shadow, 0, 0);
		batch.draw(if_frame, 120, 0);
		font1.draw(batch, market_name[index], 0, 640, 1280, Align.center, false);
		font1.draw(batch, Language.General.CAP_DO.getStr() + lvUnlock, 0, 580, 1280, Align.center, false);

		switch(state) {
		case CLOSE:
			if(F.level >= lvUnlock) {
				batch.draw(textbox, 400, 110);
				batch.draw(textbox, 800, 110);
				font1.draw(batch, Integer.toString(market_cost), 400, 170, 200, Align.center, false);
				font1.draw(batch, Integer.toString(market_cost/10), 800, 170, 200, Align.center, false);

				for(int i = 0; i < comodity; i++) {
					int kind = (require[i][0] < 12) ? require[i][0] : require[i][0] - 12;
					if(require[i][0] < 12) {
						batch.draw(F.flower[kind], 240, 446 - i * 65);
						font1.draw(batch, SeedNature.flname[kind] + ":  " + require[i][1], 320, 500 - i * 65);
					}
					else {
						batch.draw(F.fruit[kind], 240, 446 - i * 65);
						font1.draw(batch, SeedNature.frname[kind] + ":  " + require[i][1], 320, 500 - i * 65);
					}
				}

				font1.draw(batch, Language.General.GIA_MO_GD.getStr(), 0, 290, 1280, Align.center, false);
				if(btn_gold.isChecked()) {
					clicked_(btn_gold);
					renderDialog = true;
					payType = 0;
					Gdx.input.setInputProcessor(confirm_stage);
				}

				if(btn_money.isChecked()) {
					clicked_(btn_money);
					renderDialog = true;
					payType = 1;
					Gdx.input.setInputProcessor(confirm_stage);
				}
			}
			else {
				font1.draw(batch, Language.General.CHUA_DC_MO_TT.getStr() , 0, 440,
						1280, Align.center, false);
			}

			break;
		case OPEN:
			for(int i = 0; i < comodity; i++) {
				int kind = (require[i][0] < 12) ? require[i][0] : require[i][0] - 12;
				if(require[i][0] < 12) {
					batch.draw(F.flower[kind], 240, 446 - i * 65);
					font1.draw(batch, SeedNature.flname[kind] + ":  " + require[i][2] +"/" + require[i][1], 320, 500 - i * 60);
				}
				else {
					batch.draw(F.fruit[kind], 240, 446 - i * 65);
					font1.draw(batch, SeedNature.frname[kind] + ":  " + require[i][2] +"/" + require[i][1], 320, 500 - i * 60);
				}
			}

			if(id == 5) {
				font1.draw(batch, Language.General.HTNV_TANG_LO_NUOC.getStr(), 0, 290, 1280, Align.center, false);
				image.draw(batch, 1);
			} else {
				font1.draw(batch, text, 0, 290, 1280, Align.center, false);
				if(smarket) {
					text = Language.General.TT_TAM_DUNG_GD.getStr();
//					if(btnresume.isChecked()) {
//						clicked_(btnresume);
//						smarket = false;
//						Data.savesMarket(index, 1);
//						array_state[index] = 1;
//						updateMstage(id);
//					}
				} else {
					text = Language.General.TT_DANG_GD.getStr();
//					if(btnpause.isChecked()) {
//						clicked_(btnpause);
//						smarket = true;
//						Data.savesMarket(index, 2);
//						array_state[index] = 2;
//						updateMstage(id);
//					}
				}
			}
			break;

		case SUCCESS:
			for(int i = 0; i < comodity; i++) {
				int kind = (require[i][0] < 12) ? require[i][0] : require[i][0] - 12;
				if(require[i][0] < 12) {
					batch.draw(F.flower[kind], 240, 446 - i * 65);
					font1.draw(batch, SeedNature.flname[kind] + ":  " + require[i][2] +"/" + require[i][1], 320, 500 - i * 60);
				}
				else {
					batch.draw(F.fruit[kind], 240, 446 - i * 65);
					font1.draw(batch, SeedNature.frname[kind] + ":  " + require[i][2] +"/" + require[i][1], 320, 500 - i * 60);
				}
			}

			font1.draw(batch, Language.General.GD_HOAN_THANH.getStr(), 0, 230, 1280, Align.center, false);
			break;
		}
		batch.end();
		if(renderDialog) {
			renderDialog(id);
		}
		stage_other.draw();

		if(close.isChecked()) {
			clicked_(close);
			state = CLOSE;
			renderMarket = false;
			renderDialog = false;
			updateMstage(id);
			Gdx.input.setInputProcessor(stage);
		}
	}

	public void renderDialog(int id) {
		batch.begin();
		batch.draw(war_frame, 310, 200);
		batch.end();
		confirm_stage.draw();

//		if(btn_yes.isChecked()) {
//			clicked_(btn_yes);

//			if(payType == 0) {
//				if(F.coins >= market_cost) {
//					state = OPEN;
//					renderDialog = false;
//					F.coins -= market_cost;
//
//					Data.savesMarket(index, 1);
//					array_state[index] = 1;
//					updateMstage(id);
//					Audio.sunlockM.play(Audio.soundVolume);
//					Gdx.input.setInputProcessor(stage_other);
//				} else {
//					Audio.btnClick.play(Audio.soundVolume);
//					Farm.payment.onMCDialog();
//				}
//			}
//
//			if(payType == 1) {
//				if(F.money >= market_cost/10) {
//					state = OPEN;
//					renderDialog = false;
//					F.money -= market_cost/10;
//					Data.savesMarket(index, 1);
//					array_state[index] = 1;
//					updateMstage(id);
//					Audio.sunlockM.play(Audio.soundVolume);
//					Gdx.input.setInputProcessor(stage_other);
//
//					Farm.payment.Tracker(2, market_cost/10, market_name[index]); //Theo doi nguoi dung
//				} else {
//					Audio.btnClick.play(Audio.soundVolume);
//					Farm.payment.onMXDialog();
//				}
//			}
//		}

//		if(btn_no.isChecked()) {
//			clicked_(btn_no);
//			renderDialog = false;
//			payType = -1;
//			Gdx.input.setInputProcessor(stage_other);
//		}
	}

	public void updateMstage(int id) {
		group.clear();
		group.addActor(close);

		switch(state) {
		case CLOSE:
			group.addActor(btn_gold);
			group.addActor(btn_money);
			break;
		case OPEN:
			if(id != 5 && state != 3) {
//				if(smarket)  group.addActor(btnresume);
//				else  group.addActor(btnpause);

				if(smarket)  group.addActor(btnResume);
				else  group.addActor(btnPause);
			}
			break;
		}


	}

	public int initialMarket(int id) {
		int array[] = new int[24];
		int factor, count, lvUnlock, totalComodity;
		int[] n = new int[3];
		boolean lock;
		int como;

		count = 0;
		for(int i = 0; i < 12; i++) {
			lvUnlock = SeedNature.getflowerUnlock(i);
			lock = (F.level >= lvUnlock) ? false : true;
			if(!lock) {
				array[count] = i;
				count ++;
			}

			lvUnlock = SeedNature.getfruitUnlock(i);
			lock = (F.level >= lvUnlock) ? false : true;
			if(!lock) {
				array[count] = i + 12;
				count ++;
			}
		}

		if(id != 5) {
			factor = array_factor[id];
			n = getP(count);

			float time = 0;
			for(int i = 0; i < 3; i++) {
				require[i][0] = array[n[i]];
				time += getgrowth_time(array[n[i]])/3;
			}

			if(count < 3) {
				totalComodity = (int) (factor * (100 / Math.ceil(time)) * nof_plot);
				require[0][1] = (int) (totalComodity * 0.45f);
				require[1][1] = (int) (totalComodity * 0.55f);

				como = count;
			} else {
				totalComodity = (int) (factor * (100 / Math.ceil(time)) * nof_plot);
				require[0][1] = (int) (totalComodity * 0.25f);
				require[1][1] = (int) (totalComodity * 0.35f);
				require[2][1] = (int) (totalComodity * 0.4f);

				como = maxComodity;
			}
		} else {
			return -1;
		}

		market_cost = 0;
		for(int i = 0; i < maxComodity; i++) {
			int kind = (require[i][0] < 12) ? require[i][0] : require[i][0] - 12;
			if(require[i][0] < 12)
				market_cost += SeedNature.getflowerValue(kind) * require[i][1];
			else
				market_cost += SeedNature.getfruitValue(kind) * require[i][1];
		}

		market_cost /= 10;
		return como;
	}

	public int[] getP(int count) {
		int a, n[] = new int[3];

		if(count < 6) {
			n[0] = random.nextInt(count);
		} else {
			n[0] = (count - 6) + random.nextInt(6);
		}

		if(count < maxComodity)
			for(int i = 0; i < count; i++)
				n[i] = i;
		else
		for(int i = 1; i < maxComodity; i++) {
			while(true) {
				boolean flag = true;
				if(count < 6) {
					a = random.nextInt(count);
				} else {
					a = (count - 6) + random.nextInt(6);
				}

				for(int j = 0; j < i; j++) {
					if(a == n[j]) {
						flag = false;
						break;
					}
				}
				if(flag) {
					n[i] = a;
					break;
				}
			}
		}

		return n;
	}

	public int getgrowth_time(int kind) {
		int time;
		if(kind < 12)	time = SeedNature.getflgrowthTime(kind);
		else	time = SeedNature.getfrgrowthTime(kind - 12);
		return time;
	}

	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}
	@Override
	public void hide() {
		font2.dispose();
		font3.dispose();
		System.out.println("Market is hide !");
	}
}