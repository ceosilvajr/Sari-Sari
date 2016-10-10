package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.hatchpointsolutions.sssrewards.app.objects.Basket;

public class AddBasketActivity extends Activity {

	private EditText editTextBasketName;
	private EditText editTextConversionAmount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_basket);

		editTextBasketName = (EditText) findViewById(R.id.editTextBasketName);
		editTextConversionAmount = (EditText) findViewById(R.id.editTextConversionAmount);

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddBasketActivity.this.finish();
			}
		});

		ImageView buttonCancelBasket = (ImageView) findViewById(R.id.buttonCancelBasket);
		buttonCancelBasket.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddBasketActivity.this.finish();
			}
		});

		ImageView buttonSaveBasket = (ImageView) findViewById(R.id.buttonSaveBasket);

		buttonSaveBasket.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// We should trim the values
				String basketName = editTextBasketName.getText().toString()
						.trim();
				if (basketName.length() == 0) {
					editTextBasketName
							.setError("You have to specify the basket's name");
					return;
				}
				String strConversionAmount = editTextConversionAmount.getText()
						.toString().trim();
				if (strConversionAmount.length() == 0) {
					editTextConversionAmount
							.setError("You have to specify a conversion amount");
					return;
				}
				float conversionAmount = Float.parseFloat(strConversionAmount);
				if (conversionAmount == 0) {
					editTextConversionAmount
							.setError("You have to specify a conversion amount");
					return;
				}
				Basket basket = new Basket(basketName, conversionAmount, true);

//				RuntimeExceptionDao<Basket, Integer> basketDao = getHelper()
//						.getBasketRuntimeDao();
//
//				basketDao.create(basket);

				Intent i = new Intent(AddBasketActivity.this,
						AddItemActivity.class);
				i.putExtra("BASKET_ID", basket.getId());
				AddBasketActivity.this.startActivity(i);

				AddBasketActivity.this.finish();
			}
		});
	}
}
