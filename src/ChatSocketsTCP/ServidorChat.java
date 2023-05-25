package ChatSocketsTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ServidorChat extends Observable implements Runnable 
{
	private int puerto;
	
	public ServidorChat(int puerto)
	{
		this.puerto=puerto;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		ServerSocket servidor =null;
		Socket sc =null;
		// envio y recepcion del stream 
		DataInputStream in;
		//DataOutputStream out;		

		//int i=1;
		
		try 
		{
			servidor= new ServerSocket(puerto);
			System.out.println("Servidor iniciado \n");
			
			while(true)
			{
				sc=servidor.accept();
				System.out.println("Cliente conectado.");
				
				in= new DataInputStream(sc.getInputStream());
	
				
				String mensaje = "";
				mensaje=in.readUTF();				
				System.out.println(mensaje);				
				
				this.setChanged();
				this.notifyObservers(mensaje);
				this.clearChanged();
				
				sc.close();
				System.out.println("Cliente desconectado. \n");	
				//i++;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
