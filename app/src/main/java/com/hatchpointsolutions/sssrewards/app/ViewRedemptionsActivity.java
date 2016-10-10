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

public class ViewRedemptionsActivity extends Activity{

	private LinearLayout redemptionsLayout;
	private Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_redemptions);

		ctx = this;


		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewRedemptionsActivity.this.finish();
			}
		});

		ImageView addRedemptionButton = (ImageView) findViewById(R.id.addRedemptionButton);
		addRedemptionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ViewRedemptionsActivity.this,
						AddRedemptionActivity.class);
				ViewRedemptionsActivity.this.startActivityForResult(i, 1);
			}
		});

		redemptionsLayout = (LinearLayout) findViewById(R.id.redemptionsLayout);

		populateRedemptions();

	}

	public void populateRedemptions() {
//		redemptionsLayout.removeAllViews();
//		try {
//			QueryBuilder<Redemption, Integer> qb = redemptionDao.queryBuilder();
//			qb.where().eq("active", true);
//			List<Redemption> list = qb.query();
//
//			for (final Redemption r : list) {
//
//				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
//						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//				RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
//						LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//				lp2.addRule(RelativeLayout.CENTER_VERTICAL);
//
//				LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
//						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//				RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
//						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//				lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//				lp4.addRule(RelativeLayout.CENTER_VERTICAL);
//
//				RelativeLayout rl = new RelativeLayout(ctx);
//				rl.setLayoutParams(lp1);
//				rl.setBackgroundResource(R.drawable.selector_redemption);
//
//				LinearLayout ll = new LinearLayout(ctx);
//				ll.setLayoutParams(lp2);
//				ll.setOrientation(LinearLayout.VERTICAL);
//				ll.setPadding(convertDipToPx(ctx, 60), 0, 0, 0);
//
//				TextView rn = new TextView(ctx);
//				rn.setLayoutParams(lp3);
//				rn.setTextColor(Color.WHITE);
//				rn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//				rn.setTypeface(null, Typeface.BOLD);
//				rn.setText(r.getName());
//
//				TextView sp = new TextView(ctx);
//				sp.setLayoutParams(lp3);
//				sp.setTextColor(Color.WHITE);
//				sp.setTypeface(null, Typeface.BOLD);
//				sp.setText(r.getPoints() + " suki points");
//
//				ll.addView(rn);
//				ll.addView(sp);
//
//				LinearLayout dl = new LinearLayout(ctx);
//				dl.setLayoutParams(lp4);
//				dl.setGravity(Gravity.CENTER);
//				dl.setOrientation(LinearLayout.VERTICAL);
//				dl.setPadding(0, 0, convertDipToPx(ctx, 20), 0);
//
//				// Imageview of delete
//				ImageView d = new ImageView(ctx);
//				d.setLayoutParams(lp3);
//				d.setBackgroundResource(R.drawable.button_delete);
//
//				// Textview of delete
//				TextView dtv = new TextView(ctx);
//				dtv.setLayoutParams(lp3);
//				dtv.setTextColor(Color.WHITE);
//				dtv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//				dtv.setTypeface(null, Typeface.BOLD);
//				dtv.setText("DELETE");
//
//				dl.addView(d);
//				dl.addView(dtv);
//
//				dl.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//
//						LayoutInflater layoutInflater = LayoutInflater
//								.from(ctx);
//						View delete = layoutInflater.inflate(
//								R.layout.promp_delete, null);
//						TextView text = (TextView) delete
//								.findViewById(R.id.delete_text);
//						text.setText("Are you sure you want to delete "
//								+ r.getName() + " from the prize list?");
//
//						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//								ctx);
//						alertDialogBuilder.setView(delete);
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
//												r.setActive(false);
//												redemptionDao.update(r);
//
//												populateRedemptions();
//
//											}
//
//										})
//								.setNegativeButton("Cancel",
//										new DialogInterface.OnClickListener() {
//											public void onClick(
//													DialogInterface dialog,
//													int id) {
//												dialog.cancel();
//											}
//										});
//
//						AlertDialog alertDialog = alertDialogBuilder.create();
//						alertDialog.show();
//					}
//
//				});
//
//				rl.addView(ll);
//				rl.addView(dl);
//
//				redemptionsLayout.addView(rl);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		populateRedemptions();
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
