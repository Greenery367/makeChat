package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class checkRoomPanel extends JPanel {
	
	private JPanel backgroundPanel;
	private JPanel roomListPanel;
	private JPanel mainPanel;
	
	private JList<String> roomList;
	private JTextArea checkChatBoard;
	private ScrollPane scrollPane;
	
	private JLabel title;
	
	private JButton noticeBtn;
	
	private Server server;
	
	private CallBackClientbtwService callBackService;
	
	public checkRoomPanel(CallBackClientbtwService callBackService,ServerFrame serverFrame) {
		this.callBackService=callBackService;
		initObject();
		initSetting();
		initListener();
	}
	
	private void initObject() {
		backgroundPanel=new JPanel();
		mainPanel=new JPanel();
		
		title=new JLabel("채팅창 관리하기");
		
		roomListPanel=new JPanel();		
		roomList=new JList<>();
		
		checkChatBoard=new JTextArea();
		scrollPane=new ScrollPane();
		
		noticeBtn=new JButton("쪽지 보내기");
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);
		
		title.setBounds(37,20,150,50);
		title.setFont(new Font("HYGothic",Font.BOLD,20));
		add(title);
		
		roomListPanel.setBounds(37, 80, 300, 120);
		roomListPanel.setBackground(Color.WHITE);
		roomListPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "Chatting Room List"));
		
		checkChatBoard.setBounds(37,220,300,300);
		checkChatBoard.setBackground(Color.white);
		checkChatBoard.setEnabled(false);
		checkChatBoard.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "Chatting Log"));
		
		roomListPanel.add(roomList);
		add(roomListPanel);
		add(checkChatBoard);
	}
	
	private void initListener() {
		
	}
	
	
	
	
}
