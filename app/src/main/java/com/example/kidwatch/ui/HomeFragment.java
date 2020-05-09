package com.example.kidwatch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.kidwatch.R;

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
