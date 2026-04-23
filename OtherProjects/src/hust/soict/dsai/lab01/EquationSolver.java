package Lab01;
import javax.swing.JOptionPane;

public class EquationSolver {
    private static final double EPS = 1e-9;

    public static void main(String[] args) {
        try {
            String menu = "Chon loai bai toan:\n"
                    + "1. Giai phuong trinh bac nhat ax + b = 0\n"
                    + "2. Giai he phuong trinh bac nhat 2 an\n"
                    + "3. Giai phuong trinh bac hai ax^2 + bx + c = 0";

            String choiceStr = JOptionPane.showInputDialog(null, menu);

            if (choiceStr == null) {
                System.exit(0);
            }

            int choice = Integer.parseInt(choiceStr);

            switch (choice) {
                case 1:
                    solveLinearEquation();
                    break;
                case 2:
                    solveLinearSystem();
                    break;
                case 3:
                    solveQuadraticEquation();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Lua chon khong hop le.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Du lieu nhap vao khong hop le.");
        }

        System.exit(0);
    }

    private static double inputDouble(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        if (input == null) {
            System.exit(0);
        }
        return Double.parseDouble(input);
    }

    private static void solveLinearEquation() {
        double a = inputDouble("Nhap a:");
        double b = inputDouble("Nhap b:");

        String result;
        if (Math.abs(a) < EPS) {
            if (Math.abs(b) < EPS) {
                result = "Phuong trinh vo so nghiem.";
            } else {
                result = "Phuong trinh vo nghiem.";
            }
        } else {
            double x = -b / a;
            result = "Nghiem x = " + x;
        }

        JOptionPane.showMessageDialog(null, result);
    }

    private static void solveLinearSystem() {
        double a11 = inputDouble("Nhap a11:");
        double a12 = inputDouble("Nhap a12:");
        double b1 = inputDouble("Nhap b1:");

        double a21 = inputDouble("Nhap a21:");
        double a22 = inputDouble("Nhap a22:");
        double b2 = inputDouble("Nhap b2:");

        double D = a11 * a22 - a12 * a21;
        double D1 = b1 * a22 - a12 * b2;
        double D2 = a11 * b2 - b1 * a21;

        String result;
        if (Math.abs(D) < EPS) {
            if (Math.abs(D1) < EPS && Math.abs(D2) < EPS) {
                result = "He phuong trinh vo so nghiem.";
            } else {
                result = "He phuong trinh vo nghiem.";
            }
        } else {
            double x1 = D1 / D;
            double x2 = D2 / D;
            result = "Nghiem cua he:\n"
                    + "x1 = " + x1 + "\n"
                    + "x2 = " + x2;
        }

        JOptionPane.showMessageDialog(null, result);
    }

    private static void solveQuadraticEquation() {
        double a = inputDouble("Nhap a:");
        double b = inputDouble("Nhap b:");
        double c = inputDouble("Nhap c:");

        String result;

        if (Math.abs(a) < EPS) {
            if (Math.abs(b) < EPS) {
                if (Math.abs(c) < EPS) {
                    result = "Phuong trinh vo so nghiem.";
                } else {
                    result = "Phuong trinh vo nghiem.";
                }
            } else {
                double x = -c / b;
                result = "Day la phuong trinh bac nhat.\nNghiem x = " + x;
            }
        } else {
            double delta = b * b - 4 * a * c;

            if (delta < -EPS) {
                result = "Phuong trinh vo nghiem thuc.";
            } else if (Math.abs(delta) < EPS) {
                double x = -b / (2 * a);
                result = "Phuong trinh co nghiem kep x1 = x2 = " + x;
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                result = "Phuong trinh co 2 nghiem phan biet:\n"
                        + "x1 = " + x1 + "\n"
                        + "x2 = " + x2;
            }
        }

        JOptionPane.showMessageDialog(null, result);
    }
}