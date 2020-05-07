package com.example.kidwatch;

public class ScreenTime extends Currency {

	private int maximum;

	public ScreenTime(String currencyName, int amount, int childOwnerId, int maximum) {
		super(currencyName, amount, childOwnerId);
		this.maximum = maximum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
}
