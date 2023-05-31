import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class VentanaGuardarCambios extends JFrame {
    private ProductTableModel modeloTabla;

    public VentanaGuardarCambios(ProductTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
        setTitle("Guardar cambios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTable tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void guardarCambios() {
        List<Producto> productos = modeloTabla.getProductos();

        // Guardar cambios en el servidor
        try {
            Socket socket = new Socket("localhost", 1234);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            for (Producto producto : productos) {
                out.writeObject(producto);
            }
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProductTableModel modeloTabla = new ProductTableModel();
                new VentanaGuardarCambios(modeloTabla);
            }
        });
    }
}
