package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AbstractServer {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader readerStream;
	private PrintWriter writerStream;
	private BufferedReader keyboardReader;
	
	protected void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket=serverSocket;
	}
	
	protected void setSocket(Socket socket) {
		this.socket=socket;
	}
	
	protected ServerSocket getServerSocket() {
		return this.serverSocket;
	}
	
	// 실행의 흐름이 필요하다.
	// final - 상속받은 자식 클래스에서 수정 불가
	public final void run() {
		// 1. 서버 세팅 - 포트 번호 할당
		try {
			setupServer();
			connection();
			setupStream();
			startService();
		} catch (Exception e) {
			
		} finally {
			cleanup();
		}
	}
	
	// 1. 포트 번호 할당
	protected abstract void setupServer() throws IOException;
	
	// 2. 클라이언트 연결 대기 실행 (구현 클래스)
	protected abstract void connection() throws IOException;
	
	// 3. 스트림 초기화 (연결된 소켓에서 스트림을 뽑아야 한다.) <- 여기서 함.
	private void setupStream() throws IOException{
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerStream = new PrintWriter(socket.getOutputStream());
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// 4. 서비스 시작
	private void startService() {
		Thread readThread = createReadThread();
		Thread writeThread = createWriteThread();
		
		readThread.start();
		writeThread.start();
		
		try {
			readThread.join();
			writeThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 캡슐화
	// ReadStream 만들기
	private Thread createReadThread() {
		return new Thread(()->{
			String msg;
			try {
				while((msg=readerStream.readLine())!=null) {
					System.out.println("client측 msg : "+msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	// writeStream 만들기
	private Thread createWriteThread() {
		return new Thread(()->{
			try {
				String msg;
				// 서버 측 키보드에서 데이터를 한 줄 라인으로 읽는다.
				while((msg=keyboardReader.readLine())!=null) {
					writerStream.println(msg);
					writerStream.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
	// 캡슐화 - 소켓 자원 종료
	private void cleanup() {
		try {
			if(socket!=null) {
				socket.close();
			}
			if(serverSocket!=null) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
