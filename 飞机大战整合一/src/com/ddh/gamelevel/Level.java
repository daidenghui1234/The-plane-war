package com.ddh.gamelevel;

public class Level {
	public static  int FRAME_WIDTH=800,FRAME_HEIGHT=800;
	private static String background = "images/background/bg.jpg";
	private static int armys = 2;
	public static boolean creat = true;
	private static int blood =5;
	public static int score = 10;
	public static int WinScore = 50;



	public static int getBlood() {
		return blood;
	}
	public static void setBackground(String background) {
		Level.background = background;
	}
	public static String getBackground() {
		return background;
	}
	public static void setArmys(int armys) {
		Level.armys = armys;
	}
	public static int getArmys() {
		return armys;
	}

}
