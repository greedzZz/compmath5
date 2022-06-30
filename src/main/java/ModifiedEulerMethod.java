import java.util.ArrayList;

public class ModifiedEulerMethod {
    private static final double H = 0.01;
    public static ArrayList<Point> compute(Function function, double x0, double y0, double xn) {
        ArrayList<Point> points = new ArrayList<>();
        int count = (int) Math.ceil((xn - x0) / H);
        points.add(new Point(x0, y0));
        double currentX = x0;
        double currentY = y0;
        for (int i = 0; i < count; i++) {
            currentY = currentY + H * function.getValueOfFunction(currentX + H / 2,
                    currentY + H / 2 * function.getValueOfFunction(currentX, currentY));
            currentX += H;
            points.add(new Point(currentX, currentY));
        }
        return points;
    }
}
