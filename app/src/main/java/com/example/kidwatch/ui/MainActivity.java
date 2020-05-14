package com.example.kidwatch.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kidwatch.Child;
import com.example.kidwatch.Currency;
import com.example.kidwatch.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Toolbar toolbar = findViewById(R.id.toolbar);
		//setSupportActionBar(toolbar);

		boolean isFilePresent = isFilePresent("storage.json");
		if (isFilePresent) {
			String jsonString = read("storage.json");
			System.out.println("file not present");
			//
			// create("storage.json", "{}");
			//do the json parsing here and do the rest of functionality of app
		} else {
			boolean isFileCreated = create("storage.json", "[]");
			if (isFileCreated) {
				System.out.println("file created");
				//proceed with storing the first todo  or show ui
			} else {
				//show error or try again.
			}
		}

		Gson gson = new Gson();

		Currency currency = new Currency("Screen Time", 30);
		List<Currency> currencyList = new ArrayList<>();
		currencyList.add(currency);

		Child child = new Child("John Doe", currencyList);

		String json = gson.toJson(child);

		//Currency[] list = gson.fromJson(json, Currency[].class);


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


	public String read(String fileName) {
		try {
			FileInputStream fis = this.openFileInput(fileName);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader bufferedReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (FileNotFoundException fileNotFound) {
			return null;
		} catch (IOException ioException) {
			return null;
		}
	}

	public boolean create(String fileName, String jsonString) {
		String FILENAME = "storage.json";
		try {
			FileOutputStream fos = this.openFileOutput(fileName, Context.MODE_PRIVATE);
			if (jsonString != null) {
				fos.write(jsonString.getBytes());
			}
			fos.close();
			return true;
		} catch (FileNotFoundException fileNotFound) {
			return false;
		} catch (IOException ioException) {
			return false;
		}
	}

	public void write(String fileName, String data) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(fileName, this.MODE_PRIVATE));
			osw.write(data);
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isFilePresent(String fileName) {
		String path = this.getFilesDir().getAbsolutePath() + "/" + fileName;
		File file = new File(path);
		return file.exists();
	}

}
