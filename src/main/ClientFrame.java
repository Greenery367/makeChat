package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ClientFrame extends JFrame {
	
	private JTabbedPane tabPane;
	private JPanel mainPanel;
	
	private LogInPanel logIn;
	private WaitingRoomPanel waitRoom;
	private MessagePanel messagePanel;
	private JPanel checkFortune;
	
	private CallBackClientbtwService callBackService;
	

	public ClientFrame(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		initObject();
		initSetting();
	}
	
	
	public JTabbedPane getTabPane() {
		return tabPane;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public LogInPanel getLogIn() {
		return logIn;
	}

	public WaitingRoomPanel getWaitRoom() {
		return waitRoom;
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

	public CallBackClientbtwService getCallBackService() {
		return callBackService;
	}



	private void initObject() {
		logIn=new LogInPanel(callBackService);
		waitRoom=new WaitingRoomPanel(callBackService);
		messagePanel=new MessagePanel(callBackService);
		checkFortune=new checkFortunePanel(callBackService);
		tabPane=new JTabbedPane(JTabbedPane.TOP);
		mainPanel=new JPanel();
	}
	
	private void initSetting() {
		setTitle("[네이트온]");
		setSize(400,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(mainPanel);
		add(tabPane);
		
		mainPanel.setBorder(new EmptyBorder(5,5,5,5));
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		
		tabPane.setBounds(0,0,getWidth(),getHeight());
		mainPanel.add(tabPane);
		
		logIn.setLayout(null);
		tabPane.addTab("로그인", null,logIn,null);
		tabPane.addTab("대기실",null,waitRoom,null);
		tabPane.addTab("채팅",null,messagePanel,null);
		tabPane.addTab("운세 보기",null,checkFortune,null);
		
		setVisible(true);
	}

}