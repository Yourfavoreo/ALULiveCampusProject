package com.tolotranet.livecampus.Lead;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tolotranet.livecampus.R;

import java.util.ArrayList;

public class Lead_DetailListView extends Activity {

	ListView lv;
	Lead_DetailListViewAdapter myPersonDetailListViewAdapter;
	ArrayList<Lead_DetailListItem> DetailList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lead_activity_detail_list_view);
		Log.d("hello", "view mode has started");
		Intent i = getIntent();
		int Index = i.getIntExtra("index", 0);
		DetailList = getPersonalDetails(Index);

		lv = (ListView) findViewById(R.id.person_details_lv);
		myPersonDetailListViewAdapter = new Lead_DetailListViewAdapter(this,
				DetailList);
		lv.setAdapter(myPersonDetailListViewAdapter);
		lv.setOnItemClickListener(new PersonDetailListViewClickListener());
	}

	private ArrayList<Lead_DetailListItem> getPersonalDetails(int Index) {
		ArrayList<Lead_DetailListItem> DetailList = new ArrayList<Lead_DetailListItem>();
		String nullTag = "Update your";

		Lead_DetailListItem sr = new Lead_DetailListItem();
		sr.setDetailName("Question");
		sr.setDetailValue(Lead_XMLParserClass.q2.get(Index));
		DetailList.add(sr);

		if (!(Lead_XMLParserClass.q5.get(Index).equals("")|| Lead_XMLParserClass.q5.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("Description");
			sr.setDetailValue(Lead_XMLParserClass.q5.get(Index));
			DetailList.add(sr);
		}

		if (!(Lead_XMLParserClass.q3.get(Index).equals("")|| Lead_XMLParserClass.q3.get(Index).startsWith(nullTag) )) {
		sr = new Lead_DetailListItem();
		sr.setDetailName("Asked by");
		sr.setDetailValue(Lead_XMLParserClass.q3.get(Index));
		DetailList.add(sr);
		}


		if (!(Lead_XMLParserClass.q6.get(Index).equals("")|| Lead_XMLParserClass.q6.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("Answer");
			sr.setDetailValue(Lead_XMLParserClass.q6.get(Index));
			DetailList.add(sr);
		}


		if ((Lead_XMLParserClass.q6.get(Index).equals("")|| Lead_XMLParserClass.q6.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("Answer");
			sr.setDetailValue("No answer yet");
			DetailList.add(sr);
		}


		if (!(Lead_XMLParserClass.q4.get(Index).equals("")|| Lead_XMLParserClass.q4.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("Organiser");
			sr.setDetailValue(Lead_XMLParserClass.q4.get(Index));
			DetailList.add(sr);
		}



		if (!(Lead_XMLParserClass.q7.get(Index).equals("")|| Lead_XMLParserClass.q7.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("Vote");
			sr.setDetailValue(Lead_XMLParserClass.q7.get(Index));
			DetailList.add(sr);
		}
		if (!(Lead_XMLParserClass.q8.get(Index).equals("")|| Lead_XMLParserClass.q8.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("passportnumber");
			sr.setDetailValue(Lead_XMLParserClass.q8.get(Index));
			DetailList.add(sr);
		}

		if (!(Lead_XMLParserClass.q9.get(Index).equals("")|| Lead_XMLParserClass.q9.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("phonenumber1");
			sr.setDetailValue(Lead_XMLParserClass.q9.get(Index));
			DetailList.add(sr);
		}
		if (!(Lead_XMLParserClass.q10.get(Index).equals("")|| Lead_XMLParserClass.q10.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("phonenumber2");
			sr.setDetailValue(Lead_XMLParserClass.q10.get(Index));
			DetailList.add(sr);
		}

		if (!(Lead_XMLParserClass.q11.get(Index).equals("")|| Lead_XMLParserClass.q11.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("phonenumber3");
			sr.setDetailValue(Lead_XMLParserClass.q11.get(Index));
			DetailList.add(sr);
		}

		if (!(Lead_XMLParserClass.q12.get(Index).equals("")|| Lead_XMLParserClass.q12.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("apartment");
			sr.setDetailValue(Lead_XMLParserClass.q12.get(Index));
			DetailList.add(sr);
		}
		if (!(Lead_XMLParserClass.q13.get(Index).equals("")|| Lead_XMLParserClass.q13.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("residence");
			sr.setDetailValue(Lead_XMLParserClass.q13.get(Index));
			DetailList.add(sr);
		}
		if (!(Lead_XMLParserClass.q14.get(Index).equals("")|| Lead_XMLParserClass.q14.get(Index).startsWith(nullTag) )) {
			sr = new Lead_DetailListItem();
			sr.setDetailName("room");
			sr.setDetailValue(Lead_XMLParserClass.q14.get(Index));
			DetailList.add(sr);
		}
		return DetailList;
	}

	public class PersonDetailListViewClickListener implements
			OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
			// TODO Auto-generated method stub
			String TempDetailName = (String) ((Lead_DetailListItem) arg0
					.getItemAtPosition(arg2)).getDetailName();
			if (TempDetailName.equals("Mobile")
					|| TempDetailName.equals("Residence")
					|| TempDetailName.equals("Office")) {
				String DetailValue = (String) ((Lead_DetailListItem) arg0
						.getItemAtPosition(arg2)).getDetailValue();
				if (!DetailValue.equals("")) {
					Intent callintent = new Intent(Intent.ACTION_DIAL,
							Uri.parse("tel:"
									+ DetailValue.replaceAll("[A-Za-z()\\s]+",
											"").trim()));
					startActivity(callintent);
				}
			}

			if (TempDetailName.equals("Email")) {
				String ToEmailId = (String) ((Lead_DetailListItem) arg0
						.getItemAtPosition(arg2)).getDetailValue();
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
				emailIntent.setData(Uri.parse("mailto:"));
				Log.d("hello", ToEmailId);
				emailIntent.putExtra(Intent.EXTRA_EMAIL,
						new String[] { ToEmailId.trim() });
				// emailIntent.setType("message/rfc822");
				startActivity(Intent.createChooser(emailIntent,
						"Send mail using..."));
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person_detail_list_view, menu);
		return true;
	}

}
