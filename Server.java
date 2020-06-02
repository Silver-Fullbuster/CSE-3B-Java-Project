import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
	private ServerSocket serverSocket = null;
	private Socket socket;
	private int port;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	private Server() throws IOException {
		do{
			try {
				serverSocket = new ServerSocket(0, 0, InetAddress.getLocalHost()); // get an available local port
				port = serverSocket.getLocalPort();
				System.out.println("Setting up game at socket " + serverSocket.getInetAddress().getHostAddress() + ":" + port + "\n" +
						"Waiting for a client to join within 30 seconds");
				serverSocket.setSoTimeout(30000);
				socket = serverSocket.accept();
			} catch (SocketTimeoutException e){
				throw e;
			} catch (IOException e){
				System.out.println("Setting up game failed, likely no available ports found");
				e.printStackTrace();
			}
		} while(serverSocket == null);
		try{
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e){
			throw e;
		}
	}

	public static Server prep() {
		Server obj;
		try {
			obj = new Server();
		} catch (SocketTimeoutException e){
			System.out.println("Timeout: No client connected, exiting Multiplayer...");
			return null;
		} catch (IOException e){
			System.out.println("DataIOStream creation error, exiting Multiplayer...");
			return  null;
		}
		System.out.println();
		return obj;
	}

	public void send(String msg) throws IOException{
		dataOutputStream.writeUTF(msg);
		dataOutputStream.flush();
	}

	public String read() throws IOException {
		return dataInputStream.readUTF();
	}
}