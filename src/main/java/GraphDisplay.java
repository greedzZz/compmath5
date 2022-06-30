import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Ellipse2D;


public class GraphDisplay {
    Splines splines;
    String function;

    public GraphDisplay(Splines splines, String function) {
        this.function = function;
        this.splines = splines;
    }

    public void draw(Function function) {
        double[] xData = new double[10000];
        double[] yDataSpline = new double[10000];
        double[] yData = new double[10000];
        XYSeries xySeries1 = new XYSeries("xySeries1");
        XYSeries xySeries2 = new XYSeries("xySeries2");
        double step = (splines.getX()[splines.getX().length - 1] - splines.getX()[0]) / 1000;
        double x = splines.getX()[0] - 0.1;
        xData[0] = x;
        yDataSpline[0] = splines.getValueOfFunction(x, 0);
        if (function != null) yData[0] = function.getValueOfFunction(x);
        double curX = splines.getX()[0];
        int k = 0;
        int i = 0;
        while (x < splines.getX()[splines.getX().length - 1]) {
            i++;
            x += step;
            if (x > curX && k < splines.getX().length - 1) {
                k++;
                curX = splines.getX()[k];
            }
            xData[i] = x;
            yDataSpline[i] = splines.getValueOfFunction(x, k);
            if (function != null) yData[i] = function.getValueOfFunction(x);
            xySeries1.add(xData[i], yDataSpline[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries1);
        dataset.addSeries(xySeries2);
        JFreeChart chart = ChartFactory.createXYLineChart("F(x)", "x", "y", dataset, PlotOrientation.VERTICAL, false, false, false);
        XYPlot plot = chart.getXYPlot();
        XYSplineRenderer renderer = new XYSplineRenderer(10000);
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1, -1, 2, 2));
        plot.setDataset(0, dataset);
        for (int j = 0; j < splines.getX().length; j++) {
            XYSeries xySeries3 = new XYSeries("xySeries3");
            XYSeriesCollection pointDataset = new XYSeriesCollection();
            pointDataset.addSeries(xySeries3);
            xySeries3.add(splines.getX()[j], splines.getA()[j]);
            plot.setDataset(j + 1, pointDataset);
            plot.setRenderer(j + 1, renderer);
        }
        ChartFrame frame = new ChartFrame("", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
