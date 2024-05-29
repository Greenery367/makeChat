package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
	
	private CallBackClientbtwService callBackService;
	
	public JPanel getMainPanel() {
		return mainPanel;
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
		bottomPanel.setBounds(43, 380, 294, 35);
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setBorder(new LineBorder(Color.BLACK, 2));
		bottomPanel.add(writeMessageBox);
		bottomPanel.add(sendMessageBtn);
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
	
	private void sendMessage() {
		if(!writeMessageBox.getText().equals(null)) {
			String msg=writeMessageBox.getText();
			callBackService.clickSendMessageBtn(msg);
			writeMessageBox.setText("");
			writeMessageBox.requestFocus();
		}
	}
	
	
	

}
