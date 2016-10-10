package com.hatchpointsolutions.sssrewards.app;

import android.app.Activity;
import android.os.Bundle;

import org.json.JSONObject;

public class SyncActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sync);

		JSONObject jo = new JSONObject();

//		List<Basket> basketList = basketDao.queryForAll();
//		List<Customer> customerList = customerDao.queryForAll();
//
//		try {
//			if (basketList.size() > 0) {
//
//				JSONArray ba = Basket.getListToJSONArray(basketList);
//
//				jo.put("baskets", ba);
//
//				SyncTask st = new SyncTask();
//				st.execute(ba);
//
//			} else {
//				Intent i = new Intent(this, MainActivity.class);
//				this.startActivity(i);
//				this.finish();
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}

	}

}
