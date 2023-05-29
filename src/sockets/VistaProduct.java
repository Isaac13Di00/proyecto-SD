package sockets;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class VistaProduct extends JFrame {
	    private JLabel nameLabel;
	    private JLabel priceLabel;
	    private JTextField inputField;
	    private JButton searchButton;
	    private JButton modifyButton;

	    private Product[] products;

	    public VistaProduct() {
	        setTitle("Búsqueda de productos");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setMinimumSize(new Dimension(500, 200));
	        setLocationRelativeTo(null);
	        setResizable(false);
	        setVisible(true);

	        // Crear datos de ejemplo (productos)
	        products = new Product[3];
	        products[0] = new Product("001", "Producto 1", 9.99);
	        products[1] = new Product("002", "Producto 2", 14.99);
	        products[2] = new Product("003", "Producto 3", 19.99);

	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());

	        inputField = new JTextField();
	        searchButton = new JButton("Buscar");
	        searchButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                searchProduct();
	            }
	        });

	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new BorderLayout());
	        inputPanel.add(inputField, BorderLayout.CENTER);
	        inputPanel.add(searchButton, BorderLayout.EAST);

	        nameLabel = new JLabel("Nombre del producto");
	        priceLabel = new JLabel("Precio del producto");

	        panel.add(inputPanel, BorderLayout.NORTH);
	        panel.add(nameLabel, BorderLayout.CENTER);
	        panel.add(priceLabel, BorderLayout.SOUTH);

	        getContentPane().add(panel);

	        pack();
	        setVisible(true);
	    }

	    private void searchProduct() {
	        String input = inputField.getText();

	        // Realizar búsqueda en el arreglo de productos
	        for (Product product : products) {
	            if (product.getCode().equals(input)) {
	                nameLabel.setText("Nombre del producto: " + product.getName());
	                priceLabel.setText("Precio del producto: $" + product.getPrice());
	                // Mostrar el botón "Modificar Precio"
	                if (modifyButton == null) {
	                    modifyButton = new JButton("Modificar Precio");
	                    modifyButton.addActionListener(new ActionListener() {
	                        @Override
	                        public void actionPerformed(ActionEvent e) {
	                            openModifyPriceWindow();
	                        }
	                    });
	                    JPanel panel = (JPanel) getContentPane().getComponent(0);
	                    JPanel inputPanel = (JPanel) panel.getComponent(0);
	                    inputPanel.add(modifyButton, BorderLayout.SOUTH);
	                    panel.revalidate();
	                }
	                return;
	            }
	        }
	        // Producto no encontrado
	        nameLabel.setText("Producto no encontrado");
	        priceLabel.setText("");
	        // Ocultar el botón "Modificar Precio"
	        if (modifyButton != null) {
	            JPanel panel = (JPanel) getContentPane().getComponent(0);
	            JPanel inputPanel = (JPanel) panel.getComponent(0);
	            inputPanel.remove(modifyButton);
	            panel.revalidate();
	            modifyButton = null;
	        }
	    }
	    private void openModifyPriceWindow() {
	        JFrame modifyPriceFrame = new JFrame("Modificar Precio");
	        modifyPriceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        modifyPriceFrame.setSize(300, 150);
	        modifyPriceFrame.setLocationRelativeTo(null);
	        JPanel modifyPanel = new JPanel();
	        modifyPanel.setLayout(new BorderLayout());
	        JLabel modifyLabel = new JLabel("Nuevo Precio:");
	        JTextField modifyField = new JTextField();
	        JButton updateButton = new JButton("Actualizar");
	        updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                updatePrice(modifyField.getText());
	                modifyPriceFrame.dispose();
	            }
	        });
	        modifyPanel.add(modifyLabel, BorderLayout.NORTH);
	        modifyPanel.add(modifyField, BorderLayout.CENTER);
	        modifyPanel.add(updateButton, BorderLayout.SOUTH);
	        modifyPriceFrame.getContentPane().add(modifyPanel);
	        modifyPriceFrame.setVisible(true);
	    }
	    private void updatePrice(String newPrice) {
	        String input = inputField.getText();
	        // Realizar búsqueda en el arreglo de productos
	        for (Product product : products) {
	            if (product.getCode().equals(input)) {
	                product.setPrice(Double.parseDouble(newPrice));
	                priceLabel.setText("Precio del producto: $" + product.getPrice());
	                return;
	            }
	        }
	    }
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(VistaProduct::new);
	    }
	}
	class Product {
	    private String code;
	    private String name;
	    private double price;
	    public Product(String code, String name, double price) {
	        this.code = code;
	        this.name = name;
	        this.price = price;
	    }
	    public String getCode() {
	        return code;
	    }
	    public String getName() {
	        return name;
	    }
	    public double getPrice() {
	        return price;
	    }
	    public void setPrice(double price) {
	        this.price = price;
	    }
	}