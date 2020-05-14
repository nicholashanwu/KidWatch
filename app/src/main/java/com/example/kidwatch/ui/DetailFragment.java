package com.example.kidwatch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

	private CurrencyAdapter mAdapter;
	private TextView mTvName;
	private TextView mTvAmount;
	private Gson gson;


	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState
	) {
		// Inflate the layout for this fragment

		return inflater.inflate(R.layout.fragment_detail, container, false);
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		gson = new Gson();
		final Type childType = new TypeToken<ArrayList<Child>>(){}.getType();


		RecyclerView mRecyclerView = view.findViewById(R.id.rv_currency_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);

		final List<Currency> currencies;
		int id = getArguments().getInt("id");

		ArrayList<Child> children = gson.fromJson(		((MainActivity)getActivity()).read("storage.json"), childType);

		currencies = children.get(id).getCurrencyList();

		mAdapter = new CurrencyAdapter(new ArrayList<Currency>(), new CurrencyAdapter.CurrencyClickListener() {
			@Override
			public void onClick(long id) {

			}
		});

		mRecyclerView.setAdapter(mAdapter);


		mAdapter.setCurrencies(currencies);

		mTvName = view.findViewById(R.id.tvChildName);
		mTvAmount = view.findViewById(R.id.tvAmount);


//		System.out.println(child.toString());
//
//		mTvName.setText(child.getChildName());

		//System.out.println(currencies);

	}
}
