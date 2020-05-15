package com.example.kidwatch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidwatch.Child;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;

import java.util.ArrayList;
import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

	private List<Child> childList = new ArrayList<>();

	private ChildClickListener listener;

	public ChildAdapter(ArrayList<Child> childlist, ChildClickListener listener) {
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
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			listener.onClick(getAdapterPosition());
		}
	}

	@Override
	public ChildAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
		return new ChildViewHolder(v, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull ChildAdapter.ChildViewHolder holder, int position) {

		Child mChild = childList.get(position);

		holder.name.setText(mChild.getChildName());

		if (!mChild.getCurrencyList().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < mChild.getCurrencyList().size(); i++) {
				sb.append(mChild.getCurrencyList().get(i) + "\n");
			}
			holder.currency.setText(sb);

		} else {
			holder.currency.setText("\n");

		}
	}

	@Override
	public int getItemCount() {
		return childList.size();
	}

	public void setChildren(List<Child> childWithCurrenciesList) {
		this.childList = childWithCurrenciesList;
		notifyDataSetChanged();
	}


}
