package com.example.tolotranet.livecampus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Event_MainList extends Activity {

	ArrayList<Event_ItemObject> ContactItemArray;
	Event_MyCustomBaseAdapter myAdapter;
	EditText SearchET;
	ListView lv;
	int MyId = 9999999; //is 99999 because this is event activity and event don't need the user to change it
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_schedule);

		Log.d("hello", "Starting tolotra");
		ContactItemArray = MakeArrayList();
		lv = (ListView) findViewById(R.id.Contacts_list_view);
		SearchET = (EditText) findViewById(R.id.SearchET);

		myAdapter = new Event_MyCustomBaseAdapter(getApplicationContext(),
				ContactItemArray);
		lv.setAdapter(myAdapter);

		MyTextWatcher mytextwatcher = new MyTextWatcher();
		SearchET.addTextChangedListener(mytextwatcher);
		lv.setOnItemClickListener(new AllContactListViewClickListener());

	}

	public class AllContactListViewClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
			// TODO Auto-generated method stub
			int Index = ((Event_ItemObject) arg0.getItemAtPosition(arg2))
					.getIndex();
			int ThisId = ((Event_ItemObject) arg0.getItemAtPosition(arg2))
					.getUserId();

			//open editor profile if the person clicked is the current user

			if (MyId == (ThisId)){
				Intent iw = new Intent(getApplicationContext(),
						Event_DetailListViewOwner.class);
				iw.putExtra("index", Index);
				iw.putExtra("myId", MyId);

				Log.d("hello", "Position Clicked is " + arg2);
				Log.d("hello", "Item Clicked is " + Index);
				Log.d("hello", "User Clicked is " + ThisId);
				startActivity(iw);
			}else{
				Intent i = new Intent(getApplicationContext(),
						Event_DetailListView.class);
				i.putExtra("index", Index);
				Log.d("hello", "Position Clicked is " + arg2);
				Log.d("hello", "Item Clicked is " + Index);
				Log.d("hello", "User Clicked is " + ThisId);
				startActivity(i);
			}
		}

	}

	private ArrayList<Event_ItemObject> MakeArrayList() {
		ArrayList<Event_ItemObject> TempItemArray = new ArrayList<Event_ItemObject>();
		String nullTag = "Update your";

		for (int i = 0; i < Event_XMLParserClass.q1.size(); i++) {
			Event_ItemObject CIO = new Event_ItemObject();
			if(!Event_XMLParserClass.q2.get(i).equals("")){

				CIO.setName(Event_XMLParserClass.q2.get(i));
				if((Event_XMLParserClass.q4.get(i).startsWith(nullTag))){
					CIO.setBottomText("");
				}else{
					CIO.setBottomText(Event_XMLParserClass.q4.get(i));
				};
				CIO.setIndex(i);
				CIO.setUserId(Integer.parseInt(Event_XMLParserClass.q1.get(i)));
				TempItemArray.add(CIO);
			}
		}

//sorting
//		Collections.sort(TempItemArray, new Comparator<Event_ItemObject>() {
//			@Override
//			public int compare(Event_ItemObject lhs, Event_ItemObject rhs) {
//				return lhs.getName().compareTo(rhs.getName());
//			}
//		});
		return TempItemArray;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.UpdateContactsMain:

			if (isNetworkAvailable()) {
				Toast.makeText(getApplicationContext(), "Updating.....", Toast.LENGTH_LONG).show();
				Event_GetDataAsyncTask getDataTask = new Event_GetDataAsyncTask();
				getDataTask.execute(this);
			} else {
				Toast.makeText(getApplicationContext(), "check Internet Connection", Toast.LENGTH_SHORT).show();
			}

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}

	public class MyTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
									  int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
								  int arg3) {
			// TODO Auto-generated method stub
			myAdapter.getFilter().filter(arg0.toString());
		}

	}

}
