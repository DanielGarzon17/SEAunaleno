package IU;

import java.util.Arrays;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.*;
import org.jfree.data.statistics.*;
import org.jfree.data.xy.*;

import DATOS.Usuario;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

//INVESTIGAR LIBRERIA JFREE CHART
public class EstadisticasPanel extends JPanel {
    private JTable table;

    public EstadisticasPanel(int[] datos) {
        initComponents(datos);
        cargarDatosTabla(datos);
    }

    private void initComponents(int[] datos) {
        setLayout(new BorderLayout());

        // Crear tabla
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        int totalSum = 0;
        for (int dato : datos) {
            totalSum += dato;
        }
        double[] cumulativePercent = new double[datos.length];
        int cumulativeSum = 0;
        for (int i = 0; i < datos.length; i++) {
            cumulativeSum += datos[i];
            cumulativePercent[i] = (double) cumulativeSum / totalSum * 100;
        }
        // Crear gráfico de Pareto
        JPanel paretoPanel = new JPanel(new BorderLayout());
        ParetoChart paretoChart = new ParetoChart(datos,cumulativePercent);
        paretoPanel.add(paretoChart, BorderLayout.CENTER);

        // Crear gráfico de dispersión
        JPanel scatterPanel = new JPanel(new BorderLayout());
        ScatterChart scatterChart = new ScatterChart(datos);
        scatterPanel.add(scatterChart, BorderLayout.CENTER);

        // Crear gráfico de líneas
        JPanel linePanel = new JPanel(new BorderLayout());
        LineChart lineChart = new LineChart(datos);
        linePanel.add(lineChart, BorderLayout.CENTER);

        // Panel de gráficos
        JPanel chartsPanel = new JPanel(new GridLayout(2, 2));
        chartsPanel.add(scatterPanel);
        chartsPanel.add(linePanel);
        chartsPanel.add(paretoPanel);


        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, chartsPanel);
        splitPane.setResizeWeight(0.3);
        add(splitPane, BorderLayout.CENTER);
    }

    private void cargarDatosTabla(int[] datos) {
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Puntaje" }, 0);
        for (int dato : datos) {
            model.addRow(new Object[] { dato });
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Datos de ejemplo
            int[] datos = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };

            EstadisticasPanel estadisticasPanel = new EstadisticasPanel(datos);

            JFrame frame = new JFrame("Prueba EstadisticasPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(estadisticasPanel);
            frame.setVisible(true);
        });
    }

    private static class ScatterChart extends JPanel {
        private final int[] datos;

        public ScatterChart(int[] datos) {
            this.datos = datos;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Crear gráfico de dispersión
            DefaultXYDataset dataset = new DefaultXYDataset();
            double[][] scatterData = new double[2][datos.length];
            for (int i = 0; i < datos.length; i++) {
                scatterData[0][i] = i;
                scatterData[1][i] = datos[i];
            }
            dataset.addSeries("Puntajes", scatterData);

            JFreeChart chart = ChartFactory.createScatterPlot("Gráfico de dispersión", "Muestra", "Puntaje", dataset,
                    PlotOrientation.VERTICAL, false, true, false);

            XYPlot plot = chart.getXYPlot();
            plot.setDomainPannable(false);
            plot.setRangePannable(false);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            chartPanel.setDomainZoomable(false);
            chartPanel.setRangeZoomable(false);
            add(chartPanel);
        }
    }

    private static class ParetoChart extends JPanel {
        private final int[] datos;
        private final double[] cumulativePercent;

        public ParetoChart(int[] datos, double[] cumulativePercent) {
            this.datos = datos;
            this.cumulativePercent = cumulativePercent;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Crear gráfico de Pareto
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < datos.length; i++) {
                dataset.addValue(datos[i], "Puntaje", String.valueOf(datos[i]));
                dataset.addValue(cumulativePercent[i], "Porcentaje acumulado", String.valueOf(datos[i]));
            }

            JFreeChart chart = ChartFactory.createBarChart("Gráfico de Pareto", "Puntaje", "Porcentaje acumulado",
                    dataset, PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot plot = chart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setSeriesPaint(0, Color.BLUE);
            renderer.setSeriesPaint(1, Color.RED);
            renderer.setDrawBarOutline(false);

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setNumberFormatOverride(new DecimalFormat("#'%'"));

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(false);
        }
    }

    private static class LineChart extends JPanel {
        private final int[] datos;

        public LineChart(int[] datos) {
            this.datos = datos;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Crear gráfico de líneas
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < datos.length; i++) {
                dataset.addValue(datos[i], "Puntaje", String.valueOf(i + 1));
            }

            JFreeChart chart = ChartFactory.createLineChart("Gráfico de líneas", "Muestra", "Puntaje", dataset,
                    PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot plot = chart.getCategoryPlot();
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, Color.BLUE);
            renderer.setSeriesShapesVisible(0, true);
            renderer.setSeriesLinesVisible(0, true);

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setAutoRangeIncludesZero(false);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(400, 300));
            chartPanel.setDomainZoomable(false);
            chartPanel.setRangeZoomable(false);
        }
    }
}