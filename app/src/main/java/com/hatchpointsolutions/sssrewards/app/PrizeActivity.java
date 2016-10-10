package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PrizeActivity extends Activity {

	private EditText mobileNumberEditText;
	private Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize);

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PrizeActivity.this.finish();
			}
		});

		ctx = this;

		mobileNumberEditText = (EditText) findViewById(R.id.redeemMobileNumberEditText);
		Button transactNextButton = (Button) findViewById(R.id.redeemNextButton);

//		transactNextButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// Get the mobile number
//				// We should clean it...
//				// Remove the +, -
//				String mobileNumberString = mobileNumberEditText.getText()
//						.toString().trim();
//				if (mobileNumberString.length() == 0) {
//					mobileNumberEditText.setError(ctx
//							.getString(R.string.error_mobile_number_invalid));
//					return;
//				}
//				mobileNumberString = mobileNumberString
//						.replaceAll("[^0-9]", "");
//				if (mobileNumberString.substring(0, 1).equals("0")) {
//					mobileNumberString = mobileNumberString.substring(1,
//							mobileNumberString.length());
//				} else if (mobileNumberString.substring(0, 1).equals("63")) {
//					mobileNumberString = mobileNumberString.substring(2,
//							mobileNumberString.length());
//				}
//
//				if (mobileNumberString.length() != 10) {
//					mobileNumberEditText.setError(ctx
//							.getString(R.string.error_mobile_number_invalid));
//					return;
//				}
//
//				mobileNumberString = "0" + mobileNumberString;
//
//				int customerId = 0;
//
//				// query if the customer exists, if not, create a new
//				// customer
//				try {
//					QueryBuilder<Customer, Integer> qBuilder = customerDao
//							.queryBuilder();
//					Long limit = (long) 1;
//					qBuilder.limit(limit);
//					qBuilder.where().eq("mobileNumber", mobileNumberString);
//					Customer c = qBuilder.queryForFirst();
//
//					if (c != null) {
//						customerId = c.getId();
//
//						Intent i = new Intent(PrizeActivity.this,
//								Prize2Activity.class);
//						i.putExtra("CUSTOMER_ID", customerId);
//						PrizeActivity.this.startActivity(i);
//						PrizeActivity.this.finish();
//
//					} else {
//
//						LayoutInflater layoutInflater = LayoutInflater
//								.from(ctx);
//						View verificationFailed = layoutInflater.inflate(
//								R.layout.mobile_number_verification, null);
//
//						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//								ctx);
//						alertDialogBuilder.setView(verificationFailed);
//
//						alertDialogBuilder
//								.setCancelable(false)
//								.setPositiveButton("OK",
//										new DialogInterface.OnClickListener() {
//
//											@Override
//											public void onClick(
//													DialogInterface dialog,
//													int which) {
//
//												String mobileNumber = mobileNumberEditText
//														.getText().toString()
//														.trim();
//
//												Intent i = new Intent(
//														PrizeActivity.this,
//														CustomerRegistrationActivity.class);
//												startActivity(i);
//
//												i.putExtra("CUSTOMER_ID",
//														mobileNumber);
//												startActivity(i);
//												PrizeActivity.this.finish();
//											}
//										})
//								.setNegativeButton("Cancel",
//										new DialogInterface.OnClickListener() {
//											public void onClick(
//													DialogInterface dialog,
//													int id) {
//												dialog.cancel();
//											}
//										});
//						AlertDialog alertDialog = alertDialogBuilder.create();
//						alertDialog.show();
//					}
//
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//			}
//		});
	}

}
