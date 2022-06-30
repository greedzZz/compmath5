public class Data {

    public static final Function[] FUNCTIONS = new Function[]{
            new Function("sin(x)", "x"), // -4 0 4 // 1 0 3
            new Function("sin(x) + cos(y)", "x", "y"), // 1 1 3 // 1 0 3
            new Function("log(x)", "x"), // 1 2 4 // 1 1 4
            new Function("y-x^3", "x", "y"), // 1 1 5 // 1 10 5
            new Function("3*x^2*y", "x", "y") // 0 1 2 // 1 1 2
    };

}
