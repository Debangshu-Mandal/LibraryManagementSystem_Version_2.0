package com.cg.training.service;

import java.io.Serializable;

/**
 * Represents a Book in the library system. Implements Serializable to allow
 * saving/loading book objects.
 */
public class Book implements Serializable {
	private String title;
	private String author;
//	private boolean available = true;
	private int count;

	/**
	 * Constructs a new Book with the specified title and author. The initial count
	 * is set to 1.
	 *
	 * @param title  the title of the book
	 * @param author the author of the book
	 */
	public Book(String title, String author) {
		setTitle(title);
		setAuthor(author);
		this.count = 1;
	}

	/**
	 * Returns the number of copies available for this book.
	 *
	 * @return the count of available copies
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Increases the count of copies of this book by one.
	 */
	public void increaseCount() {
		this.count++;
	}

	/**
	 * Decreases the count of copies of this book by one if count is greater than
	 * zero.
	 */
	public void decreaseCount() {
		if (count > 0) {
			this.count--;
		}
	}

	/**
	 * Returns the title of this book.
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		this.title = title;
	}

	/**
	 * Returns the author of this book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author of this book.
	 *
	 * @param author the book author to set
	 */
	public void setAuthor(String author) {
		if (author == null || author.trim().isEmpty()) {
			throw new IllegalArgumentException("Author cannot be empty");
		}
		this.author = author;
	}

	/**
	 * Checks if the book is currently available (i.e., count > 0).
	 *
	 * Returns: true if available, false otherwise
	 */
	public boolean isAvailable() {
		return count > 0;
	}

	/**
	 * Sets the availability status of this book. (Note: availability is actually
	 * determined by count, so this setter might be redundant or for future use.)
	 * Parameter:available(boolean) true to set as available, false otherwise
	 */

//	public boolean getAvilable() {
//		return available;
//	}
//
//	public void setAvailable(boolean available) {
//		this.available = available;
//	}

	/**
	 * Returns a string representation of this book, including title, author,
	 * availability, and count.
	 *
	 * @return string describing the book
	 */
	@Override
	public String toString() {
		return "Title: " + title + ", Author: " + author + ", Available: " + (isAvailable() ? "Yes" : "No")
				+ ", Count: " + count;
	}
}
