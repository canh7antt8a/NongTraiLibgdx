package com.leaptechjsc.game.happyfarm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.leaptechjsc.game.happyfarm.assets.Data;
import com.leaptechjsc.game.happyfarm.assets.Language;
import com.leaptechjsc.game.happyfarm.main.Farm;
import com.leaptechjsc.game.happyfarm.nature.F;
import com.leaptechjsc.game.happyfarm.screen.MenuScreen;
import com.leaptechjsc.game.happyfarm.screen.Payment;

import java.util.Locale;

public class AndroidPayment extends AndroidApplication implements Payment, Payment.OnPaymentListener {
    public static final int EXIT_DIALOG = 0;
    public static final int ABOUT_DIALOG = 1;
    public static final int SHARE_DIALOG = 2;
    public static final int DOWNLOAD_DIALOG = 3;
    public static final int REQUESTPAYMENT = 4;
    public static final int HIGHSCORE = 5;
    public static final int SEND_SCORE_SUCESS_NEWORK = 6;
    public static final int ADVIEW_SHOW = 7;
    public static final int ADVIEW_HIDE = 8;
    public static final int SOCIAL_SHOW = 9;
    public static final int SOCIAL_HIDE = 10;
    public static final int MCDIALOG = 11;
    public static final int MXDIALOG = 12;
    public static final int QUIT_DIALOG = 13;

    public static final int DEFAULT_COINS_ITEM_POSTSCORE = 10000;

    @SuppressLint("HandlerLeak")
    private Handler mHandle = new Handler()
    {
        @Override
        public void handleMessage (Message msg)
        {
            switch (msg.what) {
                case DOWNLOAD_DIALOG:
                    showDialog(DOWNLOAD_DIALOG);
                    break;
                case ABOUT_DIALOG:
                    showDialog(ABOUT_DIALOG);
                    break;
                case SHARE_DIALOG:
                    showDialog(SHARE_DIALOG);
                    break;
                case EXIT_DIALOG:
                    showDialog(EXIT_DIALOG);
                    break;
                case HIGHSCORE:
//                    // gọi hàm post điểm
//                    core.user.highscore.sendScoreRawQuestionHandler(formatter.format(DEFAULT_COINS_ITEM_POSTSCORE) + " xu!");
                    break;
                case REQUESTPAYMENT:
//                    // Nếu cần thêm thông báo hiện lên ở đầu thông điệp hiển thị thì gọi hàm này
//                    //core.payment.setPreDescription("Bạn không đủ xu để thực hiện giao dịch. Hãy nạp thêm xu vào!");
//                    // Mã tin nhắn cấp 1 và cấp 2, được sử dụng khi trong manifest không có khai báo 2 tham số này
//                    core.payment.setPaymentContent(DEFAULT_MESSAGE_CODE,DEFAULT_MESSAGE_CODE_LEVEL_2);
//                    // Hiển thị
//                    core.payment.showDefaultPayment();
                    break;
                case SEND_SCORE_SUCESS_NEWORK:
//                    try {
//                        core.user.highscore.openScoreActivity();
//                    } catch (Exception e) {
//                    }
                    break;
                case ADVIEW_SHOW:
//                    adview.loadAd();
                    break;
                case ADVIEW_HIDE:
//                    adview.hide();
                    break;
                case SOCIAL_SHOW:
////				DynamicTabLayout.show();
////				gamehot.setVisibility(View.VISIBLE);
//////				webview.setVisibility(View.VISIBLE);
                    break;
                case SOCIAL_HIDE:
////				DynamicTabLayout.hide();
////				gamehot.setVisibility(View.GONE);
//////				webview.setVisibility(View.GONE);
                    break;
                case MCDIALOG:
                    showDialog(MCDIALOG);
                    break;
                case MXDIALOG:
                    showDialog(MXDIALOG);
                    break;
                case QUIT_DIALOG:
                    showDialog(QUIT_DIALOG);
                    break;
            }
        }
    };

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case EXIT_DIALOG:
                AlertDialog.Builder alertboxExit = new AlertDialog.Builder(this);
                alertboxExit.setTitle(Language.General.Accept.getStr());
                alertboxExit.setMessage(Language.General.BUY_GAME.getStr());
                alertboxExit.setNeutralButton(Language.General.YES.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                if(F.money >= 30000) {
                                    F.money -= 30000;
                                    onToast(Language.General.BUY_GAME_OFF_AD.getStr(), 1);
                                    Data.hideAdview();
                                    MenuScreen.isShowAd = false;
                                    onQuit();
                                } else {
                                    onRequestPayment();
                                    onToast(Language.General.KHONG_DU_XU_MUA_.getStr(), 1);
                                }

                                Farm.buyGame = true;
                            }
                        });

                alertboxExit.setNegativeButton(Language.General.NO.getStr(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        onQuit();
                    }

                });
                return alertboxExit.create();

            case ABOUT_DIALOG:
                AlertDialog.Builder alertboxInfo = new AlertDialog.Builder(this);
                alertboxInfo.setTitle(getResources()
                        .getString(R.string.about_title));
                alertboxInfo.setMessage(getResources().getString(
                        R.string.about_message));
                alertboxInfo.setNeutralButton(
                        Language.General.GAME_OTHER.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
//                                core.param.openDownloadLink(AndroidPayment.this);
                            }
                        });
                alertboxInfo.setNegativeButton(
                        Language.General.CLOSE.getStr(), null);

                return alertboxInfo.create();
            case SHARE_DIALOG:
                AlertDialog.Builder alertboxShare = new AlertDialog.Builder(this);
                alertboxShare.setTitle(Language.General.SHARE_GAME.getStr());
                alertboxShare.setMessage(Language.General.SHARE_GAME_USER.getStr());
                alertboxShare.setCancelable(false);
                alertboxShare.setPositiveButton(Language.General.YES.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                core.param.openShareDialog(	getString(R.string.title_share),
//                                        getString(R.string.message_share_subject), getString(R.string.message_share_content));

                            }
                        }).setNegativeButton(Language.General.NO.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return alertboxShare.create();
            case DOWNLOAD_DIALOG:
                AlertDialog.Builder alertboxDowload = new AlertDialog.Builder(this);
                alertboxDowload.setTitle(Language.General.DOWNLOAD_HOT_GAME.getStr());
                alertboxDowload.setMessage(Language.General.WANT_DOWNLOAD_HOT_GAME.getStr());
                alertboxDowload.setCancelable(false);
                alertboxDowload.setPositiveButton(Language.General.YES.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                core.param.openDownloadLink(AndroidPayment.this);
                            }
                        }).setNegativeButton(Language.General.NO.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return alertboxDowload.create();

            case MCDIALOG:
                AlertDialog.Builder mcDialog = new AlertDialog.Builder(this);
                mcDialog.setTitle(Language.General.Information.getStr());
                mcDialog.setMessage(Language.General.NOT_ENOUGH_MONEY.getStr());
                mcDialog.setCancelable(false);
                mcDialog.setPositiveButton(Language.General.CLOSE.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return mcDialog.create();

            case MXDIALOG:
                AlertDialog.Builder mxDialog = new AlertDialog.Builder(this);
                mxDialog.setTitle(Language.General.Information.getStr());
                mxDialog.setMessage(Language.General.NOT_ENOUGH_DIAMON.getStr());
                mxDialog.setCancelable(false);
                mxDialog.setPositiveButton(Language.General.YES.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onRequestPayment();
                            }
                        }).setNegativeButton(Language.General.NO.getStr(),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return mxDialog.create();

            case QUIT_DIALOG:
                AlertDialog.Builder alertboxQuit = new AlertDialog.Builder(this);
                alertboxQuit.setTitle(Language.General.Accept.getStr());
                alertboxQuit.setMessage(Language.General.EXIT_GAME.getStr());
                alertboxQuit.setNeutralButton(Language.General.YES.getStr(),
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Tracker(1, -1, "");
                                System.exit(0);
                                finish();
                            }
                        });

                alertboxQuit.setNegativeButton(Language.General.NO.getStr(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                return alertboxQuit.create();
        }
        return null;
    }

    public String getCurrentLanguage(){
//        Locale.ENGLISH
//        Locale.KOREAN
//        Locale.CHINESE
//        Locale.KOREAN
        return Locale.getDefault().getDisplayLanguage();
    }

    @Override
    public void onToast(final String text, final int duration) {
        mHandle.post(new Runnable()
        {
            @Override
            public void run ()
            {
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
    }

    @Override
    public void onMCDialog() {
        Message msg = new Message();
        msg.what = MCDIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onMXDialog() {
        Message msg = new Message();
        msg.what = MXDIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onDialog(final String text) {
//        Gdx.app.log("Message", text);

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                new AlertDialog.Builder(AndroidPayment.this)
                        .setTitle(Language.General.Confirm.getStr())
                        .setMessage(text)
                        .setPositiveButton(Language.General.Accept.getStr(),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }).create().show();
            }
        });
    }

    @Override
    public void onShare() {
        Message msg = new Message();
        msg.what = SHARE_DIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onDownload() {
        Message msg = new Message();
        msg.what = DOWNLOAD_DIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onInfo() {
        Message msg = new Message();
        msg.what = ABOUT_DIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onExit() {
        Message msg = new Message();
        msg.what = EXIT_DIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void onQuit() {
        Message msg = new Message();
        msg.what = QUIT_DIALOG;
        mHandle.sendMessage(msg);
    }

    @Override
    public void hideAdview() {

    }

    @Override
    public void showAdview() {

    }

    @Override
    public void onRequestPayment ()
    {
        Message msg = new Message();
        msg.what = REQUESTPAYMENT;
        mHandle.sendMessage(msg);
    }

    @Override
    public void setPaymentListener(OnPaymentListener onPaymentListener) {

    }

    @Override
    public void setHighScoreListener(OnHighScoreListener listener) {

    }

    @Override
    public Preferences onLoadPreferences(String package_dir, String name) {
        return null;
    }

    @Override
    public Preferences onLoadPreferences() {
        return null;
    }

    @Override
    public void postHighScore(long score) {
        if (score <= 0) return;
//        core.user.highscore.setScore(score);

        // request dialog
        Message msg = new Message();
        msg.what = HIGHSCORE;
        mHandle.sendMessage(msg);
        return;
    }

    @Override
    public void showHighScoreView() {
        Message msg = new Message();
        msg.what = SEND_SCORE_SUCESS_NEWORK;
        mHandle.sendMessage(msg);
    }

    @Override
    public void Tracker(int i, int j, String string) {

    }

    @Override
    public void Tracker_(int amount, PaymentType type) {

    }

    @Override
    public void onPaymentSuccess(PaymentType type, int value) {

    }

    @Override
    public void onPaymentFailure(PaymentType type, int value) {

    }

    @Override
    public void onPaymentDeny(PaymentType type, int value) {

    }
}
