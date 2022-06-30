public class MatrixMethod {
    private static boolean diagonalDominance(Matrix matrix) {
        int size = matrix.getSize();
        double[][] elements = matrix.getElements();
        int[] maxIndexes = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.abs(elements[i][j]) > Math.abs(elements[i][maxIndexes[i]])) maxIndexes[i] = j;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (maxIndexes[i] == maxIndexes[j]) return false;
            }
        }
        boolean strictCondition = false;
        for (int i = 0; i < size; i++) {
            double rowSum = 0;
            for (int j = 0; j < size; j++) {
                if (j != maxIndexes[i]) rowSum += Math.abs(elements[i][j]);
            }
            if (Math.abs(elements[i][maxIndexes[i]]) >= rowSum) {
                if (Math.abs(elements[i][maxIndexes[i]]) > rowSum) {
                    strictCondition = true;
                }
            } else return false;
        }
        if (strictCondition) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (maxIndexes[i] > maxIndexes[j]) {
                        int tmpIndex = maxIndexes[j];
                        maxIndexes[j] = maxIndexes[i];
                        maxIndexes[i] = tmpIndex;
                        double[] tmpArray = elements[j];
                        elements[j] = elements[i];
                        elements[i] = tmpArray;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static double[] compute(Matrix matrix) {
        if (!diagonalDominance(matrix)) return null;
        int size = matrix.getSize();
        double[][] elements = matrix.getElements();
        double[] variables = new double[size];
        for (int i = 0; i < size; i++) {
            variables[i] = 0.0;
        }
        double[] previousVariables;
        boolean computed;
        do {
            previousVariables = variables;
            for (int i = 0; i < size; i++) {
                double sum = 0;
                for (int j = 0; j < size; j++) {
                    if (i != j) sum += elements[i][j] * variables[j];
                }
                variables[i] = 1 / elements[i][i] * (elements[i][size] - sum);
            }
            computed = true;
            for (int i = 0; i < size; i++) {
                if (0.001 < Math.abs(variables[i] - previousVariables[i])) {
                    computed = false;
                    break;
                }
            }
        } while (!computed);
        return variables;
    }
}
