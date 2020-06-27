package com.ddh.menu;

import com.ddh.game.MyGameFrame;
import com.ddh.gamelevel.Level;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class SelectLeveJFrame extends javax.swing.JDialog {
	private static Image aboutImage;

	/***
	 * 静态代码块
	 * 	静态代码块在类加载时被执行，而且只会执行一次
	 */
	static{
		//使用JDK工具包根据图片路径获得Image对象
		aboutImage = Toolkit.getDefaultToolkit().getImage("src\\images\\level.jpg");
	}

	public SelectLeveJFrame(JFrame frame) {
		super(frame);
		initGUI();
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setResizable(false);
			this.setUndecorated(true);
			/***
			 * 鼠标移动时间
			 */
			this.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent evt) {
					//JOptionPane.showMessageDialog(MainMenuFrame.this, keyCode+"");
					//获得鼠标单击的坐标(X轴坐标，Y轴坐标)
					int x = evt.getX();//X轴坐标
					int y = evt.getY();//Y轴坐标
					// JOptionPane.showMessageDialog(AboutDialogFrame.this, x+","+y);
					if(x>=280 && x<=420 && y>=470 && y<=520){
						SelectLeveJFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}else if(x>=370 && x<=505 && y>=530 && y<=580) {
						SelectLeveJFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}else if(x>=280 && x<=420 && y>=590 && y<=640) {
						SelectLeveJFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}else if(x>=370 && x<=505 && y>=650 && y<=690) {
						SelectLeveJFrame.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
					} else{
						SelectLeveJFrame.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}
			});
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					//获得鼠标的键值，已确定当前单击的是鼠标(左键、右键、滚轴键或其他键)
					//1表示左键   3表示右键  2表示滚轴键
					int keyCode = evt.getButton();
					//判断是否单击了鼠标的左键，单击左键进行处理其他键不处理
					if(keyCode != 1){
						return;//退出方法
					}
					//JOptionPane.showMessageDialog(MainMenuFrame.this, keyCode+"");
					//获得鼠标单击的坐标(X轴坐标，Y轴坐标)
					int x = evt.getX();//X轴坐标
					int y = evt.getY();//Y轴坐标
					//JOptionPane.showMessageDialog(SelectLeveJFrame.this, x+","+y);
					if(x>=280 && x<=420 && y>=470 && y<=520){
						Level.setArmys(2);
						new MyGameFrame();
						MainMenuFrame.gso.stop();
						//卸载当前窗体，并释放窗体所占资源
						SelectLeveJFrame.this.dispose();
					}else if(x>=370 && x<=505 && y>=530 && y<=580) {
						Level.setArmys(4);
						Level.WinScore = 100;
						MainMenuFrame.gso.stop();
						Level.setBackground("images/background/一般.jpg");
						new MyGameFrame();
						//卸载当前窗体，并释放窗体所占资源
						SelectLeveJFrame.this.dispose();
					}else if(x>=280 && x<=420 && y>=590 && y<=640) {
						Level.setBackground("images/background/困难.jpg");
						Level.setArmys(10);
						Level.WinScore = 150;
						new MyGameFrame();
						MainMenuFrame.gso.stop();
						//卸载当前窗体，并释放窗体所占资源
						SelectLeveJFrame.this.dispose();
					}else if(x>=370 && x<=505 && y>=650 && y<=690) {

						Level.setBackground("images/background/地狱.jpg");
						Level.WinScore = 200;
						Level.setArmys(20);
						new MyGameFrame();
						MainMenuFrame.gso.stop();
						SelectLeveJFrame.this.dispose();
					}
				}
			});
			this.setSize(800, 730);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 *  重写父类的paint方法，用于绘制界面
	 */
	@Override
	public void paint(Graphics g) {
		//绘制界面背景图
		/***
		 * 参数1:要绘制的图片(Image)
		 * 参数2:绘制的起始坐标X轴坐标位置
		 * 参数3:绘制的起始坐标Y轴坐标位置
		 * 参数4:绘制的宽度
		 * 参数5:绘制的高度j
		 * 参数6:要绘制的界面,this表示当前窗体对象
		 */
		g.drawImage(aboutImage, 0, 0, 800,730,this);
	}

}
