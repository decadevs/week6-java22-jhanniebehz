package library.service.impl;

import library.model.Book;
import library.model.Borrower;
import library.model.BorrowerComparator;
import library.model.Library;
import library.service.LibraryService;

import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class LibraryServiceImplementation implements LibraryService {

    PriorityQueue<Borrower> borrowerOnQueue;
    Queue<Borrower> borrowerQueue;


    public LibraryServiceImplementation() {
        borrowerOnQueue = new PriorityQueue<>(new BorrowerComparator());
        borrowerQueue = new LinkedList<>();
    }

    @Override
    public void addBorrowerToQueue(Borrower borrower) {
        try {
            borrowerOnQueue.add(borrower);
        } catch (Exception e) {
            System.out.println("Error adding borrower to the priority queue: " + e.getMessage());
        }
    }

    @Override
    public String borrowBooks(Library library, Book book) {
        return Optional.ofNullable(borrowerOnQueue.poll())
                .map(borrower -> {
                    if (book.getBookQuantity() > 0) {
                        book.setBookQuantity(book.getBookQuantity() - 1);
                        return borrower.getFullName() + " has borrowed " + book.getTitle();
                    } else {
                        return "Book Taken";
                    }
                })
                .orElse("No Borrower is on the queue");
    }

    @Override
    public void addToQueueOnFirstCome(Borrower borrower) {
        try {
            borrowerQueue.add(borrower);
        } catch (Exception e) {
            System.out.println("Error adding borrower to the first-come-first-serve queue: " + e.getMessage());
        }
    }


    @Override
    public String getBookOnFirstCome(Book book) {
        return Optional.ofNullable(borrowerQueue.poll())
            .map(borrower -> {
        if (book.getBookQuantity() > 0) {
            book.setBookQuantity(book.getBookQuantity() - 1);
            return borrower.getFullName() + " has borrowed " + book.getTitle();
        } else {
            return "Book Taken";
        }
    })
            .orElse("No Borrower is on the queue");
}


//    public String borrowBooks(Library library, Book book) {
//        if (borrowerOnQueue.isEmpty()) {
//            return "No borrower is on the queue";
//        } else if (book.getBookQuantity() == 0) {
//            return "Book Taken";
//        }
//
//        Borrower borrower = borrowerOnQueue.poll();
//        book.setBookQuantity(book.getBookQuantity() - 1);
//        assert borrower != null;
//        return borrower.getFullName() + " has borrowed " + book.getTitle();
//    }
}

//        if (borrowerQueue.isEmpty()) {
//            return "No one is on Queue";
//        } else if (bookTitle.getBookQuantity() == 0) {
//            return "Book taken";
//        }
//
//        Borrower borrower = borrowerQueue.poll();
//        bookTitle.setBookQuantity(bookTitle.getBookQuantity() -1);
//
//        assert borrower != null;
//        return borrower.getFullName() + " has borrowed " + bookTitle.getTitle();
//    }
