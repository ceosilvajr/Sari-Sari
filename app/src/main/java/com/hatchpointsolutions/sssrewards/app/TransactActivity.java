package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class TransactActivity extends Activity {

	private EditText mobileNumberEditText;
	private Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ctx = this;

		checkBaskets();

	}

	public void checkBaskets() {
//		QueryBuilder<Basket, Integer> basketQueryBuilder = basketDao
//				.queryBuilder();
//		List<Basket> basketList = new ArrayList<Basket>();
//		try {
//			basketList = basketQueryBuilder.where().eq("active", true).query();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		if (basketList.size() == 0) {
//			setContentView(R.layout.activity_transact_no_basket);
//
//			TextView addPromoButton = (TextView) findViewById(R.id.addPromoButton);
//			addPromoButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					LayoutInflater layoutInflater = LayoutInflater.from(ctx);
//					View prompAdminPassword = layoutInflater.inflate(
//							R.layout.promp_admin_password, null);
//					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//							ctx);
//					alertDialogBuilder.setView(prompAdminPassword);
//
//					final EditText inputAdminPassword = (EditText) prompAdminPassword
//							.findViewById(R.id.input_password2);
//					alertDialogBuilder
//							.setCancelable(false)
//							.setPositiveButton("OK",
//									new DialogInterface.OnClickListener() {
//
//										@Override
//										public void onClick(
//												DialogInterface dialog,
//												int which) {
//
//											Password password2 = passwordDao
//													.queryForId(2);
//
//											String passwordText = inputAdminPassword
//													.getText().toString();
//											if (passwordText.equals(password2
//													.getPassword())) {
//
//												Intent i = new Intent(
//														TransactActivity.this,
//														AddBasketActivity.class);
//												TransactActivity.this
//														.startActivityForResult(
//																i, 1);
//
//											} else {
//												Toast.makeText(
//														getApplicationContext(),
//														"Your password is incorrect.",
//														Toast.LENGTH_LONG)
//														.show();
//											}
//										}
//									})
//							.setNegativeButton("Cancel",
//									new DialogInterface.OnClickListener() {
//										public void onClick(
//												DialogInterface dialog, int id) {
//											dialog.cancel();
//										}
//									});
//					AlertDialog alertDialog = alertDialogBuilder.create();
//					alertDialog
//							.getWindow()
//							.setSoftInputMode(
//									WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//					alertDialog.show();
//				}
//			});
//
//		} else {
//			setContentView(R.layout.activity_transact);
//
//			customerDao = getHelper().getCustomerRuntimeDao();
//
//			mobileNumberEditText = (EditText) findViewById(R.id.mobileNumberEditText);
//			Button transactNextButton = (Button) findViewById(R.id.transactNextButton);
//			ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
//			prevButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					TransactActivity.this.finish();
//				}
//			});
//
//			transactNextButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					// Get the mobile number
//					// We should clean it...
//					// Remove the +, -
//
//					String mobileNumberString = mobileNumberEditText.getText()
//							.toString().trim();
//					if (mobileNumberString.length() == 0) {
//						mobileNumberEditText.setError(ctx
//								.getString(R.string.error_mobile_number_invalid));
//						return;
//					}
//					mobileNumberString = mobileNumberString.replaceAll(
//							"[^0-9]", "");
//					if (mobileNumberString.substring(0, 1).equals("0")) {
//						mobileNumberString = mobileNumberString.substring(1,
//								mobileNumberString.length());
//					} else if (mobileNumberString.substring(0, 1).equals("63")) {
//						mobileNumberString = mobileNumberString.substring(2,
//								mobileNumberString.length());
//					}
//
//					if (mobileNumberString.length() != 10) {
//						mobileNumberEditText.setError(ctx
//								.getString(R.string.error_mobile_number_invalid));
//						return;
//					}
//
//					mobileNumberString = "0" + mobileNumberString;
//
//					int customerId = 0;
//
//					try {
//						QueryBuilder<Customer, Integer> qBuilder = customerDao
//								.queryBuilder();
//						Long limit = (long) 1;
//						qBuilder.limit(limit);
//						qBuilder.where().eq("mobileNumber", mobileNumberString);
//						Customer c = qBuilder.queryForFirst();
//
//						if (c != null) {
//							customerId = c.getId();
//
//							Intent i = new Intent(TransactActivity.this,
//									Transact2Activity.class);
//							i.putExtra("CUSTOMER_ID", customerId);
//							TransactActivity.this.startActivity(i);
//							TransactActivity.this.finish();
//
//						} else {
//
//							LayoutInflater layoutInflater = LayoutInflater
//									.from(ctx);
//							View verificationFailed = layoutInflater.inflate(
//									R.layout.mobile_number_verification, null);
//
//							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//									ctx);
//							alertDialogBuilder.setView(verificationFailed);
//
//							alertDialogBuilder
//									.setCancelable(false)
//									.setPositiveButton(
//											"OK",
//											new DialogInterface.OnClickListener() {
//
//												@Override
//												public void onClick(
//														DialogInterface dialog,
//														int which) {
//
//													String mobileNumber = mobileNumberEditText.getText()
//															.toString().trim();
//
//													Intent i = new Intent(
//															TransactActivity.this,
//															CustomerRegistrationActivity.class);
//													i.putExtra("CUSTOMER_ID",
//															mobileNumber);
//													startActivity(i);
//													TransactActivity.this.finish();
//												}
//											})
//									.setNegativeButton(
//											"Cancel",
//											new DialogInterface.OnClickListener() {
//												public void onClick(
//														DialogInterface dialog,
//														int id) {
//													dialog.cancel();
//												}
//											});
//							AlertDialog alertDialog = alertDialogBuilder
//									.create();
//							alertDialog.show();
//						}
//
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//
//				}
//			});
//		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		checkBaskets();
	}

}
