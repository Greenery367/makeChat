package main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JPanel{

	
	private JFrame mainFrame;
	private JPanel loginPanel;
	private JButton button;
	private JLabel label;
	private JTextField ipField;
	private JLabel ipLabel;
	private JTextField portField;
	private JLabel portLabel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JLabel nateIcon;
	
	private CallBackClientbtwService callBackService;

	public LogInPanel(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		setSize(400, 700);
		
		initData();
		setInitLayout();
		logIn();
	} 
	
	

	public JFrame getMainFrame() {
		return mainFrame;
	}



	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}



	public JPanel getLoginPanel() {
		return loginPanel;
	}



	public void setLoginPanel(JPanel loginPanel) {
		this.loginPanel = loginPanel;
	}



	public JButton getButton() {
		return button;
	}



	public void setButton(JButton button) {
		this.button = button;
	}



	public JLabel getLabel() {
		return label;
	}



	public void setLabel(JLabel label) {
		this.label = label;
	}



	public JTextField getIpField() {
		return ipField;
	}



	public void setIpField(JTextField ipField) {
		this.ipField = ipField;
	}



	public JLabel getIpLabel() {
		return ipLabel;
	}



	public void setIpLabel(JLabel ipLabel) {
		this.ipLabel = ipLabel;
	}



	public JTextField getPortField() {
		return portField;
	}



	public void setPortField(JTextField portField) {
		this.portField = portField;
	}



	public JLabel getPortLabel() {
		return portLabel;
	}



	public void setPortLabel(JLabel portLabel) {
		this.portLabel = portLabel;
	}



	public JTextField getNameField() {
		return nameField;
	}



	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}



	public JLabel getNameLabel() {
		return nameLabel;
	}



	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}



	public JLabel getNateIcon() {
		return nateIcon;
	}



	public void setNateIcon(JLabel nateIcon) {
		this.nateIcon = nateIcon;
	}



	public CallBackClientbtwService getCallBackService() {
		return callBackService;
	}



	public void setCallBackService(CallBackClientbtwService callBackService) {
		this.callBackService = callBackService;
	}



	private void initData() {
		button = new JButton("로그인");
		label = new JLabel("네이트온 채팅 참여하기");
		ipLabel = new JLabel("IP : ");
		ipField = new JTextField(10);
		portLabel = new JLabel("Port number : ");
		portField = new JTextField(10);
		nameLabel = new JLabel("Name : ");
		nameField = new JTextField(10);
		nateIcon=new JLabel(new ImageIcon("images/NATEON-PC.png"));
	}

	private void setInitLayout() {

		setLayout(null);
		setVisible(true);
		
		nateIcon.setBounds(125,125,150,150);
		ipLabel.setBounds(130, 350, 40, 20);
		ipField.setBounds(160, 350, 80, 20);
		portLabel.setBounds(70, 380, 80, 20);
		portField.setBounds(160, 380, 80, 20);
		nameLabel.setBounds(110, 410, 60, 20);
		nameField.setBounds(160, 410, 80, 20);
		button.setBounds(150,450,90,20);
		
		add(nateIcon);
		add(ipLabel);
		add(ipField);
		add(portLabel);
		add(portField);
		add(nameLabel);
		add(nameField);
		add(button);
		
	}
	
	private void logIn() {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clickConnectBtn();
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					clickConnectBtn();
				}
			}
		});
	}

	private void clickConnectBtn() {
		if((!ipField.getText().equals(null))&&(!portField.getText().equals(null))
				&&(!nameField.getText().equals(null))) {
			String ip=ipField.getText();		
			int port=Integer.parseInt(portField.getText());
			String name=nameField.getText();
			
			callBackService.clickConnectServerBtn(ip, port, name);
		
		} else {
			JOptionPane.showMessageDialog(null, "다시 입력해주세요");
		}
	}
	
	public static void main(String[] args) {
	}

}
