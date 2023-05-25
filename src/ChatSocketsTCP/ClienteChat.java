package ChatSocketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@SuppressWarnings("unused")
public class ClienteChat implements Runnable
{
	private int puerto;
	private String mensaje;
	
	public ClienteChat(int puerto, String mensaje)
	{
		this.puerto=puerto;
		this.mensaje=mensaje;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	
	final String HOST="127.0.0.1";

	DataOutputStream out;
	
	try {
		Socket sc= new Socket(HOST, puerto);
					
		out= new DataOutputStream(sc.getOutputStream());
		
		out.writeUTF(mensaje);
				
		sc.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
