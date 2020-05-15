package com.example.kidwatch;

import com.google.gson.annotations.SerializedName;

public class Currency {

	@SerializedName("currency_name")
	private String currencyName;
	@SerializedName("currency_amount")
	private int amount;

	/**
	Constructor
	*/
	public Currency(String currencyName, int amount) {
		this.currencyName = currencyName;
		this.amount = amount;
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

	@Override
	public String toString() {
		return getCurrencyName();
	}
}
