package PRUEBAS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistroFormulario extends JFrame {

    private JTextField nombreField;
    private JTextField correoField;
    private JPasswordField passwordField;

    public RegistroFormulario() {
        setTitle("Formulario de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.GRAY);
        nombreField = new JTextField();
        nombreField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nombreField.getText().equals("Nombre")) {
                    nombreField.setText("");
                    nombreField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nombreField.getText().isEmpty()) {
                    nombreField.setForeground(Color.GRAY);
                    nombreField.setText("Nombre");
                }
            }
        });
        panel.add(lblNombre);
        panel.add(nombreField);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(Color.GRAY);
        correoField = new JTextField();
        correoField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (correoField.getText().equals("Correo")) {
                    correoField.setText("");
                    correoField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (correoField.getText().isEmpty()) {
                    correoField.setForeground(Color.GRAY);
                    correoField.setText("Correo");
                }
            }
        });
        panel.add(lblCorreo);
        panel.add(correoField);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.GRAY);
        passwordField = new JPasswordField();
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Contraseña")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Contraseña");
                }
            }
        });
        panel.add(lblPassword);
        panel.add(passwordField);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroFormulario formulario = new RegistroFormulario();
            formulario.setVisible(true);
        });
    }
}
