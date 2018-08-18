package vn.sunnet.game.farm.screen;
import com.badlogic.gdx.Preferences;

/** A simple class to handle some callback from User Activity
 * 
 * @FileName: IActivityHandler.java
 * @CreateOn: Sep 15, 2012 - 11:14:22 AM
 * @Author: */
public interface Payment {

	/************************************************** Android special functional **************************************************/

	/** Create default android toast
	 * 
	 * @param text
	 * @param duration */
	public void onToast (String text, int duration);

	/************************************************** functional method **************************************************/

	/** Request for Keys.BACK press*/
	public void onMCDialog();
	public void onMXDialog();
	public void onDialog(String text);
	/** Request for share */
	public void onShare ();

	/** request for download */
	public void onDownload ();
	
	public void onInfo();
	
	public void onExit();
	
	public void onQuit();
	
	public void hideAdview();
	
	public void showAdview();

	/************************************************** functional method **************************************************/

	/** Request for payment dialog */
	public void onRequestPayment ();

	/** Set payment listener
	 * 
	 * @param onPaymentListener {@link OnPaymentListener} */
	public void setPaymentListener (OnPaymentListener onPaymentListener);

	/** Set Highscore listener
	 * 
	 * @param listener {@link OnHighScoreLisener}
	 */
	public void setHighScoreListener (OnHighScoreListener listener);
	
	/** Load the special preferences of your company
	 * 
	 * @param package_dir
	 * @param name */
	public Preferences onLoadPreferences (String package_dir, String name);

	/** The same as onLoadPreferences (String package_dir, String name) but
	 * <p>
	 * your package dir and pref name will be declared in android activity */
	public Preferences onLoadPreferences ();

	/** send high score online
	 * @param score */
	public void postHighScore (long score);

	/**************************************************
	 * 
	 **************************************************/

	/** This interface to handle payment event from activity
	 * 
	 * @FileName: IActivityHandler.java
	 * @CreateOn: Sep 16, 2012 - 3:42:14 PM
	 * @Author: */
	public static interface OnPaymentListener {
		/** @param type {@link PaymentType}
		 * @param value the value of purchase */
		public void onPaymentSuccess (PaymentType type, int value);

		/** @param type type {@link PaymentType}
		 * @param value the value of purchase */
		public void onPaymentFailure (PaymentType type, int value);

		/** @param type type {@link PaymentType}
		 * @param value the value of purchase */
		public void onPaymentDeny (PaymentType type, int value);
	}
	
	public static interface OnHighScoreListener {
		/**
		 * Sự kiện người dùng đăng xuất
		 */
		public void onLogoutSuccess();

		/**
		 * Gửi điểm không thành công
		 */
		public void onSendScoreFailure();
		/**
		 * Gửi điểm thành công
		 */
		public void onSendScoreSuccess(HighscoreSendType type);
		/**
		 * Người chơi từ chối gửi điểm
		 */
		public void onSendScoreDeny();
		/**
		 * Check thông tin trước khi điểm cao (nếu sử dụng HTTP)
		 */
		public boolean onValidBeforeSendScore(HighscoreSendType type);
	}
	
	/** @FileName: IActivityHandler.java
	 * @CreateOn: 7/12/2012
	 * @Author: */
	public static enum PaymentType {
		// tin nhan
		PAYMENT_TYPE_SMS,
		// the cao
		PAYMENT_TYPE_SCRATCH,
		// coupon
		PAYMENT_TYPE_COUPON,
		//wap charing
		PAYMENT_WAP_CHARGING
	}
	
	public static enum HighscoreSendType{
		//mang
		TYPE_NETWORK,
		//tin nhan
		TYPE_SMS,
		//Wap Charging
		WAP_CHARGING
		
	}

	void showHighScoreView();
	void Tracker(int i, int j, String string);
	void Tracker_(int amount, PaymentType type);
}