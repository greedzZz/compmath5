import java.util.Scanner;

public class IOManager {
    private final Scanner scanner = new Scanner(System.in);

    public int readFunctionNumber() {
        System.out.println("Усовершенствованный метод Эйлера.");
        int number = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Выберите функцицю:");
            System.out.println("[1]: y' = " + Data.FUNCTIONS[0].getStringFunction());
            System.out.println("[2]: y' = " + Data.FUNCTIONS[1].getStringFunction());
            System.out.println("[3]: y' = " + Data.FUNCTIONS[2].getStringFunction());
            System.out.println("[4]: y' = " + Data.FUNCTIONS[3].getStringFunction());
            System.out.println("[5]: y' = " + Data.FUNCTIONS[4].getStringFunction());
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= 1 && number <= 5) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return number;
    }

    public double readDouble(String message) {
        double num = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите " + message + ":");
            try {
                num = Double.parseDouble(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return num;
    }

    public double readXn(double x0) {
        double xn = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите xn:");
            try {
                xn = Double.parseDouble(scanner.nextLine());
                if (xn > x0) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return xn;
    }
}