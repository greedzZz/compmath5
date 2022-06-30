import java.util.ArrayList;

public class Main {

    public static void main(String[] arg) {
        try {
            IOManager ioManager = new IOManager();
            Function function = Data.FUNCTIONS[ioManager.readFunctionNumber() - 1];
            double x0 = ioManager.readDouble("x0");
            double y0 = ioManager.readDouble("y0");
            double xn = ioManager.readXn(x0);
            ArrayList<Point> answers = ModifiedEulerMethod.compute(function, x0, y0, xn);
            int n = answers.size();
            double[] x = new double[n];
            double[] y = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = answers.get(i).getX();
                y[i] = answers.get(i).getY();
                System.out.println("x = " + x[i] + "; y = " + y[i]);
            }
            SplineMethod splineMethod = new SplineMethod(x, y, n);
            Splines splines = splineMethod.getSpline();
            GraphDisplay graphDisplay = new GraphDisplay(splines, function.getStringFunction());
            graphDisplay.draw(function);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

