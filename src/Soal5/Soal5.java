package Soal5;
import java.util.Scanner;
import java.util.Stack;

class Book {
    protected String title;
    protected String author;
    protected int yearOfPublication;
    public Book(String title, String author, int yearOfPublication) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (author.length() >= 50) {
            throw new IllegalArgumentException("Author must be under 50 characters.");
        }
        if (yearOfPublication <= 1800 || yearOfPublication >= 2026) {
            throw new IllegalArgumentException("Year of publication must be between 1801 and 2025.");
        }
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }
    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year of Publication: " + yearOfPublication);
    }
}
class GeneralBook extends Book {
    private String genre;
    public GeneralBook(String title, String author, int yearOfPublication, String genre) {
        super(title, author, yearOfPublication);
        if (genre.length() > 30) {
            throw new IllegalArgumentException("Genre must be 30 characters or less.");
        }
        this.genre = genre;
    }
    @Override
    public void getInfo() {
        super.getInfo(); 
        System.out.println("Genre: " + genre);
    }
}
class ChildrenBook extends Book {
    private int minAge;
    private boolean hasVisualisation;
    public ChildrenBook(String title, String author, int yearOfPublication, int minAge, boolean hasVisualisation) {
        super(title, author, yearOfPublication);
        if (minAge <= 3 || minAge >= 12) {
            throw new IllegalArgumentException("Minimum age must be between 4 and 11.");
        }
        this.minAge = minAge;
        this.hasVisualisation = hasVisualisation;
    }
    @Override
    public void getInfo() {
        super.getInfo(); 
        System.out.println("Minimum Age: " + minAge);
        System.out.println("Has Visualisation: " + (hasVisualisation ? "Yes" : "No"));
    }
}
public class Soal5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Book> library = new Stack<>();
        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));
        library.push(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999));
        library.push(new GeneralBook("Dune", "Frank Herbert", 1965, "Sci-Fi"));
        System.out.println("Welcome to the Library of Alexandria Restoration Project!");
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View stored books");
            System.out.println("2. Add a new book");
            System.out.println("3. Delete a specific book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("4")) {
                System.out.println("Securing the library. Goodbye!");
                break;
            }
            try {
                if (choice.equals("1")) {
                    if (library.isEmpty()) {
                        System.out.println("\nThe library is currently empty.");
                    } else {
                        System.out.println("\n--- Stored Books ---");
                        for (int i = 0; i < library.size(); i++) {
                            System.out.println("Book Index: [" + i + "]");
                            library.get(i).getInfo();
                            System.out.println();
                        }
                    }
                } else if (choice.equals("2")) {
                    System.out.println("\nSelect Book Type:");
                    System.out.println("1. Book");
                    System.out.println("2. General Book (with genre)");
                    System.out.println("3. Children's Book");
                    System.out.print("Choice: ");
                    String typeChoice = scanner.nextLine().trim();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Year of Publication: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    if (typeChoice.equals("1")) {
                        library.push(new Book(title, author, year));
                        System.out.println("Book added successfully!");
                    } else if (typeChoice.equals("2")) {
                        System.out.print("Enter Genre: ");
                        String genre = scanner.nextLine();
                        library.push(new GeneralBook(title, author, year, genre));
                        System.out.println("General Book added successfully!");
                    } else if (typeChoice.equals("3")) {
                        System.out.print("Enter Minimum Age: ");
                        int minAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Has Visualisation? (true/false): ");
                        if (!scanner.hasNextBoolean()) {
                            throw new IllegalArgumentException("Invalid input for visualisation. Please enter true or false.");
                        }
                        boolean hasVis = Boolean.parseBoolean(scanner.nextLine());
                        library.push(new ChildrenBook(title, author, year, minAge, hasVis));
                        System.out.println("Children's Book added successfully!");
                    } else {
                        System.out.println("Invalid book type selected.");
                    }
                } else if (choice.equals("3")) {
                    if (library.isEmpty()) {
                        System.out.println("\nNo books available to delete.");
                        continue;
                    }
                    System.out.print("\nEnter the Book Index to delete (0 to " + (library.size() - 1) + "): ");
                    int index = Integer.parseInt(scanner.nextLine());

                    if (index >= 0 && index < library.size()) {
                        Book removedBook = library.remove(index);
                        System.out.println("Deleted: " + removedBook.title);
                    } else {
                        System.out.println("Invalid index.");
                    }
                } else {
                    System.out.println("Invalid menu option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter valid numbers where expected.");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Action aborted. Please try again.");
            }
        }
        scanner.close();
    }
}
