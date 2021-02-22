import java.util.Iterator;
import java.util.Scanner;
/**
 *
 */
public class BookLibraryApp {
    private BookRegistry bookRegistry;
    /**
     * Creates an instance of a book registry and fills it with books
     */
    private void init() {
        this.bookRegistry = new BookRegistry();
        bookRegistry.fillWithBooks();
    }

    /**
     * Print menu choices to the terminal and uses Scanner to get input from user
     */
    private int showMenu() {
        System.out.println("Library application v123.123");
        System.out.println("1. add a book");
        System.out.println("2. remove a book");
        System.out.println("3. list all books");
        System.out.println("4. find a book");
        System.out.println("5. quit the application");
        System.out.println("");

        Scanner input = new Scanner(System.in);

        int userChoice = 0;

        if(input.hasNextInt()) {
            userChoice = input.nextInt();
            if(userChoice <=0 || userChoice >=6) {
                System.out.println("Not a valid command.");
                System.out.println("");
            }
        } else {
            System.out.println("Not a valid command.");
            System.out.println("");
        }
        
        return userChoice;
    }

    /**
     * 
     */
    public void startApp() {
        init();
        boolean finished = false;
        
        while(finished != true) {
            int menuChoice = this.showMenu();
            if(menuChoice == 1) {
                addBook();
            }
            else if(menuChoice == 2) {
                removeBook();
            }
            else if(menuChoice == 3) {
                listAllBooks();
            }
            else if(menuChoice == 4) {
                findBook();
            }
            else if(menuChoice == 5) {
                System.out.println("Thank you for using this app");
                finished = true;
            }
        }
    }

    /**
     * Uses Scanner to get input about book from user, and add given book to the bookRegistry.
     */
    private void addBook() {
        System.out.println("Add book to the registry: ");       
        
        Scanner input = new Scanner(System.in);

        String title = promptTitleFromUser();
        String author = promptAuthorFromUser();
        int yearReleased = promptYearOfReleaseFromUser();
        int numberOfPages = promptNumberOfPagesFromUser();
        String barcode = promptBarcodeFromUser();
        boolean available = promptAvailabilityFromUser();

        Book book = new Book(title, author, yearReleased, numberOfPages, barcode, available);
        this.bookRegistry.addBook( book );
        
        System.out.println("The book was succesfully added to the library");
        System.out.println("");
    }

    /**
     * Uses Scanner to get input about a given book from a user.
     */
    private void removeBook() {
        Scanner input = new Scanner(System.in);

        String bookToRemove;
        boolean bookRemoved = false;

        System.out.println("Please enter either the books title or barcode to remove it: ");
        bookToRemove = input.nextLine();

        Iterator<Book> iterator = this.bookRegistry.iterator();
        while(iterator.hasNext() && bookRemoved == false) {
            Book book = iterator.next();
            if(bookRegistry.findBookByTitle(bookToRemove) == book || bookRegistry.findBookByBarcode(bookToRemove) == book) {
                this.bookRegistry.removeBook( book );
                bookRemoved = true;
                System.out.println("The book was successfully removed from the library");
                System.out.println("");
            }
        }
        
        if(bookRemoved == false) {
            System.out.println("Sorry, we could not find the book.");
            System.out.println("");
        }
    }

    /**
     * Lists all books in the book registry to the terminal.
     */
    private void listAllBooks() {
        Iterator<Book> iterator = bookRegistry.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("");
        }
    }

    /**
     * Uses Scanner to get input about a given book and
     * prints details about the given book to the terminal.
     */
    private void findBook() {
        Scanner input = new Scanner(System.in);

        String bookInfo;
        boolean bookFound = false;

        System.out.println("Please enter either the books title, author(s) or barcode");
        bookInfo = input.nextLine();

        Iterator<Book> iterator = this.bookRegistry.iterator();
        while(iterator.hasNext() && bookFound == false) {
            Book book = iterator.next();
            if(bookRegistry.findBookByBarcode(bookInfo) == book || bookRegistry.findBookByTitle(bookInfo) == book) {
                bookFound = true;
                printBookDetails(book);
            } else if(bookRegistry.findBooksByAuthor(bookInfo).contains(book)) {
                if(bookRegistry.findBooksByAuthor(book.getAuthor()).size() > 1) {
                    System.out.println("We found multiple books from this author!");
                    System.out.println("");
                }
                for(Book books : bookRegistry.findBooksByAuthor(book.getAuthor())) {
                    bookFound = true;
                    printBookDetails(books);
                }    
            }
        }

        if(bookFound == false) {
            System.out.println("Sorry, we could not find any books matching the provided info");
            System.out.println("");
        }
    }

    /**
     * Prints information about a book to the terminal.
     * @param book The book who's information is printed.
     */
    private void printBookDetails(Book book) {
        System.out.println("The title of this book is " + book.getTitle());
        System.out.println("The author of this book is " + book.getAuthor());
        System.out.println("This book was released in " + book.getReleaseYear());
        System.out.println("This book has " + book.getNumberOfPages() + " pages");
        System.out.println("EAN-13 = " + book.getBarcode());
        if(book.isBookAvailable() == true){
            System.out.println("This book is currently available in the library");
            System.out.println("");
        } else {
            System.out.println("This book is currently not available in the library");
            System.out.println("");
        }
    }
    
    /**
     * Asks the user to enter the title of the book to be added to library.
     * @return title the title of the Book that is to be added
     */
    private String promptTitleFromUser() {
        String title;
        Scanner input = new Scanner(System.in);        
        
        System.out.println("Please enter the books title: ");
        title = input.nextLine();
        
        return title;
    }
    
    /**
     * Asks the user to enter the author of the book to be added to the library.
     * @return author the author of the book that is to be added
     */
    private String promptAuthorFromUser() {
        String author;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter the books author(s): ");
        author = input.nextLine();        
        
        return author;
    }
    
    /**
     * Asks the user to enter the year of release of the book to be added to the library.
     * @return yearReleased the year of release of the book that is to be added
     */
    private int promptYearOfReleaseFromUser() {
        int yearReleased = 0;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter the books year of release");
        while(!input.hasNextInt()) {
            System.out.println("Sorry, this is not a valid value, type as a whole number (0, 1, 2 ..)");
            input.nextLine();
        }
        
        yearReleased = input.nextInt();
        
        return yearReleased;
    }
    
    /**
     * Asks the user to enter the number of pages in the book to be added to the library.
     * @return numberOfPages number of pages in to book that is to be added
     */
    private int promptNumberOfPagesFromUser() {
        int numberOfPages = 0;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter the number of pages in the book");
        while(!input.hasNextInt()) {
            System.out.println("Sorry, this is not a valid value, type as a whole number (0, 1, 2 ..)");
            input.nextLine();
        }
        
        numberOfPages = input.nextInt();
        
        return numberOfPages;
    }
    
    /**
     * Asks the user to enter the barcode of the book to be added to the library.
     * @return barcode the barcode of the book that is to be added
     */
    private String promptBarcodeFromUser() {
        String barcode;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter the books barcode: ");
        barcode = input.nextLine();
        
        return barcode;
    }
    
    /**
     * Asks the user to enter whether or not the book is available in the library.
     * @return available the status of whether the book is available(true) or not (false)
     */
    private boolean promptAvailabilityFromUser() {
        boolean available = false;
        Scanner input = new Scanner(System.in);
        String answer = null;
        
        System.out.println("Is the book currently available at the library? (yes/no)");
        while(!input.hasNext("yes") && !input.hasNext("no")){
            System.out.println("This is not an valid answer, please answer yes or no in lowercase");
            answer = input.nextLine();
        }
        
        if(input.next().equals("yes")) {
            available = true;
        } else {
            available = false;
        }

        return available;
    }
    
}