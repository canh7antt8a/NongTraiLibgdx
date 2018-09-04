package com.leaptechjsc.game.happyfarm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Data {
	
	public static String name = "Farm";
	public static int defcoins = 9000, defMoney = 0, def_level = 1, defnofPlot = 9,
			defnofMarket = 0, defnofTable = 2, defSeason, defnofSeason = 0;
	public static boolean UN_LOCK = true, LOCK = false;
	public static int experience, level;
	public static float seasonTime;
	public static String defVal = "none", defPos = "717:150";
	
	public static final Preferences pref = Gdx.app.getPreferences("vn.sunnet.game.farm." + name);//Farm.payment.onLoadPreferences("vn.sunnet.game.farm", name);
	public static final Preferences prefs = Gdx.app.getPreferences("name");
	
	// Luu lai gia tri va ma hoa
	public static int getMoney() {
		return pref.getInteger("myMoney", defMoney);
	}
	
	public static void addMoney(int addMoney) {
		int money = getMoney() + addMoney;
		pref.putInteger("myMoney", money);
	}
	
	public static void saveMoney(int xu) {
		pref.putInteger("myMoney", xu);
		pref.flush();
	}
	
	public static int getCoins() {
		return pref.getInteger("coins", defcoins);
	}
	
	public static void saveCoins(int coins) {
		pref.putInteger("coins", coins);
		pref.flush();
	}
	
	public static int getLevel() {
		return pref.getInteger("level", def_level);
	}
	
	public static void saveLevel(int value) {
		pref.putInteger("level", level + value);
		pref.flush();
	}
	
	public static long getExperience() {
		return pref.getLong("experience", 0);
	}
	
	public static void saveExperience(long experience) {
		pref.putLong("experience", experience);
		pref.flush();
	}
	
	public static boolean showAdview() {
		return pref.getBoolean("isShowAd", true);
	}
	
	public static void hideAdview() {
		pref.putBoolean("isShowAd", false);
		pref.flush();
	}
	
	public static int getnofPlot() {
		return pref.getInteger("nofPlot", defnofPlot);
	}
	
	public static void savenofPlot(int nof_plot) {
		pref.putInteger("nofPlot", nof_plot);
		pref.flush();
	}
	
	
	//Phan con lai luu nhung khong ma hoa
	public static boolean isGuideline() {
		return prefs.getBoolean("guideline", true);
	}
	
	public static void setGuideline(boolean val) {
		prefs.putBoolean("guideline", val);
		prefs.flush();
	}
	
	public static void saveEmployee(int val) {
		prefs.putInteger("employee", val);
	}
	
	public static int loadEmployee() {
		return prefs.getInteger("employee", -1);
	}
	
	//isPresent: = 0 Tang; = 1 Tang roi; = -1: Khong check nua
	public static int gifts() {
		return prefs.getInteger("present", 0);
	}
	
	public static void lockgifts(int state) {
		prefs.putInteger("present", state);
		prefs.flush();
	}		
	
	public static void savePPos(int x, int y) {
		prefs.putString("pPos", x + ":" + y);
		prefs.flush();
	}
	
	public static void saveSeason(int season) {
		prefs.putInteger("season", season);
		prefs.flush();
	}
	
	public static int loadSeason() {
		return prefs.getInteger("season", defSeason);
	}
	
	public static String loadPPos() {
		return prefs.getString("pPos", defPos);
	}
	
	public static void setisMusic(boolean isMusic) {
		prefs.putBoolean("music", isMusic);
		prefs.flush();
	}
	
	public static float getSeansonTime() {
		return prefs.getFloat("seasonTime", 0);
	}
	
	public static void saveSeasonTime(float value) {
		prefs.putFloat("seasonTime", value);
		prefs.flush();
	}
	
	public static float getETime() {
		return prefs.getFloat("eTime", 0);
	}
	
	public static void saveETime(float eTime) {
		prefs.putFloat("eTime", eTime);
		prefs.flush();
	}
	
	public static boolean getisMusic() {
		return prefs.getBoolean("music", true);
	}	
	
	public static void setisSound(boolean isSound) {
		prefs.putBoolean("sound", isSound);
		prefs.flush();
	}
	
	public static boolean getisSound() {
		return prefs.getBoolean("sound", true);
	}
	
	public static int loadnofTable() {
		return prefs.getInteger("noftable", defnofTable);
	}
	
	public static void savenofTable(int number) {
		prefs.putInteger("noftable", number);
		prefs.flush();
	}
	
	public static void savenofSeason(int nof_season) {
		prefs.putInteger("nofSeason", nof_season);
		prefs.flush();
	}
	
	public static int getnofSeason() {
		return prefs.getInteger("nofSeason", defnofSeason);
	}
	
	public static int loadnofEmployee() {
		int n = prefs.getInteger("nofEmployee", 0);
		n = (n > 1) ? 1 : n;
		return n;
	}
	
	public static void savenofEmployee(int value) {
		prefs.putInteger("nofEmployee", value);
		prefs.flush();
	}
	
	public static void saveStatePlot(int id, String value) {
		prefs.putString("p" + Integer.toString(id), value);
		prefs.flush();
	}
	
	public static String getStatePlot(int id) {
		return prefs.getString("p" + Integer.toString(id), defVal);
	}
	
	public static void saveStateSeed(int id, String value) {
		prefs.putString("s" + Integer.toString(id), value);
		prefs.flush();
	}
	
	public static String getStateSeed(int id) {
		return prefs.getString("s" + Integer.toString(id), defVal);
	}
	
	public static void addFlRepository(int id, int amount) {
		int n = loadFlRepository(id) + amount;
		prefs.putInteger("flr" + id, n);
		prefs.flush();
	}	
	
	public static int loadFlRepository(int id) {
		return prefs.getInteger("flr" + id, 0);
	}
	
	public static void addFrRepository(int id, int amount) {
		int n = loadFrRepository(id) + amount;
		prefs.putInteger("frr" + id, n);
		prefs.flush();
	}
	
	public static int loadFrRepository(int id) {
		return prefs.getInteger("frr" + id, 0);
	}
	
	public static void addFlWavehouse(int id, int amount) {
		amount += loadFlWavehouse(id);
		prefs.putInteger("flw" + id, amount);
		prefs.flush();
	}
	
	public static int loadFlWavehouse(int id) {
		return prefs.getInteger("flw" + id, 0);
	}
	
	public static void addFrWavehouse(int id, int amount) {
		amount += loadFrWavehouse(id);
		prefs.putInteger("frw" + id, amount);
		prefs.flush();
	}
	
	public static int loadFrWavehouse(int id) {
		return prefs.getInteger("frw" + id, 0);
	}
	
	public static boolean loadFlState(int id) {
		if(id == 0)
			return UN_LOCK;
		return prefs.getBoolean("flstate" + id, false);
	}
	
	public static void saveFlState(int id, boolean state) {
		prefs.putBoolean("flstate" + id, false);
		prefs.flush();
	}
	
	public static boolean loadFrState(int id) {
		if(id == 0)
			return UN_LOCK;
		return prefs.getBoolean("frstate" + id, false);
	}
	
	public static void saveFrState(int id, boolean state) {
		prefs.putBoolean("frstate" + id, false);
		prefs.flush();
	}
	
	//require{type:amount}	
	//state = 0 -> Chua mo; = 1: dang giao dich; = 2: dung giao dich; = 3: Hoan thanh giao dich
	public static int loadsMarket(int id) {
		return prefs.getInteger("smarket" + id, 0);
	}
	
	public static void savesMarket(int id, int val) {
		prefs.putInteger("smarket" + id, val);
		prefs.flush();
	}
	
	public static String loadMarket(int id) {
		return prefs.getString("mrequire" + id, defVal);
	}
	
	public static void saveMarket(int id, String require) {
		prefs.putString("mrequire" + id, require);
		prefs.flush();
	}
	
	public static int loadnofwGod() {
		return prefs.getInteger("nofwGod", 1);
	}
	
	public static void savenofwgod(int wgod) {
		prefs.putInteger("nofwGod", wgod);
		prefs.flush();
	}	
	
	public static int loadnofEPaper() {
		return prefs.getInteger("epaper", 0);
	}
	
	public static void addEPaper(int amount) {
		amount += loadnofEPaper();
		prefs.putInteger("epaper", amount);
		prefs.flush();
	}
	
	public static int loadnofLRock() {
		return prefs.getInteger("lrock", 0);
	}
	
	public static void addLRock(int amount) {
		amount += loadnofLRock();
		prefs.putInteger("lrock", amount);
		prefs.flush();
	}
	
	public static int loadTable(int id) {
		return prefs.getInteger("table" + id, -1);
	}
	
	public static void saveTable(int id, int n) {
		prefs.putInteger("table" + id, n);
		prefs.flush();
	}
}	