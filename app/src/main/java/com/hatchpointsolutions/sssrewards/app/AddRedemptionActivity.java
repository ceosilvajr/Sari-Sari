package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.hatchpointsolutions.sssrewards.app.objects.Redemption;

public class AddRedemptionActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_redemption);

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddRedemptionActivity.this.finish();
			}
		});
		ImageView buttonCancelRedemption = (ImageView) findViewById(R.id.buttonCancelRedemption);
		buttonCancelRedemption.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddRedemptionActivity.this.finish();
			}
		});

		ImageView buttonSaveRedemption = (ImageView) findViewById(R.id.buttonSaveRedemption);
		final EditText editTextRedemptionName = (EditText) findViewById(R.id.editTextRedemptionName);
		final EditText editTextRedemptionPoints = (EditText) findViewById(R.id.editTextPoints);

		buttonSaveRedemption.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// We should trim the values
				String redemptionName = editTextRedemptionName.getText()
						.toString().trim();
				if (redemptionName.length() == 0) {
					editTextRedemptionName
							.setError("You have to specify the basket's name");
					return;
				}
				String strConversionAmount = editTextRedemptionPoints.getText()
						.toString().trim();
				if (strConversionAmount.length() == 0) {
					editTextRedemptionPoints
							.setError("You have to specify the number of points for this redemption");
					return;
				}
				int points = Integer.parseInt(strConversionAmount);
				if (points == 0) {
					editTextRedemptionPoints
							.setError("You have to specify the number of points for this redemption");
					return;
				}
				Redemption redemption = new Redemption(redemptionName, points, true);

//				RuntimeExceptionDao<Redemption, Integer> redemptionDao = getHelper()
//						.getRedemptionRuntimeDao();
//
//				redemptionDao.create(redemption);

				AddRedemptionActivity.this.finish();
			}
		});
	}

}
