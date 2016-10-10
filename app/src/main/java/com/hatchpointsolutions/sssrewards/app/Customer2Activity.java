package com.hatchpointsolutions.sssrewards.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hatchpointsolutions.sssrewards.app.objects.Customer;
import com.hatchpointsolutions.sssrewards.app.objects.Purchase;
import com.hatchpointsolutions.sssrewards.app.objects.PurchasedItem;
import com.hatchpointsolutions.sssrewards.app.objects.RedemptionTransaction;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Customer2Activity extends Activity {

    private Customer customer;
    private List<Purchase> customerPurchases;
    private LinearLayout purchaseLayout;
    private Context ctx;
    private List<RedemptionTransaction> redemptionTransactionList;
    private LinearLayout redemptionLayout;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_customer2);

        decimalFormat = new DecimalFormat();
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);

        ImageView prevButton = (ImageView) findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer2Activity.this.finish();
            }
        });

        Bundle extras = getIntent().getExtras();

        int customerId = extras.getInt("CUSTOMER_ID");

        purchaseLayout = (LinearLayout) findViewById(R.id.purchaseLayout);
        redemptionLayout = (LinearLayout) findViewById(R.id.redemptionLayout);

        TextView customerTotalSukiPoints = (TextView) findViewById(R.id.customerTotalSukiPoints);
        customerTotalSukiPoints.setText(customer.getTotalSukiPoints() + "");

        TextView mobileNumberTextView = (TextView) findViewById(R.id.mobileNumberTextView);
        mobileNumberTextView.setText(customer.getMobileNumber());

        TextView name = (TextView) findViewById(R.id.registeredCustomerName);
        name.setText(customer.getCustomerFirstName() + " "
                + customer.getCustomerMiddleName() + " "
                + customer.getCustomerLastName());

        TextView birthDate = (TextView) findViewById(R.id.registeredCustomerBirthDay);
        birthDate.setText(customer.getCustomerBirthDate());

        TextView address = (TextView) findViewById(R.id.registeredCustomerAddress);
        address.setText(customer.getCustomerAddress() + " "
                + customer.getCustomerCity() + " "
                + customer.getCustomerRegion());

        ImageView photo = (ImageView) findViewById(R.id.registeredCustomerPhoto);
        // Integer image = Integer.valueOf(customer.getCustomerPicture());
        Bitmap image = StringToBitMap(customer.getCustomerPicture());
        photo.setImageBitmap(image);

        // Let's grab all necessary data - let's start with purchases
        customerPurchases = new ArrayList<Purchase>();
//        try {
//
//            QueryBuilder<RedemptionTransaction, Integer> redemptionTransQueryBuilder = redemptionTransactionDao
//                    .queryBuilder();
//            redemptionTransQueryBuilder.where().eq("customer_id", customerId);
//            redemptionTransQueryBuilder.orderBy("dateCreated", false);
//            redemptionTransactionList = redemptionTransQueryBuilder.query();
//
//            QueryBuilder<Purchase, Integer> purchasesQueryBuilder = purchaseDao
//                    .queryBuilder();
//            purchasesQueryBuilder.where().eq("customer_id", customerId);
//            purchasesQueryBuilder.orderBy("dateCreated", false);
//            customerPurchases = purchasesQueryBuilder.query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        populatePurchases();

    }

    @SuppressLint("SimpleDateFormat")
    public void populatePurchases() {
        purchaseLayout.removeAllViews();
        redemptionLayout.removeAllViews();

        int dip10 = convertDipToPx(ctx, 10);
        int dip20 = convertDipToPx(ctx, 20);
        int dip30 = convertDipToPx(ctx, 30);
        int dip40 = convertDipToPx(ctx, 40);
        int dip80 = convertDipToPx(ctx, 80);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

        if (redemptionTransactionList.size() > 0) {
            int rInc = 0;
            // Redemptions first
            for (RedemptionTransaction r : redemptionTransactionList) {
                rInc++;
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0, dip20, 0, 0);
                LinearLayout ll1 = new LinearLayout(ctx);
                ll1.setLayoutParams(lp1);
                ll1.setBackgroundResource(R.drawable.bg_basket3);
                ll1.setOrientation(LinearLayout.VERTICAL);
                ll1.setPadding(dip10, dip10, dip10, dip10);

                // Header
                LinearLayout ll2 = new LinearLayout(ctx);
                ll2.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                ll2.setGravity(Gravity.CENTER_VERTICAL);
                ll2.setOrientation(LinearLayout.HORIZONTAL);
                ll2.setPadding(0, 0, 0, dip10);

                ImageView iv1 = new ImageView(ctx);
                iv1.setLayoutParams(new LayoutParams(dip40, dip30));
                iv1.setScaleType(ScaleType.FIT_XY);
                iv1.setPadding(0, 0, dip10, 0);
                iv1.setImageResource(R.drawable.button_redemption_add_on);

                TextView tv1 = new TextView(ctx);
                tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv1.setTextColor(Color.WHITE);
                tv1.setTypeface(null, Typeface.BOLD);
                tv1.setText("PRIZE");

                ll2.addView(iv1);
                ll2.addView(tv1);

                ll1.addView(ll2);

                // Redemption item
                LinearLayout.LayoutParams lp2 = new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0, 0, 0, dip10);
                LinearLayout ll3 = new LinearLayout(ctx);
                ll3.setLayoutParams(lp2);
                ll3.setBackgroundColor(Color.WHITE);
                ll3.setOrientation(LinearLayout.VERTICAL);

                TableLayout.LayoutParams tl1Params = new TableLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                TableRow.LayoutParams trParams = new TableRow.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                TableLayout tl1 = new TableLayout(ctx);
                tl1.setLayoutParams(tl1Params);
                tl1.setPadding(dip10, dip10, dip10, dip10);
                tl1.setColumnStretchable(0, true);

                TableRow tr1 = new TableRow(ctx);
                tr1.setLayoutParams(tl1Params);

                TextView tv2 = new TextView(ctx);
                tv2.setLayoutParams(trParams);
                tv2.setTextColor(Color.parseColor("#797979"));
                tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv2.setText("Item #" + rInc);

                TextView tv3 = new TextView(ctx);
                tv3.setLayoutParams(trParams);
                tv3.setTextColor(Color.parseColor("#797979"));
                tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv3.setMinWidth(dip80);
                tv3.setText("Quantity");

                TextView tv4 = new TextView(ctx);
                tv4.setLayoutParams(trParams);
                tv4.setTextColor(Color.parseColor("#797979"));
                tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv4.setMinWidth(dip80);
                tv4.setText("Date Redeemed");

                tr1.addView(tv2);
                tr1.addView(tv3);
                tr1.addView(tv4);

                TableRow tr2 = new TableRow(ctx);
                tr2.setLayoutParams(tl1Params);

                TextView tv5 = new TextView(ctx);
                tv5.setLayoutParams(trParams);
                tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv5.setText(r.getRedemption().getName());

                TextView tv6 = new TextView(ctx);
                tv6.setLayoutParams(trParams);
                tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv6.setText(r.getQuantity() + "");

                TextView tv7 = new TextView(ctx);
                tv7.setLayoutParams(trParams);
                tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv7.setText(dateFormatter.format(r.getDateCreated()));

                tr2.addView(tv5);
                tr2.addView(tv6);
                tr2.addView(tv7);

                tl1.addView(tr1);
                tl1.addView(tr2);

                ll3.addView(tl1);

                // Second tablelayout
                TableLayout.LayoutParams tl2Params = new TableLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                TableRow.LayoutParams tr2Params = new TableRow.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                TableLayout tl2 = new TableLayout(ctx);
                tl2.setLayoutParams(tl2Params);
                tl2.setBackgroundColor(Color.parseColor("#ece8e7"));
                tl2.setPadding(dip10, dip10, dip10, dip10);
                tl2.setColumnStretchable(0, true);

                TableRow tr3 = new TableRow(ctx);
                tr3.setLayoutParams(tl2Params);

                TextView tv8 = new TextView(ctx);
                tv8.setLayoutParams(tr2Params);
                tv8.setTextColor(Color.parseColor("#989898"));
                tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv8.setTypeface(null, Typeface.BOLD);
                tv8.setText("SUKI POINTS COST");

                tr3.addView(tv8);

                TableRow tr4 = new TableRow(ctx);
                tr4.setLayoutParams(tl2Params);

                TextView tv10 = new TextView(ctx);
                tv10.setLayoutParams(tr2Params);
                tv10.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv10.setTypeface(null, Typeface.BOLD);
                tv10.setText(r.getRedemption().getPoints() + "");

                tr4.addView(tv10);

                tl2.addView(tr3);
                tl2.addView(tr4);

                ll3.addView(tl2);

                ll1.addView(ll3);
                redemptionLayout.addView(ll1);

            }
        }

        if (customerPurchases.size() > 0) {
            for (Purchase p : customerPurchases) {

                Log.i("purchase", "purchase found");
                // New LinearLayout
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                lp1.setMargins(0, dip20, 0, 0);
                LinearLayout ll1 = new LinearLayout(ctx);
                ll1.setLayoutParams(lp1);
                ll1.setPadding(dip10, dip10, dip10, dip10);
                ll1.setOrientation(LinearLayout.VERTICAL);
                ll1.setBackgroundResource(R.drawable.bg_basket1);

                // Header
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                LinearLayout ll2 = new LinearLayout(ctx);
                ll2.setLayoutParams(lp2);
                ll2.setGravity(Gravity.CENTER_VERTICAL);
                ll2.setPadding(0, 0, 0, dip10);
                ll2.setOrientation(LinearLayout.HORIZONTAL);

                // Image view of header icon
                ImageView iv1 = new ImageView(ctx);
                iv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                iv1.setPadding(0, 0, dip10, 0);
                iv1.setImageResource(R.drawable.ic_basket_orange);

                TextView purchaseIdTextView = new TextView(ctx);
                purchaseIdTextView.setLayoutParams(new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                purchaseIdTextView.setTextColor(Color.WHITE);
                purchaseIdTextView.setTypeface(null, Typeface.BOLD);
                purchaseIdTextView.setText("Purchase ID#" + p.getId());

                TextView purchaseDateTextView = new TextView(ctx);
                purchaseDateTextView.setLayoutParams(new LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                purchaseDateTextView.setGravity(Gravity.RIGHT);
                purchaseDateTextView.setTextColor(Color.WHITE);
                purchaseDateTextView.setTypeface(null, Typeface.BOLD);
                purchaseDateTextView.setText(dateFormatter.format(p
                        .getDateCreated()));

                ll2.addView(iv1);
                ll2.addView(purchaseIdTextView);
                ll2.addView(purchaseDateTextView);

                ll1.addView(ll2);

                // Query for items of this purchase
                List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
//                try {
//                    purchasedItems = purchasedItemDao.queryBuilder().where()
//                            .eq("purchase_id", p.getId()).query();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }

                int itemInc = 0;
                if (purchasedItems.isEmpty() == false) {
                    for (PurchasedItem pi : purchasedItems) {
                        itemInc++;
                        // Relativelayot for item
                        RelativeLayout.LayoutParams rlp1 = new RelativeLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT,
                                LayoutParams.WRAP_CONTENT);
                        RelativeLayout rl1 = new RelativeLayout(ctx);
                        rl1.setLayoutParams(rlp1);
                        rl1.setBackgroundColor(Color.WHITE);
                        rl1.setPadding(dip10, dip10, dip10, dip10);

                        // Tablelayout for item details
                        TableLayout.LayoutParams tlp1 = new TableLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT,
                                LayoutParams.WRAP_CONTENT);
                        TableRow.LayoutParams rowParams1 = new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT);
                        TableLayout tl1 = new TableLayout(ctx);
                        tl1.setLayoutParams(tlp1);
                        tl1.setColumnStretchable(0, true);

                        // Table row for the header
                        TableRow tr1 = new TableRow(ctx);
                        tr1.setLayoutParams(tlp1);

                        TextView tv1 = new TextView(ctx);
                        tv1.setLayoutParams(rowParams1);
                        tv1.setTextColor(Color.parseColor("#797979"));
                        tv1.setTypeface(null, Typeface.BOLD);
                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                        tv1.setText("Item #" + itemInc);

                        TextView tv2 = new TextView(ctx);
                        tv2.setLayoutParams(rowParams1);
                        tv2.setMinWidth(dip80);
                        tv2.setTextColor(Color.parseColor("#797979"));
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                        tv2.setText("Quantity");

                        tr1.addView(tv1);
                        tr1.addView(tv2);

                        TableRow tr2 = new TableRow(ctx);
                        tr2.setLayoutParams(tlp1);

                        TextView tv3 = new TextView(ctx);
                        tv3.setLayoutParams(rowParams1);
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                        tv3.setTypeface(null, Typeface.BOLD);
                        tv3.setText(pi.getItem().getName());

                        TextView tv4 = new TextView(ctx);
                        tv4.setLayoutParams(rowParams1);
                        tv4.setMinimumWidth(dip80);
                        tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                        tv4.setTypeface(null, Typeface.BOLD);
                        tv4.setText(pi.getQuantity() + "");

                        tr2.addView(tv3);
                        tr2.addView(tv4);

                        tl1.addView(tr1);
                        tl1.addView(tr2);

                        rl1.addView(tl1);

                        ll1.addView(rl1);
                    }

                }
                // Summary of this purchase
                LinearLayout ll3 = new LinearLayout(ctx);
                ll3.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));
                ll3.setBackgroundColor(Color.parseColor("#ece8e7"));
                ll3.setOrientation(LinearLayout.VERTICAL);

                TableLayout.LayoutParams tlp2 = new TableLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                TableRow.LayoutParams rowParams2 = new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                TableLayout tl2 = new TableLayout(ctx);
                tl2.setLayoutParams(tlp2);
                tl2.setPadding(dip10, dip10, dip10, dip10);
                tl2.setColumnStretchable(0, true);

                TableRow tr3 = new TableRow(ctx);
                tr3.setLayoutParams(tlp2);

                TextView tv5 = new TextView(ctx);
                tv5.setLayoutParams(rowParams2);
                tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv5.setTextColor(Color.parseColor("#989898"));
                tv5.setTypeface(null, Typeface.BOLD);
                tv5.setText("RECEIPT AMOUNT");

                TextView tv6 = new TextView(ctx);
                tv6.setLayoutParams(rowParams2);
                tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                tv6.setTextColor(Color.parseColor("#989898"));
                tv6.setTypeface(null, Typeface.BOLD);
                tv6.setText("TOTAL SUKI POINTS");

                tr3.addView(tv5);
                tr3.addView(tv6);

                TableRow tr4 = new TableRow(ctx);
                tr4.setLayoutParams(tlp2);

                TextView tv7 = new TextView(ctx);
                tv7.setLayoutParams(rowParams2);
                tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv7.setTypeface(null, Typeface.BOLD);
                tv7.setText("P" + decimalFormat.format(p.getReceiptAmount()));

                TextView tv8 = new TextView(ctx);
                tv8.setLayoutParams(rowParams2);
                tv8.setGravity(Gravity.CENTER);
                tv8.setTextColor(Color.parseColor("#1e7800"));
                tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                tv8.setTypeface(null, Typeface.BOLD);
                tv8.setText(p.getTotalPoints() + "");

                tr4.addView(tv7);
                tr4.addView(tv8);

                tl2.addView(tr3);
                tl2.addView(tr4);

                ll3.addView(tl2);
                ll1.addView(ll3);

                purchaseLayout.addView(ll1);

            }
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
