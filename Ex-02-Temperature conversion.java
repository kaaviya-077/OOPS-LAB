import java.util.Scanner;

class Convert {
    double temp;

    void celsius() {
        System.out.println("Celsius to Fahrenheit = " + ((temp * 9 / 5) + 32));
        System.out.println("Celsius to Kelvin = " + (temp + 273.15));
    }

    void fahrenheit() {
        System.out.println("Fahrenheit to Celsius = " + ((temp - 32) * 5 / 9));
        System.out.println("Fahrenheit to Kelvin = " + (((temp - 32) * 5 / 9) + 273.15));
    }

    void kelvin() {
        System.out.println("Kelvin to Celsius = " + (temp - 273.15));
        System.out.println("Kelvin to Fahrenheit = " + (((temp - 273.15) * 9 / 5) + 32));
    }
}

public class tem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Convert obj = new Convert();

        System.out.println("Temperature Conversion");
        System.out.println("1. Celsius");
        System.out.println("2. Fahrenheit");
        System.out.println("3. Kelvin");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter temperature: ");
        obj.temp = sc.nextDouble();

        switch (choice) {
            case 1:
                obj.celsius();
                break;

            case 2:
                obj.fahrenheit();
                break;

            case 3:
                obj.kelvin();
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}
