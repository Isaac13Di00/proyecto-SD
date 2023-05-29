package sockets;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public VistaLogin() {
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear un panel con fondo degradado
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color startColor = new Color(39, 40, 66);
                Color endColor = new Color(15, 17, 35);
                GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        // Crear etiquetas y campos de texto
        JLabel usernameLabel = new JLabel("Usuario:");
        JLabel passwordLabel = new JLabel("Contraseña:");

        // Establecer el color de las letras en blanco
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        // Configurar los componentes en el panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VistaLogin::new);
    }
}

