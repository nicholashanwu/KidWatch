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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.ChildDatabase;
import com.example.kidwatch.ChildViewModel;
import com.example.kidwatch.ChildWithCurrencies;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

	private ChildViewModel childViewModel;

	ChildAdapter mAdapter;
	ChildDatabase mDb;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		//mDb = Room.databaseBuilder(getActivity(), ChildDatabase.class, "children.db").build();
		FloatingActionButton fab = view.findViewById(R.id.fab);
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
						//add to database

						Child child = new Child(childName.getText().toString());
						childViewModel.insert(child);


						//System.out.println("hi" + childViewModel.getChildByName("john"));
						Currency currency = new Currency(currencyName.getText().toString(), 10,
								childViewModel.getChildByName(childName.getText().toString()).getChildId());

						childViewModel.insert(currency);
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


		RecyclerView mRecyclerView = view.getRootView().findViewById(R.id.rv_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);

		mAdapter = new ChildAdapter(new ArrayList<Child>(), new ChildAdapter.ChildClickListener() {
			@Override
			public void onClick(int id) {
				//start detail activity with position
			}
		});

		mRecyclerView.setAdapter(mAdapter);

		childViewModel = new ViewModelProvider(this).get(ChildViewModel.class);
		childViewModel.getAllChildren().observe(getViewLifecycleOwner(), new Observer<List<ChildWithCurrencies>>() {
					@Override
					public void onChanged(List<ChildWithCurrencies> childWithCurrencies) {
						//update RecyclerView
						mAdapter.setChildren(childWithCurrencies);
					}
				});

	}






}
