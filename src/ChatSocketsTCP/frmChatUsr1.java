package ChatSocketsTCP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "deprecation" })
public class frmChatUsr1 extends JFrame implements Observer{

	private JPanel contentPane;
	private JTextField txtMensaje;
	private JTextArea txtConversacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmChatUsr1 frame = new frmChatUsr1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmChatUsr1() {
		setTitle("Chat Sockets Usuario 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMensaje = new JTextField();
		txtMensaje.setFont(new Font("Gill Sans Nova", Font.PLAIN, 18));
		txtMensaje.setBounds(10, 511, 416, 40);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String mensaje="Usuario 1: "+txtConversacion.getText()+"\n";
				txtConversacion.append(mensaje);
				
				ClienteChat c= new ClienteChat(15001,mensaje);
				Thread t = new Thread(c);
				t.start();
			}
		});
		btnEnviar.setFont(new Font("Gill Sans Nova", Font.PLAIN, 18));
		btnEnviar.setBounds(436, 509, 181, 44);
		contentPane.add(btnEnviar);
		
		txtConversacion = new JTextArea();
		txtConversacion.setFont(new Font("Gill Sans Nova", Font.ITALIC, 18));
		txtConversacion.setForeground(new Color(0, 0, 128));
		txtConversacion.setBounds(10, 11, 607, 476);
		contentPane.add(txtConversacion);
		
		ServidorChat s = new ServidorChat(15000);
		s.addObserver(this);
		Thread t = new Thread(s);
		t.start();
		
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		
		this.txtConversacion.append((String) arg);
	}
}
