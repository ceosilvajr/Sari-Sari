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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hatchpointsolutions.sssrewards.app.objects.Password;

public class AdminActivity extends Activity {
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity.this.finish();
            }
        });

        RelativeLayout viewBasketsButton = (RelativeLayout) findViewById(R.id.viewBasketsButton);
        RelativeLayout viewRedemptionsButton = (RelativeLayout) findViewById(R.id.viewRedemptionsButton);
        RelativeLayout changePassword1 = (RelativeLayout) findViewById(R.id.change_password1);
        RelativeLayout changePassword2 = (RelativeLayout) findViewById(R.id.change_admin_password);

        viewBasketsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this,
                        ViewBasketsActivity.class);
                AdminActivity.this.startActivity(i);
            }
        });
        viewRedemptionsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this,
                        ViewRedemptionsActivity.class);
                AdminActivity.this.startActivity(i);
            }
        });

        changePassword1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View changePassword1 = layoutInflater.inflate(
                        R.layout.activity_change_password, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(changePassword1);

                final EditText newPassword1 = (EditText) changePassword1
                        .findViewById(R.id.input_new_password1);

                final EditText newPassword1Verification = (EditText) changePassword1
                        .findViewById(R.id.re_input_new_password1);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                        String inputNewPassword = newPassword1
                                                .getText().toString();

                                        String inputNewPasswordVerification = newPassword1Verification
                                                .getText().toString();

                                        if (inputNewPassword
                                                .equals(inputNewPasswordVerification)) {
//											Password password1 = passwordDao
//													.queryForId(1);
//
//											password1
//													.setPassword(inputNewPassword);
//											passwordDao.update(password1);
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Your password has been changed.",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Your passwords do not match. Please try again.",
                                                    Toast.LENGTH_LONG).show();
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        changePassword2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View adminPassword = layoutInflater.inflate(
                        R.layout.activity_change_admin_password, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(adminPassword);

                final EditText newAdminPassword = (EditText) adminPassword
                        .findViewById(R.id.input_new_admin_password);

                final EditText newAdminPasswordVerification = (EditText) adminPassword
                        .findViewById(R.id.re_input_new_admin_password);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String inputNewPassword = newAdminPassword
                                                .getText().toString();
                                        String inputNewPasswordVerification = newAdminPasswordVerification
                                                .getText().toString();

                                        if (inputNewPassword
                                                .equals(inputNewPasswordVerification)) {
//                                            Password passwordAdmin = passwordDao
//                                                    .queryForId(2);
//
//                                            passwordAdmin
//                                                    .setPassword(inputNewPassword);
//                                            passwordDao.update(passwordAdmin);

                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Your password has been changed.",
                                                    Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(
                                                    getApplicationContext(),
                                                    "Your passwords do not match. Please try again.",
                                                    Toast.LENGTH_LONG).show();
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

    }

}
