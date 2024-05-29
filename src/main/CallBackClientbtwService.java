package main;

public interface CallBackClientbtwService {
	
	void clickConnectServerBtn(String ip, int port, String id);
	
	void clickSendMessageBtn(String messageText);
	
	void clickSendSecretMessafeBtn(String msg);
	
	void clickMakeRoomBtn(String roomName);
	
	void clickOutRoomBtn(String roomName);
	
	void clickEnterRoomBtn(String roomName);

}
