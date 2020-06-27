/*
 * 
 * Create by_xiaoqing on 2018-04-05
 * 
 */
package com.ddh.game;

import com.ddh.gamelevel.Level;
import com.ddh.menu.MainMenuFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * 
 * 游戏主窗口
 * 游戏开始前，关闭输入法
 * w前，s后，a左，d右+shift（可以加速）
 * 
 *
 */
public class MyGameFrame extends Frame {
	private Image plane_img;//飞机图片
	private Image bg;//背景图片
	private Image supply_img;//加血包
	private MyPlane plane;//我的飞机
	private Image army_images[];//敌机图片
	private Plane armys[];//敌机
	private Image offScreenImage;//用于双缓冲
	private int score;//所获分数
	private SupplyPacket supply;//医疗包
	
	/*用于画窗口的线程*/
	private Thread paintThread;//重画窗口线程
	private Thread planefire;//我方飞机发射子弹的线程
	private Thread armysfire[];//敌方飞机发射子弹的线程
	private Thread supplyThread;//补给出现的线程
	private static Image win;

	/***aa
	 * 静态代码块 静态代码块在类加载时被执行，而且只会执行一次
	 */
	static {
		// 使用JDK工具包根据图片路径获得Image对象
		win = Toolkit.getDefaultToolkit().getImage("src/images/win.jpg");
	}
	/**
	 * 用于加载图片
	 */
	public void load(){
		/*加载飞机图片*/
		plane_img=GameUtil.getImage("images/plane.png");
		/*加载背景图片*/
		bg=GameUtil.getImage(Level.getBackground());
		/*加载敌机图片*/
		army_images=new Image[6];
		for(int i=0;i<army_images.length;i++){
			army_images[i] = GameUtil.getImage("images/army/army"+(i+1)+".png");
            army_images[i].getWidth(null);
		}
		/*加载补给包图片*/
		supply_img=GameUtil.getImage("images/supply.png");
		win=GameUtil.getImage("images/win.jpg");
		
	}
	/**
	 * 用于初始化窗口
	 */
	public void init(){

		//MainMenuFrame.gso.playBgSound("./music/background_music.mp3");


		setTitle("PlaneGame1.0");
		setVisible(true);
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(new KeyMonitors());
		plane=new MyPlane(plane_img,288,460);
		supply=new SupplyPacket(supply_img);
		armys=new Plane[Level.getArmys()];
		for(int i=0;i<armys.length;i++){
			armys[i]=new Plane(army_images[i%6]);
		}
		paintThread=new PaintThread();
		planefire=new Thread(plane);
		armysfire=new Thread[armys.length];  //创建敌方飞机发射子弹的线程
		for(int i=0;i<armys.length;i++){     //敌方飞机发射子弹的线程
			armysfire[i]=new Thread(armys[i]);
		}
		supplyThread=new Thread(supply);
	}
	/**
	 * 用于开启线程
	 */
	public void start(){
		paintThread.start();
		planefire.start();
		for(int i=0;i<armys.length;i++){
			armysfire[i].start();
		}
		supplyThread.start();
	}
	/*
	 * 内部的一个线程类，用于重画窗口
	 */
	class PaintThread extends Thread{
		public void run() {
			while(true){

				repaint();

				try{
					Thread.sleep(40);//1秒画25次
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * 内部类，监听键盘
	 */
	class KeyMonitors extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}
	@Override
	/**
	 * 用于画窗口
	 */
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0,800,730, null); // 背景图
		drawScore(g);
		supply.drawSelf(g);
		plane.drawSelf(g);
		for(int i=0;i<plane.bullets.length;i++){
			plane.bullets[i].drawSelf(g);
		}

		for(int i=0;i<armys.length;i++){
			armys[i].drawSelf(g);
		}
		for(int i=0;i<armys.length;i++){
			for(int j=0;j<armys[i].bullets.length;j++){
				armys[i].bullets[j].drawSelf(g);
			}
		}
		plane.blood.draw(g);
		isCollide();//判断是否发现碰撞
			
	}
	/**
	 * 用于双缓冲
	 */
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
	/**
	 * 用于判断是否发生碰撞
	 */
	public void isCollide(){
		for(int i=0;i<armys.length;i++){
			boolean peng=false;
			for(int j=0;j<plane.bullets.length;j++){
				if(plane.bullets[j].isLive()&&
						(armys[i].getRect().intersects
								(plane.bullets[j].getRect()))){
					peng=true;
					plane.bullets[j].setLive(false);
					score+=Level.score;
					break;
				}
				else{
					continue;
				}
			}
			if(peng){
				armys[i].setCollide(true);
				if(armys[i].explode1.count==0){
					armys[i].explode1.setLocation(armys[i].getX(), armys[i].getY());
					armys[i].ex1=true;
				}
				else{
					armys[i].explode2.setLocation(armys[i].getX(), armys[i].getY());
					armys[i].ex2=true;
				}
					armys[i].setLive(false);
			}//判断敌方飞机是否被击中
			for(int j=0;j<armys[i].bullets.length;j++){
				if(armys[i].bullets[j].isLive()&&(armys[i].bullets[j].getRect().intersects
						(plane.getRect()))){
					plane.blood.minusBlood();
//					GameSound gameSound = new GameSound();
//					gameSound.playSound("./music/Enemy_Boom.mp3");
					armys[i].bullets[j].setLive(false);
				}
			}//判断我方飞机是否被击中
		}
		if(supply.isLive()&&plane.isLive()
				&&plane.getRect()
				.intersects(supply.getRect())){
			plane.blood.addBlood();
			supply.setLive(false);
		}//判断我方飞机是否获得补给
	}
	/**)
		}
	 * 用于画分数
				*/
		public void drawScore(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.WHITE);
			g.setFont(new Font("宋体", Font.BOLD, 20));
			g.drawString("得分为："+score + " 目标分数："+Level.WinScore, 20, 50);
			g.setColor(c);
			GameSound gameSound = new GameSound();
			if(score >= Level.WinScore) {
				try{
					gameSound.playSound("./music/GetItem.mp3");
					Thread.sleep(1000);
					score = 0;
					Blood.count=5;

				}catch(Exception e){
					System.exit(0);//退出程序
				}
				MainMenuFrame.gso.stop();
				MyGameFrame.this.dispose();
				new MainMenuFrame();
			}
			if(Blood.count == 0 ){

				MainMenuFrame.gso.stop();
				try{

					gameSound.playSound("./music/gameover.mp3");
					Thread.sleep(2000);
					score = 0;
					Blood.count=5;
					MainMenuFrame.gso.stop();
					MyGameFrame.this.dispose();
					new MainMenuFrame();

				}catch(Exception e){
					System.exit(0);//退出程序
				}
			}
		}
	public MyGameFrame(){
			this.load();
			this.init();
			this.start();// setLocationRelativeTo
			this.setResizable(false);		// 禁止改变窗口大小
			this.setLocationRelativeTo(null);

	}
	public static void main(String args[]) {
		MyGameFrame f=new MyGameFrame();

	}

}
