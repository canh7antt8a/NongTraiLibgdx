package vn.sunnet.game.farm.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {	
	public static float musicVolume = 0.4f, soundVolume = 1.0f;
	public static Music music1, music2[], smarket;
	public static Sound btnClick, splant, sharvest, swatering;
	public static Sound swinner, sgameover, ssuccess, suplv;
	public static Sound sbuy, sunlockM, supgradeP, sexpandP;
	
	public static void load() {
		music1 = Gdx.audio.newMusic(Gdx.files.internal("audio/M_Menu.mp3"));
		music1.setLooping(true);
		music1.setVolume(musicVolume);
		music2 = new Music[4];
		for(int i = 0; i < 4; i++) {
			music2[i] = Gdx.audio.newMusic(Gdx.files.internal("audio/M_Soundtrack 0" + (i + 1) + ".mp3"));
			music2[i].setLooping(true);
			music2[i].setVolume(musicVolume);
		}
		
		smarket = Gdx.audio.newMusic(Gdx.files.internal("audio/S_Am thanh cho.mp3"));
		smarket.setLooping(true);
		
		sbuy = Gdx.audio.newSound(Gdx.files.internal("audio/S_Ban vat pham.mp3"));
		btnClick = Gdx.audio.newSound(Gdx.files.internal("audio/S_Button.mp3"));
		splant = Gdx.audio.newSound(Gdx.files.internal("audio/S_Gieo hat.mp3"));
		sharvest = Gdx.audio.newSound(Gdx.files.internal("audio/S_Thu hoach cay.mp3"));
		swatering = Gdx.audio.newSound(Gdx.files.internal("audio/S_Tuoi nuoc.mp3"));
		swinner = Gdx.audio.newSound(Gdx.files.internal("audio/S1_Winner.mp3"));
		sgameover = Gdx.audio.newSound(Gdx.files.internal("audio/S2_Game over.mp3"));
		sunlockM = Gdx.audio.newSound(Gdx.files.internal("audio/S_Unlock mot cho.mp3"));
		supgradeP = Gdx.audio.newSound(Gdx.files.internal("audio/S_Nang cap thanh cong o dat.mp3"));
		
		sexpandP = Gdx.audio.newSound(Gdx.files.internal("audio/S_Mo rong dat.mp3"));
		ssuccess = Gdx.audio.newSound(Gdx.files.internal("audio/S_Hoan thanh nhiem vu.mp3"));
		suplv = Gdx.audio.newSound(Gdx.files.internal("audio/S_Len level.mp3"));
	}
}