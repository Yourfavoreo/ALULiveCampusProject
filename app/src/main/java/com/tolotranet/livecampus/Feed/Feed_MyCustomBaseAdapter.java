package com.tolotranet.livecampus.Feed;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tolotranet.livecampus.R;

import java.util.ArrayList;

public class Feed_MyCustomBaseAdapter extends BaseAdapter {
	private static ArrayList<Feed_ItemObject> MyArrayObjects = new ArrayList<Feed_ItemObject>();
	private static ArrayList<Feed_ItemObject> FilteredObjects = new ArrayList<Feed_ItemObject>();
	private Context context;
	private LayoutInflater mInflater;
	private ItemFilter myFilter = new ItemFilter();


	public Feed_MyCustomBaseAdapter(Context c, ArrayList<Feed_ItemObject> MyList) {
		this.context = c;
		MyArrayObjects = MyList;
		FilteredObjects = MyList;

		mInflater = LayoutInflater.from(context);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return FilteredObjects.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return FilteredObjects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Filter getFilter(){
		return myFilter;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		
		if(convertView == null){
			if(position ==0){
				convertView = mInflater.inflate(R.layout.feed_post_head, null);
			}else {
				convertView = mInflater.inflate(R.layout.feed_list_item, null);
			}
			holder = new ViewHolder();
//			holder.NameTV = (TextView) convertView.findViewById(R.id.Contact_name_tv);
//			holder.BreakFastTV = (TextView) convertView.findViewById(R.id.Contact_bottom_tv);
//			holder.VotesTV = (TextView) convertView.findViewById(R.id.Contact_right_tv);
//			holder.CommmentTV = (TextView) convertView.findViewById(R.id.Contact_right_tv_comment);
//			holder.RightBottomTv = (TextView) convertView.findViewById(R.id.Right_bottom_tv);
//			holder.ImageViewIco = (ImageView) convertView.findViewById(R.id.PersonImageView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
//		String Name = FilteredObjects.get(position).getName();
//		String BottomText = FilteredObjects.get(position).getBottomText1();
//		String RightBottomText = FilteredObjects.get(position).getRightText();
//		int RightText = FilteredObjects.get(position).getVotes();
//		int RightCommentCount= FilteredObjects.get(position).getComments();
//		int ImgViewID = FilteredObjects.get(position).getImgID();
//
//		holder.NameTV.setText(Name);
//		holder.BreakFastTV.setText(BottomText);
//		holder.RightBottomTv.setText(RightBottomText);
//		holder.VotesTV.setText(String.valueOf(RightText));
//		holder.CommmentTV.setText(String.valueOf(RightCommentCount));
//		holder.ImageViewIco.setImageResource(ImgViewID);

		return convertView;
	}

	static class ViewHolder{
		TextView NameTV;
		TextView BottomTV;
		TextView RightTV;
		TextView RightCommentTV;
		ImageView ImageViewIco;
		TextView RightBottomTv;
	}
	
	private class ItemFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence arg0) {
			// TODO Auto-generated method stub
			FilterResults filterResults = new FilterResults();
			if(arg0 == null || arg0.length() == 0){
				filterResults.values = MyArrayObjects;
				filterResults.count = MyArrayObjects.size();
			}else{
				String filterString =arg0.toString().toLowerCase();
				final ArrayList<Feed_ItemObject> TempList = new ArrayList<Feed_ItemObject>();
				for(Feed_ItemObject Sis_ItemObject : MyArrayObjects){
					//Filters both from Name and Bottom Text
					if((Sis_ItemObject.getName() +" "+Sis_ItemObject.getRightText() +" "+ Sis_ItemObject.getBottomText()).toLowerCase().contains(filterString)){
						TempList.add(Sis_ItemObject);
					}
				}
				
				filterResults.values = TempList;
				filterResults.count = TempList.size();
			}
			
			return filterResults;
		}

		@Override
		protected void publishResults(CharSequence arg0, FilterResults arg1) {
			// TODO Auto-generated method stub
			FilteredObjects = (ArrayList<Feed_ItemObject>) arg1.values;
			notifyDataSetChanged();
		}
		
	}
	
}
