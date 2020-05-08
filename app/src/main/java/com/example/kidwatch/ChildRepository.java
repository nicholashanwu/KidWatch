package com.example.kidwatch;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ChildRepository {

	private ChildDao childDao;
	private LiveData<List<ChildWithCurrencies>> allChildren;

	public ChildRepository(Application application) {
		ChildDatabase database = ChildDatabase.getInstance(application);
		childDao = database.childDao();
		allChildren = childDao.getChildrenWithCurrencies();
	}

	public void insert(Child child) {
		new InsertChildTask(childDao).execute(child);
	}

	public void update(Child child) {
		new UpdateChildTask(childDao).execute(child);
	}

	public void delete(Child child) {
		new DeleteChildTask(childDao).execute(child);
	}

	public void deleteAllChildren() {
		new DeleteAllChildrenTask(childDao).execute();
	}

	public LiveData<List<ChildWithCurrencies>> getAllChildren() {
		return allChildren;
	}

	private static class InsertChildTask extends AsyncTask<Child, Void, Void> {
		private ChildDao childDao;

		private InsertChildTask(ChildDao childDao){
			this.childDao = childDao;
		}

		@Override
		protected Void doInBackground(Child... children) {
			childDao.insert(children[0]);
			return null;
		}
	}

	private static class UpdateChildTask extends AsyncTask<Child, Void, Void> {
		private ChildDao childDao;

		private UpdateChildTask(ChildDao childDao){
			this.childDao = childDao;
		}

		@Override
		protected Void doInBackground(Child... children) {
			childDao.update(children[0]);
			return null;
		}
	}

	private static class DeleteChildTask extends AsyncTask<Child, Void, Void> {
		private ChildDao childDao;

		private DeleteChildTask(ChildDao childDao){
			this.childDao = childDao;
		}

		@Override
		protected Void doInBackground(Child... children) {
			childDao.delete(children[0]);
			return null;
		}
	}

	private static class DeleteAllChildrenTask extends AsyncTask<Void, Void, Void> {
		private ChildDao childDao;

		private DeleteAllChildrenTask(ChildDao childDao){
			this.childDao = childDao;
		}

		@Override
		protected Void doInBackground(Void... voids) {
			childDao.deleteAllChildren();
			return null;
		}
	}

}
