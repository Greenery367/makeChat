package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client implements Protocol,CallBackClientbtwService{

	private JTextArea mainMessageBox;
	private JList<String> userList;
	private JList<String> roomList;
	private JButton enterRoomBtn;
	private JButton makeRoomBtn;
	private JButton outRoomBtn;
	private JButton secretMessageBtn;
	private JButton sendMessageBtn;
	
	private Vector<String> userIdList=new Vector<>();
	private Vector<String> roomNameList=new Vector<>();
	
	// 클라이언트 정보
	private String name;
	private String myRoomName;
	private String ip;
	private int port;
	
	private ClientFrame clientFrame;
	private Socket socket;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private String protocol;
	private String from;
	private String message;
	
	
	// 사용자 정의 생성자
	public Client() {
		clientFrame = new ClientFrame(this);
		mainMessageBox = clientFrame.getMessagePanel().getMainMessageBox();
		userList = clientFrame.getWaitRoom().getUserList();
		roomList = clientFrame.getWaitRoom().getRoomList();
		enterRoomBtn = clientFrame.getWaitRoom().getEnterRoomBtn();
		makeRoomBtn = clientFrame.getWaitRoom().getMakeRoomBtn();
		outRoomBtn = clientFrame.getWaitRoom().getOutRoomBtn();
		secretMessageBtn = clientFrame.getWaitRoom().getSecretMsgBtn();
		sendMessageBtn = clientFrame.getMessagePanel().getSendMessageBtn();
	}

	@Override
	public void clickConnectServerBtn(String ip, int port, String id) {
	
		this.port=port;
		this.name=id;
		this.ip=ip;
		connectNetwork();
		connectIO();
		try {
			writer.write(id+"\n");
			writer.flush();
			clientFrame.setTitle("[네이트온] "+name+"님의 대화창");
			
			clientFrame.getLogIn().getButton().setEnabled(false);
			makeRoomBtn.setEnabled(true);
			enterRoomBtn.setEnabled(true);
			secretMessageBtn.setEnabled(true);
			sendMessageBtn.setEnabled(true);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "접속 에러!","알림",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void connectNetwork(){
		try {
			this.socket=new Socket(ip,port);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "접속 에러!");
			e.printStackTrace();
		}
	}
	
	private void connectIO() {
		try {
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			readThread();
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "클라이언트 입출력 장치 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "클라이언트 입출력 장치 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	private void readThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						String msg = reader.readLine();

						checkProtocol(msg);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "클라이언트 입력 장치 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
						break;
					}
				}
			}
		}).start();
	}
	
	private void writer(String str) {
		try {
			writer.write(str+"\n");
			writer.flush();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "클라이언트 출력 장치 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	private void checkProtocol(String msg) {
		StringTokenizer tokenizer=new StringTokenizer(message,"/");
		
		protocol=tokenizer.nextToken();
		from=tokenizer.nextToken();
		
		if(protocol.equals("Chatting")) {
			message=tokenizer.nextToken();
			chatting();
		} else if(protocol.equals("SecretMessage")) {
			message=tokenizer.nextToken();
			secretMessage();
		} else if(protocol.equals("MakeRoom")) {
			makeRoom();
		} else if(protocol.equals("MadeRoom")) {
			madeRoom();
		} else if(protocol.equals("NewRoom")) {
			newRoom();
		} else if(protocol.equals("OutRoom")) {
			outRoom();
		} else if(protocol.equals("EnterRoom")) {
			enterRoom();
		} else if(protocol.equals("NewUser")) {
			newUser();
		} else if(protocol.equals("ConnectedUser")){
			connectedUser();
		} else if(protocol.equals("EmptyRoom")) {
			roomNameList.remove(from);
			roomList.setListData(roomNameList);
			makeRoomBtn.setEnabled(true);
			enterRoomBtn.setEnabled(true);
			outRoomBtn.setEnabled(false);
		} else if (protocol.equals("FailMakeRoom")) {
			JOptionPane.showMessageDialog(null, "같은 이름의 방이 존재합니다 !", "[알림]",
					JOptionPane.ERROR_MESSAGE, null);
		} else if (protocol.equals("UserOut")) {
			userIdList.remove(from);
			userList.setListData(userIdList);
		}
		
	}
	
	

	@Override
	public void clickSendMessageBtn(String messageText) {
		writer("Chatting/" + myRoomName + "/" + messageText);
	}

	@Override
	public void clickSendSecretMessafeBtn(String msg) {
		String user = (String) clientFrame.getWaitRoom().getUserList().getSelectedValue();
		writer("SecretMessage/" + user + "/" + msg);
		
	}

	@Override
	public void clickMakeRoomBtn(String roomName) {
		writer("MakeRoom/" + roomName);
	}

	@Override
	public void clickOutRoomBtn(String roomName) {
		writer("OutRoom/" + roomName);
	}

	@Override
	public void clickEnterRoomBtn(String roomName) {
		writer("EnterRoom/" + roomName);
	}

	@Override
	public void chatting() {
		if (name.equals(from)) {
			mainMessageBox.append("[나] \n" + message + "\n");
		} else if (from.equals("입장")) {
			mainMessageBox.append("▶" + from + "◀" + message + "\n");
		} else if (from.equals("퇴장")) {
			mainMessageBox.append("▷" + from + "◁" + message + "\n");
		} else {
			mainMessageBox.append("[" + from + "] \n" + message + "\n");
		}
	}

	@Override
	public void secretMessage() {
		JOptionPane.showMessageDialog(null, from + "님의 메세지\n\"" + message + "\"", "[비밀메세지]", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void makeRoom() {
		myRoomName = from;
		makeRoomBtn.setEnabled(false);
		enterRoomBtn.setEnabled(false);
		outRoomBtn.setEnabled(true);
	}

	@Override
	public void madeRoom() {
		roomNameList.add(from);
		if (!(roomNameList.size() == 0)) {
			roomList.setListData(roomNameList);
		}
	}

	@Override
	public void newRoom() {
		roomNameList.add(from);
		roomList.setListData(roomNameList);
	}

	@Override
	public void outRoom() {
		myRoomName = null;
		mainMessageBox.setText("");
		makeRoomBtn.setEnabled(true);
		enterRoomBtn.setEnabled(true);
		outRoomBtn.setEnabled(false);
	}

	@Override
	public void enterRoom() {
		myRoomName = from;
		makeRoomBtn.setEnabled(false);
		enterRoomBtn.setEnabled(false);
		outRoomBtn.setEnabled(true);
	}

	@Override
	public void newUser() {
		if(!from.equals(this.name)) {
			userIdList.add(from);
			userList.setListData(userIdList);
		}
		
	}

	@Override
	public void connectedUser() {
		userIdList.add(from);
		userList.setListData(userIdList);
		
	}
	
	public static void main(String[] args) {
		new Client();
	}
	
	

}
