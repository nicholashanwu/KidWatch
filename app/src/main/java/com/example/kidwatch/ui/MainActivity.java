package com.example.kidwatch.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.kidwatch.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NewChildDialog.ExampleDialogListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();

				openDialog();
//				FragmentTransaction ftr = getSupportFragmentManager().beginTransaction();
//				ftr.replace(R.id.nav_host_fragment, new HomeFragment());
//				ftr.commit();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void openDialog() {
		NewChildDialog dialog = new NewChildDialog();
		dialog.show(getSupportFragmentManager(), "example dialog");
	}


	@Override
	public void addChild(String childName, String currencyName) {
		if(childName.trim().isEmpty() || currencyName.trim().isEmpty()) {
			Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show();
			return;
		} else {
			ChildWithCurrencies childWithCurrencies = new ChildWithCurrencies();
		}
	}
}
