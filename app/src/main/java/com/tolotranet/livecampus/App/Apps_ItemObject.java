package com.tolotranet.livecampus.App;


public class Apps_ItemObject {
	private String Name;
	private String BottomText;
	private int Index;
	private int UserId;
	private int ImgId;
	private int menuId;
	private int score;

	public String getName(){
		return this.Name;
	}
	
	public String getBottomText(){
		return this.BottomText;
	}
	
	public int getIndex(){
		return this.Index;
	}
	public int getUserId(){
		return this.UserId;
	}
	public int getImgId(){
		return this.ImgId;
	}


	public void setName(String name){
		this.Name = name;
	}
	
	public void setBottomText(String bottomText){
		this.BottomText = bottomText;
	}
	
	public void setIndex(int index){
		this.Index = index;
	}

	public void setUserId(int userId){
		this.UserId = userId;
	}

	public void setImgId(int imgId){
		this.ImgId = imgId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
}
