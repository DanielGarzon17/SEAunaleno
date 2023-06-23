package IU;

import javax.swing.*;

import DATOS.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuInterfaz extends JFrame {
    private JPanel sideMenuPanel;
    private JPanel contentPanel;
    private JButton menuButton;
    private JLabel appLogoLabel;
    private JButton profileButton;
    private JButton searchButton;
    private JButton libraryButton;
    private JButton testButton;
    private JButton testInfoButton;
    private JButton statsButton;
    private JButton lastPressedButton;
    Usuario usuarioActivo;

    public MenuInterfaz(Usuario usuario) {
        // Crear el panel del menú lateral
        sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new GridBagLayout());
        sideMenuPanel.setBackground(Color.WHITE);

        // Crear los botones del menú
        profileButton = createMenuButton("Perfil de usuario");
        searchButton = createMenuButton("Buscar Usuarios");
        libraryButton = createMenuButton("Biblioteca");
        testButton = createMenuButton("Prueba");
        testInfoButton = createMenuButton("Información Prueba");
        statsButton = createMenuButton("Estadísticas");

        // Configurar restricciones para los botones del sidePanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0); // Espacio entre los botones

        // Configurar la ventana principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Aplicativo");

        // Crear el botón de menú hamburguesa
        menuButton = new JButton();
        menuButton.setFont(new Font("Arial", Font.PLAIN, 20));
        menuButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        menuButton.setBackground(Color.WHITE);
        sideMenuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        menuButton.setIcon(new ImageIcon("src/main/java/RECURSOS/menu-regular-24.png"));

        // Crear el label del logo del aplicativo en el menú
        appLogoLabel = new JLabel();
        appLogoLabel.setIcon(new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg"));
        appLogoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        appLogoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregar los botones al panel del menú
        // sideMenuPanel.add(menuButton);
        sideMenuPanel.add(appLogoLabel, gbc);
        sideMenuPanel.add(profileButton, gbc);
        sideMenuPanel.add(searchButton, gbc);
        sideMenuPanel.add(libraryButton, gbc);
        sideMenuPanel.add(testButton, gbc);
        sideMenuPanel.add(testInfoButton, gbc);
        sideMenuPanel.add(statsButton, gbc);
        sideMenuPanel.setVisible(true);

        for (Component element : sideMenuPanel.getComponents()) {
            element.setBackground(Color.WHITE);
            try {
                element.setFont(
                        Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Roboto-Light.ttf"))
                                .deriveFont(12f));
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sideMenuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Crear el panel de contenido desplegable
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setVisible(true);

        // Crear el label del logo del aplicativo en el panel de contenido desplegable
        JLabel contentLogoLabel = new JLabel();
        contentLogoLabel.setIcon(new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg"));
        contentLogoLabel.setVerticalAlignment(JLabel.CENTER);
        contentLogoLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Agregar el label del logo al panel de contenido desplegable
        contentPanel.add(contentLogoLabel);

        // Crear un layout de tipo BorderLayout para la ventana principal
        setLayout(new BorderLayout());

        add(menuButton);

        // Agregar el panel del menú al lado izquierdo de la ventana principal
        add(sideMenuPanel, BorderLayout.WEST);

        // Agregar el panel de contenido desplegable al lado derecho de la ventana
        // principal
        add(contentPanel, BorderLayout.CENTER);

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sideMenuPanel.isVisible()) {
                    sideMenuPanel.setVisible(false);
                    menuButton.setBounds(0, 0, 50, 50);
                    menuButton.setIcon(new ImageIcon("src/main/java/RECURSOS/menu-regular-24-rotate.png"));
                } else {
                    sideMenuPanel.setVisible(true);
                    menuButton.setBounds((appLogoLabel.getBounds().x) + (appLogoLabel.getBounds().width), 0, 50, 50);
                    menuButton.setIcon(new ImageIcon("src/main/java/RECURSOS/menu-regular-24.png"));
                }
            }
        });

        // Mostrar la ventana
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        menuButton.setBounds((appLogoLabel.getBounds().x) + (appLogoLabel.getBounds().width), 0, 50, 50);

        //ESCUCHADORES
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PerfilUsuarioPanel panelUsuario = new PerfilUsuarioPanel(usuario.getNombres(), usuario.getApellidos(), usuario.getEmail(),
                        usuario.getTelefono(), usuario.getPathImagen());
                panelUsuario.setPreferredSize(contentPanel.getSize());
                repintarPanel(contentPanel, panelUsuario);
            }
        });

        libraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelBiblioteca panelBiblioteca = new PanelBiblioteca();
                repintarPanel(contentPanel, panelBiblioteca);
            }
        });

    }

    private void repintarPanel(JPanel panelAnterior,JPanel panelNuevo){
        panelAnterior.removeAll();
        panelAnterior.add(panelNuevo);
        panelAnterior.repaint();
        panelAnterior.revalidate();
    }

    private JButton createMenuButton(String text) {
        JButton boton = new JButton(text);
        boton.setHorizontalAlignment(JLabel.CENTER);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setBorderPainted(false);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                if (lastPressedButton != null) {
                    lastPressedButton.setBackground(null); // Restaurar el color de fondo del último botón presionado
                }
                boton.setBackground(new Color(76, 175, 80)); // Cambiar el color de fondo del botón presionado
                lastPressedButton = boton; 
            }
        });
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //HACER LA CONEXION AL DATABASE Y TRAER DATOS DE USUARIO

                //CREACION DE Usuario
                new MenuInterfaz(new Usuario("231EDEEA1", "DANIEL", "GARZON", "38784631849", "JWBBCW@email,com", null, null,"https://fotografiamejorparavendermas.com/wp-content/uploads/2017/06/La-importancia-de-la-imagen.jpg"));
            }
        });
    }
}
