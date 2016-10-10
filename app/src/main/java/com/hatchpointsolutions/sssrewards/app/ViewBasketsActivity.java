package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewBasketsActivity extends Activity {

	private LinearLayout basketsLayout;
	Context ctx;
	private TextView contentStatementTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		setContentView(R.layout.activity_view_baskets);

		ImageView addBasketButton = (ImageView) findViewById(R.id.addBasketButton);
		basketsLayout = (LinearLayout) findViewById(R.id.basketsLayout);

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewBasketsActivity.this.finish();
			}
		});

		contentStatementTextView = (TextView) findViewById(R.id.contentStatementTextView);

		addBasketButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewBasketsActivity.this,
						AddBasketActivity.class);
				ViewBasketsActivity.this.startActivity(i);
				ViewBasketsActivity.this.finish();
			}
		});

		populateBasketList();

	}

	public void populateBasketList() {
		basketsLayout.removeAllViews();

//		RuntimeExceptionDao<Basket, Integer> basketDao = getHelper()
//				.getBasketRuntimeDao();
//		List<Basket> list = basketDao.queryForAll();

//		contentStatementTextView.setText("MAY TOTAL NA " + list.size()
//				+ " PROMOS DITO:");
//
//		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		lp2.setMargins(convertDipToPx(ctx, 60), 0, 0, 0);
//
//		for (Basket b : list) {
//
//			// Create first linearlayout
//			LinearLayout bl = new LinearLayout(ctx);
//
//			bl.setLayoutParams(lp1);
//			bl.setOrientation(LinearLayout.HORIZONTAL);
//			bl.setBackgroundResource(R.drawable.selector_basket);
//			bl.setGravity(Gravity.CENTER_VERTICAL);
//
//			TextView tv = new TextView(ctx);
//			tv.setLayoutParams(lp2);
//			tv.setTextColor(Color.WHITE);
//			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//			tv.setTypeface(null, Typeface.BOLD);
//			tv.setText(b.getName());
//
//			bl.addView(tv);
//			final int basketId = b.getId();
//
//			bl.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Intent i = new Intent(ViewBasketsActivity.this,
//							ViewItemsActivity.class);
//					i.putExtra("BASKET_ID", basketId);
//					ViewBasketsActivity.this.startActivityForResult(i, 1);
//				}
//			});
//
//			basketsLayout.addView(bl);
//		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		populateBasketList();
	}

	/**
	 * Converts a given "dip" value to its pixels
	 * 
	 * @param ctx
	 * @param dps
	 * @return
	 */
	public static int convertDipToPx(Context ctx, int dps) {
		Resources r = ctx.getResources();
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dps, r.getDisplayMetrics());
	}

}
