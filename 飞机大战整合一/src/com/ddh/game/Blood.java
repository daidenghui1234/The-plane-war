package com.ddh.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingUtilities;

import com.ddh.gamelevel.Level;
import com.ddh.menu.MainMenuFrame;

public class Blood extends Animation{
	
	private static Image[] imgs = new Image[6];
	public static int count= Level.getBlood();
    static {
        for(int i=0;i<6;i++){
            imgs[i] = GameUtil.getImage("images/blood/blood"+(i+1)+".png");
            imgs[i].getWidth(null);
        }
    }

	public static void setCount(int count) {
		Blood.count = count;
	}

	public Blood() {
	}
    public static int getCount() {
		return count;
	}
	public void draw(Graphics g) {
		g.drawImage(imgs[count], Constant.GAME_WIDTH-200, 40, null);
	}
	public void addBlood(){
		
		if(count<5)count ++;
	}
	public void minusBlood(){
		//System.out.println(count);
		if(count>0)count --;

	}


}
