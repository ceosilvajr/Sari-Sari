package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hatchpointsolutions.sssrewards.app.objects.Basket;
import com.hatchpointsolutions.sssrewards.app.objects.Item;

public class AddItemActivity extends Activity {

	private Basket basket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);

		Bundle extras = getIntent().getExtras();

		int basketId = extras.getInt("BASKET_ID");

		//basket = basketDao.queryForId(basketId);

		TextView basketNameTextView = (TextView) findViewById(R.id.basketNameTextView);
		basketNameTextView.setText(basket.getName());

		final EditText itemNameEditText = (EditText) findViewById(R.id.editTextItemName);
		final EditText itemSrpEditText = (EditText) findViewById(R.id.editTextSrp);
		ImageView buttonSaveItem = (ImageView) findViewById(R.id.buttonSaveItem);
		buttonSaveItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Validate fields
				String itemName = itemNameEditText.getText().toString().trim();
				if (itemName.length() == 0) {
					itemNameEditText
							.setError("You have to specify an item name.");
					return;
				}
				String itemSrp = itemSrpEditText.getText().toString();
				if (itemSrp.length() == 0) {
					itemSrpEditText
							.setError("You have to set an SRP for this item.");
					return;
				}
				float srp = Float.parseFloat(itemSrp);
				if (srp <= 0) {
					itemSrpEditText
							.setError("You have to set a valid SRP for this item.");
					return;
				}
				Item i = new Item(basket, itemName, srp, true);
				//itemDao.create(i);

				finishActivity();
			}
		});

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finishActivity();
			}
		});

		ImageView buttonCancelItem = (ImageView) findViewById(R.id.buttonCancelItem);
		buttonCancelItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finishActivity();
			}
		});
	}

	@Override
	public void onBackPressed() {
		finishActivity();
	}

	public void finishActivity() {
		Intent intent = new Intent(AddItemActivity.this,
				ViewItemsActivity.class);
		intent.putExtra("BASKET_ID", basket.getId());
		AddItemActivity.this.startActivity(intent);
		AddItemActivity.this.finish();
	}

}
