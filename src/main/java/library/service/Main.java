package library.service;

import library.enums.Role;
import library.model.Book;
import library.model.Borrower;
import library.model.Library;
import library.service.impl.LibraryServiceImplementation;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryServiceImplementation();

        //ADDING BORROWERS TO QUEUE WITH PRIORITY
        libraryService.addBorrowerToQueue(new Borrower("Janefrances", Role.JUNIOR_STUDENT));
        libraryService.addBorrowerToQueue(new Borrower("Abike Smith", Role.SENIOR_STUDENT));
        libraryService.addBorrowerToQueue(new Borrower("Bisola Ayodele", Role.TEACHER));

        //CREATE A BOOK
        Book book = new Book(1, "Half of A Yellow Sun", "Chimamanda Adiche", 5);
        Book book2 = new Book(11, "Switze Bansi is Dead", "Athol Fugard", 7);
        Library library = new Library(book);

        //ADDING BORROWERS WITHOUT PRIORITY
        libraryService.addToQueueOnFirstCome(new Borrower("John Makins", Role.SENIOR_STUDENT));
        libraryService.addToQueueOnFirstCome(new Borrower("Olutosin Olaleye", Role.TEACHER));
        libraryService.addToQueueOnFirstCome(new Borrower("Segun Osiki", Role.JUNIOR_STUDENT));

        System.out.println("THIS IS WITH PRIORITY");
        System.out.println(libraryService.borrowBooks(library, book));
        System.out.println(libraryService.borrowBooks(library, book));
        System.out.println(libraryService.borrowBooks(library, book));
        System.out.println("**********************************");


        System.out.println("THIS IS ON A FIRST COME FIRST SERVE BASIS");
        System.out.println(libraryService.getBookOnFirstCome(book2));
        System.out.println(libraryService.getBookOnFirstCome(book2));
        System.out.println(libraryService.getBookOnFirstCome(book2));
        System.out.println("***********************************");
    }


}


