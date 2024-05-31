package main;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ServerFrame extends JFrame{

	private Server server;
	private ScrollPane scrollPane;
	private JLabel nateIcon;
	private JTabbedPane tabPane;
	private JPanel portPanel;

	private JPanel checkFortune;
	
	private JPanel checkClientPanel;
	private JPanel checkRoomPanel;
	
	private JPanel backgroundPanel;
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
	private JLabel title;
	private JLabel title1;

	public ServerFrame(Server server) {
		this.server=server;
		initData();
		setInitLayout();
		initListener();
	} 

	private void initData() {
		checkClientPanel=new checkClientPanel(callBackService);
		checkRoomPanel=new checkRoomPanel(callBackService,this);
		checkFortune=new checkFortunePanel(callBackService);
		backgroundPanel=new JPanel();
		
		mainPanel=new JPanel();
		mainBoard=new JTextArea();
		
		tabPane=new JTabbedPane(JTabbedPane.LEFT);
		mainPanel=new JPanel();
		
		scrollPane=new ScrollPane();
		portPanel=new JPanel();
		
		button = new JButton("로그인");
		portLabel = new JLabel("Port number : ");
		portField = new JTextField(10);
		nateIcon=new JLabel(new ImageIcon("images/NATEON-PC.png"));
		
		portField.setText("5555");
		title=new JLabel("Nate on에 오신 것을 환영합니다.");
		title1=new JLabel("채팅을 효율적으로 관리해보세요.");
		
	
	}

	private void setInitLayout() {
		setTitle("[네이트온]");
		setSize(520,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backgroundPanel.setSize(getWidth(),getHeight());
		backgroundPanel.setLayout(null);
		portPanel.setLayout(null);
		add(backgroundPanel);
		
		mainPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), "Server"));
		mainPanel.setBounds(40, 100, 320, 350);
		mainBoard.setEnabled(false);
		mainPanel.add(scrollPane);
		scrollPane.setBounds(45, 100, 300, 315);
		scrollPane.add(mainBoard);
		backgroundPanel.add(mainPanel);

		
		portPanel.setBackground(new Color(0, 0, 0, 0));
		nateIcon.setBounds(125, 125, 150, 150);
		portLabel.setBounds(100, 355, 80, 20);
		portField.setBounds(190, 355, 80, 20);
		
		title.setBounds(100,300,200,30);
		title1.setBounds(100,320,200,30);
		button.setBounds(140, 410, 90, 30);
		
		portPanel.add(portLabel);
		portPanel.add(portField);
		portPanel.add(nateIcon);
		portPanel.add(button);
		portPanel.add(title);
		portPanel.add(title1);
		mainPanel.add(portPanel);
	
		
		tabPane.setBounds(0,0,getWidth(),getHeight());
		backgroundPanel.add(tabPane);
		
		
		tabPane.addTab("서버 만들기",null,portPanel,null);
		tabPane.addTab("채팅 확인하기",null,mainPanel,null);
		tabPane.addTab("접속 멤버 관리하기",null,checkClientPanel,null);
		tabPane.addTab("채팅창 관리하기",null,checkRoomPanel,null);
		tabPane.addTab("운세 보기",null,checkFortune,null);
		
		
		setVisible(true);
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

