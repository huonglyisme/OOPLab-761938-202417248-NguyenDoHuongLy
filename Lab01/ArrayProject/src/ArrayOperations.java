import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so phan tu cua mang: ");
        int n = sc.nextInt();

        double[] arr = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("arr[" + i + "] = ");
            arr[i] = sc.nextDouble();
            sum += arr[i];
        }

        Arrays.sort(arr);

        double average = sum / n;

        System.out.println("Mang sau khi sap xep: " + Arrays.toString(arr));
        System.out.println("Tong = " + sum);
        System.out.println("Trung binh = " + average);

        sc.close();
    }
}
