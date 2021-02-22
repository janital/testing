import java.util.ArrayList;
import java.util.Iterator;
/**
 * Represents a registry of books in a library.
 *
 * @Janita Lillevik Røyseth
 * @10.09.2020
 */
public class BookRegistry {
    private ArrayList<Book> bookRegistry;

    /**
     * Creates an instance of class BookRegistry.
     */
    public BookRegistry() {
        this.bookRegistry = new ArrayList<>();
    }

    /**
     * Fills the book registry with arbitraty books.
     */
    public void fillWithBooks() {
        this.bookRegistry.add(new Book("Objects First With Java", "David J. Barnes, Micheal Kölling", 2017, 630,"9 781292 159041", true));
        this.bookRegistry.add(new Book("Teknologi og Vitenskap", "Ronny Kjelsberg", 2017, 475, "9 788215 024806", false));
        this.bookRegistry.add(new Book("Mathematical Methods 1", "Pearson", 2020, 466, "9 781839 610004", true));
        this.bookRegistry.add(new Book("Mathematical Methods 2a", "Pearson", 2020, 212, "9 781839 610011", true));
        this.bookRegistry.add(new Book("Rom Stoff Tid", "Arne Auen Grimenes, Per Jerstad, Bjørn Sletbak", 2016, 464, "9 788202 511357", true));
    }

    /**
     * Adds a book to the book registry.
     * @param book the book to be added in the book registry.
     */
    public boolean addBook(Book book) {
        boolean bookAdded = false;
        if(book == null) {
            bookAdded = false;
        } else {
            this.bookRegistry.add( book );
            bookAdded = true;
        }
        return bookAdded;
    }
    
    /**
     * Removes a book from the book registry
     * @param bookTitle the book to be removed from the book registry.
     */
    public void removeBook(Book book) {
        this.bookRegistry.remove( book );
    }

    /**
     * Returs the book matching the title provided.
     * If no book is found <code>null</code> is returned.
     * 
     * @param title The title of the book to search for.
     * @return The book matching the provided title. If no match <code>null</code> is returned
     */
    public Book findBookByTitle(String title) {
        Book bookFound = null;
        boolean searching = true;
        Iterator<Book> iterator = this.bookRegistry.iterator();
        while (searching && iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equals( title )) {
                bookFound = book;
                searching = false;
            }
        }
        return bookFound;
    }

    /**
     * Returns the book matching the author provided.
     * If no book is found <code>null</code> is returned.
     * 
     * @param author The author of the to search for.
     * @return The book matching the provided author. If no match <code>null</code> is returned
     */    
    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> booksFound = new ArrayList<>();

        Iterator<Book> iterator = this.bookRegistry.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getAuthor().equals( author )) {
                booksFound.add( book );
            }
        }
        return booksFound;
    }
    
    /**
     * Returns the book matching the EAN-13 code.
     * If no book is found <code>null</code> is returned.
     * 
     * @param barcode The EAN-13 code to search for.
     * @return The book matching the provided EAN-13 code. If no match <code>null</code> is returned
     */
    public Book findBookByBarcode(String barcode) {
        Book bookFound = null;
        boolean searching = true;
        Iterator<Book> iterator = this.bookRegistry.iterator();
        while(iterator.hasNext() && searching) {
            Book book = iterator.next();
            if (book.getBarcode().equals(barcode)) {
                searching = false;
                bookFound = book;
            }
        }
        
        return bookFound;
    }

    public int numberOfBooks() {
        int numberOfBooks = bookRegistry.size();
        
        return numberOfBooks;
    }
    
    /**
     * Returns the iterator for the bookRegistry ArrayList
     * @return The iterator for the bookRegistry ArrayList
     */
    public Iterator<Book> iterator() {
        return this.bookRegistry.iterator();
    }
}
