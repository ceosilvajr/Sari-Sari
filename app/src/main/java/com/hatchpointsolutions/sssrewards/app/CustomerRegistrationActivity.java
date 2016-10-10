package com.hatchpointsolutions.sssrewards.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchpointsolutions.sssrewards.app.objects.Customer;
import com.hatchpointsolutions.sssrewards.app.utils.ResourceUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SuppressLint({ "SimpleDateFormat", "NewApi" })
public class CustomerRegistrationActivity extends Activity{

	private static Customer customer;
	private Context context;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 10;
	private Bitmap bitmap;
	TextView customerMobileNumber;
	EditText customerFirstName;
	EditText customerMiddleName;
	EditText customerLastName;
	static TextView customerBirthDateResult;
	EditText customerAddress;
	ImageView prevButton;
	Button save;
	Button setBirthDate;
	Button takePhoto;
	ImageView image;
	private Spinner regionSpinner;
	private Spinner citySpinner;
	private static String date;
	private JSONArray regionArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_registration);

		context = this;

		String regionJSON = ResourceUtility.readRawTextFile(context,
				R.raw.regions);

		try {
			regionArray = new JSONArray(regionJSON);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Bundle extras = getIntent().getExtras();
		final String customerMobile = extras.getString("CUSTOMER_ID");

		customerMobileNumber = (TextView) findViewById(R.id.customerMobileNumberText);
		customerFirstName = (EditText) findViewById(R.id.customerFirstNameText);
		customerMiddleName = (EditText) findViewById(R.id.customerMiddleNameText);
		customerLastName = (EditText) findViewById(R.id.customerLastNameText);
		customerBirthDateResult = (TextView) findViewById(R.id.customerBirthdayResult);
		prevButton = (ImageView) findViewById(R.id.customerRegistrationPrevButton);
		save = (Button) findViewById(R.id.save);
		setBirthDate = (Button) findViewById(R.id.customerBirthDateButton);
		takePhoto = (Button) findViewById(R.id.takePhoto);
		image = (ImageView) findViewById(R.id.customerPhoto);
		customerAddress = (EditText) findViewById(R.id.customerAddressText);
		// Spinner
		regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
		citySpinner = (Spinner) findViewById(R.id.citySpinner);
		List<String> cityList = new ArrayList<String>();
		cityList.add("Choose a region first");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, cityList);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(dataAdapter);
		addRegionSpinner();
		regionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				if (pos - 1 >= 0) {

					try {
						JSONObject regionObj = regionArray
								.getJSONObject(pos - 1);
						JSONArray cityArray = regionObj.getJSONArray("cities");
						List<String> cityList = new ArrayList<String>();
						cityList.add("Please choose a city");
						for (int i = 0; i < cityArray.length(); i++) {
							JSONObject city = cityArray.getJSONObject(i);
							String cityName = city.getString("name");
							cityList.add(cityName);
						}
						ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
								context, android.R.layout.simple_spinner_item,
								cityList);
						dataAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						citySpinner.setAdapter(dataAdapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		customerMobileNumber.setText(customerMobile);

		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomerRegistrationActivity.this.finish();
			}
		});

		setBirthDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		takePhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (checkCameraHardware(context)) {

					String state = Environment.getExternalStorageState();

					if (Environment.MEDIA_MOUNTED.equals(state)) {

					} else {
						Toast.makeText(getApplicationContext(),
								"No sdcard available", Toast.LENGTH_LONG)
								.show();
					}

					capturePhoto();

				} else {
					Toast.makeText(getApplicationContext(),
							"No camera detected", Toast.LENGTH_LONG).show();
				}
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				LayoutInflater layoutInflater = LayoutInflater.from(context);
				View prompAdminPassword = layoutInflater.inflate(
						R.layout.promp_registration_save, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
				alertDialogBuilder.setView(prompAdminPassword);

				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										String firstName = customerFirstName
												.getText().toString();
										String middleName = customerMiddleName
												.getText().toString();
										String lastName = customerLastName
												.getText().toString();
										String address = customerAddress
												.getText().toString();
										String region = regionSpinner
												.getSelectedItem().toString();
										String city = citySpinner
												.getSelectedItem().toString();

										String capturedImage = BitMapToString(bitmap);

										if (firstName.length() == 0) {
											customerFirstName
													.setError("this field cannot be empty");
										} else if (middleName.length() == 0) {
											customerMiddleName
													.setError("this field cannot be empty");
										} else if (lastName.length() == 0) {
											customerLastName
													.setError("this field cannot be empty");
										} else if (address.length() == 0) {
											customerAddress
													.setError("this field cannot be empty");
										} else if (capturedImage.length() == 0) {

											Toast.makeText(
													getApplicationContext(),
													"Please take a photo for verification",
													Toast.LENGTH_LONG).show();

										} else if (date == null) {
											Toast.makeText(
													getApplicationContext(),
													"Please indicate your birth date",
													Toast.LENGTH_LONG).show();
										} else {

											customer = new Customer(
													customerMobile);
											customer.setCustomerFirstName(firstName);
											customer.setCustomerMiddleName(middleName);
											customer.setCustomerLastName(lastName);
											customer.setCustomerBirthDate(date);
											customer.setCustomerAddress(address);
											customer.setCustomerRegion(region);
											customer.setCustomerCity(city);
											customer.setCustomerPicture(capturedImage);

											if (customer != null) {

											}
											CustomerRegistrationActivity.this
													.finish();
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

	private void addRegionSpinner() {
		try {
			List<String> regionList = new ArrayList<String>();
			regionList.add("Please choose a region");
			for (int i = 0; i < regionArray.length(); i++) {
				JSONObject region = regionArray.getJSONObject(i);
				String regionName = region.getString("name");
				regionList.add(regionName);
			}
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, regionList);
			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			regionSpinner.setAdapter(dataAdapter);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void capturePhoto() {

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, "data");
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				handleSmallCameraPhoto(data);

			} else if (resultCode == RESULT_CANCELED) {
				// User cancelled the image capture
			} else {
				// Image capture failed, advise user
			}
		}
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		bitmap = (Bitmap) extras.get("data");
		image.setImageBitmap(bitmap);
	}

	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			Log.d("Error", "No compatible Camera Found");
			// no camera on this device
			Toast.makeText(CustomerRegistrationActivity.this,
					"Sorry, no camera on this device", Toast.LENGTH_LONG)
					.show();
			return false;
		}
	}

	public String BitMapToString(Bitmap bitmap) {

		String temp = "";

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] b = baos.toByteArray();
			temp = Base64.encodeToString(b, Base64.DEFAULT);
			return temp;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return temp;
	}

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {

			if (month < 10 && day > 10) {
				date = String.valueOf(year) + "-" + "0"
						+ String.valueOf(month + 1) + "-" + String.valueOf(day);
			} else if (day < 10 && month > 10) {
				date = String.valueOf(year) + "-" + String.valueOf(month + 1)
						+ "-" + "0" + String.valueOf(day);
			} else if (day < 10 && month < 10) {
				date = String.valueOf(year) + "-" + "0"
						+ String.valueOf(month + 1) + "-" + "0"
						+ String.valueOf(day);
			} else {
				date = String.valueOf(year) + "-" + String.valueOf(month + 1)
						+ "-" + String.valueOf(day);
			}

			customerBirthDateResult.setText(date);
		}
	}
}
