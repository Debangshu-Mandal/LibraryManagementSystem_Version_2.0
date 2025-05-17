package com.cg.training.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class BookTest {
	@Test
	public void testBookCreation() {
		Book book = new Book("Effective Java", "Joshua Bloch");
		assertEquals("Effective Java", book.getTitle());
		assertEquals("Joshua Bloch", book.getAuthor());
		assertTrue(book.isAvailable());
	}

	@Test
	public void testEmptyTitle() {
		assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				new Book("", "Author");
			}
		});
	}

	@Test
	public void testEmptyAuthor() {
		assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				new Book("Title", "");
			}
		});
	}

	@Test
	public void testAvailability() {
		Book book = new Book("Test", "Author");
		assertTrue(book.isAvailable());
//		book.setAvailable(false);
	}
}
