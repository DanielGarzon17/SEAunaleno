package IU;

import LOGICA.CalificarExamen;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

public class HojaRespuestas extends JFrame {
    
    private static String[] vectorLinks={"https://drive.google.com/file/d/1MErkL66cQhvA56vFidzEXhhd6PCr0Mky/view?usp=share_link",
        "https://drive.google.com/file/d/1tpt6hqmxW40xtoYmD_qmpw-lbJjh58Pj/view"};
    private final int frecuencia = 1000;
    private int segundos = 0, minutos = 30, horas = 3;
    private static int aleatorio;
    private final int numeroPreguntas;
    private String[] respuestasUsuario;
    private JRadioButton jRadioButton[][];
    private JLabel labelCronometro;
    Cronometro cronometro = new Cronometro();
    Thread Hilo1 = new Thread(cronometro);
    
    public String[] getRespuestasUsuario() {
        return respuestasUsuario;
    }

    public void setRespuestasUsuario(String[] respuestasUsuario) {
        this.respuestasUsuario = respuestasUsuario;
    }

    public HojaRespuestas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
        initComponents();
    }

    public HojaRespuestas() {
        this(120);
    }

    public void initComponents() {

        //instanciacion de objetos
        Color fondo = new Color(223, 246, 255);
        JPanel jPanel = new JPanel();
        JButton Finalizarprueba = new JButton("CALIFICAR PRUEBA");
        this.jRadioButton = new JRadioButton[numeroPreguntas][4];
        ButtonGroup buttonGroup[] = new ButtonGroup[numeroPreguntas];
        JLabel jLabel[] = new JLabel[numeroPreguntas];
        labelCronometro = new JLabel("0" + horas + ":0" + minutos + ":0" + segundos);
        JScrollPane jScrollPane = new JScrollPane();
        JLabel titulo = new JLabel("HOJA DE RESPUESTAS");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //CONFIGURACION DE OBJETOS:

        //ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 270, screenSize.height - 50);
        setTitle("Hoja de respuestas");//titulo de la ventana
        setResizable(false);
        aleatorio = (int)(Math.random()*2); 
        //Panel
        jPanel.setLayout(null);
        jPanel.setBackground(fondo);

        //Jlabel Titulo
        titulo.setFont(new Font("Georgia", 1, 18));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBounds(0, 0, 240, 40);
        jPanel.add(titulo);

        //Jlabel Cronometro
        labelCronometro.setFont(new Font("Georgia", 1, 18));
        labelCronometro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCronometro.setBounds(0, 40, 240, 40);
        jPanel.add(labelCronometro);

        //boton
        Finalizarprueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalizarpruebaActionPerformed(evt);
            }
        });
        for (int i = 0; i < numeroPreguntas; i++) {
            //intanciacion de objetos d ela matriz
            buttonGroup[i] = new ButtonGroup();

            jLabel[i] = new JLabel((i + 1) + ".");
            jLabel[i].setBounds(15, 100 + 30 * i, 40, 30);
            jLabel[i].setFont(new Font("Times new roman", 1, 12));
            jPanel.add(jLabel[i]);
            for (int j = 0; j < 4; j++) {
                String textoRadioButton;
                switch (j) {
                    case 0:
                        textoRadioButton = "A";
                        break;
                    case 1:
                        textoRadioButton = "B";
                        break;
                    case 2:
                        textoRadioButton = "C";
                        break;
                    case 3:
                        textoRadioButton = "D";
                        break;
                    default:
                        textoRadioButton = "";
                        break; //te amo
                }
                jRadioButton[i][j] = new JRadioButton(textoRadioButton);
                jRadioButton[i][j].setBackground(fondo);
                jRadioButton[i][j].setBounds(40 + 50 * j, 100 + 30 * i, 40, 30);
                jRadioButton[i][j].setFont(new Font("Times new roman", 1, 12));
                jPanel.add(jRadioButton[i][j]);
                buttonGroup[i].add(jRadioButton[i][j]);
                jScrollPane.setBounds(240, 0, 15, 400);
                jPanel.setPreferredSize(new Dimension(200, 100 + 30 * (numeroPreguntas + 1)));
                jScrollPane.setViewportView(jPanel);

                if (i == numeroPreguntas - 1) {
                    Finalizarprueba.setBounds(5, 100 + 30 * (i + 1), 225, 30);
                }
            }
        }
        jPanel.add(Finalizarprueba);
        add(jScrollPane);
        
        System.out.println("aleatorio en interfaz:"+aleatorio);
        AbrirLinks(vectorLinks[aleatorio]);
        
        Hilo1.start();
    }

    public void FinalizarpruebaActionPerformed(java.awt.event.ActionEvent evt) {
        Finalizarpruebaoyente(evt);
    }

    public void Finalizarpruebaoyente(java.awt.event.ActionEvent evt) {
        respuestasUsuario = new String[numeroPreguntas];
        for (int i = 0; i < numeroPreguntas; i++) {
            respuestasUsuario[i] = "Z";
            for (int j = 0; j < 4; j++) {
                if (jRadioButton[i][j].isSelected()) {
                    respuestasUsuario[i] = jRadioButton[i][j].getText();
                }
            }
        }
        CalificarExamen u = new CalificarExamen();
        u.setAleatorio(aleatorio);
        u.abrirArchivo(respuestasUsuario);
        Hilo1.stop();
        dispose();
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
    
    public static void main(String args[]) {
        HojaRespuestas hojaDeRespuestas = new HojaRespuestas();
        hojaDeRespuestas.setVisible(true);
    }

    public class Cronometro extends Thread {

        long Last = 0;

        @Override
        public void run() {
            while (true) {
                if ((java.lang.System.currentTimeMillis() - Last) > frecuencia) {
                    segundos--;

                    if (segundos == -1) {
                        segundos = 59;
                        minutos--;
                    }

                    if (minutos == -1) {
                        minutos = 59;
                        horas--;
                    }

                    String textoCronometro = "";
                    if (horas < 10) {
                        textoCronometro += "0" + horas;
                    } else {
                        textoCronometro += horas;
                    }

                    if (minutos < 10) {
                        textoCronometro += ":0" + minutos;
                    } else {
                        textoCronometro += ":" + minutos;
                    }

                    if (segundos < 10) {
                        textoCronometro += ":0" + segundos;
                    } else {
                        textoCronometro += ":" + segundos;
                    }

                  if (horas == 0 && minutos == 5 && segundos == 0) {
                      JOptionPane.showMessageDialog(rootPane, "TE QUEDAN 5 MINUTOS");
                  }
                    if (horas == 0 && minutos == 0 && segundos == 0) {
                        dispose();
                        break;
                    }
                    labelCronometro.setText("Tiempo:    " + textoCronometro);
                    Last = java.lang.System.currentTimeMillis();
                }
            }
        }
    }
}
