package com.cg.training.service;

import java.io.Serializable;

import com.cg.training.model.Member;

/*
 * Represents a loan record of a book borrowed by a library member.
 * Implements Serializable to support serialization of loan data.
 */
public class Loan implements Serializable {
	private Member member;
	private Book book;
	private String status;

	/*
	 * Creates a new Loan instance linking a member to a borrowed book. Sets the
	 * loan status to "Borrowed".
	 * Parameter:member(Member) :- the member borrowing the book
	 * Parameter:book(Book) :- the book being borrowed
	 */
	public Loan(Member member, Book book) {
		this.member = member;
		this.book = book;
		this.status = "Borrowed";
//		this.book.setAvailable(false);
	}

	/*
	 * Marks this loan as completed by setting the status to "Returned".
	 */
	public void completeLoan() {
		this.status = "Returned";
//		this.book.setAvailable(true);
	}

	/*
	 * Returns the current status of the loan.
	 */
	public String getStatus() {
		return status;
	}

	/*
	 * Returns a formatted string describing the details of this loan, including the
	 * member name, book title, and current loan status.
	 */
	public String loanDetails() {
		return "Loan -> Member: " + member.getName() + ", Book: " + book.getTitle() + ", Status: " + status;
	}
}