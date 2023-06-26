package IU;

import javax.swing.*;

import DATOS.Usuario;
import LOGICA.LinkedList;
import LOGICA.conexionBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuInterfaz extends JFrame {
    LinkedList<Usuario> ListaUsuarios = new conexionBD("usuarios").getUsuarios();
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

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public MenuInterfaz(Usuario usuario) {
        setUsuarioActivo(usuario);
        initComponents(usuario);
    }

    public MenuInterfaz() {
        System.out.println(getUsuarioActivo().getNombres());
        new MenuInterfaz(getUsuarioActivo());
    }

    public void initComponents(Usuario usuario) {
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
        menuButton.setIcon(new ImageIcon("src/main/java/RECURSOS/menu-regular-24.png"));

        // Crear el label del logo del aplicativo en el menú
        appLogoLabel = new JLabel();
        appLogoLabel.setIcon(new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg"));
        appLogoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        appLogoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Agregar los botones al panel del menú
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
                        Font.createFont(Font.TRUETYPE_FONT,
                                new File("src/main/resources/fonts/Roboto-Light.ttf"))
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
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setVisible(true);

        // Crear el label del logo del aplicativo en el panel de contenido desplegable
        JLabel contentLogoLabel = new JLabel();
        contentLogoLabel.setIcon(new ImageIcon("src/main/java/RECURSOS/SEAUNALENOLOGO.jpg"));
        // contentLogoLabel.setVerticalAlignment(JLabel.CENTER);
        contentLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLogoLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Agregar el label del logo al panel de contenido desplegable
        contentPanel.add(contentLogoLabel, BorderLayout.NORTH);

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

        // ESCUCHADORES
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelPerfilUsuario panelUsuario = new PanelPerfilUsuario(usuario.getNombres(), usuario.getApellidos(),
                        usuario.getEmail(), usuario.getTelefono(), usuario.getImagen());
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

        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HojaRespuestas prueba = new HojaRespuestas(getUsuarioActivo());
                prueba.setVisible(true);
                dispose();

            }
        });

        testInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbrirLinks("https://admisiones.unal.edu.co/pregrado/");

            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusquedaUsuariosPanel panelBusqueda = new BusquedaUsuariosPanel(ListaUsuarios);
                repintarPanel(contentPanel,panelBusqueda);
            }
        });

        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadisticasPanel estadisticas = new EstadisticasPanel(usuarioActivo.getNotas());
                repintarPanel(contentPanel,estadisticas);
            }
        });
    }

    public void AbrirLinks(String str) {
        if (java.awt.Desktop.isDesktopSupported()) {
            Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    java.net.URI url = new java.net.URI(str);
                    desktop.browse(url);
                } catch (Exception e) {
                }
            }
        }
    }

    private void repintarPanel(JPanel panelAnterior, JPanel panelNuevo) {
        panelAnterior.removeAll();
        panelAnterior.add(panelNuevo);
        panelAnterior.repaint();
        panelAnterior.revalidate();
    }

    public void repintarPanel(PanelResultados panelNuevo) {
        contentPanel.add(panelNuevo, BorderLayout.CENTER);
        contentPanel.repaint();
        contentPanel.revalidate();
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
            double[] datos = {643.651,601.38,718.499,798.895,5.743,3.969,73.681,238.759,343.546,37.221,212.369,986.924,316.639,933.434,804.277,623.183,925.821,16.696,945.289,929.446,412.415,470.029,943.42,157.06,910.495,336.07,82.635,116.205,515.349,954.736,554.803,165.72,396.138,33.752,265.443,767.891,280.288,520.326,803.188,430.215,815.789,906.844,50.584,553.606,18.769,139.745,923.436,589.144,475.666,681.329,23.109,862.553,36.531,771.893,768.554,484.217,211.749,855.909,195.202,387.432,759.054,156.237,951.249,542.387,818.485,317.076,449.476,479.327,553.574,331.655,147.226,198.021,490.587,890.988,907.718,393.557,9.016,62.053,769.342,514.674,97.224,67.506,396.101,662.011,792.813,873.06,185.226,714.049,553.602,859.49,686.893,509.205,287.214,330.438,169.552,16.311,977.911,138.969,938.269,491.575,924.045,40.403,774.511,792.409,455.593,401.414,897.491,426.21,35.894,921.897,214.83,490.565,675.064,877.855,72.561,972.888,410.078,125.937,101.305,216.048,757.551,795.052,833.402,444.582,199.593,861.127,827.204,540.61,354.919,433.729,757.866,784.661,592.66,221.351,411.923,582.685,866.233,321.169,221.319,419.146,442.626,762.397,193.825,415.982,502.394,577.568,429.735,891.794,713.883,719.35,454.452,936.179,437.867,851.289,61.145,577.838,181.503,519.128,831.744,403.61,839.693,466.304,228.879,104.673,352.135,374.165,910.226,911.007,584.766,977.78,888.202,804.504,400.441,572.342,179.78,949.549,472.717,894.777,636.169,902.42,536.818,192.341,317.863,767.302,76.249,297.831,199.959,884.052,99.135,208.953,386.01,322.627,258.031,555.719,247.104,931.131,172.899,482.108,970.108,664.172,966.639,986.08,903.509,863.332,88.284,991.566,679.296,792.64,391.061,517.939,848.728,401.363,299.644,296.034,339.624,226.083,262.428,969.515,786.284,757.277,428.829,690.987,621.444,913.044,307.194,841.751,66.758,169.088,925.873,477.36,662.049,557.836,31.681,291.291,342.699,277.648,457.836,456.982,296.231,23.066,820.679,221.765,363.831,273.792,26.236,713.647,717.935,58.479,186.624,248.676,634.349,849.784,979.255,89.374,124.839,421.503,480.956,818.354,612.208,752.702,588.422,776.788,621.141,370.888,802.736,906.285,722.944,927.345,201.001,117.955,419.114,513.216,746.403,186.226,491.145,286.037,449.506,345.591,618.795,690.524,477.658,83.451,172.245,85.763,91.633,661.619,769.201,126.525,124.858,19.754,971.257,872.383,819.481,455.07,180.469,927.031,945.08,930.953,869.554,679.913,251.682,453.351,931.185,596.502,755.09,68.27,241.752,631.46,785.242,814.25,687.564,587.539,641.397,983.379,860.348,451.271,587.021,287.748,222.201,833.794,313.992,330.964,573.041,836.624,560.143,614.415,517.037,389.7,455.786,549.43,29.019,114.71,685.676,742.248,364.056,442.246,771.539,812.63,658.442,827.86,485.024,249.584,761.955,159.181,321.339,231.835,422.896,220.48,233.36,32.208,537.409,773.884,909.739,417.416,368.628,981.568,644.231,985.297,924.56,848.607,512.399,871.386,226.44,183.211,305.279,687.146,264.643,445.685,95.751,986.63,332.615,974.44,23.074,448.012,844.644,333.001,766.418,880.418,3.936,616.68,500.415,665.527,495.678,598.206,699.907,457.578,987.413,461.141,627.917,211.332,357.548,60.337,707.137,195.786,50.434,669.495,497.407,117.492,422.581,379.507,905.365,447.077,468.951,330.597,10.89,712.517,934.779,178.8,559.597,56.444,29.232,668.021,799.051,133.646,724.509,219.59,763.004,824.631,391.952,600.337,274.519,607.73,689.824,536.486,375.576,724.47,429.303,994.169,371.921,791.548,484.752,421.192,440.649,725.251,223.81,254.893,148.141,26.404,83.105,473.051,920.18,230.01,31.408,865.381,283.45,933.792,853.31,674.884,316.322,286.212,635.606,517.949,682.597,598.352,780.145,527.128,343.716,600.152,504.74,652.133,480.563,615.595,31.957,245.89,19.998,438.36,486.923,641.033,328.747,482.496,556.717,195.486,300.214,175.514,645.941,544.782,58.38,681.52,323.108,245.758,361.038,541.344,877.559,114.656,314.339,499.689,785.84,376.968,510.705,938.445,276.293,83.7,875.704,636.347,611.288,170.7,785.28,579.65,238.9,803.021,954.65,382.523,115.274,890.061,537.333,657.698,854.893,692.053,661.385,219.569,233.733,148.062,848.16,664.921,215.953,634.299,247.144,347.472,696.886,495.283,254.299,928.81,714.089,117.926,547.743,95.134,544.271,761.392,778.591,500.29,251.946,117.669,922.967,756.175,811.852,139.769,215.746,545.333,622.059,453.841,958.507,995.343,277.827,225.685,39.984,760.723,698.852,455.267,458.1,596.262,356.083,936.984,953.547,858.604,647.289,368.832,574.975,441.785,567.733,382.951,34.912,709.813,957.558,578.168,653.073,513.129,501.901,866.689,565.423,25.611,25.465,859.817,761.601,878.718,511.829,289.632,925.252,909.077,470.602,113.784,898.986,8.891,672.96,870.427,354.674,764.778,947.663,584.465,732.289,223.743,301.707,578.024,652.851,101.767,429.275,723.602,78.382,328.898,399.751,529.01,792.957,227.475,114.629,976.255,392.867,817.371,16.119,705.274,583.371,139.342,32.372,528.309,873.748,606.708,108.659,439.306,43.294,738.794,676.307,160.576,646.912,914.884,959.28,321.752,823.382,868.255,355.82,169.202,372.657,405.736,76.629,912.086,498.481,634.469,180.85,412.721,533.264,436.197,462.509,61.114,730.938,488.537,275.78,903.032,306.488,661.716,404.15,412.201,591.039,976.882,667.947,878.39,578.377,730.271,586.682,575.295,225.929,382.857,444.506,951.271,477.695,557.147,46.119,546.407,134.18,166.574,580.042,236.048,359.497,419.737,884.354,485.054,810.938,409.543,416.927,615.839,351.903,406.26,313.35,33.378,982.031,392.625,699.434,625.689,327.823,10.45,305.538,480.041,463.093,784.949,987.826,156.843,532.101,734.541,142.046,736.627,75.626,497.073,143.287,693.92,895.023,384.811,926.111,707.253,1.587,46.554,911.372,983.188,983.499,581.907,659.067,229.402,966.897,732.646,513.368,659.178,900.573,955.171,750.028,274.967,255.787,280.047,693.734,934.782,829.266,51.262,718.41,395.6,274.954,324.725,907.634,37.739,868.308,607.045,366.845,374.547,537.437,113.413,454.562,744.894,681.093,453.71,295.615,327.755,810.41,433.127,10.932,814.281,634.769,131.903,144.516,74.475,695.692,343.466,993.835,384.964,526.671,119.785,849.524,105.646,496.967,921.361,160.564,93.009,17.906,422.619,999.403,517.394,182.692,225.877,219.232,401.418,346.317,745.138,984.431,556.012,759.881,548.559,830.475,23.14,325.156,564.028,181.931,686.248,806.242,605.242,362.245,56.799,805.962,380.065,794.07,163.489,676.366,542.685,169.868,533.724,905.084,808.612,595.44,566.654,86.572,515.858,255.277,174.84,340.774,817.799,789.997,308.316,603.838,507.203,258.179,913.812,108.942,687.218,839.628,670.919,625.864,861.419,830.124,354.336,583.147,18.079,79.315,479.81,156.429,765.173,148.179,265.056,418.797,103.471,968.609,257.789,230.02,956.58,92.41,493.914,398.085,38.678,154.349,275.879,228.909,25.235,32.113,730.618,192.571,775.918,502.123,201.014,747.713,880.51,814.027,180.57,74.226,740.614,83.417,320.165,451.22,946.997,708.998,233.988,887.091,771.934,207.533,230.061,180.4,216.533,912.932,262.395,871.247,452.357,954.425,770.269,194.984,453.781,333.592,649.209,549.693,634.005,636.573,84.292,21.599,812.529,896.847,500.325,957.556,692.775,567.893,738.39,9.136,153.286,553.863,418.256,99.858,313.234,9.937,321.631,244.937,182.433,735.319,68.226,294.608,434.118,493.827,132.923,883.732,983.531,427.221,863.318,122.843,650.732,109.548,944.415,565.493,126.947,434.602,860.938,93.487,850.771,469.487,742.873,967.065,607.029,588.006,112.786,644.469,770.385,382.119,538.726,286.905,924.608,692.997,444.241,207.753,341.826,50.368,2.799,931.267,826.888,830.301,685.004,564.446,935.373,216.101,800.902,563.512,53.366,95.804,687.771,341.649,387.067,755.575,582.989,223.526,407.689,317.133,374.576,924.225,269.165,168.713,567.119,783.592,826.528,957.825,956.456,503.477,487.03,237.737,419.409,14.357,146.643,563.27,11.49,9.45,942.863,795.667,210.799,215.569,536.951,556.25,561.06,142.195,63.72,421.85,124.751,567.707,435.574,976.661,227.629,701.891,198.555,824.727,476.502,982.092,880.802,503.842,690.398,364.702,472.641,78.021,394.472};
            @Override
            public void run() {
                new MenuInterfaz(new Usuario("231EDEEA1", "DANIEL", "GARZON", "38784631849", "JWBBCW@email,com", null,
                datos,null,"contraseña"));
            }
        });
    }
}
