package com.tolotranet.livecampus.Maint;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tolotranet.livecampus.R;

public class Maint_SpreadSheetActivity extends Activity {

	ProgressDialog mProgress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maint_spread_sheet);

		mProgress = new ProgressDialog(this);
		mProgress.setMessage("Loading data ...");
		mProgress.show();

		Maint_startApplicationAsyncTask myTask = new Maint_startApplicationAsyncTask();
		myTask.execute(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spread_sheet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.UpdateContactsSpreadSheet:
			if (isNetworkAvailable()) {
				Toast.makeText(getApplicationContext(), "Updating.....",
						Toast.LENGTH_SHORT).show();
				Maint_GetDataAsyncTask getDataTask = new Maint_GetDataAsyncTask();
				getDataTask.execute(this);
			} else {
				Toast.makeText(getApplicationContext(),
						"check Internet Connection", Toast.LENGTH_SHORT).show();
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
	protected void onDestroy()
	{
		super.onDestroy();
		if (mProgress!=null && mProgress.isShowing()){
			mProgress.dismiss();
		}
	}

}
