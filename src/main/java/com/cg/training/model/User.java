package com.cg.training.model;

/*
 * An abstract base class representing a generic user in the library system.
 * Provides common fields and behavior for all user types such as Member, Librarian, and Admin.
 */
abstract class User
{

    /*
     * A static integer counter used to generate unique user IDs.
     */
    private static int counter = 1;

    /*
     * Unique identifier for the user.
     */
    protected String id;

    /*
     * Name of the user.
     */
    protected String name;

    /*
     * Returns the unique ID of the user.
     */
    public String getId() {
        return id;
    }

    /*
     * Sets the unique ID of the user.
     * Parameter:id(String) : Initializes this id with its object
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /*
     * Returns the name of the user.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the user.
     * Only allows letters, periods, and spaces. Throws an exception for invalid input.
     * Parameter:name(String) :- The name to set for the user.
     * Throws IllegalArgumentException If the name contains invalid characters.
     */
    public void setName(String name)
    {
        if (name == null || !name.matches("[a-zA-Z. ]+"))
            throw new IllegalArgumentException("Name must contain only letters and spaces. No special characters");
        this.name = name;
    }

    /**
     * Constructs a User using a UserDTO object.
     * Automatically assigns a unique ID and validates the name.
     *
     * Parameter:dto(UserDTO) :- The UserDTO containing user input data.
     */
    public User(UserDTO dto)
    {
        setId(String.valueOf(counter++));
        setName(dto.getName());
    }

    /*
     * Abstract method to display the user's profile.
     * Must be implemented by all subclasses.
     */
    abstract void showProfile();
}