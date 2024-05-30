package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class checkClientPanel extends JPanel {
	
	private JPanel backgroundPanel;
	private JPanel clientListPanel;
	private JPanel mainPanel;
	
	private JList<String> userList;
	
	private JButton noticeBtn;
	
	private Vector<String> clientIdVector=new Vector<>();
	
	private CallBackClientbtwService callBackService;
	
	public checkClientPanel(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		initObject();
		initSetting();
		initListener();
	}
	
	private void initObject() {
		backgroundPanel=new JPanel();
		mainPanel=new JPanel();
		clientListPanel=new JPanel();
		
		userList=new JList<>();
		
		noticeBtn=new JButton("쪽지 보내기");
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);
		
		clientListPanel.setBounds(50, 30, 120, 260);
		clientListPanel.setBackground(Color.WHITE);
		clientListPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 3), "user List"));
		
		clientListPanel.add(userList);
		add(clientListPanel);
	}
	
	private void initListener() {
		
	}
	
	
	
	
}
