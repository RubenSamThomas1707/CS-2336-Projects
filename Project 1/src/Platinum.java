/*
 * Name: Ruben Sam Thomas
 * Net ID: rst180005
 */


import java.lang.*;

    //  Creating a PlatinumCustomer class that inherits from the RegularCustomer base class
public class Platinum extends Customer
{
        //  Defining member variables
    protected double bonusBucks;

        //  Default Constructor
    public Platinum()
    {
        super();
    }

        //  Overloaded Constructor
    public Platinum(String fName, String lName, String ID, double amountSpent, double bBucks)
    {
        super(fName, lName, ID, amountSpent);
        this.bonusBucks = bBucks;
    }

        //  Mutator function for the bonusBucks
    public void setBonusBucks(double bBucks)
    {
        this.bonusBucks = bBucks;
    }

        //  Accessor function for the bonusBucks
    public double getBonusBucks()
    {
        return bonusBucks;
    }
}
