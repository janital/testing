/**
* Represents a given book
*/
public class Book {

    private final String title;
    private final String author;
    private final int yearReleased;
    private final int numberOfPages;
    private final String barcode;
    private boolean available;
    
    /**
     * Creates an instance of class Book
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param yearReleased The release year of the book
     * @param numberOfPages Number of pages in the book
     * @param barcode The EAN-13 code on the book
     * @param available Information if the book is available
     */
    public Book(String title, String author, int yearReleased, int numberOfPages, String barcode, boolean available) {
        this.title = title;
        this.author = author;
        this.yearReleased = yearReleased;
        this.numberOfPages = numberOfPages;
        this.barcode = barcode;
        this.available = available;
    }

    /**
     * Returns the book title
     * @return the title as String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the book author
     * @return the author as String
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the books year of release
     * @return the year of release as int
     */
    public int getReleaseYear() {
        return yearReleased;
    }

    /**
     * Returns the number of pages in the book
     * @return the number of pages in the book as int
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }
    
    /**
     * Returns the barcode of a book
     * @return the barcode of a book as String
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns if the book is available in library.
     * @return if the book is available in the library as boolean
     */
    public boolean isBookAvailable() {
        return available;
    }

    /**
     * Sets the status of the book as available = false
     */
    public void borrowBook() {
        available = false;
    }

    /**
     * Sets the status of the book as available = true
     */
    public void returnBook() {
        available = true;
    }

    /**
     * Prints information about a given book in the library
     */
    //public void printBookDetails() {
    //    System.out.println("The title of this book is " + title);
    //    System.out.println("The author of this book is " + author);
    //    System.out.println("This book was released in " + yearReleased);
    //    System.out.println("This book has " + numberOfPages + " pages");
    //    System.out.println("EAN-13 = " + barcode);
    //    if(available == true){
    //        System.out.println("This book is currently available in the library");
    //    }
    //    else {
    //        System.out.println("This book is currently not available in the library");
    //    }
    //}

}
