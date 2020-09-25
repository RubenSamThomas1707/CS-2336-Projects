/*
 * Name: Ruben Sam Thomas
 * Net ID: rst180005
 */


import java.lang.*;

    //  Creating a RegularCustomer class
public class Customer
{
        //  Creating member variables
    protected String firstName;
    protected String lastName;
    protected String guestID;
    protected double amountSpent;

        //  Default Constructor
    public Customer() { }

        //  Overloaded Constructors
    public Customer(String firstName, String lastName, String ID, double amount)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestID = ID;
        this.amountSpent = amount;
    }

        //  Mutator function for firstName
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

        //  Mutator function for lastName
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

        //  Mutator function for guestID
    public void setGuestID(String ID)
    {
        this.guestID = ID;
    }

        //  Mutator function for amountSpent
    public void setAmountSpent(double amount)
    {
        this.amountSpent = amount;
    }

        //  Accessor function for firstName
    public String getFirstName()
    {
        return firstName;
    }

        //  Accessor function for lastName
    public String getLastName()
    {
        return lastName;
    }

        //  Accessor function for guestID
    public String getGuestID()
    {
        return guestID;
    }

        //  Accessor function for amountSpent
    public double getAmountSpent()
    {
        return amountSpent;
    }

}
