package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Server {
	
	private Vector<ConnectedUser> connectedUsers=new Vector<>();
	private Vector<MyRoom> madeRooms = new Vector<>();
	
	private ServerFrame serverFrame;
	private JTextArea mainBoard;
	
	public ServerSocket serverSocket;
	public Socket socket;
	public String name;
	public String ip;
	
	private Boolean roomCheck;
	private String protocol;
	private String from;
	private String message;
	private FileWriter fileWriter;
	
	private static Vector<PrintWriter> clientWriters=new Vector<>();
	
	public Server() {
		serverFrame=new ServerFrame(this);
		roomCheck=true;
		mainBoard=serverFrame.getMainBoard();
	}
	
	public void startConnect() {
		try {
			serverSocket=new ServerSocket(5555);
			serverViewAppendWriter("[알림] 서버 시작\n");
			serverFrame.getButton().setEnabled(false);
			connectClient();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "이미 사용중인 포트입니다.", "알림", JOptionPane.ERROR_MESSAGE, null);
			serverFrame.getButton().setEnabled(true);
		}
	}
	
	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						socket=serverSocket.accept();
						serverViewAppendWriter("[알림] 사용자 접속 대기");
						ConnectedUser user=new ConnectedUser(socket);
						user.start();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	private void broadcast(String message) {
		for(int i=0; i<connectedUsers.size(); i++) {
			ConnectedUser user = connectedUsers.elementAt(i);
			user.writer(message);
		}
	}
	
	
	private void serverViewAppendWriter(String str) {
		try {
			fileWriter=new FileWriter("NateOn.txt",true);
			mainBoard.append(str);
			fileWriter.write(str);
			fileWriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	private class ConnectedUser extends Thread implements Protocol{
		private Socket socket;
		
		private BufferedReader reader;
		private BufferedWriter writer;
		
		private String id;
		private String myRoomName;
		
		public ConnectedUser(Socket socket) {
			this.socket=socket;
			connectIO();
		}
		
		private void connectIO() {
			try {
				reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				sendInformation();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "서버 입출력 장치 에러!", "알림", JOptionPane.ERROR_MESSAGE, null);
				serverViewAppendWriter("[에러] 서버 입출력 장치 에러 ! !\n");
			}
		}
		
		private void sendInformation() {
			try {
				id=reader.readLine();
				serverViewAppendWriter("[접속]"+id+"님\n");
				newUser();
				connectedUser();
				madeRoom();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "접속 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
				serverViewAppendWriter("[에러] 접속 에러 ! !\n");
			}
		}
		
		public void run() {
			try {
				while(true) {
					String str=reader.readLine();
					checkProtocol(str);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "유저 접속 끊김 !", "알림", JOptionPane.ERROR_MESSAGE, null);
				serverViewAppendWriter("[에러] 유저 " + id +" 접속 끊김 ! !\n");
				for (int i = 0; i < madeRooms.size(); i++) {
					MyRoom myRoom = madeRooms.elementAt(i);
					if(myRoom.roomName.equals(this.myRoomName)) {
						myRoom.removeRoom(this);
					}
				}
				connectedUsers.remove(this);
				broadcast("UserOut/"+id);
			}
		}
		
		private void checkProtocol(String str) {
			StringTokenizer tokenizer=new StringTokenizer(str,"/");
			
			protocol=tokenizer.nextToken();
			from=tokenizer.nextToken();
			
			if(protocol.equals("Chatting")){
				message=tokenizer.nextToken();
				chatting();
			} else if (protocol.equals("SecretMessage")) {
				message = tokenizer.nextToken();
				secretMessage();
			} else if (protocol.equals("MakeRoom")) {
				makeRoom();

			} else if (protocol.equals("OutRoom")) {
				outRoom();

			} else if (protocol.equals("EnterRoom")) {
				enterRoom();
			}
		}
		
		private void writer(String str) {
			try {
				writer.write(str+"\n");
				writer.flush();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "서버 출력 에러 !", "알림", JOptionPane.ERROR_MESSAGE, null);
			}
		}

		@Override
		public void chatting() {
			serverViewAppendWriter("[메세지] " + from + "_" + message + "\n");

			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom myRoom = madeRooms.elementAt(i);

				if (myRoom.roomName.equals(from)) {
					myRoom.roomBroadcast("Chatting/" + id + "/" + message);
				}
			}
			
		}

		@Override
		public void secretMessage() {
			serverViewAppendWriter("[비밀 메세지] " + id + "ㅡ>" + from + "_" + message + "\n");

			for (int i = 0; i < connectedUsers.size(); i++) {
				ConnectedUser user = connectedUsers.elementAt(i);

				if (user.id.equals(from)) {
					user.writer("SecretMessage/" + id + "/" + message);
				}
			}
			
		}

		@Override
		public void makeRoom() {
			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom room = madeRooms.elementAt(i);

				if (room.roomName.equals(from)) {
					writer("FailMakeRoom/" + from);
					serverViewAppendWriter("[방 생성 실패]" + id + "_" + from + "\n");
					roomCheck = false;
				} else {
					roomCheck = true;
				}
			}

			if (roomCheck) {
				myRoomName = from;
				MyRoom myRoom = new MyRoom(from, this);
				madeRooms.add(myRoom);
				serverViewAppendWriter("[방 생성]" + id + "_" + from + "\n");

				newRoom();
				writer("MakeRoom/" + from);
			}
		}

		@Override
		public void madeRoom() {
			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom myRoom = madeRooms.elementAt(i);
				writer("MadeRoom/" + myRoom.roomName);
			}
		}

		@Override
		public void newRoom() {
			broadcast("NewRoom/" + from);
			
		}

		@Override
		public void outRoom() {
			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom myRoom = madeRooms.elementAt(i);

				if (myRoom.roomName.equals(from)) {
					myRoomName = null;
					myRoom.roomBroadcast("Chatting/퇴장/" + id + "님 퇴장");
					serverViewAppendWriter("[방 퇴장]" + id + "_" + from + "\n");
					myRoom.removeRoom(this);
					writer("OutRoom/" + from);
				}
			}
		}

		@Override
		public void enterRoom() {
			for (int i = 0; i < madeRooms.size(); i++) {
				MyRoom myRoom = madeRooms.elementAt(i);

				if (myRoom.roomName.equals(from)) {
					myRoomName = from;
					myRoom.addUser(this);
					myRoom.roomBroadcast("Chatting/입장/" + id + "님 입장");
					serverViewAppendWriter("[입장]" + from + " 방_" + id + "\n");
					writer("EnterRoom/" + from);
				}
			}
		}

		@Override
		public void newUser() {
			connectedUsers.add(this);
			broadcast("NewUser/" + id);
		}

		@Override
		public void connectedUser() {
			for (int i = 0; i < connectedUsers.size(); i++) {
				ConnectedUser user = connectedUsers.elementAt(i);
				writer("ConnectedUser/" + user.id);
			}
		}
	
	}
	
	private class MyRoom{
		private String roomName;
		private Vector<ConnectedUser> myRoom=new Vector<>();
		
		public MyRoom(String roomName,ConnectedUser connectedUser) {
			this.roomName=roomName;
			this.myRoom.add(connectedUser);
			connectedUser.myRoomName=roomName;
		}
		
		private void roomBroadcast(String msg) {
			for(int i=0; i<myRoom.size();i++) {
				ConnectedUser user=myRoom.elementAt(i);
				user.writer(msg);
			}
		}
		
		private void addUser(ConnectedUser user) {
			myRoom.add(user);
		}
		
		private void removeRoom(ConnectedUser user) {
			myRoom.remove(user);
			boolean empty = myRoom.isEmpty();
			if (empty) {
				for (int i = 0; i < madeRooms.size(); i++) {
					MyRoom myRoom = madeRooms.elementAt(i);

					if (myRoom.roomName.equals(roomName)) {
						madeRooms.remove(this);
						serverViewAppendWriter("[방 삭제]" + user.id + "_" + from + "\n");
						roomBroadcast("OutRoom/" + from);
						broadcast("EmptyRoom/" + from);
						break;
					}
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new Server();
	}
	
}




