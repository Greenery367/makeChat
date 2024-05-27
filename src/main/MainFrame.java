package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	
	private ImageIcon backgroundImage=new ImageIcon("images/kakao.png");
	private Image bImage=backgroundImage.getImage();
	private MyPanel myPanel;
	private JPanel firstPanel;
	
	
	public MainFrame() {
		initData();
	}
	
	private void initData() {
		ImageIcon backgroundImage=new ImageIcon("images/kakao.png");
		Image bImage=backgroundImage.getImage();
		
		this.setTitle("카카오톡 예제 만들기");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyPanel myPanel=new MyPanel();
		myPanel.setLayout(new FlowLayout());
		myPanel.add(new JButton("Log In"));
		
		this.add(myPanel);
		this.setSize(400,711);
		this.setVisible(true);
	}
	
	public class MyPanel extends JPanel{
		public void paintComponbents(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bImage,0,0,400,711,this);
		}
	}
	

	public static void main(String[] args) {
		new MainFrame();
	}
	
}
