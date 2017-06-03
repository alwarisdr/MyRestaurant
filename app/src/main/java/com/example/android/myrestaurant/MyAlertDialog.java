package com.example.android.myrestaurant;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by alwaris on 5/30/17.
 */


public class MyAlertDialog {

    AlertDialog.Builder objAlert;

    public void errorDialog(Context context, String strTitle, String strMessage) {


        objAlert = new AlertDialog.Builder(context);
        //objAlert.setIcon(R.drawable.n_alarmanotice);
        objAlert.setTitle(strTitle);
        objAlert.setMessage(strMessage);
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        objAlert.show();

    } // errorDialog


} // Main Class
