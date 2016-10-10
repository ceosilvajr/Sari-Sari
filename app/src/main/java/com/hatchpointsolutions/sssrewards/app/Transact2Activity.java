package com.hatchpointsolutions.sssrewards.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hatchpointsolutions.sssrewards.app.objects.Basket;
import com.hatchpointsolutions.sssrewards.app.objects.Item;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Transact2Activity extends Activity{

	private List<Basket> basketList;
	private Context ctx;

	private List<ItemQty> itemQtyList;
	private List<BasketLayout> basketLayoutList;
	private TextView totalSukiPointsTextView;
	private int purchaseTotalSukiPoints = 0;
	private EditText receiptAmountEditText;
	private DecimalFormat decimalFormat;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transact2);

		ctx = this;

		decimalFormat = new DecimalFormat();
		decimalFormat.setMinimumFractionDigits(2);
		decimalFormat.setMaximumFractionDigits(2);

		Bundle extras = getIntent().getExtras();
		int customerId = extras.getInt("CUSTOMER_ID");

		ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Transact2Activity.this.finish();
			}
		});

		TextView mobileNumberTextView = (TextView) findViewById(R.id.mobileNumberTextView);
//		mobileNumberTextView.setText(customer.getMobileNumber());

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(c.getTime());
		TextView currentDate = (TextView) findViewById(R.id.purchaseDateEditText);
		currentDate.setText(formattedDate);

//		basketsLayout = (LinearLayout) findViewById(R.id.basketsLayout);
		totalSukiPointsTextView = (TextView) findViewById(R.id.totalSukiPointsTextView);
		receiptAmountEditText = (EditText) findViewById(R.id.receiptAmountEditText);
		Button transactNextButton = (Button) findViewById(R.id.transactNextButton);
		transactNextButton.setOnClickListener(new SavePurchases());

		itemQtyList = new ArrayList<ItemQty>();
		basketLayoutList = new ArrayList<BasketLayout>();

		// populate baskets
		populateBaskets();
	}

	public void populateBaskets() {
	/*	// Clear baskets
		basketsLayout.removeAllViews();

		// Query for baskets
		basketList = basketDao.queryForAll();

		int dip10 = convertDipToPx(ctx, 10);

		int basketInc = 1;
		for (Basket b : basketList) {
			try {
				// for each basket lets query the items
				QueryBuilder<Item, Integer> queryBuilder = itemDao
						.queryBuilder();
				List<Item> items = queryBuilder.where()
						.eq("basket_id", b.getId()).query();

				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

				LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(
						TableLayout.LayoutParams.MATCH_PARENT,
						TableLayout.LayoutParams.WRAP_CONTENT);
				TableRow.LayoutParams rowParams = new TableRow.LayoutParams(
						TableRow.LayoutParams.WRAP_CONTENT,
						TableRow.LayoutParams.WRAP_CONTENT);

				LinearLayout.LayoutParams blLp = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				blLp.setMargins(0, 0, 0, convertDipToPx(ctx, 10));
				LinearLayout bl = new LinearLayout(ctx);
				bl.setLayoutParams(blLp);
				bl.setOrientation(LinearLayout.VERTICAL);
				bl.setPadding(dip10, dip10, dip10, dip10);
				switch (basketInc % 3) {
				case 2:
					bl.setBackgroundResource(R.drawable.bg_basket1);
					break;
				case 1:
					bl.setBackgroundResource(R.drawable.bg_basket2);
					break;
				case 0:
					bl.setBackgroundResource(R.drawable.bg_basket3);
					break;
				}
				basketInc++;

				// Basket header
				LinearLayout bhl = new LinearLayout(ctx);
				bhl.setLayoutParams(lp);
				bhl.setPadding(0, 0, 0, dip10);
				bhl.setGravity(Gravity.CENTER_VERTICAL);
				bhl.setOrientation(LinearLayout.HORIZONTAL);

				// Basket icon
				ImageView biv = new ImageView(ctx);
				biv.setLayoutParams(lp1);
				biv.setImageResource(R.drawable.ic_basket_orange);
				biv.setPadding(0, 0, dip10, 0);

				// Basket name
				TextView btv = new TextView(ctx);
				btv.setLayoutParams(lp1);
				btv.setTextColor(Color.WHITE);
				btv.setTypeface(null, Typeface.BOLD);
				btv.setText(b.getName());

				bhl.addView(biv);
				bhl.addView(btv);

				bl.addView(bhl);

				int itemInc = 1;
				for (Item i : items) {
					// Items layout
					RelativeLayout il = new RelativeLayout(ctx);
					il.setLayoutParams(lp);
					if ((itemInc % 2) > 0)
						il.setBackgroundColor(Color.WHITE);
					else
						il.setBackgroundColor(Color.parseColor("#f0f0f0"));

					il.setPadding(dip10, dip10, dip10, dip10);

					// Item details table layout

					TableLayout itl = new TableLayout(ctx);
					itl.setId(101);
					itl.setLayoutParams(tableParams);
					itl.setColumnStretchable(0, true);

					// Table row for item header
					TableRow htr = new TableRow(ctx);
					htr.setLayoutParams(tableParams);

					// TextView for header item
					TextView hi = new TextView(ctx);
					hi.setLayoutParams(rowParams);
					hi.setTextColor(Color.GRAY);
					hi.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
					hi.setText("Item #" + itemInc);

					// TextView for header item
					TextView hs = new TextView(ctx);
					hs.setLayoutParams(rowParams);
					hs.setTextColor(Color.GRAY);
					hs.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
					hs.setMinWidth(convertDipToPx(ctx, 70));
					hs.setText("SRP");

					// TextView for header item
					TextView ha = new TextView(ctx);
					ha.setLayoutParams(rowParams);
					ha.setTextColor(Color.GRAY);
					ha.setMinWidth(convertDipToPx(ctx, 80));
					ha.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
					ha.setText("AMOUNT");

					htr.addView(hi);
					htr.addView(hs);
					htr.addView(ha);

					// New table row
					TableRow tr = new TableRow(ctx);
					tr.setLayoutParams(tableParams);

					TextView itnv = new TextView(ctx);
					itnv.setLayoutParams(rowParams);
					itnv.setTextColor(Color.BLACK);
					itnv.setTypeface(null, Typeface.BOLD);
					itnv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
					itnv.setText(i.getName());

					// TextView for header item
					TextView its = new TextView(ctx);
					its.setLayoutParams(rowParams);
					its.setTextColor(Color.BLACK);
					its.setTypeface(null, Typeface.BOLD);
					its.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
					its.setText("P" + decimalFormat.format(i.getSrp()));

					// TextView for header item
					TextView ita = new TextView(ctx);
					ita.setLayoutParams(rowParams);
					ita.setTextColor(Color.BLACK);
					ita.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
					ita.setText("P0.00");

					tr.addView(itnv);
					tr.addView(its);
					tr.addView(ita);

					itl.addView(htr);
					itl.addView(tr);

					// LinearLayout for quantity controls
					RelativeLayout.LayoutParams rlu = new RelativeLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					rlu.addRule(RelativeLayout.BELOW, itl.getId());
					rlu.setMargins(0, dip10, 0, 0);
					LinearLayout lu = new LinearLayout(ctx);
					lu.setId(102);
					lu.setLayoutParams(rlu);
					lu.setOrientation(LinearLayout.VERTICAL);

					// Text for unit label
					TextView utv = new TextView(ctx);
					utv.setLayoutParams(lp1);
					utv.setTextColor(Color.GRAY);
					utv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
					utv.setTypeface(null, Typeface.BOLD);
					utv.setText("Units");

					// Add unit text label on linear layout
					lu.addView(utv);

					// Edittext for quantity input
					RelativeLayout.LayoutParams lpqty = new RelativeLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					lpqty.addRule(RelativeLayout.ALIGN_BOTTOM, lu.getId());
					lpqty.addRule(RelativeLayout.RIGHT_OF, lu.getId());
					final EditText qtyInput = new EditText(ctx);
					qtyInput.setLayoutParams(lpqty);
					qtyInput.setHint("Quantity");
					qtyInput.setInputType(InputType.TYPE_CLASS_NUMBER);
					qtyInput.addTextChangedListener(new TextWatcher() {

						@Override
						public void afterTextChanged(Editable s) {
							computeTotal();
						}

						@Override
						public void beforeTextChanged(CharSequence s,
								int start, int count, int after) {
						}

						@Override
						public void onTextChanged(CharSequence s, int start,
								int before, int count) {
						}

					});
					ItemQty iq = new ItemQty(b, i, qtyInput, ita);
					itemQtyList.add(iq);

					// LinearLayout for unit buttons
					LinearLayout lub = new LinearLayout(ctx);
					lub.setLayoutParams(lp1);
					lub.setOrientation(LinearLayout.HORIZONTAL);

					for (int uInc = 1; uInc <= 5; uInc++) {

						ImageView ub = new ImageView(ctx);
						ub.setLayoutParams(lp1);
						ub.setPadding(0, 0, convertDipToPx(ctx, 5), 0);
						switch (uInc) {
						case 1:
							ub.setImageResource(R.drawable.ic_1_on);
							break;
						case 2:
							ub.setImageResource(R.drawable.ic_2_on);
							break;
						case 3:
							ub.setImageResource(R.drawable.ic_3_on);
							break;
						case 4:
							ub.setImageResource(R.drawable.ic_4_on);
							break;
						case 5:
							ub.setImageResource(R.drawable.ic_5_on);
							break;
						}

						final int qtyInc = uInc;

						ub.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								qtyInput.setText(qtyInc + "");
								computeTotal();
							}

						});

						lub.addView(ub);
					}

					lu.addView(lub);
					lu.addView(qtyInput);

					il.addView(itl);
					il.addView(lu);
					bl.addView(il);

					itemInc++;
				}

				// View for the summary of the basket
				LinearLayout.LayoutParams sblp = new LinearLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				LinearLayout sbl = new LinearLayout(ctx);
				sbl.setLayoutParams(sblp);
				sbl.setBackgroundColor(Color.parseColor("#ece8e7"));
				sbl.setOrientation(LinearLayout.VERTICAL);

				// New table layout for the summary
				TableLayout stl = new TableLayout(ctx);
				stl.setLayoutParams(tableParams);
				stl.setPadding(dip10, dip10, dip10, dip10);
				stl.setColumnStretchable(0, true);

				// Summary header row
				TableRow str = new TableRow(ctx);
				str.setLayoutParams(tableParams);

				// New textview for total amount
				TextView totalAmountLabel = new TextView(ctx);
				totalAmountLabel.setLayoutParams(rowParams);
				totalAmountLabel.setTextColor(Color.parseColor("#989898"));
				totalAmountLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
				totalAmountLabel.setTypeface(null, Typeface.BOLD);
				totalAmountLabel.setText("TOTAL AMOUNT");

				// New textview for TOTAL SUKI POINTS
				TextView totalSukiLabel = new TextView(ctx);
				totalSukiLabel.setLayoutParams(rowParams);
				totalSukiLabel.setTextColor(Color.parseColor("#989898"));
				totalSukiLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
				totalSukiLabel.setTypeface(null, Typeface.BOLD);
				totalSukiLabel.setText("TOTAL SUKI POINTS");

				// DONE WITH THE HEADER ROW
				str.addView(totalAmountLabel);
				str.addView(totalSukiLabel);

				// Row for the actual amount
				TableRow str2 = new TableRow(ctx);
				str2.setLayoutParams(tableParams);

				// New textview for total amount
				TextView totalAmountTextView = new TextView(ctx);
				totalAmountTextView.setLayoutParams(rowParams);
				totalAmountTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				totalAmountTextView.setTypeface(null, Typeface.BOLD);
				totalAmountTextView.setText("P0.00");

				// New textview for total suki points
				TextView totalSukiTextView = new TextView(ctx);
				totalSukiTextView.setLayoutParams(rowParams);
				totalSukiTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
				totalSukiTextView.setTextColor(Color.parseColor("#1e7800"));
				totalSukiTextView.setGravity(Gravity.RIGHT);
				totalSukiTextView.setTypeface(null, Typeface.BOLD);
				totalSukiTextView.setText("0");

				str2.addView(totalAmountTextView);
				str2.addView(totalSukiTextView);

				stl.addView(str);
				stl.addView(str2);

				sbl.addView(stl);

				BasketLayout summaryBasketLayout = new BasketLayout(b);
				summaryBasketLayout.setTotalAmountTextView(totalAmountTextView);
				summaryBasketLayout
						.setTotalSukiPointsTextView(totalSukiTextView);
				basketLayoutList.add(summaryBasketLayout);

				// Linearlayout for basket points details
				LinearLayout dl = new LinearLayout(ctx);
				dl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT));
				dl.setGravity(Gravity.RIGHT);
				dl.setOrientation(LinearLayout.VERTICAL);
				dl.setPadding(0, 0, dip10, 0);

				// New textview for TOTAL SUKI POINTS
				TextView conversionAmountTextView = new TextView(ctx);
				conversionAmountTextView.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				conversionAmountTextView.setTextSize(
						TypedValue.COMPLEX_UNIT_SP, 10);
				conversionAmountTextView.setTypeface(null, Typeface.BOLD);
				conversionAmountTextView.setText("CONVERSION AMOUNT:");

				// New textview for TOTAL SUKI POINTS
				TextView sukiPointTextView = new TextView(ctx);
				sukiPointTextView.setLayoutParams(new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				sukiPointTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
				sukiPointTextView.setTypeface(null, Typeface.BOLD);
				sukiPointTextView.setText("1 SUKI POINT = P"
						+ decimalFormat.format(b.getConversionAmount()));

				dl.addView(conversionAmountTextView);
				dl.addView(sukiPointTextView);

				sbl.addView(dl);
				bl.addView(sbl);

				basketsLayout.addView(bl);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		*/
	}

	public void computeTotal() {

		for (BasketLayout bl : basketLayoutList) {
			bl.setTotalAmount(0);
		}
		for (ItemQty i : itemQtyList) {
			i.updateTextViewAmount();

			for (BasketLayout bl : basketLayoutList) {
				if (bl.getBasket().equals(i.getBasket())) {
					bl.setTotalAmount(bl.getTotalAmount()
							+ i.getTotalItemAmount());
				}
			}
		}
		int totalSukiPoints = 0;
		for (BasketLayout bl : basketLayoutList) {
			bl.updateValues();
			totalSukiPoints = totalSukiPoints + bl.getTotalSukiPoints();
		}

		totalSukiPointsTextView.setText("" + totalSukiPoints);
		purchaseTotalSukiPoints = totalSukiPoints;
	}

	public class ItemQty {

		private Basket basket;
		private Item item;
		private EditText qty;
		private TextView textViewAmount;

		ItemQty(Basket b, Item i, EditText q, TextView ta) {
			this.basket = b;
			this.item = i;
			this.qty = q;
			this.textViewAmount = ta;
		}

		public float getQty() {
			String q = qty.getText().toString().trim();
			if (q.length() > 0) {
				return Float.parseFloat(q);
			} else {
				return 0;
			}
		}

		public Basket getBasket() {
			return basket;
		}

		public Item getItem() {
			return item;
		}

		public TextView getTextViewAmount() {
			return textViewAmount;
		}

		public float getTotalItemAmount() {
			return this.getQty() * item.getSrp();
		}

		public void updateTextViewAmount() {
			float totalAmount = this.getTotalItemAmount();
			this.getTextViewAmount().setText(
					"P" + decimalFormat.format(totalAmount));
		}
	}

	public class BasketLayout {

		private Basket basket;
		private TextView totalAmountTextView;
		private TextView totalSukiPointsTextView;
		private float totalAmount = 0;
		private int totalSukiPoints = 0;

		public BasketLayout(Basket b) {
			this.basket = b;
		}

		public Basket getBasket() {
			return basket;
		}

		public void setBasket(Basket basket) {
			this.basket = basket;
		}

		public TextView getTotalAmountTextView() {
			return totalAmountTextView;
		}

		public void setTotalAmountTextView(TextView totalAmountTextView) {
			this.totalAmountTextView = totalAmountTextView;
		}

		public TextView getTotalSukiPointsTextView() {
			return totalSukiPointsTextView;
		}

		public void setTotalSukiPointsTextView(TextView totalSukiPointsTextView) {
			this.totalSukiPointsTextView = totalSukiPointsTextView;
		}

		public float getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(float totalAmount) {
			this.totalAmount = totalAmount;
		}

		public int getTotalSukiPoints() {
			return totalSukiPoints;
		}

		public void setTotalSukiPoints(int totalSukiPoints) {
			this.totalSukiPoints = totalSukiPoints;
		}

		public void updateValues() {
			this.getTotalAmountTextView().setText(
					"P" + decimalFormat.format(this.getTotalAmount()));
			int points = (int) (totalAmount / basket.getConversionAmount());

			this.setTotalSukiPoints(points);

			this.getTotalSukiPointsTextView().setText("" + points);
		}

	}

	public class SavePurchases implements OnClickListener {

		@Override
		public void onClick(View v) {
			// Get the reciept amount
			String ra = receiptAmountEditText.getText().toString().trim();
			if (ra.length() == 0) {
				receiptAmountEditText
						.setError("Receipt amount needs to be specified");
				Toast.makeText(ctx, "Receipt amount needs to be specified",
						Toast.LENGTH_SHORT).show();
				return;
			}
			float receiptAmount = Float.parseFloat(ra);

			int totalItemsPurchased = 0;
			float totalReceiptAmount = 0;
			for (ItemQty i : itemQtyList) {
				if (i.getQty() > 0) {
					totalReceiptAmount = totalReceiptAmount
							+ i.getTotalItemAmount();
					totalItemsPurchased++;
				}
			}

			System.out.println("receipt amont input is: " + receiptAmount
					+ " total receipt amount is: " + totalReceiptAmount);
			if (totalReceiptAmount > receiptAmount) {
				receiptAmountEditText.setError("Receipt amount is not valid");
				Toast.makeText(
						ctx,
						"Receipt amount needs to be greater than the cost of the items purchased",
						Toast.LENGTH_SHORT).show();
				return;
			}

			if (totalItemsPurchased == 0) {
				Toast.makeText(ctx,
						"You need to select at least 1 item for purchase",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// We create a purchase
//			Purchase purchase = new Purchase(customer, purchaseTotalSukiPoints,
//					receiptAmount, new Date());

//			purchaseDao.create(purchase);
//			customer.setTotalSukiPoints(purchaseTotalSukiPoints
//					+ customer.getTotalSukiPoints());
//			customerDao.update(customer);

//			Log.i("p", purchase.toString());

			// So we make purchased items for this purchase

			for (ItemQty i : itemQtyList) {
				if (i.getQty() > 0) {
					totalItemsPurchased++;
//					PurchasedItem pi = new PurchasedItem(purchase, i.getItem(),
//							i.getTotalItemAmount(), (int) (i.getQty()));
//
//					purchasedItemDao.create(pi);

//					Log.i("p", pi.toString());
				}
			}

			Toast.makeText(ctx, "Transaction saved", Toast.LENGTH_LONG).show();
			Transact2Activity.this.finish();

		}
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