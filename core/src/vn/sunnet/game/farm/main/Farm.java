package vn.sunnet.game.farm.main;

import vn.sunnet.game.farm.assets.Assets;
import vn.sunnet.game.farm.assets.Audio;
import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.screen.MenuScreen;
import vn.sunnet.game.farm.screen.Payment;
import vn.sunnet.game.farm.screen.Payment.HighscoreSendType;
import vn.sunnet.game.farm.screen.Payment.OnHighScoreListener;
import vn.sunnet.game.farm.screen.Payment.OnPaymentListener;
import vn.sunnet.game.farm.screen.Payment.PaymentType;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Farm extends Game {

	public static Payment payment;
	public static boolean buyGame = false;
	
	public Farm(Payment mPayment){
		this.payment = mPayment;
	}

	@Override
	public void create() {
		F.LoadText();
        Assets.load();
//		payment.setHighScoreListener(new OnHighScoreListener() {
//
//			public boolean onValidBeforeSendScore(HighscoreSendType type) {
//
//				if (type==HighscoreSendType.TYPE_NETWORK) {
//					if (Data.getMoney() < 10000) {
//						payment.onRequestPayment();
//						return false;
//					}
//				}
//
//				return true;
//			}
//
//			public void onSendScoreSuccess(HighscoreSendType type) {
//
//				switch (type) {
//				case TYPE_NETWORK:
//					Data.addMoney(-10000);
//					F.money -= 10000;
//					break;
//				case TYPE_SMS:
//					break;
//				case WAP_CHARGING:
//					break;
//				}
//				payment.onToast("Chúc mừng bạn đã gửi điểm cao thành công.", 0);
//			}
//
//			public void onSendScoreFailure() {
//				payment.onToast("Gửi điểm thất bại.", 0);
//			}
//
//			public void onSendScoreDeny() {
//
//			}
//
//			public void onLogoutSuccess() {
//
//			}
//		});
//
//		payment.setPaymentListener(new OnPaymentListener() {
//
//			public void onPaymentSuccess(PaymentType type, int value) {
//				int addCoins = value * 2;
//
//				if (type != PaymentType.PAYMENT_TYPE_SMS && type != PaymentType.PAYMENT_WAP_CHARGING)
//					if (value < 20000)
//						addCoins += addCoins * 5 / 100;
//					else if (value < 50000)
//						addCoins += addCoins * 10 / 100;
//					else if (value < 100000)
//						addCoins += addCoins * 15 / 100;
//					else if (value < 200000)
//						addCoins += addCoins * 20 / 100;
//					else if (value < 500000)
//						addCoins += addCoins * 25 / 100;
//					else
//						addCoins += addCoins * 30 / 100;
//
//				if(buyGame) {
//					buyGame = false;
//					if(addCoins >= 30000) {
//						addCoins -= 30000;
//						payment.onToast("Chúc mừng bạn đã mua game thành công !", 1);
//					} else {
//						payment.onToast("Bạn không đủ xu để mua game !", 1);
//					}
//				}
//
//				Data.addMoney(addCoins);
//				F.money += addCoins;
//
//				payment.Tracker_(addCoins, type);
//				payment.onToast("Chúc mừng bạn. Đã nạp thành công "+ addCoins, 0);
//			}
//
//			public void onPaymentFailure(PaymentType type, int value) {
//				payment.onToast("Nạp xu thất bại! Xin kiểm tra lại.", 0);
//			}
//
//			public void onPaymentDeny(PaymentType type, int value) {
//
//			}
//		});
//
		Audio.load();
		setScreen(new MenuScreen(this));
		Gdx.input.setCatchBackKey(true);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		getScreen().dispose();
	}
}