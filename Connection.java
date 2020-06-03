import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Connection {

	protected Socket socket;
	protected DataInputStream dataInputStream;
	protected DataOutputStream dataOutputStream;

	protected abstract String read() throws IOException;

	protected abstract void write(String msg) throws IOException;

}
