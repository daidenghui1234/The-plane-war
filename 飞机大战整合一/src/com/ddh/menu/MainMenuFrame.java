package com.ddh.menu;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import com.ddh.game.Blood;
import com.ddh.game.GameSound;
import com.ddh.game.MyGameFrame;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainMenuFrame extends javax.swing.JFrame{
	// 声明一个用于表示背景图的对象Image对象
	private static Image bgImage;
	//实例化声音
	public static GameSound gso = new GameSound();
	/***aa
	 * 静态代码块 静态代码块在类加载时被执行，而且只会执行一次
	 */
	static {
		// 使用JDK工具包根据图片路径获得Image对象
		bgImage = Toolkit.getDefaultToolkit().getImage("src\\images\\zhujiemian.jpg");
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		MainMenuFrame inst = new MainMenuFrame();
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				MainMenuFrame inst = new MainMenuFrame();
//				//inst.setLocationRelativeTo(null);
//				//inst.setVisible(true);
//			}
//		});

	}

	public MainMenuFrame() {
		super();
		initGUI();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		gso.playBgSound("./music/background_music.mp3");
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			this.setUndecorated(true);

			//线程的开启
			myThread mt = new myThread();
			mt.start();
			//是否显示
			this.addMouseMotionListener(new MouseMotionAdapter() {
				/**
				 * 鼠标移动事件
				 * 当鼠标移动到按钮上时让鼠标的样式变成手状
				 * **/
				public void mouseMoved(MouseEvent evt) {
					 //获得鼠标单击的坐标(X轴坐标，Y轴坐标)
					 int x = evt.getX();//X轴坐标
					 int y = evt.getY();//Y轴坐标
					 //根据鼠标单击的坐标位置，确定当前单击的按钮
					 if (x >= 300 && x <= 420 && y >= 450 && y <= 520) {//开始游戏按钮
						 //设置鼠标样式为手状
						MainMenuFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					 }else if (x >= 380 && x <= 500 && y >= 520 && y <= 570)  {//关于我们按钮
						//设置鼠标样式为手状
							MainMenuFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					 }else if (x >= 300 && x <= 420 && y >= 580 && y <= 630) {//退出按钮
						//设置鼠标样式为手状
							MainMenuFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					 }else if (x >= 380 && x <= 500 && y >= 650 && y <= 690) {
						 MainMenuFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					 }else{//当鼠标离开按钮时让鼠标样式回到默认样式
						 MainMenuFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					 }
				}
			});

			this.addMouseListener(new MouseAdapter() {
				/**
				 * 鼠标移动事件 当鼠标移动到按钮上时让鼠标的样式变成手状
				 * **/
				public void mouseClicked(MouseEvent evt) {
					// 获得鼠标的键值，已确定当前单击的是鼠标(左键、右键、滚轴键或其他键)
					// 1表示左键 3表示右键 2表示滚轴键
					int keyCode = evt.getButton();
					// 判断是否单击了鼠标的左键，单击左键进行处理其他键不处理
					if (keyCode != 1) {
						return;// 退出方法
					}
					// JOptionPane.showMessageDialog(MainMenuFrame.this,
					// keyCode+"");
					// 获得鼠标单击的坐标(X轴坐标，Y轴坐标)
					int x = evt.getX();// X轴坐标
					int y = evt.getY();// Y轴坐标
					//JOptionPane.showMessageDialog(MainMenuFrame.this, x + ","
					//		+ y);
					// 根据鼠标单击的坐标位置，确定当前单击的按钮
					if (x >= 300 && x <= 420 && y >= 450 && y <= 520) {// 开始游戏按钮
						//JOptionPane.showMessageDialog(MainMenuFrame.this,
						//		"开始游戏");
						MainMenuFrame.this.dispose(); // 关闭
						gso.stop();
						new MyGameFrame();
					}
					else if (x >= 380 && x <= 500 && y >= 520 && y <= 570) {

						//JOptionPane.showMessageDialog(MainMenuFrame.this,
						//		"选择难度");
						new SelectLeveJFrame(MainMenuFrame.this);
						//MainMenuFrame.this.setVisible(false);

						MainMenuFrame.this.dispose(); // 关闭
						//selectLeveJFrame.setVisible(true);
						//MainMenuFrame.this.setVisible(true);
					}
					else if (x >= 300 && x <= 420 && y >= 580 && y <= 630) {// 关于我们按钮
						// JOptionPane.showMessageDialog(MainMenuFrame.this,"关于我们");
						// 创建AboutDialogFrame对象
						//JOptionPane.showMessageDialog(MainMenuFrame.this,
						//		"关于我们");
						//MainMenuFrame.this.dispose(); // 关闭
						AboutDialogFrame about = new AboutDialogFrame(MainMenuFrame.this);
						about.setVisible(true);

					} else if (x >= 380 && x <= 500 && y >= 650 && y <= 690) {// 退出按钮
						// JOptionPane.showMessageDialog(MainMenuFrame.this,"退出");
						// 弹出确认对话框
						// 0表示确认 2表示取消
						int num = JOptionPane.showConfirmDialog(
								MainMenuFrame.this, "确认退出吗?", "系统提示",
								JOptionPane.OK_CANCEL_OPTION);
						if (num == 0) {
							System.exit(0);// 退出JVM
						}

					}
				}
			});
			pack();
			this.setSize(800, 730);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}

	}


	/***
	 * 重写父类的paint方法，用于绘制界面
	 */
	@Override
	public void paint(Graphics g) {
		// 绘制界面背景图
		/***
		 * 参数1:要绘制的图片(Image) 参数2:绘制的起始坐标X轴坐标位置 参数3:绘制的起始坐标Y轴坐标位置 参数4:绘制的宽度
		 * 参数5:绘制的高度j 参数6:要绘制的界面,this表示当前窗体对象
		 */
		g.drawImage(bgImage, 0, 0, 800, 730, this);
	}
	class myThread extends Thread {
		@Override
		public void run() {
			super.run();

		}
	}
	
}
