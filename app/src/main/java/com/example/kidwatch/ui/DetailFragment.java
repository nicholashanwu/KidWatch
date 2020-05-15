package com.example.kidwatch.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailFragment extends Fragment {

	private CurrencyAdapter mAdapter;
	private TextView mTvChildName;
	private ExtendedFloatingActionButton mFabAddCurrency;
	private Gson gson;
	private List<Currency> currencies;
	private CircleImageView mIvProfile;

	public static final int PICK_IMAGE = 1;

	private int id;

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


		mTvChildName = view.findViewById(R.id.tvChildName);
		mFabAddCurrency = view.findViewById(R.id.fabAddCurrency);
		mIvProfile = view.findViewById(R.id.ivProfile);

		gson = new Gson();
		final Type childType = new TypeToken<ArrayList<Child>>(){}.getType();

		RecyclerView mRecyclerView = view.findViewById(R.id.rv_currency_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecyclerView.setHasFixedSize(true);

		id = getArguments().getInt("id");

		ArrayList<Child> children = gson.fromJson(		((MainActivity)getActivity()).read("storage.json"), childType);

		currencies = children.get(id).getCurrencyList();

		mTvChildName.setText(children.get(id).getChildName());



		mAdapter = new CurrencyAdapter(new ArrayList<Currency>(), new CurrencyAdapter.CurrencyClickListener() {
			@Override
			public void onClick(long id) {

			}
		});

		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setCurrencies(currencies);



		mIvProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
			}
		});

		mFabAddCurrency.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
				View newView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);

				final EditText currencyName = newView.findViewById(R.id.edit_name);

				builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						ArrayList<Child> children = gson.fromJson(		((MainActivity)getActivity()).read("storage.json"), childType);
						Currency currency = new Currency(currencyName.getText().toString(), 10);
						children.get(id).getCurrencyList().add(currency);

						String json = gson.toJson(children);

						((MainActivity)getActivity()).write("storage.json", json);
						currencies = children.get(id).getCurrencyList();

						mAdapter.setCurrencies(currencies);
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == PICK_IMAGE) {
			try {
				InputStream inputStream = getContext().getContentResolver().openInputStream(data.getData());
				Uri selectedImage = data.getData();
				Picasso.get().load(selectedImage).resize(100, 100).centerCrop().into(mIvProfile);

				//String encodedImage = Base64.encodeToString(Picasso.get().load(selectedImage));

				Picasso.get().load(selectedImage);


				mIvProfile.setImageResource(inputStream.read());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
