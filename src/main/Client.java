package main;

import java.io.IOException;
import java.net.Socket;

public class Client extends AbstractClient{

	
	public Client(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void connectToServer() throws IOException {
		super.setSocket(new Socket("127.0.0.1",5555));
		
	}
	
	public static void main(String[] args) {
		Client client=new Client("홍길동");
		client.run();
	}
}
