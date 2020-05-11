package com.example.kidwatch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Currency;
import com.example.kidwatch.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

	private List<Currency> currencyList = new ArrayList<>();

	private CurrencyClickListener listener;

	public CurrencyAdapter(ArrayList<Currency> currencyList, CurrencyClickListener listener){
		this.currencyList = currencyList;
		this.listener = listener;
	}

	public interface CurrencyClickListener {
		void onClick(long id);
	}

	public class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView name;
		TextView amount;
		CurrencyClickListener listener;

		public CurrencyViewHolder(@NonNull View itemView, CurrencyClickListener listener) {
			super(itemView);
			this.listener = listener;
			this.name = itemView.findViewById(R.id.tvName);
			this.amount = itemView.findViewById(R.id.tvAmount);
		}

		@Override
		public void onClick(View v) {
			//listener.onClick(currencyList.get(getAdapterPosition()).getChildId());

		}
	}

	@Override
	public CurrencyAdapter.CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
		return new CurrencyViewHolder(v, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull CurrencyAdapter.CurrencyViewHolder holder, int position) {
		Currency mCurrency = currencyList.get(position);

		ArrayList<Currency> currencies = new ArrayList<>();

		holder.name.setText(mCurrency.getCurrencyName());
		holder.amount.setText(mCurrency.getAmount());
	}

	@Override
	public int getItemCount() {
		return currencyList.size();
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencyList = currencies;
		notifyDataSetChanged();
	}


}
