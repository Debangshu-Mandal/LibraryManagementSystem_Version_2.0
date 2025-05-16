package com.cg.training.model;

public class UserDTO {
	/*
	 * A Data Transfer Object (DTO) for representing user-related information. It
	 * holds the name of the users for a particular id and uses getter and setter
	 * methods to get and set the name of the user
	 */

	/*
	 * The name of the user.
	 */
	private String name;

	/*
	 * Constructs a UserDTO with the specified name. Parameter:name :- The name of
	 * the user.
	 */
	public UserDTO(String name) {
		setName(name);
	}

	/*
	 * Retrieves the name of the user. Return: The user's name.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Sets the name of the user. Parameter:name :- The name to set for the user.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
