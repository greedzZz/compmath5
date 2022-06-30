public class SplineMethod {
    double[] x;
    double[] y;
    int n;

    public SplineMethod(double[] x, double[] y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public Splines getSpline() {
        double[] h = new double[x.length - 1];
        for (int i = 0; i < h.length; i++) {
            h[i] = x[i + 1] - x[i];
        }
        double[][] c = getSplineMatrix(h, y);
        Matrix matrix = new Matrix(y.length, c);
        double[] roots = MatrixMethod.compute(matrix);
        double[] b = new double[y.length];
        for (int i = 1; i < b.length; i++)
            b[i] = (y[i] - y[i - 1]) / h[i - 1] + h[i - 1] * (roots[i] * 2 / 3 + roots[i - 1] * 2 / 6);
        double[] d = new double[y.length];
        d[0] = roots[0] / h[0];
        for (int i = 1; i < d.length; i++) d[i] = (roots[i] - roots[i - 1]) / (3 * h[i - 1]);
        return new Splines(y, b, roots, d, x);
    }

    private double[][] getSplineMatrix(double[] h, double[] y) {
        int length = y.length;
        double[][] matrix = new double[length][length + 1];
        for (int i = 0; i < length; i++) for (int j = 0; j < length; j++) matrix[i][j] = 0;
        for (int i = 1; i < length - 1; i++) {
            matrix[i][i - 1] = h[i - 1];
            matrix[i][i] = 2 * (h[i] + h[i - 1]);
            matrix[i][i + 1] = h[i];
            matrix[i][h.length + 1] = 3 * ((y[i + 1] - y[i]) / h[i] - (y[i] - y[i - 1]) / h[i - 1]);
        }
        matrix[0][0] = 1;
        matrix[h.length][h.length] = 1;
        matrix[0][h.length + 1] = 0;
        matrix[h.length][h.length + 1] = 0;
        return matrix;
    }
}
