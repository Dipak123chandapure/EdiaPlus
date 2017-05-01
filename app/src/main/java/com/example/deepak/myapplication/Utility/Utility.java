package com.example.deepak.myapplication.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deepak.myapplication.Application.App;
import com.example.deepak.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Utility {

    public static String CURRENT_FRAGMENT;
    public static EditText headerEditText;
    public static ImageView headerImageOne;
    public static ImageView headerImageTwo;
    public static ImageView headerImageThree;
    public static boolean isConnectedToInternet;


    public static int getCircularTextBackground(char c) {
        switch (c) {
            case 'R':
                return Color.rgb(190, 220, 250);
            case 'D':
                return Color.rgb(200, 250, 200);

            case 'A':
                return Color.rgb(180, 250, 250);

            case 'S':
                return Color.rgb(250, 180, 200);
            case 'G':
                return Color.rgb(250, 200, 120);
            case 'J':
                return Color.rgb(250, 200, 160);
            case 'H':
                return Color.rgb(250, 180, 250);
            case 'K':
                return Color.rgb(200, 200, 230);

            default:
                return Color.rgb(210, 220, 210);
        }
    }


    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);

                if (networkInfo != null && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo networkInfo : info) {
                        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                            Log.d("Network",
                                    "NETWORKNAME: " + networkInfo.getTypeName());
                            return true;
                        }
                    }
                }
            }
        }
        Log.v("network", "not connected to internet");
        return false;
    }


    public static void showNoInternetDialogWithFinish(final Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("No internet connction")
                .setContentText("please check your internet connection")
                .setConfirmText("Ok")
                .show();

    }

    public static void showNoInternetDialogWithFinish(final Context context, final String title, final String message,SweetAlertDialog.OnSweetClickListener onConfirmClick) {
        new SweetAlertDialog(App.getInstance().getLiveContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("No internet connction")
                .setContentText("please check your internet connection")
                .setConfirmText("Ok")
                .setConfirmClickListener(onConfirmClick)
                .show();
    }

    public static void showErrorDialog(final Context context, final String title, final String message) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText("ok")
                .show();
    }

    public static void showErrorDialogWithFinish(final Context context, final String title, final String message,SweetAlertDialog.OnSweetClickListener onConfirmClick) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText("ok")
                .setConfirmClickListener(onConfirmClick)
                .show();
    }

    public static void showSuccessDialog(final Context context, final String title, final String message) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText("ok")
                .show();
    }


    public static void showSuccessDialogWithFinish(final Context context, final String title, final String message,SweetAlertDialog.OnSweetClickListener onConfirmClick) {
        SweetAlertDialog dialog =  new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmClickListener(onConfirmClick)
                .setConfirmText("ok");

        dialog.show();
    }


    public static void showWarningDialog(final Context context, final String title, final String message, final OnDialogClicked dialogClicked) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setConfirmText(context.getString(android.R.string.ok))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        if (null != dialogClicked) {
                            dialogClicked.onPositiveButtonClick(sDialog);
                        } else {
                            sDialog.dismissWithAnimation();
                        }

                    }
                })
                .show();
    }

    public static SweetAlertDialog showProgressDialog(Context context) {
        SweetAlertDialog  sProgressDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        sProgressDialog.getProgressHelper().setBarColor(ContextCompat.getColor(context, R.color.Grey_600));
        sProgressDialog.setTitleText("Loading..");
        sProgressDialog.setCancelable(false);
        sProgressDialog.show();
        return sProgressDialog;
    }

    public static void hideProgressDialog( SweetAlertDialog  sProgressDialog ) {
        if (null != sProgressDialog)
            sProgressDialog.dismiss();
    }

    public interface OnDialogClicked {
        void onPositiveButtonClick(DialogInterface dialog);
        void onNegativeButtonClick(DialogInterface dialog, int which);
    }

    public static Long getRandomDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh mm", Locale.getDefault());
        Calendar calender = Calendar.getInstance();
        int month = new Random().nextInt(12);
        int date = new Random().nextInt(28);
        int hours = new Random().nextInt(12);
        int min = new Random().nextInt(60);

        calender.set(2017, month, date, hours, min);
        Log.d("rohit", "Date: " + fmt.format(calender.getTime()));
        Log.d("rohit", "Time: " + calender.getTime().getTime());
        return calender.getTime().getTime();
    }
}
