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

import com.hatchpointsolutions.sssrewards.app.objects.Basket;

public class ViewItemsActivity extends Activity {

	private Context ctx;
	private LinearLayout addItemLayout;
	private Basket basket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_items);

		ctx = this;

		Bundle extras = getIntent().getExtras();

		int basketId = extras.getInt("BASKET_ID");

//		RuntimeExceptionDao<Basket, Integer> basketDao = getHelper()
//				.getBasketRuntimeDao();
//		basket = basketDao.queryForId(basketId);
//
//		itemDao = getHelper().getItemRuntimeDao();

		TextView basketNameTextView = (TextView) findViewById(R.id.basketNameTextView);
		basketNameTextView.setText(basket.getName());

		TextView conversionAmount = (TextView) findViewById(R.id.conversion_getAmount);
		conversionAmount.setText("Php. " + basket.getConversionAmount() + "0");

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewItemsActivity.this.finish();
			}
		});

		ImageView addItemButton = (ImageView) findViewById(R.id.addItemButton);

		addItemButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewItemsActivity.this,
						AddItemActivity.class);
				i.putExtra("BASKET_ID", basket.getId());
				ViewItemsActivity.this.startActivityForResult(i, 1);
			}
		});

		addItemLayout = (LinearLayout) findViewById(R.id.addItemLayout);

		populateItemsLayout();
	}

	public void populateItemsLayout() {
//
//		addItemLayout.removeAllViews();
//
//		try {
//			// Clear table layout
//			QueryBuilder<Item, Integer> qb = itemDao.queryBuilder();
//			qb.where().eq("basket_id", basket).and().eq("active", true);
//
//			List<Item> results = qb.query();
//
//			if (results.size() > 0) {
//
//				for (final Item i : results) {
//
//					RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
//							LayoutParams.MATCH_PARENT,
//							LayoutParams.WRAP_CONTENT);
//					RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
//							LayoutParams.WRAP_CONTENT,
//							LayoutParams.MATCH_PARENT);
//					lp2.addRule(RelativeLayout.CENTER_VERTICAL);
//
//					LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
//							LayoutParams.WRAP_CONTENT,
//							LayoutParams.WRAP_CONTENT);
//
//					RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
//							LayoutParams.WRAP_CONTENT,
//							LayoutParams.WRAP_CONTENT);
//					lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//					lp4.addRule(RelativeLayout.CENTER_VERTICAL);
//
//					final RelativeLayout rl = new RelativeLayout(ctx);
//					rl.setLayoutParams(lp1);
//					rl.setBackgroundResource(R.drawable.selector_redemption);
//
//					LinearLayout ll = new LinearLayout(ctx);
//					ll.setLayoutParams(lp2);
//					ll.setOrientation(LinearLayout.VERTICAL);
//					ll.setPadding(convertDipToPx(ctx, 60), 0, 0, 0);
//
//					TextView rn = new TextView(ctx);
//					rn.setLayoutParams(lp3);
//					rn.setTextColor(Color.WHITE);
//					rn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//					rn.setTypeface(null, Typeface.BOLD);
//					rn.setText(i.getName());
//
//					TextView sp = new TextView(ctx);
//					sp.setLayoutParams(lp3);
//					sp.setTextColor(Color.WHITE);
//					sp.setTypeface(null, Typeface.BOLD);
//					sp.setText("Php. " + i.getSrp() + "0");
//
//					ll.addView(rn);
//					ll.addView(sp);
//
//					LinearLayout dl = new LinearLayout(ctx);
//					dl.setLayoutParams(lp4);
//					dl.setGravity(Gravity.CENTER);
//					dl.setOrientation(LinearLayout.VERTICAL);
//					dl.setPadding(0, 0, convertDipToPx(ctx, 20), 0);
//
//					// Imageview of delete
//					ImageView d = new ImageView(ctx);
//					d.setLayoutParams(lp3);
//					d.setBackgroundResource(R.drawable.button_delete);
//
//					// Textview of delete
//					TextView dtv = new TextView(ctx);
//					dtv.setLayoutParams(lp3);
//					dtv.setTextColor(Color.WHITE);
//					dtv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//					dtv.setTypeface(null, Typeface.BOLD);
//					dtv.setText("DELETE");
//
//					dl.addView(d);
//					dl.addView(dtv);
//
//					dl.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//
//							LayoutInflater layoutInflater = LayoutInflater
//									.from(ctx);
//							View delete = layoutInflater.inflate(
//									R.layout.promp_delete, null);
//							TextView text = (TextView) delete
//									.findViewById(R.id.delete_text);
//							text.setText("Are you sure you want to delete "
//									+ i.getName() + " from the item list?");
//
//							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//									ctx);
//							alertDialogBuilder.setView(delete);
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
//													i.setActive(false);
//													itemDao.update(i);
//													populateItemsLayout();
//
//												}
//
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
//
//							AlertDialog alertDialog = alertDialogBuilder
//									.create();
//							alertDialog.show();
//						}
//
//					});
//
//					rl.addView(ll);
//					rl.addView(dl);
//
//					addItemLayout.addView(rl);
//
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		populateItemsLayout();
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
