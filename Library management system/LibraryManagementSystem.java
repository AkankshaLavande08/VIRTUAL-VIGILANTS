import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class LibraryManagementSystem {
    private Map<String, Book> books;
    private Map<String, User> users;

    public LibraryManagementSystem() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        books.put(isbn, book);
        System.out.println("Book added successfully.");
    }

    public void removeBook(String isbn) {
        if (books.containsKey(isbn)) {
            books.remove(isbn);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    public void displayBooks() {
        System.out.println("\nBook Catalog:");
        for (Book book : books.values()) {
            System.out.println("ISBN: " + book.getISBN() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public void addUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        System.out.println("User added successfully.");
    }

    public void removeUser(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    public void displayUsers() {
        System.out.println("\nUser List:");
        for (User user : users.values()) {
            System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getName());
        }
    }

    public void checkoutBook(String userId, String isbn) {
        if (users.containsKey(userId) && books.containsKey(isbn)) {
            Book book = books.get(isbn);
            if (book.isAvailable()) {
                book.setAvailable(false);
                users.get(userId).borrowBook(book);
                System.out.println("Book checked out successfully.");
            } else {
                System.out.println("Book with ISBN " + isbn + " is not available for checkout.");
            }
        } else {
            System.out.println("User or book not found.");
        }
    }

    public void returnBook(String userId, String isbn) {
        if (users.containsKey(userId) && books.containsKey(isbn)) {
            Book book = books.get(isbn);
            if (!book.isAvailable()) {
                book.setAvailable(true);
                users.get(userId).returnBook(book);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book with ISBN " + isbn + " is already available.");
            }
        } else {
            System.out.println("User or book not found.");
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display Books");
            System.out.println("4. Add User");
            System.out.println("5. Remove User");
            System.out.println("6. Display Users");
            System.out.println("7. Checkout Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(isbn, title, author);
                    break;
                case 2:
                    System.out.print("Enter ISBN of book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 3:
                    library.displayBooks();
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    library.addUser(userId, name);
                    break;
                case 5:
                    System.out.print("Enter User ID to remove: ");
                    String removeUserId = scanner.nextLine();
                    library.removeUser(removeUserId);
                    break;
                case 6:
                    library.displayUsers();
                    break;
                case 7:
                    System.out.print("Enter User ID: ");
                    String checkoutUserId = scanner.nextLine();
                    System.out.print("Enter ISBN of book to checkout: ");
                    String checkoutIsbn = scanner.nextLine();
                    library.checkoutBook(checkoutUserId, checkoutIsbn);
                    break;
                case 8:
                    System.out.print("Enter User ID: ");
                    String returnUserId = scanner.nextLine();
                    System.out.print("Enter ISBN of book to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnUserId, returnIsbn);
                    break;
                case 9:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
