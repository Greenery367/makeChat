package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {
	
	public String name;
	public Socket socket;
	private PrintWriter socketWriter;
	private BufferedReader socketReader;
	private BufferedReader keyboardReader;
	
	public AbstractClient(String namer) {
		this.name=name;
	}
	
	public void setSocket(Socket socket) {
		this.socket=socket;
	}
	
	public void run() {
		try {
			connectToServer();
			setupStreams();
			startService();
		} catch (IOException e) {
			System.out.println(">>> 접속 종료 <<<");
		} finally {
		}
	}
	
	protected abstract void connectToServer() throws IOException;
	
	private void setupStreams() throws IOException{
		socketReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketWriter=new PrintWriter(socket.getOutputStream(),true);
		keyboardReader=new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	private void startService() throws IOException{
		Thread readThread=createReadThread();
		Thread writeThread=createWriteThread();
		
		readThread.start();
		writeThread.start();
		
		try {
			readThread.join();
			writeThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Thread createReadThread() {
		return new Thread(()->{			
			try {
				String msg;
				while((msg=socketReader.readLine())!=null) {
					System.out.println("[received] : "+msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	private Thread createWriteThread() {
		return new Thread(()->{
			try {
				String msg;
				while((msg=keyboardReader.readLine())!=null) {
					socketWriter.println("["+name+"] :"+msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	

}
