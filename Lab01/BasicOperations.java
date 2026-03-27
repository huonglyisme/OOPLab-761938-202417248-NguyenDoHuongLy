package Lab01;
import javax.swing.JOptionPane;

public class BasicOperations {
    public static void main(String[] args) {
        try {
            String strNum1 = JOptionPane.showInputDialog(null, "Nhap so thu nhat:");
            String strNum2 = JOptionPane.showInputDialog(null, "Nhap so thu hai:");

            if (strNum1 == null || strNum2 == null) {
                System.exit(0);
            }

            double num1 = Double.parseDouble(strNum1);
            double num2 = Double.parseDouble(strNum2);

            double sum = num1 + num2;
            double difference = num1 - num2;
            double product = num1 * num2;

            String message = "Tong = " + sum + "\n"
                    + "Hieu = " + difference + "\n"
                    + "Tich = " + product + "\n";

            if (num2 != 0) {
                double quotient = num1 / num2;
                message += "Thuong = " + quotient;
            } else {
                message += "Thuong = khong xac dinh (chia cho 0)";
            }

            JOptionPane.showMessageDialog(null, message);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Du lieu nhap vao khong hop le.");
        }

        System.exit(0);
    }
}