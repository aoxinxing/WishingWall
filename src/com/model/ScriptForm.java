package com.model;

import java.util.Date;

public class ScriptForm {
	public int id = 0;				
	public String wishMan = "";		
	public String wellWisher = "";	
	public String content = "";		
	public int color = 0;			
	public int face = 0;			
	public Date sendTime = null;	
	public int hits = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWishMan() {
		return wishMan;
	}
	public void setWishMan(String wishMan) {
		this.wishMan = wishMan;
	}
	public String getWellWisher() {
		return wellWisher;
	}
	public void setWellWisher(String wellWisher) {
		this.wellWisher = wellWisher;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}	
}
