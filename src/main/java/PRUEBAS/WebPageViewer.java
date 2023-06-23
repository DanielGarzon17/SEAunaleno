package PRUEBAS;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class WebPageViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Web Page Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String url = "https://admisiones.unal.edu.co/pregrado/"; // Aquí puedes colocar el enlace que desees mostrar

        try {
            JEditorPane editorPane = new JEditorPane(url);
            editorPane.setEditable(false);
            editorPane.addHyperlinkListener(e -> {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        editorPane.setPage(e.getURL());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(editorPane);
            panel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame.getContentPane().add(panel);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}

