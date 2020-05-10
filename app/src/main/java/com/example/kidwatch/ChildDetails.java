package com.example.kidwatch;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ChildDetails {

	public ChildDetails() {
	}


	@Embedded
	private Child child;

	@Relation
			(parentColumn = "childId",
					entityColumn = "childOwnerId",
					entity = Currency.class
			)
	private List<Currency> currencies;
	//// might not need this

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

}
