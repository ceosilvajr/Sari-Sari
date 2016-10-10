package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hatchpointsolutions.sssrewards.app.objects.Customer;

public class Prize2Activity extends Activity{

	private Customer customer;
	private Context ctx;
	private LinearLayout catalog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize2);

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Prize2Activity.this.finish();
			}
		});

		ctx = this;

		Bundle extras = getIntent().getExtras();
		int customerId = extras.getInt("CUSTOMER_ID");

		TextView mobileNumberTextView = (TextView) findViewById(R.id.redeemMobileNumber);
		mobileNumberTextView.setText(customer.getMobileNumber());
		mobileNumberTextView.setTextColor(Color.WHITE);
		mobileNumberTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

		TextView name = (TextView) findViewById(R.id.redeemCustomerName);
		name.setText(customer.getCustomerFirstName() + " "
				+ customer.getCustomerMiddleName() + " "
				+ customer.getCustomerLastName());

		TextView birthDate = (TextView) findViewById(R.id.redeemCustomerBirthDay);
		birthDate.setText(customer.getCustomerBirthDate());

		TextView address = (TextView) findViewById(R.id.redeemCustomerAddress);
		address.setText(customer.getCustomerAddress() + " "
				+ customer.getCustomerCity() + " "
				+ customer.getCustomerRegion());

		ImageView photo = (ImageView) findViewById(R.id.redeemCustomerPhoto);
		// Integer image = Integer.valueOf(customer.getCustomerPicture());
		Bitmap image = StringToBitMap(customer.getCustomerPicture());
		photo.setImageBitmap(image);

		TextView totalSukiPoints = (TextView) findViewById(R.id.redeemTotalSukiPoints);
		totalSukiPoints.setText(customer.getTotalSukiPoints() + "");

		catalog = (LinearLayout) findViewById(R.id.redeemLinearLayout);

		papulateCatalog();
	}

	private void papulateCatalog() {
	/*	catalog.removeAllViews();

		int dip5 = convertDipToPx(ctx, 5);
		int dip10 = convertDipToPx(ctx, 10);
		int dip20 = convertDipToPx(ctx, 20);

		List<Redemption> list = redemptionDao.queryForAll();

		for (final Redemption r : list) {

			LinearLayout.LayoutParams rlp1 = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			rlp1.setMargins(0, dip20, 0, 0);
			RelativeLayout relativeLayoutCatalog = new RelativeLayout(ctx);

			relativeLayoutCatalog.setLayoutParams(rlp1);
			relativeLayoutCatalog.setPadding(dip10, dip10, dip10, dip10);
			relativeLayoutCatalog.setBackgroundResource(R.drawable.bg_gray);

			RelativeLayout.LayoutParams redeemNameLayout = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			TextView redeemName = new TextView(ctx);
			redeemName.setId(01);
			redeemName.setLayoutParams(redeemNameLayout);
			redeemName.setTextColor(Color.BLACK);
			redeemName.setTypeface(null, Typeface.BOLD);
			redeemName.setTextSize(18);
			redeemName.setText(r.getName() + "");

			RelativeLayout.LayoutParams redeemPointsLayout = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			TextView redeemPoints = new TextView(ctx);
			redeemPoints.setLayoutParams(redeemPointsLayout);
			redeemPoints.setId(02);
			redeemPointsLayout
					.addRule(RelativeLayout.BELOW, redeemName.getId());
			redeemPoints.setTextColor(Color.BLACK);
			redeemPoints.setTypeface(null, Typeface.BOLD);
			redeemPoints.setText(r.getPoints() + " POINTS");

			RelativeLayout.LayoutParams redeemCheckButtonPointsLayout = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			ImageView redeemCheckButton = new ImageView(ctx);
			redeemCheckButton.setLayoutParams(redeemCheckButtonPointsLayout);
			redeemCheckButton.setId(03);
			redeemCheckButtonPointsLayout
					.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			redeemCheckButtonPointsLayout.setMargins(0, 0, dip20, 0);

			RelativeLayout.LayoutParams redeemCheckLabelLayout = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			TextView redeemCheckLabel = new TextView(ctx);
			redeemCheckLabel.setLayoutParams(redeemCheckLabelLayout);
			redeemCheckLabel.setId(04);
			redeemCheckLabelLayout.addRule(RelativeLayout.BELOW,
					redeemCheckButton.getId());
			redeemCheckLabelLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			redeemCheckLabelLayout.setMargins(0, 0, dip20, 0);
			redeemCheckLabel.setTextSize(12);
			redeemCheckLabel.setText("REDEEM");

			if (r.getPoints() <= customer.getTotalSukiPoints()) {

				redeemCheckButton.setImageResource(R.drawable.selector_accept);
				redeemCheckLabel.setTextColor(Color.BLACK);

				RelativeLayout.LayoutParams redeemQuantityLayout = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				TextView redeemQuantity = new TextView(ctx);
				redeemQuantity.setLayoutParams(redeemQuantityLayout);
				redeemQuantity.setId(05);
				redeemQuantityLayout.addRule(RelativeLayout.BELOW,
						redeemCheckLabel.getId());
				redeemQuantityLayout.setMargins(0, dip20, 0, 0);
				redeemQuantity.setTextColor(Color.BLACK);
				redeemQuantity.setText("QUANTITY");
				redeemQuantity.setTypeface(null, Typeface.BOLD);

				RelativeLayout.LayoutParams editQuantityLayout = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				final EditText editQuantity = new EditText(ctx);
				editQuantity.setId(06);
				editQuantity.setLayoutParams(editQuantityLayout);
				editQuantityLayout.addRule(RelativeLayout.RIGHT_OF,
						redeemQuantity.getId());
				editQuantityLayout.addRule(RelativeLayout.ALIGN_BOTTOM,
						redeemQuantity.getId());
				editQuantityLayout.addRule(RelativeLayout.BELOW,
						redeemCheckLabel.getId());
				editQuantityLayout.setMargins(dip10, 0, 0, 0);
				editQuantity.setTextColor(Color.BLACK);
				editQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
				editQuantity.setHint("0");

				redeemCheckButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						String zero = "0";
						String inputQuantity = editQuantity.getText()
								.toString().trim();
						Integer userQuantity = 0;

						if (inputQuantity.length() == 0
								|| inputQuantity.equals(zero)) {
							Toast.makeText(getApplicationContext(),
									"Please redeem atleast 1 item.",
									Toast.LENGTH_LONG).show();

						} else {
							userQuantity = Integer.valueOf(editQuantity
									.getText().toString());

							int totalSukipointsComputation = userQuantity
									* r.getPoints();

							if (totalSukipointsComputation <= customer
									.getTotalSukiPoints()) {

								int computedSukiPoints = customer
										.getTotalSukiPoints()
										- totalSukipointsComputation;
								customer.setTotalSukiPoints(computedSukiPoints);
								customerDao.update(customer);

								// create Redeem transaction
								try {
									RedemptionTransaction rt = new RedemptionTransaction(
											r, customer, computedSukiPoints,
											userQuantity);
									redemptionTransactionDao.create(rt);
								} catch (Exception e) {
									// TODO: handle exception
								}

								Toast.makeText(
										getApplicationContext(),
										"Congratulations! You redeem "
												+ userQuantity + " items of "
												+ r.getName(),
										Toast.LENGTH_LONG).show();

								Intent intent = new Intent(Prize2Activity.this,
										MainActivity.class);
								startActivity(intent);

							} else {
								Toast.makeText(getApplicationContext(),
										"Not enough sukipoints.",
										Toast.LENGTH_LONG).show();
							}
						}
					}
				});

				relativeLayoutCatalog.addView(redeemQuantity);
				relativeLayoutCatalog.addView(editQuantity);

			} else {
				redeemCheckButton
						.setImageResource(R.drawable.ic_redeem_disabled);
				redeemCheckLabel.setTextColor(Color.DKGRAY);

				RelativeLayout.LayoutParams notEnoughPointsLayout = new RelativeLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				TextView notEnoughPoints = new TextView(ctx);
				notEnoughPointsLayout.setMargins(0, dip10, 0, 0);
				notEnoughPoints.setLayoutParams(notEnoughPointsLayout);
				notEnoughPoints.setPadding(dip5, dip5, dip5, dip5);
				notEnoughPoints.setId(07);
				notEnoughPointsLayout.addRule(RelativeLayout.BELOW,
						redeemCheckLabel.getId());
				notEnoughPoints.setText("Not enough points.");
				notEnoughPoints.setBackgroundResource(R.drawable.bg_pink);
				notEnoughPoints.setTextColor(Color.parseColor("#b44144"));
				notEnoughPoints.setTextSize(12);
				notEnoughPoints.setGravity(Gravity.CENTER);
				notEnoughPoints.setTypeface(null, Typeface.BOLD);

				relativeLayoutCatalog.addView(notEnoughPoints);
			}

			relativeLayoutCatalog.addView(redeemName);
			relativeLayoutCatalog.addView(redeemPoints);
			relativeLayoutCatalog.addView(redeemCheckButton);
			relativeLayoutCatalog.addView(redeemCheckLabel);

			catalog.addView(relativeLayoutCatalog);
		}

		*/

	}

	public static int convertDipToPx(Context ctx, int dps) {
		Resources r = ctx.getResources();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dps, r.getDisplayMetrics());
	}

	public Bitmap StringToBitMap(String encodedString) {
		try {
			byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
					encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
