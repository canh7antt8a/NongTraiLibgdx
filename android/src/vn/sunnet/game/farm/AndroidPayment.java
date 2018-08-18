package vn.sunnet.game.farm;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.AndroidApplication;


import vn.sunnet.game.farm.assets.Data;
import vn.sunnet.game.farm.main.Farm;
import vn.sunnet.game.farm.nature.F;
import vn.sunnet.game.farm.screen.MenuScreen;
import vn.sunnet.game.farm.screen.Payment;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

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
                alertboxExit.setTitle(getResources().getString(
                        R.string.confirm_dialog_title));
                alertboxExit.setMessage(getResources().getString(
                        R.string.exit_app_dialog_message));
                alertboxExit.setNeutralButton(getResources()
                                .getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                if(F.money >= 30000) {
                                    F.money -= 30000;
                                    onToast("Chúc mừng bạn đã mua game thành công !", 1);
                                    Data.hideAdview();
                                    MenuScreen.isShowAd = false;
                                    onQuit();
                                } else {
                                    onRequestPayment();
                                    onToast("Bạn không đủ xu để mua game !", 1);
                                }

                                Farm.buyGame = true;
                            }
                        });

                alertboxExit.setNegativeButton(getResources()
                        .getString(R.string.no), new DialogInterface.OnClickListener() {
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
                        getResources().getString(R.string.LS_OTHER_GAME),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
//                                core.param.openDownloadLink(AndroidPayment.this);
                            }
                        });
                alertboxInfo.setNegativeButton(
                        getResources().getString(R.string.LS_CLOSE), null);

                return alertboxInfo.create();
            case SHARE_DIALOG:
                AlertDialog.Builder alertboxShare = new AlertDialog.Builder(this);
                alertboxShare.setTitle(R.string.title_share);
                alertboxShare.setMessage(R.string.message_share_title);
                alertboxShare.setCancelable(false);
                alertboxShare.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                core.param.openShareDialog(	getString(R.string.title_share),
//                                        getString(R.string.message_share_subject), getString(R.string.message_share_content));

                            }
                        }).setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return alertboxShare.create();
            case DOWNLOAD_DIALOG:
                AlertDialog.Builder alertboxDowload = new AlertDialog.Builder(this);
                alertboxDowload.setTitle(R.string.title_download);
                alertboxDowload.setMessage(R.string.message_download_title);
                alertboxDowload.setCancelable(false);
                alertboxDowload.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                core.param.openDownloadLink(AndroidPayment.this);
                            }
                        }).setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return alertboxDowload.create();

            case MCDIALOG:
                AlertDialog.Builder mcDialog = new AlertDialog.Builder(this);
                mcDialog.setTitle(R.string.title_m);
                mcDialog.setMessage(R.string.message_mc_title);
                mcDialog.setCancelable(false);
                mcDialog.setPositiveButton("Đóng",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return mcDialog.create();

            case MXDIALOG:
                AlertDialog.Builder mxDialog = new AlertDialog.Builder(this);
                mxDialog.setTitle(R.string.title_m);
                mxDialog.setMessage(R.string.message_mx_title);
                mxDialog.setCancelable(false);
                mxDialog.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onRequestPayment();
                            }
                        }).setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                return mxDialog.create();

            case QUIT_DIALOG:
                AlertDialog.Builder alertboxQuit = new AlertDialog.Builder(this);
                alertboxQuit.setTitle(getResources().getString(
                        R.string.confirm_dialog_title));
                alertboxQuit.setMessage(getResources().getString(
                        R.string.quit_game_dialog_message));
                alertboxQuit.setNeutralButton(getResources()
                                .getString(R.string.yes),
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Tracker(1, -1, "");
                                System.exit(0);
                                finish();
                            }
                        });

                alertboxQuit.setNegativeButton(getResources()
                        .getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                return alertboxQuit.create();
        }
        return null;
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
        Gdx.app.log("Message", text);

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                new AlertDialog.Builder(AndroidPayment.this)
                        .setTitle(R.string.confirm_dialog_title)
                        .setMessage(text)
                        .setPositiveButton("Đồng ý",
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
