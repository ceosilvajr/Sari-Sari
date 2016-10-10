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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity {

  Context ctx = this;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    checkPassword();
  }

  private void checkPassword() {

    final Button passwordButton = (Button) findViewById(R.id.PasswordNextButton);
    final EditText passwordInput = (EditText) findViewById(R.id.passwordEditText);

    passwordButton.setOnClickListener(new OnClickListener() {

      @Override public void onClick(View v) {

        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);

        //String inputPassword = passwordInput.getText().toString();
        // Grab password1 from the database
        //				Password password1 = passwordDao.queryForId(1);
        //				if (password1.getPassword().equals(inputPassword)) {
        //
        //					if (inputPassword.equals("p1")) {
        //
        //						forceToChangePassword1();
        //
        //					} else {
        //						Intent intent = new Intent(LogInActivity.this,
        //								MainActivity.class);
        //						startActivity(intent);
        //						finish();
        //					}
        //
        //				} else {
        //					passwordInput.setError("Your password is incorect");
        //				}
      }
    });
  }

  private void forceToChangePassword1() {

    Toast.makeText(getApplicationContext(),
        "Please change your default password for security reasons.", Toast.LENGTH_LONG).show();

    LayoutInflater layoutInflater = LayoutInflater.from(ctx);
    View adminPassword = layoutInflater.inflate(R.layout.activity_change_password, null);
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
    alertDialogBuilder.setView(adminPassword);

    final EditText newAdminPassword =
        (EditText) adminPassword.findViewById(R.id.input_new_password1);

    final EditText newAdminPasswordVerification =
        (EditText) adminPassword.findViewById(R.id.re_input_new_password1);

    alertDialogBuilder.setCancelable(false)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

          @Override public void onClick(DialogInterface dialog, int which) {
            String inputNewPassword = newAdminPassword.getText().toString();
            String inputNewPasswordVerification = newAdminPasswordVerification.getText().toString();

            if (inputNewPassword.equals(inputNewPasswordVerification)) {
              //							Password passwordAdmin = passwordDao.queryForId(1);
              //
              //							passwordAdmin.setPassword(inputNewPassword);
              //							passwordDao.update(passwordAdmin);

              Toast.makeText(getApplicationContext(), "Your password 1 has been changed.",
                  Toast.LENGTH_LONG).show();

              forceToChangePassword2();
            } else {
              Toast.makeText(getApplicationContext(),
                  "Your passwords do not match. Please try again.", Toast.LENGTH_LONG).show();
            }
          }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
          }
        });

    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }

  private void forceToChangePassword2() {

    LayoutInflater layoutInflater = LayoutInflater.from(ctx);
    View adminPassword = layoutInflater.inflate(R.layout.activity_change_admin_password, null);
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
    alertDialogBuilder.setView(adminPassword);

    final EditText newAdminPassword =
        (EditText) adminPassword.findViewById(R.id.input_new_admin_password);

    final EditText newAdminPasswordVerification =
        (EditText) adminPassword.findViewById(R.id.re_input_new_admin_password);

    alertDialogBuilder.setCancelable(false)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

          @Override public void onClick(DialogInterface dialog, int which) {
            String inputNewPassword = newAdminPassword.getText().toString();
            String inputNewPasswordVerification = newAdminPasswordVerification.getText().toString();

            if (inputNewPassword.equals(inputNewPasswordVerification)) {
              //							Password passwordAdmin = passwordDao.queryForId(2);
              //
              //							passwordAdmin.setPassword(inputNewPassword);
              //							passwordDao.update(passwordAdmin);

              Toast.makeText(getApplicationContext(), "Your password 2 has been changed.",
                  Toast.LENGTH_LONG).show();

              Intent intent = new Intent(LogInActivity.this, MainActivity.class);
              startActivity(intent);
              finish();
            } else {
              Toast.makeText(getApplicationContext(),
                  "Your passwords do not match. Please try again.", Toast.LENGTH_LONG).show();
            }
          }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
          }
        });

    AlertDialog alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }
}
