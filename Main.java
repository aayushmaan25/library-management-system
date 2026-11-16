import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Book[] books = new Book[100];
    static int count = 0;
    static final int FINE_PER_DAY = 5;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a book");
            System.out.println("2. Display all books");
            System.out.println("3. Search by book ID");
            System.out.println("4. Search by title");
            System.out.println("5. Issue a book");
            System.out.println("6. Return a book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                displayBooks();
            } else if (choice == 3) {
                searchById();
            } else if (choice == 4) {
                searchByTitle();
            } else if (choice == 5) {
                issueBook();
            } else if (choice == 6) {
                returnBook();
            } else if (choice == 0) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addBook() {
        if (count == books.length) {
            System.out.println("The library is full.");
            return;
        }

        System.out.print("Enter book ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter title: ");
        String title = input.nextLine();

        System.out.print("Enter author: ");
        String author = input.nextLine();

        books[count] = new Book(id, title, author);
        count++;

        System.out.println("Book added.");
    }

    static void displayBooks() {
        if (count == 0) {
            System.out.println("No books have been added.");
        }

        for (int i = 0; i < count; i++) {
            books[i].display();
        }
    }

    static void searchById() {
        System.out.print("Enter book ID: ");
        int id = input.nextInt();
        input.nextLine();

        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else {
            book.display();
        }
    }

    static void searchByTitle() {
        System.out.print("Enter title or part of the title: ");
        String title = input.nextLine();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {
                books[i].display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = input.nextInt();
        input.nextLine();

        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("That book is already issued.");
        } else {
            book.issue();
            System.out.println("Book issued.");
        }
    }

    static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = input.nextInt();

        Book book = findBook(id);

        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("That book was not issued.");
        } else {
            System.out.print("How many days late? Enter 0 if on time: ");
            int lateDays = input.nextInt();

            int fine = lateDays * FINE_PER_DAY;
            book.returnBook();

            System.out.println("Book returned.");
            System.out.println("Fine: Rs. " + fine);
        }

        input.nextLine();
    }

    static Book findBook(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].getId() == id) {
                return books[i];
            }
        }

        return null;
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        available = true;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    boolean isAvailable() {
        return available;
    }

    void issue() {
        available = false;
    }

    void returnBook() {
        available = true;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (available ? "Available" : "Issued"));
        System.out.println("--------------------");
    }
}