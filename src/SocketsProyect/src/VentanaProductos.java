import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class VentanaProductos extends JFrame {
    private JTable tablaProductos;
    private ProductTableModel modeloTabla;

    public VentanaProductos() {
        setTitle("Gestión de productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new ProductTableModel();
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaProductos.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    editarProducto(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(VentanaProductos.this, "Seleccione un producto para editar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditar);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void editarProducto(int fila) {
        Producto producto = modeloTabla.getProducto(fila);
        String nombre = JOptionPane.showInputDialog(VentanaProductos.this, "Ingrese el nuevo nombre:",
                producto.getNombre());
        if (nombre != null) {
            double precio = Double.parseDouble(
                    JOptionPane.showInputDialog(VentanaProductos.this, "Ingrese el nuevo precio:",
                            producto.getPrecio()));
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            modeloTabla.fireTableDataChanged();

            // Enviar producto actualizado al servidor
            try {
                Socket socket = new Socket("localhost", 1234);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(producto);
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaProductos();
            }
        });
    }
}
