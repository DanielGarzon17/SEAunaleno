package PRUEBAS;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParetoDiagram {
    public static void main(String[] args) {
        List<Double> datos = new ArrayList<>();
        datos.add(50.0);
        datos.add(60.0);
        datos.add(30.0);
        datos.add(40.0);
        datos.add(70.0);
        datos.add(80.0);
        datos.add(90.0);

        JFrame frame = new JFrame("Diagrama de Pareto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        generarDiagramaPareto(datos, panel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void generarDiagramaPareto(List<Double> datos, JPanel panel) {
        // Ordenar los datos en orden descendente
        List<Double> datosOrdenados = new ArrayList<>(datos);
        Collections.sort(datosOrdenados, Collections.reverseOrder());

        // Calcular el número de rangos
        int numRangos = 5;

        // Calcular los rangos y sus intervalos
        double rangoIntervalo = datos.size() / (double) numRangos;
        List<Rango> rangos = new ArrayList<>();
        double inicio = 0;
        double amplitud = (Collections.max(datos) - Collections.min(datos)) / numRangos;
        for (int i = 0; i < numRangos; i++) {
            double fin = inicio + amplitud;
            rangos.add(new Rango(inicio, fin));
            inicio = fin;
        }

        // Calcular los datos para cada rango
        List<Integer> datosRangos = new ArrayList<>();
        for (Rango rango : rangos) {
            int cont = 0;
            double rangoInicio = rango.getInicio();
            double rangoFin = rango.getFin();
            for (double dato : datosOrdenados) {
                if (dato >= rangoInicio && dato < rangoFin) {
                    cont++;
                }
            }
            datosRangos.add(cont);
        }

        // Calcular el porcentaje acumulado
        List<Double> porcentajeAcumulado = new ArrayList<>();
        for (int i = 0; i < datosRangos.size(); i++) {
            double aux = datosRangos.get(i) / (double) datos.size() * 100;
            if (i == 0) {
                porcentajeAcumulado.add(aux);
            } else {
                porcentajeAcumulado.add(porcentajeAcumulado.get(i - 1) + aux);
            }
        }

        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < numRangos; i++) {
            String categoria = String.format("[%d, %d]", (int) rangos.get(i).getInicio(), (int) rangos.get(i).getFin());
            dataset.addValue(datosRangos.get(i), "Frecuencia", categoria);
        }

        // Crear el gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart("Diagrama de Pareto", "Puntajes obtenidos", "Frecuencia",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        // Establecer color azul para las barras
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setSeriesPaint(0, Color.BLUE);

        // Formatear el eje y con separadores de miles
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,###"));

        // Crear el gráfico de línea para el porcentaje acumulado
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, Color.RED);
        plot.setRenderer(1, lineRenderer);
        plot.setDataset(1, dataset);
        plot.mapDatasetToRangeAxis(1, 1);

        // Crear un nuevo eje para el porcentaje acumulado
        NumberAxis percentageAxis = new NumberAxis("Porcentaje acumulado (%)");
        percentageAxis.setNumberFormatOverride(new DecimalFormat("0.0"));
        plot.setRangeAxis(1, percentageAxis);

        // Ajustar los espacios entre las barras
        barRenderer.setItemMargin(0.0);

        // Rotar las etiquetas del eje x en 45 grados
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Crear un ChartPanel y agregarlo al panel
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel, BorderLayout.CENTER);
    }

    static class Rango {
        private final double inicio;
        private final double fin;

        public Rango(double inicio, double fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        public double getInicio() {
            return inicio;
        }

        public double getFin() {
            return fin;
        }
    }
}
