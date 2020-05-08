package com.example.kidwatch;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ChildWithCurrencies {
	@Embedded
	public Child child;
	@Relation(
			parentColumn = "childId",
			entityColumn = "childOwnerId"
	)
	public List<Currency> currencies;

}
