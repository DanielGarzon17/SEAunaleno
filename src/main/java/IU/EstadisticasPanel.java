package IU;

import java.util.Arrays;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.xy.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.table.DefaultTableModel;

//INVESTIGAR LIBRERIA JFREE CHART
public class EstadisticasPanel extends JPanel {
    private JTable table;

    public EstadisticasPanel(double[] datos) {
        initComponents(datos);
        cargarDatosTabla(datos);
    }

    private void initComponents(double[] datos) {
        setLayout(new BorderLayout());

        // Crear tabla
        table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);

        int totalSum = 0;
        for (double dato : datos) {
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
        // paretoPanel.add(paretoChart, BorderLayout.CENTER);
        DefaultTableModel modelo = new DefaultTableModel(new Object[] { "", "Valor" }, 0);
        JTable tablaMedias = new JTable();
        modelo.addRow(new Object[] { "Media", totalSum / datos.length });
        double desv = calcularDesviacionEstandar(datos);
        modelo.addRow(new Object[] { "Desviacion estandar", desv });
        tablaMedias.setModel(modelo);

        generarDiagramaPareto(datos, paretoPanel);

        // Crear gráfico de dispersión
        JPanel scatterPanel = new JPanel(new BorderLayout());
        ScatterChart scatter = new ScatterChart(datos);
        scatterPanel.add(scatter, BorderLayout.CENTER);
        // Crear gráfico de líneas
        JPanel linePanel = new JPanel(new BorderLayout());
        LineChart lineChart = new LineChart(datos);
        linePanel.add(lineChart, BorderLayout.CENTER);

        // Panel de gráficos
        JPanel chartsPanel = new JPanel(new GridLayout(2, 2));
        chartsPanel.add(scatterPanel);
        chartsPanel.add(linePanel);
        chartsPanel.add(paretoPanel);
        chartsPanel.add(tablaMedias);

        // SplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, chartsPanel);
        splitPane.setResizeWeight(0.3);
        add(splitPane, BorderLayout.CENTER);
    }

    private void cargarDatosTabla(double[] datos) {
        DefaultTableModel model = new DefaultTableModel(new Object[] { "Puntaje" }, 0);
        for (double dato : datos) {
            model.addRow(new Object[] { dato });
        }
        table.setModel(model);
    }

    public static double calcularDesviacionEstandar(double[] valores) {
        int n = valores.length;

        // Calcular la media
        double suma = 0;
        for (double valor : valores) {
            suma += valor;
        }
        double media = suma / n;

        // Calcular la suma de los cuadrados de la diferencia entre cada valor y la
        // media
        double sumaCuadrados = 0;
        for (double valor : valores) {
            double diferencia = valor - media;
            sumaCuadrados += diferencia * diferencia;
        }

        // Dividir la suma de los cuadrados entre la cantidad de valores y calcular la
        // raíz cuadrada
        double desviacionEstandar = Math.sqrt(sumaCuadrados / n);

        return desviacionEstandar;
    }

    public static void generarDiagramaPareto(double[] datos, JPanel panel) {
        // Ordenar los datos en orden descendente
        double[] datosOrdenados = Arrays.copyOf(datos, datos.length);
        Arrays.sort(datosOrdenados);

        // Calcular el número de rangos
        int numRangos = 5;

        // Calcular los rangos y sus intervalos
        // double rangoIntervalo = datos.length / (double) numRangos;
        Rango[] rangos = new Rango[numRangos];
        double inicio = 0;
        double amplitud = (datosOrdenados[datosOrdenados.length - 1] - datosOrdenados[0]) / numRangos;
        for (int i = 0; i < numRangos; i++) {
            double fin = inicio + amplitud;
            rangos[i] = new Rango(inicio, fin);
            inicio = fin;
        }

        // Calcular los datos para cada rango
        int[] datosRangos = new int[numRangos];
        for (int i = 0; i < numRangos; i++) {
            int cont = 0;
            double rangoInicio = rangos[i].getInicio();
            double rangoFin = rangos[i].getFin();
            for (double dato : datosOrdenados) {
                if (dato >= rangoInicio && dato < rangoFin) {
                    cont++;
                }
            }
            datosRangos[i] = cont;
        }

        // Calcular el porcentaje acumulado
        double[] porcentajeAcumulado = new double[numRangos];
        for (int i = 0; i < numRangos; i++) {
            double aux = datosRangos[i] / (double) datos.length * 100;
            if (i == 0) {
                porcentajeAcumulado[i] = aux;
            } else {
                porcentajeAcumulado[i] = porcentajeAcumulado[i - 1] + aux;
            }
        }

        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < numRangos; i++) {
            String categoria = String.format("[%d, %d]", (int) rangos[i].getInicio(), (int) rangos[i].getFin());
            dataset.addValue(datosRangos[i], "Frecuencia", categoria);
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

    private static class ScatterChart extends JPanel {
        private final double[] datos;

        public ScatterChart(double[] datos) {
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
            plot.setDomainPannable(true);
            plot.setRangePannable(true);

            ChartPanel chartPanel = new ChartPanel(chart);

            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(true);
            chartPanel.setPreferredSize(new Dimension(450, 350));
            add(chartPanel);
        }
    }

    private static class LineChart extends JPanel {
        private final double[] datos;

        public LineChart(double[] datos) {
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
            rangeAxis.setAutoRangeIncludesZero(true);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(450, 350));
            chartPanel.setDomainZoomable(true);
            chartPanel.setRangeZoomable(true);
            add(chartPanel);
        }
    }
}