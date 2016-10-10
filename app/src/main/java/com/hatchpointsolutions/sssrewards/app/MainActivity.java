package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
  private final Context context = this;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RelativeLayout adminButton = (RelativeLayout) findViewById(R.id.button4);
    RelativeLayout redeemButton = (RelativeLayout) findViewById(R.id.button2);
    RelativeLayout transactButton = (RelativeLayout) findViewById(R.id.transactButton);
    RelativeLayout customerButton = (RelativeLayout) findViewById(R.id.button3);

    adminButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View arg0) {

        Intent i = new Intent(MainActivity.this, AdminActivity.class);
        MainActivity.this.startActivity(i);

        //LayoutInflater layoutInflater = LayoutInflater.from(context);
        //View prompAdminPassword = layoutInflater.inflate(R.layout.promp_admin_password, null);
        //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        //alertDialogBuilder.setView(prompAdminPassword);
        //final EditText inputAdminPassword =
        //    (EditText) prompAdminPassword.findViewById(R.id.input_password2);
        //
        //inputAdminPassword.requestFocus();
        //
        //alertDialogBuilder.setCancelable(false)
        //    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        //
        //      @Override public void onClick(DialogInterface dialog, int which) {
        //
        //        //Password password2 = passwordDao
        //        //		.queryForId(2);
        //        //
        //        //String passwordText = inputAdminPassword
        //        //		.getText().toString();
        //        //if (passwordText.equals(password2
        //        //		.getPassword())) {
        //        //
        //        //	Intent i = new Intent(
        //        //			MainActivity.this,
        //        //			AdminActivity.class);
        //        //	MainActivity.this.startActivity(i);
        //        //
        //        //} else {
        //        //	Toast.makeText(
        //        //			getApplicationContext(),
        //        //			"Your password is incorrect.",
        //        //			Toast.LENGTH_LONG).show();
        //        //}
        //      }
        //    })
        //    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        //      public void onClick(DialogInterface dialog, int id) {
        //        dialog.cancel();
        //      }
        //    });
        //AlertDialog alertDialog = alertDialogBuilder.create();
        //alertDialog.getWindow()
        //    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //alertDialog.show();
      }
    });

    transactButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, TransactActivity.class);
        MainActivity.this.startActivity(i);
      }
    });

    customerButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, CustomerActivity.class);
        MainActivity.this.startActivity(i);
      }
    });

    redeemButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, PrizeActivity.class);
        MainActivity.this.startActivity(i);
      }
    });
  }
}
