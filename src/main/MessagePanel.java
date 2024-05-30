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
		imojiBtn=new JButton(new ImageIcon("images/emoji/icon.png"));
		
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
		JOptionPane imojiPane=new JOptionPane();
		JButton[] emojis=new JButton[6];
		emojis[0]=new JButton(new ImageIcon("images/emoji/emoji1.png"));
		emojis[1]=new JButton(new ImageIcon("images/emoji/emoji2.png"));
		emojis[2]=new JButton(new ImageIcon("images/emoji/emoji3.png"));
		emojis[3]=new JButton(new ImageIcon("images/emoji/icon.png"));
		emojis[4]=new JButton(new ImageIcon("images/emoji/icon.png"));
		imojiPane.showOptionDialog(this, "이모티콘을 선택하세요.", "Choose Emoji", ALLBITS, ABORT, null, emojis,null);
		imojiPane.add(emojis[0]);
		imojiPane.add(emojis[1]);
		imojiPane.add(emojis[2]);
		imojiPane.add(emojis[3]);
		imojiPane.add(emojis[4]);
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
