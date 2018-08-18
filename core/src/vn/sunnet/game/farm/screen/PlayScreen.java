package vn.sunnet.game.farm.screen;

import java.util.ArrayList;
import java.util.Random;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.nature.SeedNature;
import vn.sunnet.game.farm.object.Employee;
import vn.sunnet.game.farm.object.Experience;
import vn.sunnet.game.farm.object.Flower;
import vn.sunnet.game.farm.object.Fruit;
import vn.sunnet.game.farm.object.Player;
import vn.sunnet.game.farm.object.Plot;
import vn.sunnet.game.farm.object.RetailCustomer;
import vn.sunnet.game.farm.object.Seed;
import vn.sunnet.game.farm.object.Table;
import vn.sunnet.game.farm.object.Wholesalers;
import vn.sunnet.game.stage.ExpansionGarden;
import vn.sunnet.game.stage.GuideLine;
import vn.sunnet.game.stage.Message;
import vn.sunnet.game.stage.Mission;
import vn.sunnet.game.stage.PUpgrade;
import vn.sunnet.game.stage.Repository;
import vn.sunnet.game.stage.Shop;
import vn.sunnet.game.stage.Warehouse;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class PlayScreen extends BaseScreen implements InputProcessor {

	private static final int PLOT_WIDTH = 210, PLOT_HEIGHT = 153;
	private static final int FLOWER = 0, FRUIT = 1;
	private static final int NONE = 0, ACTION_PLANT = 1, ACTION_FLUCK = 2, ACTION_WATERING = 3, 
			ACTION_HARVEST = 4, UPGRADE1 = 5, UPGRADE2 = 6;
	private static final int GAME_READY = 0;
	private static final int GAME_RUNNING = 1;
	private static final int GAME_PAUSED = 2;
	private static final int GAME_STOPPED = 3;
	private static final String defVal = "none";
	private float season_time, eTime;
	private String[] array_season = {"mùa xuân", "mùa hạ", "mùa thu", "mùa đông"};
	
	private int nof_season, nof_plot, nof_garden, currentGarden;
	public static long experience, beginExp, endExp;
	
	private Farm farm;
//	private OrthographicCamera cam;
	private BitmapFont lvFont, garFont, coiFont, clockFont;	
//	private SpriteBatch batch;
	private GuideLine guide;
	private Stage /*stage,*/ stage2, stage3, stage4, stage5; //stage4: pause
	private Button btn_pause, btn_shop, btn_warehouse, btn_shovel, btn_seed, btn_market;
	private Button prev_garden, next_garden, expansion, btn_yes, btn_no;
	private Button resume, setting, archivement, home, recharge;
	private Button upgrade[], btn_employee, btn_mission, btn_gift, btn_gift_;
	private Vector2 pPos, basePos1, basePos2;
	private Texture background[], pause_bg;
	private Texture land0, bar1, bar2, signage;
	private TextureRegion bar3;
	private Texture ic_coin, cir1, cir2, shadow, dlgshovel;
	private Texture wayside[];
	private Vector3 touchPoint;
	public int state, action, pAction;
	private String path = "data/texture/";
	private String fpath = "data/font/";
	private int season, number, hmargin = 20, vmargin = 15;
	private int t_id, p_id, type, kind, RType;
	public int pidwasOccupied = -1, pidwasUpgrade = -1, PPGarden;
	//PPGarden: So o dat tren thua ruong hien tai
	
	private InputMultiplexer multiPlexer;
	private Rectangle bound, signageBound;
	private Player player;
	private Employee employee;
	private RetailCustomer retailsaler;
	private Wholesalers wholesaler;
	private ArrayList<RetailCustomer> arrayRetail;
	private ArrayList<Wholesalers> arrayWholesaler;
	private ArrayList<Employee> arrayEmployee;
	
	private ExpansionGarden expansionGarden;
	private Mission mission;
	private Setting setup;
	private Message message;
	private PUpgrade pUpgrade;
	private Experience exp;
	private Shop shop;
	private Warehouse warehouse;
	private Repository repository;
	private Seed se;
	private Flower flower;
	private Fruit fruit;
	private Table table;
	private ArrayList<Flower> arrayFlower[];
	private ArrayList<Fruit> arrayFruit[];
	private ArrayList<Experience> arrayExp;
	private ArrayList<Table> arrayTable;
	private Group group;
	
	private Plot plot;
	public ArrayList<Plot> arrayPlot;
	private boolean renderShop, renderWarehouse, renderRepository, renderExpansion;
	public boolean updatepAction, oneTime, renderMessage, renderSetup;
	public boolean renderUpgrade, renderSignage, renderMission, renderGuide;
	public Vector2 pos;
	private Random random;
	private int gifts;
	
	@SuppressWarnings("unchecked")
	public PlayScreen(Farm farm) {
		super();
		this.farm = farm;
		state = GAME_RUNNING;
		random = new Random();
		
//		cam = new OrthographicCamera(F.viewportWidth, F.viewportHeight);
//		cam.position.set(F.viewportWidth/2, F.viewportHeight/2, 0);
//		batch = new SpriteBatch();
		shadow = Assets.manager.get(path + "shadow.png", Texture.class);
		dlgshovel = Assets.manager.get(path + "nho-cay.png", Texture.class);
		
		basePos1 = new Vector2(60, 520);
		basePos2 = new Vector2(900, 60);
		touchPoint = new Vector3();
		
//		stage = new Stage();
		group = new Group();
		stage.addActor(group);
		
		stage2 = new Stage();
		stage3 = new Stage();
		stage4 = new Stage();
		stage5 = new Stage();

		stage2.setViewport(viewport);
		stage3.setViewport(viewport);
		stage4.setViewport(viewport);
		stage5.setViewport(viewport);
		
		garFont = Assets.manager.get(fpath + "garden.fnt", BitmapFont.class);
		garFont.getData().setScale(0.6f);
		lvFont = new BitmapFont(Gdx.files.internal("data/font/level.fnt"), 
				Gdx.files.internal("data/font/level.png"), false);
		lvFont.getData().setScale(0.6f);
		coiFont = new BitmapFont(Gdx.files.internal(fpath + "xu.fnt"), false);
		coiFont.getData().setScale(0.8f);
		clockFont = Assets.manager.get(fpath + "font_clock.fnt", BitmapFont.class);
		clockFont.getData().setScale(1.4f);
		
		btn_yes = createButton("icon-YES.png", 0);
		btn_yes.setPosition(235, 270);
		btn_no = createButton("icon-NO.png", 0);
		btn_no.setPosition(555, 270);
		btn_pause = createButton("icon-pause.png", 1);
		btn_pause.setPosition(1135, 575);
		btn_shop = createButton("icon-cua-hang.png", 0);
		btn_shop.setPosition(980, 575);
		btn_warehouse = createButton("icon-kho-chua.png", 0);
		btn_warehouse.setPosition(670, 575);
		btn_shovel = createButton("icon-nho-cay.png", 1);
		btn_shovel.setPosition(825, 575);
		btn_seed = createButton("icon-hat-giong.png", 0);
		btn_seed.setPosition(515, 575);
		btn_market = createButton("icon-thi-truong.png", 0);
		btn_market.setPosition(1020, 400);
		prev_garden = createButton("bien-bao-2.png", 0);
		prev_garden.setPosition(18, 565);
		next_garden = createButton("bien-bao-1.png", 0);
		next_garden.setPosition(112, 565);
		expansion = createButton("expansion.png", 0);
		signageBound = new Rectangle();
		recharge = createButton("nap-xu.png", 0);
		recharge.setPosition(10, 660);
		
		//resume, setting, archivement, exit
		home = createButton("home.png", 0);
		home.setPosition(180, 218);
		setting = createButton("cai-dat.png", 0);
		setting.setPosition(440, 210);
		archivement = createButton("thanh-tich.png", 0);
		archivement.setPosition(670, 190);
		resume = createButton("tiep-tuc.png", 0);
		resume.setPosition(960, 220);
		
		//Chuc nang mo rong
		btn_mission = createButton("giay-nhiem-vu.png", 0);
		btn_mission.setPosition(150, 10);
		btn_gift = createButton("qua-tang.png", 0);
		btn_gift.setPosition(250, 10);
		btn_gift_ = createButton("qua-gan-bo.png", 0);
		btn_gift_.setPosition(400, 10);
		btn_employee = createButton("nhan-cong.png", 0);
		btn_employee.setPosition(550, 10);
		
		stage.addActor(btn_mission);
		stage.addActor(btn_gift);
		stage.addActor(btn_gift_);
		stage.addActor(recharge);
		
		stage.addActor(btn_pause);
		stage.addActor(btn_shop);
		stage.addActor(btn_warehouse);
		stage.addActor(btn_shovel);
		stage.addActor(btn_seed);
		stage.addActor(btn_market);
		
		stage.addActor(prev_garden);
		stage.addActor(next_garden);
		stage2.addActor(expansion);
		stage3.addActor(btn_yes);
		stage3.addActor(btn_no);
		
		stage4.addActor(resume);
		stage4.addActor(archivement);
		stage4.addActor(setting);
		stage4.addActor(home);
		
		pause_bg = Assets.manager.get(path + "pause-bg.png", Texture.class);
		background = new Texture[4];
		background[0] = Assets.manager.get(path + "mua-xuan.jpg", Texture.class);
		background[1] = Assets.manager.get(path + "mua-ha.jpg", Texture.class);
		background[2] = Assets.manager.get(path + "mua-thu.jpg", Texture.class);
		background[3] = Assets.manager.get(path + "mua-dong.jpg", Texture.class);
		
		land0 = Assets.manager.get(path + "land-0.png", Texture.class);
		ic_coin = Assets.manager.get(path + "xu.png", Texture.class);
		cir1 = Assets.manager.get(path + "level-1.png", Texture.class);
		cir2 = Assets.manager.get(path + "level-2.png", Texture.class);
		
		bar1 = Assets.manager.get(path + "thanh-mau-1.png", Texture.class);
		bar2 = Assets.manager.get(path + "thanh-mau-2.png", Texture.class);
		bar3 = new TextureRegion(bar2, 0, 0);
		signage = Assets.manager.get(path + "icbien-bao.png", Texture.class);
		
		wayside = new Texture[6];
		wayside[0] = Assets.manager.get(path + "land-1.png", Texture.class);
		wayside[1] = Assets.manager.get(path + "bui-cay-xanh.png", Texture.class);
		wayside[2] = Assets.manager.get(path + "bui-cay-trang.png", Texture.class);
		wayside[3] = Assets.manager.get(path + "bui-cay-vang.png", Texture.class);
		wayside[4] = Assets.manager.get(path + "bui-cay-do.png", Texture.class);
		wayside[5] = Assets.manager.get(path + "bui-cay-tim.png", Texture.class);
		
		multiPlexer = new InputMultiplexer();
		multiPlexer.addProcessor(this);
		multiPlexer.addProcessor(stage);
		multiPlexer.addProcessor(stage2);
		multiPlexer.addProcessor(stage5);
		Gdx.input.setInputProcessor(multiPlexer);
		
		arrayPlot = new ArrayList<Plot>();
		arrayFruit = new ArrayList[10];
		arrayFlower = new ArrayList[10];
		arrayExp = new ArrayList<Experience>();
		arrayTable = new ArrayList<Table>();
		
		for(int i = 0; i < 10; i++) {
			arrayFlower[i] = new ArrayList<Flower>();
			arrayFruit[i] = new ArrayList<Fruit>();
		}
		arrayRetail = new ArrayList<RetailCustomer>();
		arrayWholesaler = new ArrayList<Wholesalers>();
		arrayEmployee = new ArrayList<Employee>();
		
		bound = new Rectangle(0, 240, 1280, 260);
		
		gifts = Data.gifts();
		loadData();	
		
		upgrade = new Button[12];
		for(int i = 0; i < 12; i++) {
			upgrade[i] = createButton("hnang-cap.png", 0);
			upgrade[i].setPosition(212 + 210 * (i%4), 390 - 153 * (i/4));
		}
		
		updateSignagePos();
		updateUpgradePos();
		for(int i = 0; i < F.nof_table; i++) {
			int n = Data.loadTable(i);
			if(n == -1) table = new Table(i);
			else table = new Table(i, n);
			arrayTable.add(table);
		}
		
		String srequire = Data.loadMarket(5);
		if(srequire == "none") {
			initialMarket(5);
		}
		
		int array[], num = 0;
		array = new int[12];
		for(int i = 0; i < 12; i++)
			if(F.level >= Market.array_lvUnlock[i])
				if(Data.loadsMarket(i) == 1) {
					array[num] = i;
					num ++;
				}
		
		//System.out.println("number: " + num);
		for(int i = 0; i < num; i++) {
			wholesaler = new Wholesalers(array[i], arrayTable, arrayExp);
			arrayWholesaler.add(wholesaler);
			//System.out.println("Market is transactions: " + array[i]);
		}
		
		//System.out.println("Level: " + level + " " + Math.ceil(level/10f));
		for(int i = 0; i < Math.ceil((F.level + 1)/10f); i++) {
			retailsaler = new RetailCustomer(arrayTable, arrayExp);
			arrayRetail.add(retailsaler);
			//System.out.println("Retail: " + i);
		}
		
		//System.out.println("retailsaler: " + level/5);
		if(Data.getisMusic())
			Audio.music2[season].play();
		player = new Player(this, 0, (int)pPos.x, (int)pPos.y);
		
		if(nof_season < 1 && F.isGuide) {
			guide = new GuideLine(stage);
			renderGuide = true;
			pause_();
		}
	}
	
	public void loadData() {
		String value, array[];
		int id, gcurrent;
		float wTime = 0, cTime = 0;
		
		season = Data.loadSeason();
		value = Data.loadPPos();
		array = value.split(":");
		pPos = new Vector2(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
		
		experience = Data.getExperience();		
		beginExp = F.get_lvexperience(F.level - 1);
		endExp = F.get_lvexperience(F.level);
		
		float delta = endExp - beginExp;
		number = (int) (((experience - beginExp) / delta) * 30);
		
		//System.out.println("loadE: " + experience);
		season_time = Data.getSeansonTime();
		eTime = Data.getETime();
		nof_season = Data.getnofSeason();
		nof_plot = Data.getnofPlot();
		
		for(int i = 0; i < F.nof_employee; i++) {
			addEmployee();
		}
		currentGarden = 0;
		nof_garden = ((nof_plot + 1) % 12 == 0) ? (nof_plot + 1)/12 : (nof_plot/12 + 1);
		
		boolean wither = false;
		for(int i = 0; i < nof_plot; i++) {
			value = Data.getStatePlot(i);
			if(value.equals(defVal)) {
				plot = new Plot(i, 0);
			}
			else {
				array = value.split(":");
				plot = new Plot(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
			}
			arrayPlot.add(plot);
						
			value = Data.getStateSeed(i);			
			if(!value.equals(defVal)) {
				id = plot.id;
				gcurrent = id/12;
				
				array = value.split(":");
				id = Integer.parseInt(array[0]);
				type = Integer.parseInt(array[1]);
				kind = Integer.parseInt(array[2]);				
				float time = Float.parseFloat(array[3]);
				
				if(array.length == 6) {
					wTime = Float.parseFloat(array[4]);
					cTime = Float.parseFloat(array[5]);
				} else {
					wither = Boolean.parseBoolean(array[4]);
				}
					
				if(type == 0) {
					flower = new Flower(plot.level, id, kind, false);
					if(array.length == 6) {
						flower.setTime(time, wTime, cTime);
					}
					else {
						flower.setTime(time);
						flower.wither = wither;
					}
					arrayFlower[gcurrent].add(flower);
					plot.assignSeed(flower);
				} else {
					fruit = new Fruit(plot.level, id, kind, false);
					if(array.length == 6) {
						fruit.setTime(time, wTime, cTime);
					}
					else {
						fruit.setTime(time);
						fruit.wither = wither;
					}
					arrayFruit[gcurrent].add(fruit);
					plot.assignSeed(fruit);
				}
			}
		}
	}
	
	public void saveData() {
		Data.saveMoney(F.money);
		Data.saveCoins(F.coins);		
		Data.saveLevel(F.level);
		Data.savenofwgod(F.nof_wgod);
		Data.savenofTable(F.nof_table);
		
		Data.saveExperience(experience);
		Data.saveSeason(season);
		Data.savenofSeason(nof_season);
		Data.saveSeasonTime(season_time);
		Data.savenofPlot(nof_plot);
		Data.saveETime(eTime);
		
		
		Gdx.app.log("Message", "nof_employee: " + F.nof_employee);
		Data.savenofEmployee(F.nof_employee);
		if(F.nof_employee > 0) {
			employee = arrayEmployee.get(0);
			if(employee.thanhbq)
				Data.saveEmployee(employee.index);
			else
				Data.saveEmployee(-1);
		}
		
		String a;
		for(int i = 0; i < F.nof_table; i++) {
			int n;
			table = arrayTable.get(i);
			if(table.flag)	n = table.type * 12 + table.kind;
			else n = -1;
			Data.saveTable(i, n);
		}
		for(int i = 0; i < arrayPlot.size(); i++) {
			plot = arrayPlot.get(i);
			a = new String(i + ":" + plot.level);
			Data.saveStatePlot(i, a);
			se = plot.seed;
			
			//System.out.println("Save !");
			if(plot.flag) {
				a = new String(i + ":" + se.type + ":" + se.kind + ":" + se.time);
				if(se.needWatering) {
					a += ":" + se.wTime + ":" + se.cTime;
				} else {
					a += ":" + se.wither;
					//System.out.println("PID: " + i + " Wither: " + se.wither);
				}
				
				//System.out.println("Save" + a);
				Data.saveStateSeed(i, a);
			} else
				Data.saveStateSeed(i, defVal);
		}
	}
	
	public void updateSignagePos() {
		int id = nof_plot % 12;
		pos = new Vector2(75 + 210 * (id % 4), 371 - 153 * (id / 4));
		signageBound.set(pos.x, pos.y, 205, 153);
		expansion.setPosition(pos.x + 50, pos.y + 30);
		
		if(currentGarden != (nof_garden - 1) || nof_plot == 120) {
			renderSignage = false;
			stage2.clear();
		} else {
			renderSignage = true;
			stage2.addActor(expansion);
		}
	}
	
	public void updateUpgradePos() {
		PPGarden = (nof_plot - currentGarden * 12 > 12) ? 12 : nof_plot - currentGarden * 12;
		stage5.clear();
		
		for(int i = 0; i < PPGarden; i++) {
			if(i != pidwasUpgrade)
				stage5.addActor(upgrade[i]);
		}	
		
		for(int i = 0; i < F.nof_employee; i++) {
			employee = arrayEmployee.get(i);
			employee.updateGarden(currentGarden);
		}
	}
	
	public Button createButton(String name, int checked) {
		Texture texture = Assets.manager.get(path + name);
		TextureRegion[] region = TextureRegion.split(texture, texture.getWidth()/2, texture.getHeight())[0];
		return new Button(new TextureRegionDrawable(region[0]), new TextureRegionDrawable(region[1]), 
				new TextureRegionDrawable(region[checked]));
	}	

	@Override
	protected void stageDraw(float delta){
		super.stageDraw(delta);
		stage2.draw();
		stage5.draw();

		batch.begin();
		renderEmployee();
		renderWholesalers();
		renderRetailsalers();
		batch.end();
	}

	@Override
	protected void draw(float delta) {
		batch.draw(background[season], 0, 0);
		batch.draw(signage, 0, 495);
		garFont.draw(batch, Integer.toString(currentGarden + 1), 35, 652, 100, Align.center, false);
		renderObjects();
	}

	@Override
	protected void update(float delta) {
		switch(state) {
			case GAME_READY:
				updateReady();

				break;
			case GAME_RUNNING:
				season_time += delta;

				switch(gifts) {
					case 0:
						if(season_time >= 1 && F.nof_employee == 0) {
							gifts = 1;
							Message(3);
							Data.lockgifts(1);
							Data.saveEmployee(1);
						}
						break;
					case 1:
						if(season_time > 3600) {
							Message(4);
							gifts = -1;
							Data.lockgifts(-1);
						}
						break;
					case -1:
						if(F.nof_employee == 1) {
							eTime -= delta;
							if(eTime <= 0) {
								Message(4);
							}
						}
						break;
				}

				//Todo:
				if(season_time >= 7200) {
					season_time = 0;
					if(Data.getisMusic()) {
						Audio.music2[season].stop();
					}

					if(++season > 3)
						season = 0;
					if(Data.getisMusic()) {
						Audio.music2[season].play();
					}

					nof_season ++;
					Message(1);
				}

				if(updatepAction) {
					updatepAction();
				}

				updateButton();
				updateRunning(delta);
				break;
			case GAME_PAUSED:
				updatePaused();
				break;
			case GAME_STOPPED:
				updateStopped();
				break;
		}

		switch(state) {
			case GAME_READY:
				presentReady();
				break;
			case GAME_RUNNING:
				presentRunning();
				break;
			case GAME_PAUSED:
				presentPaused();
				break;
			case GAME_STOPPED:
				presentStopped();
				break;
		}
	}

	//Ve ra cac doi tuong game
	public void renderObjects() {
		renderSeasonTime();
		renderPlot();
		renderFlower();
		renderFruit();
		
		for(int i = 0; i < F.nof_table; i ++) {
			table = arrayTable.get(i);
			table.render(batch);
		}
		
		batch.draw(ic_coin, 195, 617);
		renderExperience();
		lvFont.draw(batch, Integer.toString(F.level), 170, 716, 50, Align.center, false);
		coiFont.draw(batch, "" + F.coins, 334, 722, 150, Align.center, false);
	}
	
	//Ham ve thoi gian da qua trong 1 mua
	public void renderSeasonTime() {
		float percent = (season_time / 7200);
		int width = (int) (percent * 292);
		bar3 = new TextureRegion(bar2, 0, 0, width, 26);
		batch.draw(bar1, 210, 575);
		batch.draw(bar3, 210, 575);
	}
	
	//Ham ve cac thua ruong
	public void renderPlot() {
		int len = arrayPlot.size();
		int start = currentGarden * 12;
		int end = (start + 12 < len) ? start + 12 : len;
		
		for(int i = start; i < end; i++) {
			plot = arrayPlot.get(i);
			batch.draw(land0, plot.pos1.x, plot.pos1.y);
			if(plot.level == 0)
				batch.draw(wayside[0], plot.pos2.x, plot.pos2.y);
			else
				batch.draw(wayside[plot.level], plot.pos3.x, plot.pos3.y);
		}
		
		if(renderSignage) {
			batch.draw(land0, pos.x, pos.y);
			batch.draw(wayside[0], pos.x, pos.y);
		}
	}
	
	//Ham ve ra hoa
	public void renderFlower() {
		int len = arrayFlower[currentGarden].size();
		for(int j = 0; j < len; j++) {
			flower = arrayFlower[currentGarden].get(j);
			flower.render(batch);
		}
	}
	
	//Ham ve qua
	public void renderFruit() {
		int len = arrayFruit[currentGarden].size();
		for(int j = 0; j < len; j++) {
			fruit = arrayFruit[currentGarden].get(j);
			fruit.render(batch);
		}
	}
	
	//Ve ra khi o trang thai Ready
	public void presentReady() {
		
	}
	
	//Ve ra khi o trang thai RUNNING
	public void presentRunning() {
		batch.begin();
		int len = arrayExp.size();
		for(int i = 0; i < len; i++) {
			exp = arrayExp.get(i);
			exp.render(batch);
		}
		batch.end();
	}
	
	//Ve ra khi o trang thai PAUSE
	public void presentPaused() {
		batch.begin();
		batch.draw(shadow, 0, 0);
		batch.end();
		
		if(renderShop)	{
			shop.render(batch);		
		}		
		if(renderWarehouse)	{
			warehouse.render(batch);		
		}		
		if(renderRepository) {
			renderRepository(RType);		
		}
		if(renderMessage)	{
			message.render(batch);
		}		
		if(renderExpansion) {			
			expansionGarden.render(batch);
		}		
		if(renderUpgrade) {						
			pUpgrade.render(batch);
		}		
		if(renderMission) {
			mission.render(batch);
		}		
		if(renderGuide) {
			guide.render(batch);
		}
	}
	
	public void renderRepository(int n) {
		repository.render(batch);
		int id = repository.choose_id();
		
		if(id != -1) {
			switch(n) {
			case 0:
				repository.resetChooseValue();
				
				if(id == -2) {
					action = UPGRADE1;
				} else {
					action = ACTION_PLANT;
				}
				if(id < 12) {
					type = FLOWER;
					kind = id;
				}
				else {
					type = FRUIT;
					kind = id % 12;
				}
				
				break;
			case 1:
				if(F.nof_employee > 0) {
					arrayEmployee.get(0).thanhbq(id);
				}
				break;
			}
		
			resume_();
			repository.dispose();
			renderRepository = false;
			Gdx.input.setInputProcessor(multiPlexer);
		}
	}
	
	public void actionPerform(int action, int p_id, int type, int kind) {
		plot = arrayPlot.get(p_id);
		
		switch(action) {
		case NONE:
			break;
		case ACTION_PLANT:
			Audio.splant.play(Audio.soundVolume);
			switch(type) {
			case FLOWER:
				Data.addFlRepository(kind, -1);
				flower = new Flower(plot.level, p_id, kind, true);
				arrayFlower[currentGarden].add(flower);
				plot.assignSeed(flower);
				break;
			case FRUIT:
				Data.addFrRepository(kind, -1);
				fruit = new Fruit(plot.level, p_id, kind, true);
				arrayFruit[currentGarden].add(fruit);
				plot.assignSeed(fruit);
				break;
			}
			break;
		case ACTION_FLUCK:
			se = plot.seed;
			if(se.type == 0)
				arrayFlower[currentGarden].remove((Flower)se);
			else
				arrayFruit[currentGarden].remove((Fruit)se);
			
			plot.assignEmpty();
			se = null;
			break;
		case ACTION_WATERING:
			se = plot.seed;
			se.reset();
			Audio.swatering.play(Audio.soundVolume);
			break;
		case ACTION_HARVEST:			
			int e;
			se = plot.seed;
			e = (int)  Math.round(se.experience * (1 + plot.level * 0.1f));
			if(se.wither) {
				e -= Math.round(se.experience * 0.1f);
			}
						
			exp = new Experience(0, p_id, e);
			arrayExp.add(exp);
			
			experience += e;
			if(experience > F.get_lvexperience(F.level)) {
				Message(0);
			}
			float delta = endExp - beginExp;			
			number = (int) (((experience - beginExp) / delta) * 30);
			
			if(se.type == 0) {
				Data.addFlWavehouse(se.kind, 1);
				arrayFlower[currentGarden].remove((Flower)se);
			}
			else {
				Data.addFrWavehouse(se.kind, 1);
				arrayFruit[currentGarden].remove((Fruit)se);
			}
			
			plot.assignEmpty();
			se = null;
			break;
		}
		
		pidwasOccupied = -1;
	}
	
	public void renderEmployee() {
		player.render(batch);
		for(int i = 0; i < F.nof_employee; i++) {
			employee.render(batch);
		}
	}
	
	public void renderRetailsalers() {
		int len = arrayRetail.size();
		for(int i = 0; i < len; i++) {
			retailsaler = arrayRetail.get(i);
			retailsaler.render(batch);
		}
	}
	
	public void renderWholesalers() {
		int len = arrayWholesaler.size();
		for(int i = 0; i < len; i++) {
			wholesaler = arrayWholesaler.get(i);
			wholesaler.render(batch);
		}
	}
	
	public void renderExperience() {
		batch.draw(cir1, 145, 615);		
		for(int i = 0; i < number; i++)
			batch.draw(new TextureRegion(cir2), 145, 615, 50, 50, 100, 100, 1, 1, i * 12 + 55);
		
		int time = (int) (7200 - season_time);
		int hour = time/3600;
		time -= hour * 3600;
		int minute = time/60;
		time -= minute * 60;
		String t = String.format("%02d", hour) + ":" + String.format("%02d", minute) 
				+ ":" + String.format("%02d", time);
		clockFont.draw(batch, t, 305, 634);
	}
	
	public void pause_() {
		state = GAME_PAUSED;
		ps();
	}
	
	public void stop_() {
		state = GAME_STOPPED;
		Audio.music2[season].stop();
		ps();
	}
	
	public void ps() {
		player.pause = true;
		for(int i = 0; i < F.nof_employee; i++) {
			employee = arrayEmployee.get(i);
			employee.pause_();
		}
		
		btn_shovel.setChecked(false);
	}
	
	public void resume_() {
		state = GAME_RUNNING;
		player.pause = false;
		btn_pause.setChecked(false);

		for(int i = 0; i < F.nof_employee; i++) {
			employee.resume_();
		}
	}
	
	public void presentStopped() {
		batch.begin();
		batch.draw(shadow, 0, 0);
		batch.end();		
		if(renderSetup) {
			setup.render(batch);
		} else {
			batch.begin();
			batch.draw(pause_bg, 56, 95);
			batch.end();
			stage4.draw();
		}
	}

	public void updatepAction() {	
		if(action == ACTION_FLUCK) {
			if(pAction != NONE) {
				batch.begin();
				batch.draw(dlgshovel, 160, 245);
				batch.end();
				stage3.draw();
				if(btn_yes.isChecked()) {
					clicked_(btn_yes);
					updatepAction = false;
					//System.out.println("touchPoint: " + touchPoint.x + " " + touchPoint.y);
					player.setDestination(touchPoint, pAction, p_id, -1, -1);
					Gdx.input.setInputProcessor(multiPlexer);
				}
				if(btn_no.isChecked()) {
					clicked_(btn_no);
					updatepAction = false;
					//System.out.println("touchPoint: " + touchPoint.x + " " + touchPoint.y);
					player.setDestination(touchPoint, NONE, p_id, -1, -1);					
					Gdx.input.setInputProcessor(multiPlexer);
				}
			} else {
				updatepAction = false;
				player.setDestination(touchPoint, NONE, p_id, -1, -1);
				Gdx.input.setInputProcessor(multiPlexer);
			}
		} else {			
			switch(action) {
			case UPGRADE1:
				if(p_id != -1 && p_id < nof_plot)
					if(F.nof_wgod > 0) {
						plot = arrayPlot.get(p_id);
						if(plot.level < 4) {
							F.nof_wgod--;
							plot.upgrade();
							Audio.supgradeP.play(Audio.soundVolume);
							Farm.payment.onToast("Nâng cấp thành công !", 0);
						}
					} else
						action = NONE;
				break;
			case UPGRADE2:
				pUpgrade = new PUpgrade(pidwasUpgrade, arrayPlot.get(pidwasUpgrade), stage);
				renderUpgrade = true;
				action = NONE;
				pause_();
				break;
			default:
				player.setDestination(touchPoint, pAction, p_id, type, kind);
				break;
			}
			updatepAction = false;
		}
	}
	
	public void updateButton() {
		if(Gdx.input.justTouched()) {
			Vector3 vector = new Vector3();	
			camera.unproject(vector.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			for(int i = 0; i < F.nof_table; i++) {
				table = arrayTable.get(i);
				if(table.isContains(vector)) {
					Audio.btnClick.play(Audio.soundVolume);
					pause_();
					t_id = i;
					renderWarehouse = true;
					warehouse = new Warehouse(1, stage);
				}
			}
		}
		
		if(btn_pause.isChecked()) {
			Audio.btnClick.play(Audio.soundVolume);
			stop_();
			btn_shovel.setChecked(false);
			Gdx.input.setInputProcessor(stage4);
		}
		
		if(expansion.isChecked()) {
			expansion.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			
			boolean flag = false;
			if(nof_plot < F.level + 9) flag = true;
			
			if(!flag) {
				Farm.payment.onDialog("Để mở ô đất hiện tại bạn phải đạt level " + (F.level + 1) + " !");
			} else {
				pause_();
				renderExpansion = true;
				expansionGarden = new ExpansionGarden(nof_plot, stage);
			}
		}
		
		if(prev_garden.isChecked()) {
			clicked_(prev_garden);
			
			if(currentGarden > 0) {
				player.action = NONE;
				currentGarden --;				
				updateSignagePos();
				updateUpgradePos();
			}
		}
		
		if(next_garden.isChecked()) {
			clicked_(next_garden);
			
			if(currentGarden < (nof_garden - 1)) {
				player.action = NONE;
				if(currentGarden < 9) {
					currentGarden ++;
				}
				updateSignagePos();
				updateUpgradePos();
			}			
		}
		
		if(action != ACTION_FLUCK) {
			if(btn_shovel.isChecked()) {
				Audio.btnClick.play(Audio.soundVolume);
				action = ACTION_FLUCK;
			}
		} else {
			if(!btn_shovel.isChecked()) {
				Audio.btnClick.play(Audio.soundVolume);
				action = NONE;
				player.action = NONE;
			}
		}
		
		if(btn_shop.isChecked()) {
			btn_shop.setChecked(false);
			Audio.btnClick.play(Audio.soundVolume);
			pause_();
			renderShop = true;
			shop = new Shop(stage);
		}
		
		if(btn_seed.isChecked()) {
			clicked_(btn_seed);
			pause_();
			RType = 0;
			renderRepository = true;
			repository = new Repository(RType, stage);
		}
		
		if(btn_warehouse.isChecked()) {
			clicked_(btn_warehouse);
			pause_();
			renderWarehouse = true;
			warehouse = new Warehouse(0, stage);
		}
		
		if(btn_market.isChecked()) {
			saveData();
			if(Data.getisMusic()) {
				Audio.music2[season].stop();
			}
			farm.setScreen(new Market(farm));
		}
		
		if(btn_mission.isChecked()) {
			clicked_(btn_mission);
			pause_();
			renderMission = true;
			mission = new Mission(stage);
		}
		
		if(btn_employee.isChecked()) {
			clicked_(btn_employee);
			pause_();
			RType = 1;
			renderRepository = true;
			repository = new Repository(RType, stage);
		}
		
		if(btn_gift_.isChecked()) {
			clicked_(btn_gift_);
			Farm.payment.onDialog("Chức năng này hiện đang được phát triển !");
		}
		
		if(recharge.isChecked()) {
			clicked_(recharge);
			Farm.payment.onRequestPayment();
		}
	}
	
	public void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;			
		}		
	}
	
	public void updateRunning(float deltaTime) {
		int len;
		for(int i = 0; i < 9; i++) {
			len = arrayFlower[i].size();
			
			for(int j = 0; j < len; j++){
				flower = arrayFlower[i].get(j);
				flower.update(deltaTime);
			}
			
			len = arrayFruit[i].size();
			for(int j = 0; j < len; j++){
				fruit = arrayFruit[i].get(j);
				fruit.update(deltaTime);
			}
		}
		
		len = arrayExp.size();
		for(int i = 0; i < len; i++) {
			exp = arrayExp.get(i);
			
			if(exp.gc()){
				exp.dispose();
				arrayExp.remove(exp);
				len --;
				exp = null;
				//Gdx.app.log("Message", "EXP size: " + arrayExp.size());
			}
			else exp.update();
		}
		
		//Todo:
		len = arrayWholesaler.size();
		for(int i = 0; i < len; i++) {
			wholesaler = arrayWholesaler.get(i);
			if(wholesaler.gc()) {
				if(wholesaler.mid == 5) {
					Message(2);
				}
				arrayWholesaler.remove(wholesaler);
				len --;
			} else {
				wholesaler.update();
			}
		}
		
		len = arrayRetail.size();
		for(int i = 0; i < len; i++) {
			retailsaler = arrayRetail.get(i);
			retailsaler.update();
		}
		
		len = arrayTable.size();
		for(int i = 0; i < len; i ++) {
			table = arrayTable.get(i);
			table.update();
		}
		
		for(int i = 0; i < PPGarden; i++) {
			if(upgrade[i].isChecked()) {
				clicked_(upgrade[i]);
				pidwasUpgrade = i + currentGarden * 12;
				updateUpgradePos();
				action = UPGRADE2;
				updatepAction();
			}
		}
		
		for(int i = 0; i < F.nof_employee; i++) {
			employee = arrayEmployee.get(i);
			employee.update();
		}
	}
	
	public void updatePaused() {		
		if(renderShop) {
			if(shop.close[0].isChecked()) {
				Audio.btnClick.play(Audio.soundVolume);
				renderShop = false;
				shop.dispose();
				shop = null;
				
				if(F.nof_table < Data.loadnofTable()) {
					table = new Table(2);
					arrayTable.add(table);
					F.nof_table = 3;
				}
				
				if(F.nof_employee < Data.loadnofEmployee()) {
					addEmployee();
					eTime = 7200;
					F.nof_employee = 1;
					employee.updateGarden(currentGarden);
				}
				
				resume_();
				Gdx.input.setInputProcessor(multiPlexer);
			}
		}
		
		if(renderWarehouse) {
			switch(warehouse.state) {
			case 0:
				break;
			case 1:
				if(warehouse.gc()) {
					arrayTable.get(t_id).assignSeed(warehouse.n);
					resume_();
					warehouse.dispose();
					renderWarehouse = false;
					Gdx.input.setInputProcessor(multiPlexer);
				}					
				break;
			}
			
			if(Gdx.input.justTouched()) {
				camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				if(checkOutPanel(touchPoint) && !warehouse.gc()) {
					resume_();
					warehouse.dispose();
					renderWarehouse = false;
					Gdx.input.setInputProcessor(multiPlexer);
				}
			}
		}
		
		if(renderRepository) {
			if(Gdx.input.justTouched()) {
				camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				boolean outPanel = checkOutPanel(touchPoint);
				if(outPanel) {
					resume_();
					repository.dispose();
					renderRepository = false;
					Gdx.input.setInputProcessor(multiPlexer);
				}
			}
		}
		
		if(renderMessage) {
			if(message.gc()) {
				renderMessage = false;
				
				switch(message.type) {
				case 1:
					resetMission();
					int year = (int) Math.ceil((nof_season + 1)/4f);
					Farm.payment.onToast("Năm " + year + " - " + array_season[season], 1);
					break;
				case 3:
					addEmployee();
					F.nof_employee = 1;
					break;
				case 4:
					removeEmployee();
					break;
				}
				
				resume_();
				if(Data.getisMusic()) {
					if(!Audio.music2[season].isPlaying()) {
						Audio.music2[season].play();
					}
				}
				
				message.dispose();
				Gdx.input.setInputProcessor(multiPlexer);
			}
		}
		
		if(renderUpgrade) {
			pUpgrade.update();
			
			if(pUpgrade.gc()) {
				pidwasUpgrade = -1;
				updateUpgradePos();
				renderUpgrade = false;
				resume_();
				Gdx.input.setInputProcessor(multiPlexer);
			}
			
			if(pUpgrade.state() == 0) {
				if(Gdx.input.isTouched()) {
					camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
					pUpgrade.contains(touchPoint.x, touchPoint.y);
				}
			}
		}
		
		if(renderExpansion) {
			int success = expansionGarden.success();
			switch(success) {
			case -1:
				break;
			case 0:
				renderExpansion = false;
				resume_();
				Gdx.input.setInputProcessor(multiPlexer);
				break;
			case 1:
				renderExpansion = false;
				addPlot();
				resume_();
				Gdx.input.setInputProcessor(multiPlexer);
				break;
			}
		}
		
		if(renderMission) {
			if(mission.gc()) {
				mission.dispose();
				resume_();
				renderMission = false;
				Gdx.input.setInputProcessor(multiPlexer);
			}
		}
		
		if(renderGuide) {
			if(guide.gc()) {
				resume_();
				renderGuide = false;
				F.isGuide = false;
				Gdx.input.setInputProcessor(multiPlexer);
			}
		}
	}
	
	public void updateStopped() {
		//Gdx.input.isKeyPressed !!!
		if(resume.isChecked()) {
			resume.setChecked(false);
			clicked_(btn_pause);
			if(Data.getisMusic()) {
				Audio.music2[season].play();
			}
			
			resume_();
			Gdx.input.setInputProcessor(multiPlexer);
		}
		
		if(archivement.isChecked()) {
			clicked_(archivement);
			Message(1);
			btn_pause.setChecked(false);
		}
		
		if(setting.isChecked()) {
			clicked_(setting);
			renderSetup = true;
			setup = new Setting(stage);
		}
		
		if(home.isChecked()) {
			clicked_(home);
			Assets.manager.clear();
			saveData();
			Farm.payment.Tracker(1, 0, "");
			Farm.payment.postHighScore(experience);
			farm.setScreen(new MenuScreen(farm));
		}
		
		if(renderSetup) {
			if(setup.gc()) {
				renderSetup = false;
				clicked_(btn_pause);				
				if(Data.getisMusic()) {
					Audio.music2[season].play();
				}
				resume_();
				Gdx.input.setInputProcessor(multiPlexer);
			}
		}
	}	
	
	public void clicked_(Button btn) {
		btn.setChecked(false);
		Audio.btnClick.play(Audio.soundVolume);
	}

	
	public boolean checkOutPanel(Vector3 touchPoint) {
		if(bound.contains(touchPoint.x, touchPoint.y))
			return false;
		return true;
	}
	
	/*
	 * Flag = true: Ruong da duoc trong
	 * Flag = false: Ruong chua duoc trong
	 */
	public boolean get_plotFlag(int id) {
		if(id < arrayPlot.size()) {
			plot = arrayPlot.get(id);
			return plot.flag;
		}
		return false;
	}
	
	public int get_plotState(int id) {
		plot = arrayPlot.get(id);
		return plot.seed.state();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean flag, inside = true;
		int fstate;
		pAction = NONE;
		//variable: oneTime Hành động thực hiện một lần
		//Gdx.app.log("Message", "touchDown !!!");
		
		if(state == GAME_RUNNING) {
			camera.unproject(touchPoint.set(screenX, screenY, 0));
			if(currentGarden == (nof_garden - 1) && 
					signageBound.contains(touchPoint.x, touchPoint.y)) {
				inside = false;
			}
			
			if(touchPoint.x < basePos1.x || touchPoint.x > basePos2.x || touchPoint.y > basePos1.y || touchPoint.y < basePos2.y)
				inside = false;
			
			if(oneTime) {
				oneTime = false;
				action = pAction = NONE;
			}
			if(inside) {
				p_id = calculatePos(touchPoint.x, touchPoint.y);
				
				if(!isPopupUpgrade(p_id, touchPoint.x, touchPoint.y)) {
				if(p_id != -1) {
					p_id += currentGarden * 12;
					
					if(p_id < nof_plot) {
						flag = get_plotFlag(p_id);
						
						if(flag && action != ACTION_FLUCK && action != UPGRADE1) {
						//if(flag && action != ACTION_FLUCK) {
							fstate = get_plotState(p_id);
							switch(fstate) {
							case 0:
								action = NONE;
								break;
							case 1:
								pidwasOccupied = p_id;
								action = ACTION_WATERING;
								break;
							case 2:
								pidwasOccupied = p_id;
								action = ACTION_HARVEST;
								break;
							}					
							pAction = action;
							oneTime = true;
						}
						
						//if(!flag && action == NONE) {
						/*if(action == NONE) {
							if(isPopupUpgrade(p_id, touchPoint.x, touchPoint.y))
								action = UPGRADE2;
						}*/
						
						if(pAction == NONE)
						switch(action) {
							case NONE:
								///for(int i = 0; i < 12; i++) {
									//plot = arrayPlot.get(i);
									//System.out.println("index: " + i + " flag: " + plot.flag);
								//}
								pidwasOccupied = -1;
								break;
							case ACTION_PLANT:
								pidwasOccupied = p_id;
								flag = get_plotFlag(p_id);					
								if(flag)	pAction = NONE;
								else	pAction = ACTION_PLANT;
								
								if(type == FLOWER){
									int amount = Data.loadFlRepository(kind);
									if(amount <= 0)
										pAction = NONE;
								} else {
									int amount = Data.loadFrRepository(kind);
									if(amount <= 0)
										pAction = NONE;
								}
								break;
							case ACTION_FLUCK:
								pidwasOccupied = p_id;
								//System.out.println("FLUCK pidwasOccupied: " + pidwasOccupied);
								Gdx.input.setInputProcessor(stage3);
								flag = get_plotFlag(p_id);
									
								if(!flag)	pAction = NONE;
								else   pAction = ACTION_FLUCK;
								break;
							case ACTION_WATERING:
								pidwasOccupied = p_id;
								pAction = action;
								break;
							case ACTION_HARVEST:
								pidwasOccupied = p_id;
								//System.out.println("Action pidwas: " + p_id);
								pAction = action;
								break;
							case UPGRADE1:
								//System.out.println("Upgrade1 !!!!!");
								break;
							case UPGRADE2:
								oneTime = true;
								//System.out.println("Upgrade id: " + p_id);
								break;
							}
					} else
						pAction = NONE;
				} else
					pAction = NONE;
				
					updatepAction = true;
				}		
				//System.out.println("Action: " + action);
			} else action = NONE;			
		}
		
		return false;
	}
	
	public int calculatePos(float screenX, float screenY) {
		
		int xCell, yCell;
		screenX -= basePos1.x;
		screenY = basePos1.y - screenY;
		
		xCell = (int) (screenX/PLOT_WIDTH);
		yCell = (int) (screenY/PLOT_HEIGHT);
		
		Rectangle bound = new Rectangle(xCell * PLOT_WIDTH + hmargin, yCell * PLOT_HEIGHT + vmargin, 170, 130);
		if(bound.contains(screenX, screenY))
			return yCell * 4 + xCell;		
		return -1;
	}
	
	public boolean isPopupUpgrade(int id, float screenX, float screenY) {
		int xCell, yCell;
		screenX -= basePos1.x;
		screenY = basePos1.y - screenY;
		
		xCell = (int) (screenX/PLOT_WIDTH);
		yCell = (int) (screenY/PLOT_HEIGHT);
		Rectangle bound = new Rectangle(xCell * PLOT_WIDTH + hmargin + 85, (yCell + 0.5f) * PLOT_HEIGHT + vmargin - 10, 85, 65);
		
		if(bound.contains(screenX, screenY)) {
			return true;
		}
		return false;
	}
	
	//Ham thuc hien chuc nang reset lai nhiem vu
	public void resetMission() {		
		for(int i = 0; i < 12; i++) {
			Data.savesMarket(i, 0);
			Data.saveMarket(i, "none");
		}
		
		int len = arrayWholesaler.size();
		for(int i = 0; i < len; i++) {
			arrayWholesaler.remove(0);			
		}
		
		len = arrayRetail.size();
		for(int i = 0; i < len; i++) {
			retailsaler = arrayRetail.get(i);
			retailsaler.back();
		}
		
		if(arrayTable.size() > 2) {
			arrayTable.remove(2);
			F.nof_table = 2;
			Data.savenofTable(2);
		}
		
		season_time = 0;
		initialMarket(5);
	}
	
	public void Message(int type) {
		switch(type) {
		case 0:
			F.level ++;
			beginExp = F.get_lvexperience(F.level - 1);
			endExp = F.get_lvexperience(F.level);
			message = new Message(type, F.level, experience, stage);
			break;
		case 1:
			message = new Message(type, F.level, nof_season, stage);
			break;
		case 2:
			message = new Message(type, 0, 0, stage);
			Audio.ssuccess.play(Audio.soundVolume);
			break;
		case 3:
			message = new Message(type, 0, 0, stage);
			break;
		case 4:
			message = new Message(type, 0, 0, stage);
			break;
		}
		
		renderMessage = true;
		pause_();
		Audio.suplv.play(Audio.soundVolume);
	}	
	
	public void addPlot() {
		nof_plot ++;
		plot = new Plot(nof_plot - 1, 0);
		arrayPlot.add(plot);
		Audio.sexpandP.play(Audio.soundVolume);
		if((nof_plot + 1) > (currentGarden + 1) * 12) {
			if(currentGarden < 9) {
				currentGarden ++;
				nof_garden ++;
			}
			player.action = NONE;
		}
		
		updateSignagePos();
		updateUpgradePos();
	}
	
	//id cua thua ruong bi player chiem giu
	public int pidwasOccupied() {
		return pidwasOccupied;
	}
	
	//Todo:
	public void addEmployee() {
		employee = new Employee(this, 102, 300);
		arrayEmployee.add(employee);
		group.addActor(btn_employee);
	}
	
	public void removeEmployee() {
		eTime = 0;
		if(F.nof_employee > 0) {
			Data.savenofEmployee(0);
			arrayEmployee.remove(0);
		}
		
		F.nof_employee = 0;
		group.removeActor(btn_employee);
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {

		return false;
	}
	
	//Reset nhiem vu kho luong thuc
	public int initialMarket(int id) {
		int array[], require[][];
		int count, lvUnlock, growth_time;
		int[] coefficient, n;
		boolean lock;
		int maxComodity = 3, como;
		
		require = new int[3][3];
		array = new int[24];
		
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
		
		coefficient = reverseArray();
		if(count < maxComodity) {
			for(int i = 0; i < count; i++) {
				require[i][0] = array[i];
				if(array[i] < 12)
					growth_time = SeedNature.getflgrowthTime(array[i]);
				else
					growth_time = SeedNature.getfrgrowthTime(array[i] - 12);
				require[i][1] = (int) (coefficient[i] * (120f / growth_time) * nof_plot * Math.ceil(F.level/20f));
			}
			
			como = count;
		} else {
			n = getP(count);
			for(int i = 0; i < maxComodity; i++) {
				require[i][0] = array[n[i]];
				if(array[n[i]] < 12) {
					growth_time = SeedNature.getflgrowthTime(array[n[i]]);
				}
				else {
					growth_time = SeedNature.getfrgrowthTime(array[n[i]] - 12);
				}
				require[i][1] = (int) (coefficient[i] * (120f / growth_time) * nof_plot * Math.ceil(F.level/20f));
			}
			
			como = maxComodity;
		}
		
		String s = "";
		for(int j = 0; j < como; j++) {
			if(j == como - 1)
				s += require[j][0] + ":" + require[j][1] + ":0";
			else
				s += require[j][0] + ":" + require[j][1] + ":0" + ":";
		}
		
		Data.saveMarket(id, s);
		Data.savesMarket(id, 1);		
		return 0;
	}
		
	public int[] reverseArray() {
		int reverse[] = {};
		int n = random.nextInt(6);
		
		switch(n) {
		case 0: 	reverse = new int[] {2, 3, 4};
			break;
		case 1:		reverse = new int[] {2, 4, 3};
			break;
		case 2:		reverse = new int[] {3, 2, 4};
			break;
		case 3:		reverse = new int[] {3, 4, 2};
			break;
		case 4:		reverse = new int[] {4, 3, 2};
			break;
		case 5:		reverse = new int[] {4, 2, 3};
			break;
		}
			
		return reverse;
	}
	
	public int[] getP(int count) {
		int a, n[] = new int[3];
		
		if(count < 6) {
			n[0] = random.nextInt(count);
		} else {		
			n[0] = (count - 6) + random.nextInt(6);
		}
		
		if(count < 3)
			for(int i = 0; i < count; i++)
				n[i] = i;
		else
		for(int i = 1; i < 3; i++) {
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
}