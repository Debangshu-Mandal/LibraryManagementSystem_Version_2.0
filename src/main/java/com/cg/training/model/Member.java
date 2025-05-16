package com.cg.training.model;


/*
 * Represents a library member in this system.
 * A member is a type of user who can borrow books and view their profile.
 */
public class Member extends User
{
    /*
     * Constructs a Member with the given UserDTO.
     * Initializes the member's ID and name using the parent {@link User} constructor.
     * Parameter:name(UserDTO) :- object containing the member's name.
     */
    public Member(UserDTO name)
    {
        super(name);
    }

    /*
     * Displays the profile information of this member, including member ID and name.
     */
    @Override
    void showProfile()
    {
        System.out.println("Member id: " + super.id + ", Name: " + super.name);
    }
}
