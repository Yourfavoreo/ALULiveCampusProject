package com.example.tolotranet.livecampus;

public class Transp_ItemObject {
	private String Name;
	private String BottomText;
	private String DayText;
	private int Index;
	
	public String getName(){
		return this.Name;
	}
	
	public String getBottomText(){
		return this.BottomText;
	}
	public String getDayText(){
		return this.DayText;
	}

	public int getIndex(){
		return this.Index;
	}
	
	public void setName(String name){
		this.Name = name;
	}
	public void setDayText(String datTxt){
		this.DayText = datTxt;
	}

	public void setBottomText(String bottomText){
		this.BottomText = bottomText;
	}
	
	public void setIndex(int index){
		this.Index = index;
	}
}
