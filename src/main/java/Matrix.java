public class Matrix {
    private final int size;
    private final double[][] elements;

    public Matrix(int size, double[][] elements) {
        this.size = size;
        this.elements = elements;
    }

    public int getSize() {
        return this.size;
    }

    public double[][] getElements() {
        return this.elements;
    }
}
