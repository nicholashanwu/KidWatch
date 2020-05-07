package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public abstract class Currency {

	@PrimaryKey
	private int currencyId;

	private String currencyName;

	private int amount;

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

	public void setChildId(int childOwnerId) {
		this.childOwnerId = childOwnerId;
	}
}
