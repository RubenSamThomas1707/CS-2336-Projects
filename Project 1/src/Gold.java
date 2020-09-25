/*
 * Name: Ruben Sam Thomas
 * Net ID: rst180005
 */


import java.lang.*;

    //  Creating a GoldCustomer class which inherits from the RegularCustomer base class.
public class Gold extends Customer
{
        //  Member variables
    protected double discountRate;

        //  Default Constructor
    public Gold()
    {
        super();
    }

        //  Overloaded constructor
    public Gold(String fName, String lName, String ID, double amount, double discount)
    {
        super(fName, lName, ID, amount);
        this.discountRate = discount;
    }

        //  Mutator function for discountRate
    public void setDiscountRate(double discount)
    {
        this.discountRate = discount;
    }

        //  Accessor function for discountRate
    public double getDiscountRate()
    {
        return discountRate;
    }
}
