package main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ServerFrame extends JFrame{

	private Server server;
	private ScrollPane scrollPane;
	
	private JTabbedPane tabPane;
	private LogInPanel logIn;
	private WaitingRoomPanel waitRoom;
	private MessagePanel messagePanel;

	private CallBackClientbtwService callBackService;
	
	private JPanel mainPanel;
	private JTextArea mainBoard;
	
	private JButton button;
	private JLabel label;
	private JTextField ipField;
	private JLabel ipLabel;
	private JTextField portField;
	private JLabel portLabel;
	private JTextField nameField;
	private JLabel nameLabel;
	private JLabel nateIcon;

	public ServerFrame(Server server) {
		this.server=server;
		initData();
		setInitLayout();
		initListener();
	} 

	private void initData() {
		
		mainPanel=new JPanel();
		mainBoard=new JTextArea();
		
		logIn=new LogInPanel(callBackService);
		waitRoom=new WaitingRoomPanel(callBackService);
		messagePanel=new MessagePanel(callBackService);
		tabPane=new JTabbedPane(JTabbedPane.TOP);
		mainPanel=new JPanel();
		
		scrollPane=new ScrollPane();
		
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

		setTitle("[네이트온 서버관리자]");
		setSize(400,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		add(mainPanel);
		add(tabPane);
		
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		tabPane.setBounds(0,0,getWidth(),getHeight());
		mainPanel.add(tabPane);
		
		logIn.setLayout(null);
		tabPane.addTab("로그인", null,logIn,null);
		tabPane.addTab("대기실",null,waitRoom,null);
		tabPane.addTab("채팅",null,messagePanel,null);
		
		add(nateIcon);
		add(ipLabel);
		add(ipField);
		add(portLabel);
		add(portField);
		add(nameLabel);
		add(nameField);
		add(button);
		
	}
	
	private void initListener() {
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				server.startConnect();
			}
		});
	}
	
	private class BackgroundPanel extends JPanel{
		private JPanel backgroundPanel;
		
		public BackgroundPanel() {
			backgroundPanel=new JPanel();
			add(backgroundPanel);
		}
	}

	public Server getServer() {
		return server;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}


	public JTextArea getMainBoard() {
		return mainBoard;
	}
	public JButton getButton() {
		return button;
	}

	public JLabel getLabel() {
		return label;
	}

	public JTextField getIpField() {
		return ipField;
	}

	public JLabel getIpLabel() {
		return ipLabel;
	}

	public JTextField getPortField() {
		return portField;
	}

	public JLabel getPortLabel() {
		return portLabel;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JLabel getNateIcon() {
		return nateIcon;
	}
	
	}

