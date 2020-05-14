package com.example.kidwatch.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

	private ChildAdapter mAdapter;
	private Gson gson;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		gson = new Gson();
		final Type childType = new TypeToken<ArrayList<Child>>(){}.getType();


		FloatingActionButton fab = view.findViewById(R.id.fab);


		RecyclerView mRecyclerView = view.getRootView().findViewById(R.id.rv_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);
		ArrayList<Child> children = gson.fromJson(		((MainActivity)getActivity()).read("storage.json"), childType);



		mAdapter = new ChildAdapter(new ArrayList<Child>(), new ChildAdapter.ChildClickListener() {
			@Override
			public void onClick(int id) {

				Bundle bundle = new Bundle();
				bundle.putInt("id", id);

				Fragment fragment = new DetailFragment();

				fragment.setArguments(bundle);

				NavHostFragment.findNavController(HomeFragment.this).
						navigate(R.id.action_HomeFragment_to_DetailFragment, bundle);

				//start detail activity with position
			}
		});

		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setChildren(children);


		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				View newView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);

				final EditText childName = newView.findViewById(R.id.edit_name);
				final EditText currencyName = newView.findViewById(R.id.edit_currency);

				builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						List<Currency> currencyList = new ArrayList<>();
						currencyList.add(new Currency(currencyName.getText().toString(), 10));

						ArrayList<Child> children = gson.fromJson(		((MainActivity)getActivity()).read("storage.json"), childType);
						Child child = new Child(childName.getText().toString(), currencyList);
						children.add(child);

						String json = gson.toJson(children);

						((MainActivity)getActivity()).write("storage.json", json);


						mAdapter.setChildren(children);
						//add to database

					}
				});

				builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//do nothing
					}
				});

				builder.setView(newView);
				builder.show();

			}
		});







	}


}
