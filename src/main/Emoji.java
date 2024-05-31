package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Emoji extends JFrame {
	
	private CallBackClientbtwService callBackService;
	
	private JFrame sendEmoji;
	private JPanel sendEmojiPanel;
	
	private JButton[] smallE;
	
	JButton smallE1=new JButton();
	JButton smallE2=new JButton();
	JButton smallE3=new JButton();
	JButton smallE4=new JButton();
	JButton smallE5=new JButton();
	JButton smallE6=new JButton();
	JButton smallE7=new JButton();
	JButton smallE8=new JButton();
	JButton smallE9=new JButton();
	JButton smallE10=new JButton();
	JButton smallE11=new JButton();
	
	
	public Emoji(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		initData();
		sendEmoji();
	}
	
	private void initData() {
		JFrame sendEmoji=new JFrame("이모티콘 선택창");
		
		sendEmoji.setSize(292,135);
		sendEmoji.setVisible(true);
		sendEmoji.setLocationRelativeTo(null);
		
		sendEmoji.setLayout(null);
		sendEmoji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton smallE1=new JButton(new ImageIcon("images/emoji/a1.png"));
		JButton smallE2=new JButton(new ImageIcon("images/emoji/a2.png"));
		JButton smallE3=new JButton(new ImageIcon("images/emoji/a3.png"));
		JButton smallE4=new JButton(new ImageIcon("images/emoji/a4.png"));
		JButton smallE5=new JButton(new ImageIcon("images/emoji/a5.png"));
		JButton smallE6=new JButton(new ImageIcon("images/emoji/a6.png"));
		JButton smallE7=new JButton(new ImageIcon("images/emoji/a7.png"));
		JButton smallE8=new JButton(new ImageIcon("images/emoji/a8.png"));
		JButton smallE9=new JButton(new ImageIcon("images/emoji/a9.png"));
		JButton smallE10=new JButton(new ImageIcon("images/emoji/a10.png"));
		JButton smallE11=new JButton(new ImageIcon("images/emoji/a11.png"));
		JButton smallE12=new JButton(new ImageIcon("images/emoji/a12.png"));
		
		smallE1.setBounds(5,5,40,40);
		smallE2.setBounds(50,5,40,40);
		smallE3.setBounds(95,5,40,40);
		smallE4.setBounds(140,5,40,40);
		smallE5.setBounds(185,5,40,40);
		smallE6.setBounds(230,5,40,40);
		smallE7.setBounds(5,50,40,40);
		smallE8.setBounds(50,50,40,40);
		smallE9.setBounds(95,50,40,40);
		smallE10.setBounds(140,50,40,40);
		smallE11.setBounds(185,50,40,40);
		smallE12.setBounds(230,50,40,40);
		
		sendEmoji.getContentPane().add(smallE1);
		sendEmoji.getContentPane().add(smallE2);
		sendEmoji.getContentPane().add(smallE3);
		sendEmoji.getContentPane().add(smallE4);
		sendEmoji.getContentPane().add(smallE5);
		sendEmoji.getContentPane().add(smallE6);
		sendEmoji.getContentPane().add(smallE7);
		sendEmoji.getContentPane().add(smallE8);
		sendEmoji.getContentPane().add(smallE9);
		sendEmoji.getContentPane().add(smallE10);
		sendEmoji.getContentPane().add(smallE11);
		sendEmoji.getContentPane().add(smallE12);
		
	}
	
	private void sendEmoji() {
		smallE1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("이모지 보내기~");
				callBackService.clickSendMessageBtn("images/emoji/a1.png");
			}
		});
		
	}
	
	
	

}
