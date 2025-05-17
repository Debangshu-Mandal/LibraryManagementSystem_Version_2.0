package com.cg.training.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cg.training.model.Member;
import com.cg.training.model.UserDTO;

public class LoanTest {
    private Member member;
    private Book book;
    
    @Before
    public void setUp() {
        member = new Member(new UserDTO("Test Member"));
        book = new Book("Test Book", "Test Author");
    }
    
    @Test
    public void testLoanCreation() {
        Loan loan = new Loan(member, book);
        assertEquals("Borrowed", loan.getStatus());
    }
    
    @Test
    public void testCompleteLoan() {
        Loan loan = new Loan(member, book);
        loan.completeLoan();
        assertEquals("Returned", loan.getStatus());
        assertTrue(book.isAvailable());
    }
    
    @Test
    public void testLoanDetails() {
        Loan loan = new Loan(member, book);
        String details = loan.loanDetails();
        assertTrue(details.contains("Test Member"));
        assertTrue(details.contains("Test Book"));
    }
}
