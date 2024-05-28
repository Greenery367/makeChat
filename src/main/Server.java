package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	
	
	public int port;
	public ServerSocket serverSocket;
	public Socket socket;
	public String name;
	public String ip;
	
	private static Vector<PrintWriter> clientWriters=new Vector<>();
	
	public Server() {}
	
	public Server(int port) {
		try (ServerSocket serverSocket=new ServerSocket(port);){
			ServerFrame serverFrame=new ServerFrame();
			while(true) {
				Socket socket=serverSocket.accept();
				new ClientHandler(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class ClientHandler extends Thread{
		private Socket socket;
		private PrintWriter out;
		private BufferedReader keyboard=new BufferedReader(new InputStreamReader(System.in));
		private BufferedReader in;
		
		public ClientHandler(Socket socket) {
			this.socket=socket;
			createReadThread().start();
			createWriteThread().start();
			
		}
		
		private Thread createWriteThread() {
			return new Thread(()->{
				try {
					String msg;
					// 서버 측 키보드에서 데이터를 한 줄 라인으로 읽음
					while((msg=keyboard.readLine())!=null) {
						// 클라이언트와 연결된 소켓에 데이터를 보낸다.
						out.println(msg);
						out.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		public Thread createReadThread() {
			return new Thread(()->{
				try {
					in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out=new PrintWriter(socket.getOutputStream(),true);
					clientWriters.add(out);
					String message;
					while((message=in.readLine())!=null) {
						System.out.println("received"+message);
						broadcastMessage(message);}
					} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						socket.close();
						System.out.println("클라이언트 연결 해제");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	
	
	private static void broadcastMessage(String message) {
		for(PrintWriter writer:clientWriters) {
			writer.println(message);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("연결 중...");
		new Server(5555);
		}
	}
	
	public void startConnect() {
		try {
			serverSocket=new ServerSocket(5555);
			connectClient();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						socket=serverSocket.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ConnectedUser user=new ConnectedUser(socket);
					user.start();
				}
				
			}
			
		});
	}
	
	private class ConnectedUser extends Thread{
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
				// TODO: handle exception
			}
		}
		
		private void sendInformation() {
			try {
				id=reader.readLine();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	}
