import java.util.ArrayList;
import java.util.Scanner;

class StringArrayList {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        // Adding strings
        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter string " + (i + 1) + ": ");
            list.add(sc.nextLine());
        }

        // Display
        System.out.println("\nStrings in ArrayList:");
        for (String s : list) {
            System.out.println(s);
        }

        // Search
        System.out.print("\nEnter string to search: ");
        String search = sc.nextLine();

        if (list.contains(search)) {
            System.out.println(search + " is found.");
        } else {
            System.out.println(search + " is not found.");
        }

        // Update
        System.out.print("\nEnter index to update: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index >= 0 && index < list.size()) {
            System.out.print("Enter new string: ");
            String newString = sc.nextLine();
            list.set(index, newString);
        } else {
            System.out.println("Invalid index.");
        }

        // Remove
        System.out.print("\nEnter string to remove: ");
        String remove = sc.nextLine();

        if (list.remove(remove)) {
            System.out.println(remove + " removed successfully.");
        } else {
            System.out.println(remove + " not found.");
        }

        // Final ArrayList
        System.out.println("\nFinal ArrayList:");
        for (String s : list) {
            System.out.println(s);
        }

        sc.close();
    }
}
