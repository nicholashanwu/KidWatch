package com.example.kidwatch;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChildRepository {

	private ChildDao childDao;
	private LiveData<List<ChildWithCurrencies>> allChildren;
	private static Child currentChild;

	public ChildRepository(Application application) {
		ChildDatabase database = ChildDatabase.getInstance(application);
		childDao = database.childDao();
		allChildren = childDao.getChildrenWithCurrencies();
	}

	public void insert(Child child) {
		new InsertChildTask(childDao).execute(child);
	}

	public void insert(Currency currency) {
		new InsertCurrencyTask(childDao).execute(currency);
	}

	public void insertChildWithCurrencies(Child child, Currency currency) {
		new InsertChildWithCurrencyTask(childDao, child, currency).execute();
	}

	public Child getChild(Child child) {
		new GetChildTask(childDao).execute(child);
		return currentChild;
	}

	public Child getChildByName(String childName) {
		try {
			return new GetChildByNameTask(childDao).execute(childName).get();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return currentChild;
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

	private static class InsertChildWithCurrencyTask extends AsyncTask<Void, Void, Void> {
		private ChildDao childDao;
		private Child child;
		private Currency currency;

		private InsertChildWithCurrencyTask(ChildDao childDao, Child child, Currency currency){
			this.childDao = childDao;
			this.child = child;
			this.currency = currency;
		}

		@Override
		protected Void doInBackground(Void... voids) {
			childDao.insertChildWithCurrencies(child, currency);
			return null;
		}
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

	private static class InsertCurrencyTask extends AsyncTask<Currency, Void, Void> {
		private ChildDao childDao;

		private InsertCurrencyTask(ChildDao childDao){
			this.childDao = childDao;
		}

		@Override
		protected Void doInBackground(Currency... currencies) {
			childDao.insert(currencies[0]);
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

	private static class GetChildTask extends AsyncTask<Child, Void, Child> {
		private ChildDao childDao;

		private GetChildTask(ChildDao childDao) {
			this.childDao = childDao;
		}

		@Override
		protected Child doInBackground(Child... children) {
			return childDao.getChild(children[0].getChildId());
		}

		@Override
		protected void onPostExecute(Child child) {
			super.onPostExecute(child);
			currentChild = child;


		}
	}

	private static class GetChildByNameTask extends AsyncTask<String, Void, Child> {
		private ChildDao childDao;

		private GetChildByNameTask(ChildDao childDao) {
			this.childDao = childDao;
		}

		@Override
		protected Child doInBackground(String... children) {
			return childDao.getChild(children[0]);
		}

		@Override
		protected void onPostExecute(Child child) {
			super.onPostExecute(child);
			currentChild = child;
		}
	}

}
