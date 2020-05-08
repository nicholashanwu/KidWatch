package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Currency {

	@PrimaryKey(autoGenerate = true)
	private int currencyId;

	private String currencyName;

	private int amount;

	/*@ForeignKey(entity = Child.class,
	parentColumns = "currencyId",
	childColumns = "childOwnerId",
	onDelete = CASCADE)*/
	private int childOwnerId;

	public Currency(String currencyName, int amount, int childOwnerId) {
		this.currencyName = currencyName;
		this.amount = amount;
		this.childOwnerId = childOwnerId;
	}




	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getChildOwnerId() {
		return childOwnerId;
	}

	public void setChildOwnerId(int childOwnerId) {
		this.childOwnerId = childOwnerId;
	}
}
