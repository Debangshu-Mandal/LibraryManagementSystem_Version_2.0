package com.cg.training.model;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.cg.training.annotations.RoleCheck;
import com.cg.training.exceptions.InvalidLoanException;
import com.cg.training.service.Book;

/*
 * Represents an Admin in the library system.
 * Admin can remove books
 * This class is annotated with @RoleCheck to enforce role-based access.
 */
@RoleCheck(role = "Admin")
public class Admin extends User {
	/*
     * Constructs an Admin with the specified name.
     */
    public Admin(UserDTO name) {
        super(name);
    }
    /**
     * Removes a book with the specified title from the provided list of books.
     * Checks for admin role authorization via @RoleCheck annotation.
     * books: List of Books from which a specific book gets removed
     * title: The title of the removed book
     */
    public void removeBook(List<Book> books, String title) throws Exception
    {
    	 try
    	 {
             RoleCheck annotation = this.getClass().getAnnotation(RoleCheck.class);
             if (annotation == null || !annotation.role().equals("Admin"))
             {
                 throw new InvalidLoanException("Unauthorized access: Admin role required");
             }

             Iterator<Book> iterator = books.iterator();
             while (iterator.hasNext())
             {
                 Book book = iterator.next();
                 if (book.getTitle().equalsIgnoreCase(title))
                 {
                     iterator.remove();
                     System.out.println("Book removed successfully: " + title);
                     return;
                 }
             }
             System.out.println("Book not found: " + title);
         }
    	 catch (Exception e)
    	 {
             throw new InvalidLoanException("Error removing book: " + e.getMessage());
         }
    }
    /*
     * Displays the admin's profile including ID and name.
     */
    public void showProfile()
    {
        System.out.println("Admin ID: " + id + ", Name: " + name);
    }
}