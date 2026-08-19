import java.util.Scanner;

class ElectricityBill {
    String customerName;
    double units;
    double bill;

    void getData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        customerName = sc.nextLine();

        System.out.print("Enter Units Consumed: ");
        units = sc.nextDouble();
    }

    void calculateBill() {
        if (units <= 100) {
            bill = units * 1.5;
        }
        else if (units <= 200) {
            bill = (100 * 1.5) + (units - 100) * 2.5;
        }
        else if (units <= 500) {
            bill = (100 * 1.5) + (100 * 2.5)
                   + (units - 200) * 4.0;
        }
        else {
            bill = (100 * 1.5) + (100 * 2.5)
                   + (300 * 4.0) + (units - 500) * 6.0;
        }
    }

    void display() {
        System.out.println("\n----- ELECTRICITY BILL -----");
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Units Consumed : " + units);
        System.out.println("Bill Amount    : Rs." + bill);
    }
}

public class Electricity {
    public static void main(String[] args) {
        ElectricityBill obj = new ElectricityBill();

        obj.getData();
        obj.calculateBill();
        obj.display();
    }
}
