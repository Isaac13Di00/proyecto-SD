package SocketsProyect;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class VentanaVisualizacion extends JFrame {
	private JTextArea txtAreaProductos;

	public VentanaVisualizacion() {
		setTitle("Visualizaci�n de productos");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		txtAreaProductos = new JTextArea();
		txtAreaProductos.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtAreaProductos);
		add(scrollPane, BorderLayout.CENTER);

		setVisible(true);

		//Por alguna razon no me dejaba iniciar el programa xd, pero ya
		
		// Iniciar el hilo para recibir productos actualizados del servidor
		new Thread(new ProductReceiver()).start();
	}

	private class ProductReceiver implements Runnable {
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
				while (true) {
					Socket socket = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					Producto producto = (Producto) in.readObject();
					in.close();
					socket.close();
					actualizarProductos(producto);
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void actualizarProductos(Producto producto) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				txtAreaProductos.append("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio() + "\n");
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaVisualizacion();
			}
		});
	}
}
