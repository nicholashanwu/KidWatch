package com.example.kidwatch;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ChildViewModel extends AndroidViewModel {

	private ChildRepository repository;
	private LiveData<List<ChildWithCurrencies>> allChildren;

	public ChildViewModel(@NonNull Application application) {
		super(application);

		repository = new ChildRepository(application);
		allChildren = repository.getAllChildren();
	}

	public void insert(Child child) {
		repository.insert(child);
	}

	public void insert(Currency currency) {
		repository.insert(currency);
	}

	public void insertChildWithCurrencies(Child child, Currency currency) {
		repository.insertChildWithCurrencies(child, currency);
	}

	public void update(Child child) {
		repository.update(child);
	}

	public Child getChild(Child child) {
		return repository.getChild(child);
	}

	public Child getChildByName(String childName) {
		return repository.getChildByName(childName);
	}

	public void delete(Child child) {
		repository.delete(child);
	}

	public void deleteAllChildren() {
		repository.deleteAllChildren();
	}

	public LiveData<List<ChildWithCurrencies>> getAllChildren() {
		return allChildren;
	}
}
