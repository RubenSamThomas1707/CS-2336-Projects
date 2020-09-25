/*
 * Name: Ruben Sam Thomas
 * Net ID: rst180005
 */


import java.text.DecimalFormat;
import java.util.*;
import java.lang.*;
import java.io.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Main
{
    //  Function to calculate bonusBucks
    public static int calculateBonusBucks(double prevAmountSpent, double currTransaction)
    {
        double bBucks;
        int bonusBucks;

        //  Checking to make sure that the customer is of platinum status
        if(prevAmountSpent >= 200)
        {
            bBucks= ((prevAmountSpent + currTransaction) - (prevAmountSpent - (prevAmountSpent % 5))) / 5;
            bonusBucks = (int) bBucks;

            return bonusBucks;
        }

        //  Checking to see if after the current transaction the total amount spent exceeds 200
        else if((prevAmountSpent + currTransaction) >= 200)
        {
            bBucks= ((prevAmountSpent + currTransaction) - 200) / 5;
            bonusBucks = (int) bBucks;
            return bonusBucks;

        }

        else
        {
            return -1;
        }
    }       //  End of function calculateBonusBucks()

    //  Function to print the final output to the file
    public static void printOutput(Customer[] regularCustomerData, Customer[] preferredCustomerData) throws FileNotFoundException
    {
        //  Creating PrintWriter objects to print to the output file
        PrintWriter outFile1 = new PrintWriter(new File("customer.dat"));
        PrintWriter outFile2 = new PrintWriter(new File("preferred.dat"));

        //  Printing output to 'customer.dat' output file
        for(int i = 0; i < regularCustomerData.length; i++)
        {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            outFile1.print(regularCustomerData[i].getGuestID() + " " + regularCustomerData[i].getFirstName() + " " + regularCustomerData[i].getLastName() + " ");
            outFile1.printf("%.2f", (regularCustomerData[i].getAmountSpent()));
            outFile1.println();
        }

        //  Printing output to 'preferred.dat' output file
        for(int i = 0; i < preferredCustomerData.length; i++)
        {
            outFile2.print(preferredCustomerData[i].getGuestID() + " " + preferredCustomerData[i].getFirstName() + " " + preferredCustomerData[i].getLastName() + " ");
            outFile2.printf("%.2f", (preferredCustomerData[i].getAmountSpent()));

            if((preferredCustomerData[i] instanceof Gold) & ((preferredCustomerData[i].getAmountSpent() >= 50.0) & (preferredCustomerData[i].getAmountSpent() < 200.0)))
            {
                outFile2.println(" " + (int)((Gold) preferredCustomerData[i]).getDiscountRate() + "%");
            }

            else if((preferredCustomerData[i] instanceof Platinum) | (preferredCustomerData[i].getAmountSpent() >= 200.0))
            {
                if(preferredCustomerData[i] instanceof Gold)
                {
                    outFile2.println(" " + (int) ((Gold) preferredCustomerData[i]).getDiscountRate());
                }

                else
                {
                    outFile2.println(" " + (int) ((Platinum) preferredCustomerData[i]).getBonusBucks());
                }
            }
        }

        //  Closing PrintWriter objects.
        outFile1.close();
        outFile2.close();

    }   //  End of printOutput() function

    //  Function to calculate the total amount of the current transaction
    public static double totalPrice(String drinkType, String drinkSize, int totalQuantity, double costPerSqInch)
    {
        //  Defining variables
        double drinkPrice = -1.0;
        double designingPrice = 0.0;

        //  Calculating the amount for any drink that is small
        if(drinkSize.equals("S") & (drinkType.equals("soda") | drinkType.equals("tea") | drinkType.equals("punch")))
        {
            //  Defining variables
            double radius = (4.0 / 2.0);
            double height = 4.5;
            double smallDrinkOunces = 12;

            designingPrice = (2.0 * Math.PI * radius * height * costPerSqInch);

            //  Calculating price if drink was soda
            if(drinkType.equals("soda"))
            {
                drinkPrice = (0.20 * smallDrinkOunces);
            }

            else if(drinkType.equals("tea"))
            {
                drinkPrice = (0.12 * smallDrinkOunces);
            }

            else
            {
                drinkPrice = (0.15 * smallDrinkOunces);
            }
        }

        //  Calculating the amount for any drink that is medium
        else if(drinkSize.equals("M") & (drinkType.equals("soda") | drinkType.equals("tea") | drinkType.equals("punch")))
        {
            //  Defining variables
            double radius = (4.5 / 2.0);
            double height = 5.75;
            double mediumDrinkOunces = 20;

            designingPrice = (2.0 * Math.PI * radius * height * costPerSqInch);

            //  Calculating price if drink was soda
            if(drinkType.equals("soda"))
            {
                drinkPrice = (0.20 * mediumDrinkOunces);
            }

            else if(drinkType.equals("tea"))
            {
                drinkPrice = (0.12 * mediumDrinkOunces);
            }

            else
            {
                drinkPrice = (0.15 * mediumDrinkOunces);
            }
        }

        //  Calculating the amount for any drink that is large
        else if(drinkSize.equals("L") & (drinkType.equals("soda") | drinkType.equals("tea") | drinkType.equals("punch")))
        {
            //  Defining variables
            double radius = (5.5 / 2.0);
            double height = 7.0;
            double largeDrinkOunces = 32;

            designingPrice = (2.0 * Math.PI * radius * height * costPerSqInch);

            //  Calculating price if drink was soda
            if(drinkType.equals("soda"))
            {
                drinkPrice = (0.20 * largeDrinkOunces);
            }

            else if(drinkType.equals("tea"))
            {
                drinkPrice = (0.12 * largeDrinkOunces);
            }

            else
            {
                drinkPrice = (0.15 * largeDrinkOunces);
            }
        }

        //  Calculating the total amount of the transaction
        return ((designingPrice + drinkPrice) * totalQuantity);

    }       //  End of function totalPrice

    //  Function for reading and inputting information about regular customer into an array
    public static Customer[] readRegularCustomerData(File inFile) throws FileNotFoundException
    {
        Scanner fileCount = new Scanner(inFile);
        Scanner customerData = new Scanner(inFile);
        int totalCustomers = 0;

        //  Iterating through the file to calculate the total number of customers in the file.
        while(fileCount.hasNextLine())
        {
            String customer = fileCount.nextLine();
            totalCustomers++;
        }

        //  Creating a regular customer array to store the regular customer data
        Customer[] regularCustomerData = new Customer[totalCustomers];

        //  Inputting information from the input file into the designated array locations
        for(int i = 0; i < totalCustomers; i++)
        {
            String ID = customerData.next();
            String firstName = customerData.next();
            String lastName = customerData.next();
            double amountSpent = parseDouble(customerData.next());

            //  Creating an object and inputting information
            regularCustomerData[i] = new Customer(firstName, lastName, ID, amountSpent);
        }

        //  Returning the array containing regular customer information
        return regularCustomerData;
    }       //  End of function readRegularCustomerData()

    //  Function for reading and inputting information about preferred customer into an array
    public static Customer[] readPreferredCustomerData(File inFile2) throws FileNotFoundException
    {
        Scanner fileCount = new Scanner(inFile2);
        Scanner customerData = new Scanner(inFile2);
        int totalCustomers = 0;

        //  Iterating through the file to calculate the total number of customers in the file.
        while(fileCount.hasNextLine())
        {
            String customer = fileCount.nextLine();
            totalCustomers++;
        }

        //  Creating a preferred customer array to store the preferred customer data
        Customer[] preferredCustomerData = new Customer[totalCustomers];

        //  Inputting information into the preferredCustomerData array
        for(int i = 0; i < totalCustomers; i++)
        {
            String ID = customerData.next();
            String firstName = customerData.next();
            String lastName = customerData.next();
            double amountSpent = parseDouble(customerData.next());

            if(amountSpent >= 50 & amountSpent <= 200)
            {
                //  Calculating the discount rate of the customer
                String custDiscount = customerData.next();
                int percentIndex = custDiscount.indexOf("%");
                String discount = custDiscount.replaceAll("[^0-9]", "");
                double discountRate = parseInt(discount);

                //  Entering the information of the preferred customer into the preferred customer data array
                preferredCustomerData[i] = new Gold(firstName, lastName, ID, amountSpent, discountRate);
            }

            else
            {
                //  Taking information about bonus Bucks from the input file.
                int bonusBucks = customerData.nextInt();

                //  Entering the information of the preferred customer into the preferred customer data array
                preferredCustomerData[i] = new Platinum(firstName, lastName, ID, amountSpent, bonusBucks);
            }
        }

        //  Returning the array from the function.
        return preferredCustomerData;
    }       //  End of function readPreferredCustomerData()

    //  Function to apply the eligible discount for the customer
    public static double applyDiscountOrBonusBucks(Object o, double currentTotal)
    {
        //  Setting the new total equal to the current total to return the current total if the user is not eligible for any discount.
        double discountedTotal = currentTotal;

        //  Checking to see if the object passed in is a Gold customer object
        if(o instanceof Gold)
        {
            discountedTotal = (currentTotal - (currentTotal * (((Gold) o).getDiscountRate() / 100.0)));
        }

        //  Checking to see if the object passed in is a platinum customer
        else if(o instanceof Platinum)
        {
            discountedTotal = (currentTotal - ((Platinum) o).getBonusBucks());
        }

        //  return currentAmount if the customer is not applicable for any discount
        return discountedTotal;

    }

    //  Function to validate the input from the orders file name.
    public static Customer validateCustomerID(String ID, Customer[] regularCustomerData, Customer[] preferredCustomerData)
    {
        //  Iterating through the regularCustomerArray
        for(int i = 0; i < regularCustomerData.length; i++)
        {
            //  Checking to see if the guestID from the orders file is present in the regularCustomerArray
            if(regularCustomerData[i].getGuestID().equals(ID))
            {
                return regularCustomerData[i];
            }
        }

        //  Iterating through the preferredCustomerArray
        if(preferredCustomerData.length != 0)
        {
            for (int i = 0; i < preferredCustomerData.length; i++) {
                //  Checking to see if the guestID from the orders file is present in the preferredCustomerArray
                if (preferredCustomerData[i].getGuestID().equals(ID)) {
                    return preferredCustomerData[i];
                }
            }
        }

        //  Returning another instance of the customer class if the guestID was not found
        Customer noCustomer = new Customer();
        noCustomer.setGuestID("-1");

        return noCustomer;
    }       //  End of function validateOrderInput()

    //  Function to calculate the index of a customer object
    public static int indexOf(Customer[] customerArr, Customer customerData)
    {
        for(int i = 0; i < customerArr.length; i++)
        {
            //  Checking the index of the customer object passed in with the index of the customer in the array
            if(customerArr[i].getGuestID().equals(customerData.getGuestID()))
            {
                return i;
            }
        }

        return -1;
    }   //  End of function indexOf()

    //  Function to update the array contents
    public static Customer[] resizeArray(Customer[] customerData, Customer newCustomer, double discountedPrice, boolean addCustomer, boolean resize)
    {
        Customer[] tempCustomer = null;
        int sameCustomerIndex = indexOf(customerData, newCustomer);

        //  Creating a new array that is one size larger than the original
        if(addCustomer == true)
        {
            tempCustomer = new Customer[customerData.length + 1];
            System.arraycopy(customerData, 0, tempCustomer, 0, customerData.length);
            tempCustomer[customerData.length] = newCustomer;
        }

        //  Decreasing the size of the regular customer array by 1 and copying all the elements except for the class transferred
        else if(addCustomer == false)
        {
            tempCustomer = new Customer[customerData.length - 1];
            System.arraycopy(customerData, 0, tempCustomer, 0, sameCustomerIndex);
            System.arraycopy(customerData, (sameCustomerIndex + 1), tempCustomer, (sameCustomerIndex), (customerData.length - sameCustomerIndex - 1));
        }

        if(!resize & !addCustomer)
        {
            tempCustomer = new Customer[customerData.length];
            System.arraycopy(customerData, 0, tempCustomer, 0, customerData.length);
            tempCustomer[sameCustomerIndex] = newCustomer;
        }

        return tempCustomer;

    }       //  End of function resizeArray()

    //  Function to update the status of the customer from regular to Gold or from Gold to Platinum
    public static Customer statusUpdate(Customer customerInfo, double discountedAmount, double totalTransaction)
    {
        double totalAmountSpent = customerInfo.getAmountSpent() + discountedAmount;
        double updatedDiscount = -1;
        double updatedBonusBucks = -1;

        //  Returning the customer information as it is since it is regular customer
        if(totalAmountSpent < 50)
        {
            return customerInfo;
        }

        //  Validating the amount spent by the customer to check whether eligible for gold status
        if(totalAmountSpent >= 50 & totalAmountSpent < 200)
        {
            //  Setting discount to first level of Gold
            if(totalAmountSpent >= 50 & totalAmountSpent < 100)
            {
                updatedDiscount = 5;
            }

            //  Setting discount to second level of gold
            else if(totalAmountSpent >= 100 & totalAmountSpent < 150)
            {
                updatedDiscount = 10;
            }

            //  Setting discount to final level of gold.
            else
            {
                updatedDiscount = 15;
            }
        }

        //  Validating to see if the customer is eligible for platinum status.
        else if(totalAmountSpent >= 200)
        {
            if(customerInfo instanceof Gold | (!(customerInfo instanceof Gold) & !(customerInfo instanceof Platinum)))
            {
                discountedAmount = (totalTransaction - (totalTransaction * 0.15));
            }

            updatedBonusBucks = calculateBonusBucks(customerInfo.getAmountSpent(), discountedAmount);
        }

        //  Creating a new customer object if the original customer was upgraded to gold or platinum
        Customer newCustomer = null;

        if((updatedDiscount == 5 | updatedDiscount == 10 | updatedDiscount == 15) & (totalAmountSpent >= 50 & totalAmountSpent < 200))
        {
            //  Creating a new gold object to enter values for discounts
            newCustomer = new Gold();

            //  Initializing the new customer with the information about the old customer
            newCustomer.setGuestID(customerInfo.getGuestID());
            newCustomer.setFirstName(customerInfo.getFirstName());
            newCustomer.setLastName(customerInfo.getLastName());
            newCustomer.setAmountSpent(customerInfo.getAmountSpent());

            //  Initializing the new Customer with the new Discount rate
            ((Gold) newCustomer).setDiscountRate(updatedDiscount);
        }

        else if((updatedBonusBucks != -1) & (totalAmountSpent >= 200))
        {
            //  Creating a new platinum object to copy information from old customer
            newCustomer = new Platinum();

            //  Initializing the new customer with the information about the old customer
            newCustomer.setGuestID(customerInfo.getGuestID());
            newCustomer.setFirstName(customerInfo.getFirstName());
            newCustomer.setLastName(customerInfo.getLastName());
            newCustomer.setAmountSpent(customerInfo.getAmountSpent() + discountedAmount);

            //  Initializing the new platinum level customer with bonusBucks
            ((Platinum) newCustomer).setBonusBucks(updatedBonusBucks);
        }

        //  returning the new customer object
        return newCustomer;
    }       //  End of function statusUpdate()

    //  Function main()
    public static void main(String[] args) throws FileNotFoundException
    {
        //  Defining variables.
        String regularCustomerFileName;
        String preferredCustomerFileName;
        String ordersFileName;
        String ordersData;
        double totalTransaction;
        double discountedPrice;
        Customer[] preferredCustomerData = new Customer[0];

        //  Defining Scanner object to get the user input
        Scanner input = new Scanner(System.in);

        //  Prompting the user to enter the regular customer file name
        System.out.println("Enter the name of the regular customer file.");
        regularCustomerFileName = input.nextLine();

        //  Prompting the user to enter the preferred customer file name
        System.out.println("Enter the name of the preferred customer file");
        preferredCustomerFileName = input.nextLine();

        //  Prompting the user to enter the orders file name.
        System.out.println("Enter the name of the orders file");
        ordersFileName = input.nextLine();

        //  Creating a file object for the regular customer information
        File inFile = new File(regularCustomerFileName);
        Customer[] regularCustomerData = readRegularCustomerData(inFile);

        //  Creating a file object for the preferred customer information
        File inFile2 = new File(preferredCustomerFileName);

        //  Check to see if the file exists or is empty!!
        if(inFile2.exists())
        {
            Scanner preferredCustomerInfo = new Scanner(inFile2);

            if(preferredCustomerInfo.hasNextLine())
            {
                preferredCustomerData = readPreferredCustomerData(inFile2);
            }
        }

        //  Creating a file object for the orders file.
        File orderFileInput = new File(ordersFileName);
        Scanner inFile3 = new Scanner(orderFileInput);
        while(inFile3.hasNextLine())
        {
            //  Inputting information from the orders file into an array.
            ordersData = inFile3.nextLine();
            String[] orderInfo = ordersData.split("\\s");

            //  Validation for the orders file.
            if(orderInfo.length != 5)
            {
                continue;
            }

            //  Checking to see if the customer ID is present in both the regular customer or preferred customer array.
            Customer customerInfo = validateCustomerID(orderInfo[0], regularCustomerData, preferredCustomerData);

            if(parseInt(customerInfo.getGuestID()) == -1)
            {
                continue;
            }

            //  For debugging purposes.
            System.out.println("The customer ID is: " + customerInfo.getGuestID());

            //  Try Block to check if any of the fields of information from the orders file is invalid
            try {
                totalTransaction = totalPrice(orderInfo[2], orderInfo[1], parseInt(orderInfo[4]), parseDouble(orderInfo[3]));

                if(totalTransaction < 0)
                {
                    continue;
                }

                System.out.println("The total price for everything is " + totalTransaction);

                //  Calculating the transaction amount after the discount is applied.
                discountedPrice = applyDiscountOrBonusBucks(customerInfo, totalTransaction);
                System.out.println("The total discounted price for everything is " + discountedPrice);

                //  Checking to see if any customer is eligible for promotion
                Customer newCustomer = statusUpdate(customerInfo, discountedPrice, totalTransaction);

                if (newCustomer instanceof Gold) {
                    discountedPrice = applyDiscountOrBonusBucks(newCustomer, totalTransaction);
                    newCustomer.setAmountSpent(newCustomer.getAmountSpent() + discountedPrice);
                }
                else if (newCustomer instanceof Platinum)
                {
                    newCustomer.setAmountSpent(newCustomer.getAmountSpent());
                }

                else
                {
                    newCustomer.setAmountSpent(newCustomer.getAmountSpent() + discountedPrice);
                }

                //  Checking to see if the customer is eligible for promotion
                if ((newCustomer instanceof Gold || newCustomer instanceof Platinum) && !(customerInfo instanceof Gold || customerInfo instanceof Platinum)) {
                    preferredCustomerData = resizeArray(preferredCustomerData, newCustomer, discountedPrice, true, true);
                    regularCustomerData = resizeArray(regularCustomerData, newCustomer, discountedPrice, false, true);
                }

                //  Checking to see if the customer object was o=already a gold or platinum object
                else if ((newCustomer instanceof Gold || newCustomer instanceof Platinum)) {
                    preferredCustomerData = resizeArray(preferredCustomerData, newCustomer, discountedPrice, false, false);
                }
            }       //  End of try block

            //  Catch statement to exit from the current orders information and continue with the next set of orders.
            catch(NumberFormatException exception)
            {
                System.out.println("There is some invalid information in the orders file provided.");
                System.out.println("The error obtained is " + exception.getMessage());
                continue;
            }
        }

        //  Printing the output to the output file
        printOutput(regularCustomerData, preferredCustomerData);

    }       //  End of function main()
}
