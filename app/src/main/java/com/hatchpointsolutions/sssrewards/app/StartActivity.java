package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hatchpointsolutions.sssrewards.app.objects.DeviceKey;

import org.json.JSONException;
import org.json.JSONObject;

public class StartActivity extends Activity {

	private static final String BASE_URL = "http://172.20.10.7:8888/index.php/";

	private Context ctx;
	private String deviceId;
	private ProgressBar pb;
	private ImageView refreshImageView;
	private AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		pb = (ProgressBar) findViewById(R.id.startProgressBar);
		refreshImageView = (ImageView) findViewById(R.id.refreshImageView);
		refreshImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivateTask at = new ActivateTask();
				at.execute(deviceId);
			}
		});

		ctx = this;

		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		deviceId = tm.getDeviceId();

		alertDialog = new AlertDialog.Builder(ctx).create();
		alertDialog.setTitle("Application needs activation");
		alertDialog
				.setMessage("Your application needs to be activated. Send a request to activate this application by clicking on the button below.");
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,
				"Request Activation", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Request activation
						RequestActivationTask st = new RequestActivationTask();
						st.execute(deviceId);
						return;
					}
				});
		alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						StartActivity.this.finish();
						return;
					}
				});

		// Let's see if the device key exists

//		List<DeviceKey> dkList = deviceKeyDao.queryForAll();
//
//		if (dkList.size() > 0) {
//			Intent intent = new Intent(this, MainActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		} else {
//			ActivateTask at = new ActivateTask();
//			at.execute(deviceId);
//		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	class ActivateTask extends AsyncTask<String, Void, String> {

		boolean connectionSuccessful;
		JSONObject response;

		@Override
		protected void onPreExecute() {
			// show the progress bar
			pb.setVisibility(View.VISIBLE);
			refreshImageView.setVisibility(View.GONE);
		}

		protected void onPostExecute(String data) {
			pb.setVisibility(View.GONE);

			if (connectionSuccessful) {
				try {
					String status = response.getString("status");
					if (status.equals("ok")) {
						// then it means that this user is exists
						DeviceKey dk = new DeviceKey(
								response.getString("device_key"));
						//deviceKeyDao.create(dk);

						AlertDialog successDialog = new AlertDialog.Builder(ctx)
								.create();
						successDialog.setTitle("Congratulations!");
						successDialog
								.setMessage("Your device has been activated.");
						successDialog.setButton(AlertDialog.BUTTON_POSITIVE,
								"OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = new Intent(
												StartActivity.this,
												MainActivity.class);
										StartActivity.this
												.startActivity(intent);
										StartActivity.this.finish();
										return;
									}
								});
						successDialog.show();
						return;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				alertDialog.show();
			} else {
				refreshImageView.setVisibility(View.VISIBLE);
			}

		}

		@Override
		protected String doInBackground(String... arg0) {
			String did = arg0[0];
			// test sending POST request
			String requestURL = BASE_URL
					+ "cokeloyalty/check_activation?device_id=" + did;

//			try {
//				HttpUtility.sendGetRequest(requestURL);
//				try {
//					response = new JSONObject(
//							HttpUtility.readSingleLineResponse());
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				connectionSuccessful = true;
//			} catch (IOException e) {
//				connectionSuccessful = false;
//				e.printStackTrace();
//			}

			return null;
		}

	}

	class RequestActivationTask extends AsyncTask<String, Void, String> {

		boolean connectionSuccessful;

		@Override
		protected void onPreExecute() {
			// show the progress bar
			pb.setVisibility(View.VISIBLE);
			refreshImageView.setVisibility(View.GONE);
		}

		protected void onPostExecute(String data) {
			pb.setVisibility(View.GONE);

			if (connectionSuccessful) {
				AlertDialog successDialog = new AlertDialog.Builder(ctx)
						.create();
				successDialog.setTitle("Request Sent");
				successDialog
						.setMessage("A request for activation has been sent. You can launch the application again once your request has been processed.");
				successDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								StartActivity.this.finish();
								return;
							}
						});
				successDialog.show();
			} else {
				refreshImageView.setVisibility(View.VISIBLE);

			}
		}

		@Override
		protected String doInBackground(String... arg0) {
			String did = arg0[0];
			// test sending POST request
			String requestURL = BASE_URL
					+ "cokeloyalty/request_activation?device_id=" + did;

//			try {
//				//HttpUtility.sendGetRequest(requestURL);
//				//HttpUtility.readSingleLineResponse();
//				connectionSuccessful = true;
//			} catch (IOException e) {
//				connectionSuccessful = false;
//				e.printStackTrace();
//			}

			return null;
		}

	}

}
