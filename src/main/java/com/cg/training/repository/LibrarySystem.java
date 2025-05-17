
package com.cg.training.repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import com.cg.training.model.*;
import com.cg.training.exceptions.InvalidLoanException;
import com.cg.training.service.Book;
import com.cg.training.service.Loan;

public class LibrarySystem {
    // Our treasured collection of members, librarians, books, and who has borrowed what.
    List<Member> members = new ArrayList<>();
    List<Librarian> librarians = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    List<Loan> loans = new ArrayList<>();

    // Welcoming a new member to our library family.
    public void registerMember(Member member) {
        // Let's just double-check if we already have someone with this name.
        boolean exists = members.stream()
                .anyMatch(m -> m.getName().trim().equalsIgnoreCase(member.getName().trim()));

        if (exists) {
            System.out.println("Member: " + member.getName() + " already exists");
        } else {
            members.add(member);
            System.out.println(member.getName() + " added as a member.");
        }
    }

    // Adding a new friendly face to our librarian team.
    public void registerLibrarian(Librarian librarian) {
        // We don't want any duplicates on our wonderful team.
        boolean exists = librarians.stream()
                .anyMatch(l -> l.getName().trim().equalsIgnoreCase(librarian.getName().trim()));

        if (exists) {
            System.out.println(librarian.getName() + " already exists");
        } else {
            librarians.add(librarian);
            System.out.println(librarian.getName() +" added as a librarian");
        }
    }

    // When we get a new book, or more copies of an old favorite.
    public void addBook(Book newBook) {
        books.stream()
                .filter(b -> b.getTitle().trim().equalsIgnoreCase(newBook.getTitle().trim()) &&
                           b.getAuthor().trim().equalsIgnoreCase(newBook.getAuthor().trim()))
                .findFirst()
                .ifPresentOrElse(existingBook -> {
                    // Oh, we already have this one! Just adding another copy.
                    existingBook.increaseCount();
                    System.out.println("Just added another copy of " + existingBook.getTitle());
                }, () -> {
                    // A brand new book for our shelves!
                    books.add(newBook);
                    System.out.println("Wonderful! We've added '" + newBook.getTitle() + "' to our collection.");
                });
    }

    // Let's take a peek at all the wonderful stories we have.
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Our shelves are looking a little bare at the moment. No books to show yet.");
        } else {
            System.out.println("Here are all the books we currently have:");
            books.forEach(System.out::println);
        }
    }

    // When a member wants to borrow a book and embark on a new adventure.
    public Loan issueBook(Member member) throws InvalidLoanException {
        Scanner sc = new Scanner(System.in);
        showAllBooks(); // Let's show them what treasures we have.
        System.out.print("Which book would you like to borrow? Please enter the title: ");
        String bookTitle = sc.nextLine().trim();

        // Let's see if we have that title.
        List<Book> matchingBooks = books.stream()
                .filter(book -> book.getTitle().trim().equalsIgnoreCase(bookTitle.trim()))
                .collect(Collectors.toList());

        if (matchingBooks.isEmpty()) {
            throw new InvalidLoanException("Hmm, I can't seem to find a book with the title '" + bookTitle + "'. Are you sure that's the correct title?");
        }

        // If there's only one, easy peasy!
        if (matchingBooks.size() == 1) {
            Book book = matchingBooks.get(0);
            return issueBookToMember(book, member);
        }

        // Oh, it seems we have a few books with the same title! Let's help them choose the right one.
        System.out.println("Ah, we have a few books titled '" + bookTitle + "'. Could you tell me the author?");
        for (int i = 0; i < matchingBooks.size(); i++) {
            Book b = matchingBooks.get(i);
            System.out.println((i + 1) + ". By " + b.getAuthor() + " (Available: " + (b.isAvailable() ? "Yes" : "No") + ")");
        }

        System.out.print("Please enter the number corresponding to the author: ");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidLoanException("That doesn't look like a valid number. Please try again.");
        }

        if (choice < 1 || choice > matchingBooks.size()) {
            throw new InvalidLoanException("Oops, that wasn't one of the options. Please select a number from the list.");
        }

        Book selectedBook = matchingBooks.get(choice - 1);
        return issueBookToMember(selectedBook, member);
    }

    // The actual process of handing over the book to the eager member.
    private Loan issueBookToMember(Book book, Member member) throws InvalidLoanException {
        if (!book.isAvailable()) {
            throw new InvalidLoanException("Oh dear, it seems '" + book.getTitle() + "' by " + book.getAuthor() + " is currently borrowed by someone else. Please check back later.");
        }

        book.decreaseCount(); // One less copy on the shelf for now.
        Loan loan = new Loan(member, book); // Keeping track of who borrowed what.
        loans.add(loan);
        System.out.println("Great! '" + book.getTitle() + "' by " + book.getAuthor() + " has been issued to " + member.getName() + ". Happy reading!");
        return loan;
    }

    // Making sure we remember which books are out on loan, even if the system restarts.
    public void saveLoans() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("loans.ser"))) {
            out.writeObject(loans);
            System.out.println("We've safely recorded all the current loans.");
        } catch (IOException e) {
            System.err.println("Oh no! Something went wrong while trying to save the loan information: " + e.getMessage());
        }
    }

    // Tidying up our book collection details into a file.
    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getCount());
                writer.newLine();
            }
            System.out.println("All our book details have been saved.");
        } catch (IOException e) {
            System.err.println("Uh oh! We couldn't save the book information to the file: " + e.getMessage());
        }
    }


    // When we start up, let's remember all the books we had.
    public void loadBooksFromFile() {
        books.clear(); // Start fresh
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int count = Integer.parseInt(parts[2].trim());

                    Book book = new Book(title, author);
                    // Set count appropriately
                    for (int i = 1; i < count; i++) {
                        book.increaseCount();
                    }
                    books.add(book);
                }
            }
            System.out.println("Welcome back! We've loaded all the book information.");
        } catch (IOException e) {
            System.out.println("Looks like we don't have a book record file yet. Starting fresh with our collection.");
        } catch (NumberFormatException e) {
            System.err.println("Error reading book count. Please check the file format.");
        }
    }


    // Just in case anyone wants to know who our lovely members are.
    public List<Member> getMembers() {
        return members;
    }

    // And for anyone curious about our book collection.
    public List<Book> getBooks() {
        return books;
    }
}