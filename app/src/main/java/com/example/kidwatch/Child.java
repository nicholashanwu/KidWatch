package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Child {

	@PrimaryKey
	private int childId;

	private String childName;

	public Child (String childName) {
		this.childName = childName;

	}

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

}
