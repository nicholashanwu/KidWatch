package com.example.kidwatch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Child {

	@SerializedName("child_name")
	private String childName;
	@SerializedName("child_currencies")
	private List<Currency> currencyList;


	public Child(String childName, List<Currency> currencyList) {
		this.childName = childName;
		this.currencyList = currencyList;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
	}

	@Override
	public String toString() {
		return childName;
	}


}
