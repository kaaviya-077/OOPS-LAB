```java
import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean issued;

    // Constructor
    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    // Display book details
    void displayBook() {
        System.out.println("Book ID : " + bookId);
        System.out.println("Title   : " + title);
        System.out.println("Author  : " + author);
        System.out.println("Status  : " + (issued ? "Issued" : "Available"));
        System.out.println("--------------------------------");
    }

    int getBookId() {
        return bookId;
    }

    String getTitle() {
        return title;
    }

    boolean isIssued() {
        return issued;
    }

    void issueBook() {
        issued = true;
    }

    void returnBook() {
        issued = false;
    }
}

// Member Class
class Member {
    private int memberId;
    private String name;
    private String department;

    // Constructor
    Member(int memberId, String name, String department) {
        this.memberId = memberId;
        this.name = name;
        this.department = department;
    }

    int getMemberId() {
        return memberId;
    }

    String getName() {
        return name;
    }

    // Display member details
    void displayMember() {
        System.out.println("Member ID  : " + memberId);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("--------------------------------");
    }
}

// Main Library Management Class
public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    // Add Book
    static void addBook() {

        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        Book book = new Book(id, title, author);

        books.add(book);

        System.out.println("\nBook added successfully!");
    }

    // Add Member
    static void addMember() {

        System.out.print("Enter Member ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Member member = new Member(id, name, department);

        members.add(member);

        System.out.println("\nMember added successfully!");
    }

    // Display Books
    static void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("\nNo books available.");
            return;
        }

        System.out.println("\n========== BOOK LIST ==========");

        for (Book book : books) {
            book.displayBook();
        }
    }

    // Display Members
    static void displayMembers() {

        if (members.isEmpty()) {
            System.out.println("\nNo members registered.");
            return;
        }

        System.out.println("\n========== MEMBER LIST ==========");

        for (Member member : members) {
            member.displayMember();
        }
    }

    // Search Book
    static void searchBook() {

        scanner.nextLine();

        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();

        boolean found = false;

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                System.out.println("\nBook Found!");
                book.displayBook();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nBook not found.");
        }
    }

    // Issue Book
    static void issueBook() {

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        Book selectedBook = null;
        Member selectedMember = null;

        // Find book
        for (Book book : books) {

            if (book.getBookId() == bookId) {
                selectedBook = book;
                break;
            }
        }

        // Find member
        for (Member member : members) {

            if (member.getMemberId() == memberId) {
                selectedMember = member;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("\nBook ID not found.");
            return;
        }

        if (selectedMember == null) {
            System.out.println("\nMember ID not found.");
            return;
        }

        if (selectedBook.isIssued()) {
            System.out.println("\nSorry! This book is already issued.");
            return;
        }

        selectedBook.issueBook();

        System.out.println("\nBook issued successfully!");
        System.out.println("Book   : " + selectedBook.getTitle());
        System.out.println("Member : " + selectedMember.getName());
    }

    // Return Book
    static void returnBook() {

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                if (!book.isIssued()) {
                    System.out.println("\nThis book is already available.");
                    return;
                }

                book.returnBook();

                System.out.println("\nBook returned successfully!");
                return;
            }
        }

        System.out.println("\nBook ID not found.");
    }

    // Main Method
    public static void main(String[] args) {

        int choice = 0;

        System.out.println("==========================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        do {

            System.out.println("\n--------------- MENU ----------------");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display All Members");
            System.out.println("5. Search Book");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.println("-------------------------------------");

            try {

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        addMember();
                        break;

                    case 3:
                        displayBooks();
                        break;

                    case 4:
                        displayMembers();
                        break;

                    case 5:
                        searchBook();
                        break;

                    case 6:
                        issueBook();
                        break;

                    case 7:
                        returnBook();
                        break;

                    case 8:
                        System.out.println("\nThank you for using");
                        System.out.println("Library Management System!");
                        break;

                    default:
                        System.out.println("\nInvalid choice!");
                }

            } catch (Exception e) {

                System.out.println("\nInvalid input! Please enter a number.");
                scanner.nextLine();
            }

        } while (choice != 8);

        scanner.close();
    }
}
```

### GitHub

Your repository can simply look like:

```text
Library-Management-System
│
├── LibraryManagementSystem.java
└── README.md
```

**Run it:**

```bash
javac LibraryManagementSystem.java
java LibraryManagementSystem
```

The important point is that although it is **one `.java` file**, it still has multiple classes (`Book`, `Member`, and `LibraryManagementSystem`), so you can demonstrate the **OOP concepts** to your staff.
