package library.service.impl;

import library.enums.Role;
import library.model.Book;
import library.model.Borrower;
import library.model.Library;
import library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplementationTest {

    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        libraryService = new LibraryServiceImplementation();
    }

    @Test
    public void testPriorityBorrowing() {
        Book book = new Book(1, "Half of A Yellow Sun", "Chinua Achebe", 2);
        Library library = new Library(book);

        // Add borrowers with different priorities
        libraryService.addBorrowerToQueue(new Borrower("Janefrances", Role.JUNIOR_STUDENT));
        libraryService.addBorrowerToQueue(new Borrower("Abike Smith", Role.TEACHER));
        libraryService.addBorrowerToQueue(new Borrower("Bisola Ayodele", Role.SENIOR_STUDENT));

        // First borrow should be the teacher
        String result1 = libraryService.borrowBooks(library, book);
        assertEquals("Abike Smith has borrowed Half of A Yellow Sun", result1);
        assertEquals(1, book.getBookQuantity());

        // Second borrow should be the senior student
        String result2 = libraryService.borrowBooks(library, book);
        assertEquals("Bisola Ayodele has borrowed Half of A Yellow Sun", result2);
        assertEquals(0, book.getBookQuantity());

        // Third borrow should show book is taken
        String result3 = libraryService.borrowBooks(library, book);
        assertEquals("Book Taken", result3);
    }

    @Test
    public void testFirstComeFirstServeBorrowing() {
        Book book = new Book(2, "Switze Bansi is Dead", "ROllins Achebe", 1);

        // Add borrowers to the queue
        libraryService.addToQueueOnFirstCome(new Borrower("John Makins", Role.SENIOR_STUDENT));
        libraryService.addToQueueOnFirstCome(new Borrower("Olutosin Olaleye", Role.TEACHER));

        // First borrow should be the first added
        String result1 = libraryService.getBookOnFirstCome(book);
        assertEquals("John Makins has borrowed Switze Bansi is Dead", result1);
        assertEquals(0, book.getBookQuantity());

        // Second borrow should show book is taken
        String result2 = libraryService.getBookOnFirstCome(book);
        assertEquals("Book Taken", result2);
    }

    @Test
    public void testNoBorrowersInQueue() {
        Book book = new Book(3, "Test Book", "Test Author", 1);

        // No borrowers in the queue
        String result1 = libraryService.getBookOnFirstCome(book);
        assertEquals("No Borrower is on the queue", result1);
    }



}

