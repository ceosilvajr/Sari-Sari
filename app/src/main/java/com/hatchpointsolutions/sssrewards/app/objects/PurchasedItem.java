package com.hatchpointsolutions.sssrewards.app.objects;

import android.annotation.SuppressLint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressLint("SimpleDateFormat")
public class PurchasedItem {

	// id is generated by the database and set on the object automagically
	int id;
	Purchase purchase;
	Item item;
	float totalSrp;
	int totalPoints;
	int quantity;
	Date dateCreated;

	PurchasedItem() {
		// needed by ormlite
	}


	public PurchasedItem(Purchase p, Item i, float ts, int q) {
		this.purchase = p;
		this.item = i;
		this.totalSrp = ts;
		this.quantity = q;
		this.dateCreated = new Date();
	}

	public static JSONArray getListToJSONArray(List<PurchasedItem> l)
			throws JSONException {
		JSONArray ja = new JSONArray();

		for (PurchasedItem o : l) {
			ja.put(o.toJSONObject());
		}

		return ja;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		jo.put("id", this.getId());
		jo.put("purchase_id", this.getPurchase().getId());
		jo.put("item_id", this.getItem().getId());
		jo.put("total_srp", this.getTotalSrp());
		jo.put("quantity", this.getQuantity());
		jo.put("date_created", dateFormatter.format(this.getDateCreated()));

		return jo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append("purchase=").append(purchase.toString());
		sb.append(", ").append("item=").append(item.toString());
		sb.append(", ").append("totalSrp=").append(totalSrp);
		sb.append(", ").append("totalPoints=").append(totalPoints);
		sb.append(", ").append("quantity=").append(quantity);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss.S");
		sb.append(", ").append("date=")
				.append(dateFormatter.format(dateCreated));
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getTotalSrp() {
		return totalSrp;
	}

	public void setTotalSrp(float totalSrp) {
		this.totalSrp = totalSrp;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}