import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket socket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;


	private Client(String ip, int port) throws IOException {
		int connectionAttemptTries = 5; // number of times it'll try to connect
		do {
			try {
				socket = new Socket(ip, port);
			} catch (UnknownHostException e) {
				throw e;
			} catch (IOException e) {
				if (connectionAttemptTries == 0) throw e;
				System.out.println("Socket connection failed, trying " + connectionAttemptTries-- + "times");
			}
		} while (socket == null);
		dataInputStream = new DataInputStream(socket.getInputStream());
		dataOutputStream = new DataOutputStream(socket.getOutputStream());
	}

	public static Client connect(String ip, int port) {
		Client obj;
		try {
			obj = new Client(ip, port);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host, exiting Multiplayer...");
			return null;
		} catch (IOException e) {
			System.out.println("Connection error, exiting Multiplayer...");
			return null;
		}
		System.out.println();
		return obj;
	}
	public void send(String msg) throws IOException {
		dataOutputStream.writeUTF(msg);
		dataOutputStream.flush();
	}

	public String read() throws IOException {
		return dataInputStream.readUTF();
	}

}