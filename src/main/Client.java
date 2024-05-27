package main;

import java.io.IOException;
import java.net.Socket;

public class Client extends AbstractClient{

	public Client(String name) {
		super(name);
	}

	@Override
	protected void connectToServer() throws IOException {
		super.setSocket(new Socket("localhost",5555));
		
	}
	
	public static void main(String[] args) {
		Client chatClient=new Client("1");
		chatClient.run();
	}

}
