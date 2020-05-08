package com.example.kidwatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
		ChildClickListener listener;

		public ChildViewHolder(@NonNull View itemView, ChildClickListener listener) {
			super(itemView);
			this.listener = listener;
			this.name = itemView.findViewById(R.id.tvName);

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
		holder.name.setText(mChild.getChildName());
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
