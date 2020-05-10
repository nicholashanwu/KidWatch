package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;


@Entity
public class Child {

	@PrimaryKey(autoGenerate = true)
	private long childId;

	private String childName;

	@Ignore
	private List<Currency> childCurrencies = null;

	public Child(String childName) {
		this.childName = childName;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public String toString() {
		return childName;
	}
}
