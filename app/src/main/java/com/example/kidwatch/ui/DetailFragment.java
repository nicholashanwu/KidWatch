package com.example.kidwatch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.ChildViewModel;
import com.example.kidwatch.ChildWithCurrencies;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

	private ChildViewModel childViewModel;
	private CurrencyAdapter mAdapter;
	private TextView mTvName;
	private TextView mTvAmount;

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

		childViewModel = new ViewModelProvider(this).get(ChildViewModel.class);


		RecyclerView mRecyclerView = view.findViewById(R.id.rv_currency_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);

		final List<Currency> currencies;
		long id = getArguments().getLong("id");
		currencies = childViewModel.getCurrency(id);

		mRecyclerView.setAdapter(mAdapter);

		mAdapter = new CurrencyAdapter(new ArrayList<Currency>(), new CurrencyAdapter.CurrencyClickListener() {
			@Override
			public void onClick(long id) {

			}
		});


		mAdapter.setCurrencies(currencies);

		mTvName = view.findViewById(R.id.tvChildName);
		mTvAmount = view.findViewById(R.id.tvAmount);






		Child child = childViewModel.getChildById(id);

		System.out.println(child.toString());

		mTvName.setText(child.getChildName());



		System.out.println(currencies);





		childViewModel.getAllChildren().observe(getViewLifecycleOwner(), new Observer<List<ChildWithCurrencies>>() {
			@Override
			public void onChanged(List<ChildWithCurrencies> childWithCurrencies) {
				mAdapter.setCurrencies(currencies);
			}
		});


	}
}
