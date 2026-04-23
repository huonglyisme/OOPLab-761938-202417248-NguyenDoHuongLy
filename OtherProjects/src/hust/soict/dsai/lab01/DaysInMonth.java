import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int month = -1;
        int year = -1;

        while (month == -1) {
            System.out.print("Nhap thang: ");
            String monthInput = sc.nextLine().trim().toLowerCase().replace(".", "");
            month = parseMonth(monthInput);

            if (month == -1) {
                System.out.println("Thang khong hop le. Vui long nhap lai.");
            }
        }

        while (year < 0) {
            System.out.print("Nhap nam (so nguyen khong am): ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                if (year < 0) {
                    System.out.println("Nam khong hop le. Vui long nhap lai.");
                }
            } else {
                System.out.println("Nam khong hop le. Vui long nhap lai.");
                sc.next();
            }
        }

        int days = getDaysInMonth(month, year);
        System.out.println("Thang " + month + " nam " + year + " co " + days + " ngay.");

        sc.close();
    }

    public static int parseMonth(String input) {
        switch (input) {
            case "1":
            case "01":
            case "january":
            case "jan":
                return 1;

            case "2":
            case "02":
            case "february":
            case "feb":
                return 2;

            case "3":
            case "03":
            case "march":
            case "mar":
                return 3;

            case "4":
            case "04":
            case "april":
            case "apr":
                return 4;

            case "5":
            case "05":
            case "may":
                return 5;

            case "6":
            case "06":
            case "june":
            case "jun":
                return 6;

            case "7":
            case "07":
            case "july":
            case "jul":
                return 7;

            case "8":
            case "08":
            case "august":
            case "aug":
                return 8;

            case "9":
            case "09":
            case "september":
            case "sept":
            case "sep":
                return 9;

            case "10":
            case "october":
            case "oct":
                return 10;

            case "11":
            case "november":
            case "nov":
                return 11;

            case "12":
            case "december":
            case "dec":
                return 12;

            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                return -1;
        }
    }
}
