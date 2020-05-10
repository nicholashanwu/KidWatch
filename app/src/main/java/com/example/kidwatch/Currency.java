package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Currency {

	@PrimaryKey(autoGenerate = true)
	private int currencyId;

	private String currencyName;

	private int amount;

	@ForeignKey
			(
					entity = Child.class,
					parentColumns = "currencyId",
					childColumns = "childOwnerId",
					onDelete = CASCADE)
	private long childOwnerId;

	public Currency(String currencyName, int amount) {
		this.currencyName = currencyName;
		this.amount = amount;
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

	public long getChildOwnerId() {
		return childOwnerId;
	}

	public void setChildOwnerId(long childOwnerId) {
		this.childOwnerId = childOwnerId;
	}

	@Override
	public String toString() {
		return getCurrencyName();
	}
}
