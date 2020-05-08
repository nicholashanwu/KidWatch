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

	public void update(Child child) {
		repository.update(child);
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
