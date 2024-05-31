package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MessagePanel extends JPanel {
	
	private JPanel backgroundPanel;
	
	private JPanel mainPanel;
	private JPanel bottomPanel;
	
	private ScrollPane scrollPane;
	
	private JTextArea mainMessageBox;
	private JTextField writeMessageBox;
	
	private JButton sendMessageBtn;
	private JButton sendImageBtn;
	private JButton saveLogBtn;
	private JButton imojiBtn;
	
	private CallBackClientbtwService callBackService;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}


	public JPanel getBackgroundPanel() {
		return backgroundPanel;
	}


	public JPanel getBottomPanel() {
		return bottomPanel;
	}


	public ScrollPane getScrollPane() {
		return scrollPane;
	}


	public JTextField getWriteMessageBox() {
		return writeMessageBox;
	}


	public JButton getSendImageBtn() {
		return sendImageBtn;
	}


	public JButton getImojiBtn() {
		return imojiBtn;
	}


	public JButton getSaveLogBtn() {
		return saveLogBtn;
	}


	public CallBackClientbtwService getCallBackService() {
		return callBackService;
	}


	public JButton getSendMessageBtn() {
		return sendMessageBtn;
	}

	public JTextArea getMainMessageBox() {
		// TODO Auto-generated method stub
		return mainMessageBox;
	}
	
	public MessagePanel(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		initObject();
		initSetting();
		initListener();
		sendImoji();
	}
	
	private void initObject() {
		backgroundPanel=new JPanel();
		
		mainPanel=new JPanel();
		bottomPanel=new JPanel();
		
		scrollPane=new ScrollPane();
		
		mainMessageBox = new JTextArea();
		writeMessageBox=new JTextField(17);
		sendMessageBtn=new JButton("전송");
		sendImageBtn=new JButton("이미지 전송");
		saveLogBtn=new JButton("채팅 저장하기");
		imojiBtn=new JButton(new ImageIcon("images/emoji/clickButton.png"));
		
	}
	
	private void initSetting() {
		setSize(getWidth(),getHeight());
		setLayout(null);
		
		backgroundPanel.setSize(getWidth(),getHeight());
		backgroundPanel.setLayout(null);
		add(bottomPanel);
		
		mainMessageBox.setEnabled(false);
		mainPanel.setBounds(40, 20, 300, 350);
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "Message"));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.add(scrollPane);
		scrollPane.setBounds(45, 15, 280, 310);
		scrollPane.add(mainMessageBox);
		add(mainPanel);

		sendMessageBtn.setBackground(Color.WHITE);
		sendMessageBtn.setPreferredSize(new Dimension(60, 20));
		sendMessageBtn.setEnabled(false);
		
		imojiBtn.setBackground(Color.WHITE);
		imojiBtn.setPreferredSize(new Dimension(20, 20));
		imojiBtn.setEnabled(false);
		
		sendImageBtn.setBackground(Color.WHITE);
		sendImageBtn.setEnabled(false);
		
		saveLogBtn.setBackground(Color.WHITE);
		saveLogBtn.setEnabled(false);
		
		bottomPanel.setBounds(43, 380, 294, 70);
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setBorder(new LineBorder(Color.BLACK, 2));
		bottomPanel.add(writeMessageBox);
		bottomPanel.add(sendMessageBtn);
		bottomPanel.add(imojiBtn);
		bottomPanel.add(sendImageBtn);
		bottomPanel.add(saveLogBtn);
		
		add(bottomPanel);
		
	}
	
	private void initListener() {
		sendMessageBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sendMessage();
			}
		});
		
		writeMessageBox.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});
	}
	
	private void sendImage() {
		saveLogBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sendingImage();
			}
		});
	}
	
	private void sendImoji() {
		imojiBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("이닛리스너 작동");
				sendingImoji();
			}
		});
	}
	
	private void saveLog() {
		sendImageBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				savingLog();
			}
		});
	}
	
	public void sendingImage() {
		JFileChooser fileComponent = new JFileChooser();
	}
	
	public void sendingImoji() {
		System.out.println("센딩 이모지 시작");
		JFrame sendEmoji=new JFrame("이모티콘 선택창");
		JPanel sendEmojiPanel=new JPanel();
		sendEmojiPanel.setBounds(0,0,getWidth(),getHeight());
		sendEmoji.setLocationRelativeTo(null);
		
		System.out.println("이미지 아이콘 생성 시작");
		ImageIcon[] emojis=new ImageIcon[10];
		emojis[0]=new ImageIcon("images/emoji/a1.png");
		emojis[1]=new ImageIcon("images/emoji/a2.png");
		emojis[2]=new ImageIcon("images/emoji/a3.png");
		emojis[3]=new ImageIcon("images/emoji/a4.png");
		emojis[4]=new ImageIcon("images/emoji/a5.png");
		emojis[5]=new ImageIcon("images/emoji/a6.png");
		emojis[6]=new ImageIcon("images/emoji/a7.png");
		emojis[7]=new ImageIcon("images/emoji/a8.png");
		emojis[8]=new ImageIcon("images/emoji/a9.png");
		emojis[9]=new ImageIcon("images/emoji/a10.png");
		
		System.out.println("버튼 생성");
		JButton s1=new JButton(emojis[0]);
		JButton s2=new JButton(emojis[1]);
		JButton s3=new JButton(emojis[2]);
		JButton s4=new JButton(emojis[3]);
		JButton s5=new JButton(emojis[4]);
		JButton s6=new JButton(emojis[5]);
		JButton s7=new JButton(emojis[6]);
		JButton s8=new JButton(emojis[7]);
		JButton s9=new JButton(emojis[8]);
		JButton s10=new JButton(emojis[9]);
		
		System.out.println("이모지 패널을 프레임에 붙이기");
		sendEmoji.add(sendEmojiPanel);
		
		System.out.println("패널에 버튼 붙이기");
		sendEmojiPanel.add(s1);
		sendEmojiPanel.add(s2);
		sendEmojiPanel.add(s3);
		sendEmojiPanel.add(s4);
		sendEmojiPanel.add(s5);
		sendEmojiPanel.add(s6);
		sendEmojiPanel.add(s7);
		sendEmojiPanel.add(s8);
		sendEmojiPanel.add(s9);
		sendEmojiPanel.add(s10);
		
		sendEmoji.setSize(420,160);
		sendEmoji.setLayout(null);
		sendEmoji.setVisible(true);
		sendEmoji.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void savingLog() {
		
	}
	
	private void sendMessage() {
		if(!writeMessageBox.getText().equals(null)) {
			String msg=writeMessageBox.getText();
			callBackService.clickSendMessageBtn(msg);
			writeMessageBox.setText("");
			writeMessageBox.requestFocus();
		}
	}
	
	
	

}
