package com.example.kidwatch;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class ChildDao {

	@Query("SELECT * FROM child WHERE childId == :childId")
	public abstract Child getChild(long childId);

	@Query("SELECT * FROM child WHERE childName == :childName")
	public abstract Child getChild(String childName);


	@Transaction
	@Query("SELECT * FROM child")
	public abstract LiveData<List<ChildWithCurrencies>> getChildrenWithCurrencies();

	@Transaction
	@Insert
	public void insertChildWithCurrencies(Child child, Currency currency) {

		final long childId = insert(child);

		currency.setChildOwnerId(childId);
		insert(currency);

	}

	@Query("SELECT * FROM currency WHERE childOwnerId == :childOwnerId")
	public abstract List<Currency> getCurrency(long childOwnerId);


	@Query("DELETE FROM child")
	public abstract void deleteAllChildren();

	@Insert
	public abstract long insert(Child child);

	@Insert
	public abstract void insert(Currency currency);

	@Delete
	public abstract void delete(Child child);

	@Update
	public abstract void update(Child child);

}
