package com.example.kidwatch;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;


public class NewChildDialog extends AppCompatDialogFragment {
	private EditText editTextName;
	private EditText editTextCurrency;
	private ExampleDialogListener listener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_layout, null);

		builder.setView(view)
				.setTitle("Login")
				.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				})
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						String childName = editTextName.getText().toString();
						String currencyName = editTextCurrency.getText().toString();
						listener.addChild(childName, currencyName);
					}
				});

		editTextName = view.findViewById(R.id.edit_name);
		editTextCurrency = view.findViewById(R.id.edit_currency);

		return builder.create();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		try {
			listener = (ExampleDialogListener) context;
		} catch (ClassCastException e) {
			throw new ClassCastException(context.toString() +
					"must implement ExampleDialogListener");
		}
	}

	public interface ExampleDialogListener {
		void addChild(String childName, String currencyName);
	}
}