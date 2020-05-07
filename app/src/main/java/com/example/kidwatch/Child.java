package com.example.kidwatch;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Child {

	public Child (String name, ArrayList<Currency> currencyList) {
		this.name = name;
		this.currencyList = currencyList;
	}

	@PrimaryKey
	private int id;
	private String name;
	private ArrayList<Currency> currencyList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Currency> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(ArrayList<Currency> currencyList) {
		this.currencyList = currencyList;
	}
}
