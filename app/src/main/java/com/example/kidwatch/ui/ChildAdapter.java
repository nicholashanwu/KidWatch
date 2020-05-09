package com.example.kidwatch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.ChildWithCurrencies;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

	private List<ChildWithCurrencies> childList = new ArrayList<>();

	private ChildClickListener listener;

	public ChildAdapter (ArrayList<Child> childlist, ChildClickListener listener){
		this.childList = childList;
		this.listener = listener;
	}

	public interface ChildClickListener {
		void onClick(int id);
	}

	public class ChildViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		TextView name;
		TextView currency;
		ChildClickListener listener;

		public ChildViewHolder(@NonNull View itemView, ChildClickListener listener) {
			super(itemView);
			this.listener = listener;
			this.name = itemView.findViewById(R.id.tvName);
			this.currency = itemView.findViewById(R.id.tvCurrency);
		}

		@Override
		public void onClick(View v) {
			listener.onClick(childList.get(getAdapterPosition()).child.getChildId());


		}
	}

	@Override
	public ChildAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
		return new ChildViewHolder(v, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ChildAdapter.ChildViewHolder holder, int position) {
		Child mChild = childList.get(position).child;

		ArrayList<Currency> childCurrencies = new ArrayList<>();

		for(int i = 0; i < childList.get(position).currencies.size(); i++) {
			System.out.println(childList.get(position).currencies.get(i).getCurrencyName());
		}


		holder.name.setText(mChild.getChildName());
		holder.currency.setText(Arrays.toString(childList.get(position).currencies.toArray()));



	}

	@Override
	public int getItemCount() {
		return childList.size();
	}

	public void setChildren(List<ChildWithCurrencies> childWithCurrenciesList) {
		this.childList = childWithCurrenciesList;
		notifyDataSetChanged();
	}


}
