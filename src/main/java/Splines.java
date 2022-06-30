import lombok.Data;

@Data
public class Splines {
    private double[] a;
    private double[] b;
    private double[] c;
    private double[] d;
    private double[] x;

    public Splines(double[] a, double[] b, double[] c, double[] d, double[] x) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.x = x;
    }

    public double getValueOfFunction(double x, int i) {
        return this.a[i] + this.b[i] * (x - this.x[i]) + this.c[i] * (x - this.x[i]) * (x - this.x[i]) +
                this.d[i] * (x - this.x[i]) * (x - this.x[i]) * (x - this.x[i]);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x.length; i++) {
            sb.append(a[i]).append(" ").append(b[i]).append(" ").append(c[i]).append(" ").append(d[i]).append("\n");
        }
        return sb.toString();
    }
}
