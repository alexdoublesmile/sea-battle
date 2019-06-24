package com.plohoy.seabattle.model;

public class Shot {

	private int xCoord, yCoord;
	private boolean shot;
	
	Shot(int x, int y, boolean shot) {
		
		this.xCoord = x;	
		this.yCoord = y;	
		this.shot = shot;
	}
	public int getXCoord() {
		return xCoord;
	}
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	public int getYCoord() {
		return yCoord;
	}
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	public boolean isShot() {
		return shot;
	}
	
}
