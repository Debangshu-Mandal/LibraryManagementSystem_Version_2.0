package com.cg.training.model;

/*
 * Represents a Librarian in this library system.
 * A Librarian has a duty status and can display their profile.
 */
public class Librarian extends User
{
	/*
     * Indicates if this librarian is currently on duty.
     */
	private boolean isOnDuty=true;
	
	public Librarian(UserDTO name)
	{
		super(name);
	}
	/*
	 * Displays the profile of this librarian 
	 */
	@Override
	void showProfile()
	{
		 System.out.println("Librarian Name: " + super.name + ", Availabity: " + isOnDuty());
	}
	/*
	 * Returns a true of false value 
	 */
	public boolean isOnDuty()
	{
		return isOnDuty;
	}
	/*
	 * Sets the status of the librarian
	 */
	public void setOnDuty(boolean status)
	{
		this.isOnDuty = status;
	}

}