package Soal4;
import java.util.ArrayList;
import java.util.Scanner;

public class Soal4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Media> catalog = new ArrayList<>();
        System.out.println("Welcome to Bob's Physical Media Store!");
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add a new DVD");
            System.out.println("2. Add a new Magazine");
            System.out.println("3. Add a new Vinyl");
            System.out.println("4. Print Catalog & Exit");
            System.out.print("Choose an option (1-4): ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("4")) {
                break;
            }
            try {
                if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Release Year: ");
                    int releaseYear = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    if (choice.equals("1")) {
                        System.out.print("Enter Runtime (minutes): ");
                        double runtime = Double.parseDouble(scanner.nextLine());
                        catalog.add(new Dvd(title, releaseYear, price, runtime));
                        System.out.println("DVD successfully added to catalog!");
                    } else if (choice.equals("2")) {
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Number of Pages: ");
                        int numPages = Integer.parseInt(scanner.nextLine());
                        catalog.add(new Magazine(title, releaseYear, price, author, numPages));
                        System.out.println("Magazine successfully added to catalog!");
                    } else if (choice.equals("3")) {
                        System.out.print("Enter Size (inches): ");
                        int size = Integer.parseInt(scanner.nextLine());
                        catalog.add(new Vinyl(title, releaseYear, price, size));
                        System.out.println("Vinyl successfully added to catalog!");
                    }
                } else {
                    System.out.println("ERROR: Invalid option. Please enter 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid number format. Please enter numerical values where expected.");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Item was NOT added. Please try again.");
            }
        }
        System.out.println("\n====================================");
        System.out.println("Item(s) in Catalog:\n");
        if (catalog.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            for (Media item : catalog) {
                item.getDescription();
            }
        }
        scanner.close();
    }
}
class Media {
    protected String title;
    protected int releaseYear;
    protected double price;
    public Media(String title, int releaseYear, double price) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be strictly under 255 characters.");
        }
        if (releaseYear <= 1800 || releaseYear >= 2026) {
            throw new IllegalArgumentException("Release year must be between 1801 and 2025.");
        }
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
    }
    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        if (price == (long) price) {
            System.out.println("Price: " + (long) price);
        } else {
            System.out.println("Price: " + price);
        }
    }
}
// Subclass for DVDs
class Dvd extends Media {
    private double runtime;
    public Dvd(String title, int releaseYear, double price, double runtime) {
        super(title, releaseYear, price);
        if (runtime >= 720) {
            throw new IllegalArgumentException("Runtime must be strictly less than 720 minutes.");
        }
        this.runtime = runtime;
    }
    @Override
    public void getDescription() {
        super.getDescription();
        if (runtime == (long) runtime) {
            System.out.println("Runtime: " + (long) runtime + " minutes");
        } else {
            System.out.println("Runtime: " + runtime + " minutes");
        }
        System.out.println();
    }
}
// Subclass for Magazines
class Magazine extends Media {
    private String author;
    private int numPages;
    public Magazine(String title, int releaseYear, double price, String author, int numPages) {
        super(title, releaseYear, price);
        if (author.length() >= 50) {
            throw new IllegalArgumentException("Author must be strictly under 50 characters.");
        }
        this.author = author;
        this.numPages = numPages;
    }
    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Author: " + author);
        System.out.println("Number of Pages: " + numPages);
        System.out.println();
    }
}
// Subclass for Vinyl Records
class Vinyl extends Media {
    private int size;
    public Vinyl(String title, int releaseYear, double price, int size) {
        super(title, releaseYear, price);
        if (size > 12) {
            throw new IllegalArgumentException("Size must be <= 12 inches.");
        }
        this.size = size;
    }
    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Size in inches: " + size);
        System.out.println();
    }
}