package com.leaptechjsc.game.happyfarm.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/** @FileName: IDesktopHandler.java
 * @CreateOn: 6-12-2012
 * @Author:  */
public class DestopPayment implements Payment {
	OnPaymentListener paymentlistener ;
	OnHighScoreListener highscorelistener;
	
	@Override
	public void onShare () {
		System.out.println("On Share !!!!");
	}

	@Override
	public void onDownload () {
		System.out.println("On Download !!!!");
	}

	@Override
	public void onRequestPayment () {
		if(paymentlistener != null )
			paymentlistener.onPaymentSuccess(PaymentType.PAYMENT_TYPE_COUPON, 15000);
		System.out.println("On Request Payment !!!!");
	}

	@Override
	public void setPaymentListener (OnPaymentListener listener) {
		this.paymentlistener = listener;
		System.out.println("On Payment Listener!");
	}

	@Override
	public void setHighScoreListener(OnHighScoreListener listener) {
		this.highscorelistener = listener;
		System.out.println("On Highscore Listener!");
		
	}

	@Override
	public Preferences onLoadPreferences (String package_dir, String name) {
		return Gdx.app.getPreferences(package_dir + "." + name);
	}

	@Override
	public Preferences onLoadPreferences () {
		return Gdx.app.getPreferences("GamePreferences");
	}

	@Override
	public void onToast (String text, int duration) {
		System.out.println("onToast: " + text + (duration == 1 ? "LONG_DURATION" : "SHORT_DURATION"));
	}

	@Override
	public void postHighScore(long score) {
		if (highscorelistener!=null){
			System.out.println("Post HighScore success :"+score);
		}
	}

	@Override
	public void onInfo() {
			System.out.println("Info game!");
	}

	@Override
	public void onExit() {
		Gdx.app.exit();
	}

	@Override
	public void hideAdview() {
		System.out.println("Hide Adview");
	}

	@Override
	public void showAdview() {
		System.out.println("Show Adview");
	}

	@Override
	public void loadRewardedVideoAd() {
		System.out.println("loadRewardedVideoAd");
	}

	@Override
	public void onMCDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMXDialog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialog(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showHighScoreView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Tracker(int i, int j, String k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Tracker_(int amount, PaymentType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onQuit() {
		// TODO Auto-generated method stub
		
	}
}