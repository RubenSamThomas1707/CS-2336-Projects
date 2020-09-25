/*
* Name: Ruben Sam Thomas
* Net ID: rst180005
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main
{
        //  Function to print components in the array.
    public static void PrintMenu(char[][] auditoriumSeats, int totalRows, String firstLine, int totalSeats)
    {
        int count = 0;
        System.out.print("   ");

            //  Printing the alphabets to label the seats.
        for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++)
        {
            System.out.print(alphabet);
            count++;

                //  Make sure that the total alphabets required is equal to the number of rows.
            if(count == totalSeats)
            {
                break;
            }
        }

        System.out.println();

        //  Printing information converted from string variable to the character array.
        for(int counter = 0; counter < totalRows; counter++)
        {
            System.out.print((counter + 1) + "  ");

            for (int i = 0; i < (firstLine.length()); i++)
            {
                    //  Changing the character to # if the seat is already reserved.
                if(auditoriumSeats[counter][i] != '.')
                {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(auditoriumSeats[counter][i]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }       //  End of function PrintMenu()

        //  Function to print information to the output file.
    public static void PrintOutputFile(PrintWriter outFile, char[][] auditoriumSeats, int totalRows, int totalSeats)
    {
        for(int i = 0; i < totalRows; i++)
        {
            for(int j = 0; j < totalSeats; j++)
            {
                outFile.print(auditoriumSeats[i][j]);
            }

            outFile.println();
        }
        outFile.close();
    }       //  End of function PrintOutputFile()

        //  Function to check the availability of the seat.
    public static boolean CheckAvailability(char[][] auditoriumSeats, int rowInput, int totalTickets, int startingSeat)
    {
            //  Iterate through the array to check if the number of seats the user wants are available.
        for(int i = 0; i < totalTickets; i++)
        {
            if(auditoriumSeats[rowInput - 1][startingSeat - 1] != '.')
            {
                return false;
            }

            startingSeat++;
        }

        return true;
    }       //  End of function CheckAvailability

        //  Function to reserve all the seats.
    public static void ReserveSeats(char[][] auditoriumSeats, int rowInput, int position ,int adultTickets, int childTickets, int seniorTickets)
    {
            //  For loop to input 'A' for the adult seats
        for(int i = 0; i < adultTickets; i++)
        {
            auditoriumSeats[rowInput - 1][position] = 'A';
            position++;
        }

            //  For loop to input 'C' for the child seats
        for(int i = 0; i < childTickets; i++)
        {
            auditoriumSeats[rowInput - 1][position] = 'C';
            position++;
        }

            //  For loop to input 'S' for the senior seats
        for(int i = 0; i < seniorTickets; i++)
        {
            auditoriumSeats[rowInput - 1][position] = 'S';
            position++;
        }

    }       //  End of function Reserve Seats.

    public static char ValidateSeat(char seatInput, int totalSeats, Scanner input)
    {
        boolean valid = true;

        while(valid)
        {
            char lastSeat = ' ';
            char test = 'A';

                //  Loop to find the letter of the last seat.
            for(int i = 0; i < totalSeats; i++)
            {
                lastSeat = test++;
            }

                //  Checking if the seat letter entered is right.
            for(test = 'A'; test <= lastSeat; test++)
            {
                if(seatInput == test)
                {
                    valid = false;
                    return seatInput;
                }
            }

                //  Prompts the user to enter the valid seat.
            System.out.println("Enter a valid seat letter");
            seatInput = input.next().charAt(0);
        }

        return seatInput;
    }

        //  Function to print the final report.
    public static void PrintReport(char[][] auditoriumSeats, int totalSeats, int totalRows)
    {
        int adultTickets = 0;
        int childTickets = 0;
        int seniorTickets = 0;

            //  Iterate through the array to find the total number of seats for adults, children and seniors.
        for(int i = 0; i < totalRows; i++)
        {
            for(int j = 0; j < totalSeats; j++)
            {
                if(auditoriumSeats[i][j] == 'A')
                {
                    adultTickets++;
                }

                if(auditoriumSeats[i][j] == 'C')
                {
                    childTickets++;
                }

                if(auditoriumSeats[i][j] == 'S')
                {
                    seniorTickets++;
                }
            }
        }

        System.out.println("Total Seats:    " + (totalRows * totalSeats));
        System.out.println("Total Tickets:  " + (adultTickets + childTickets + seniorTickets));
        System.out.println("Adult Tickets:  " + adultTickets);
        System.out.println("Child Tickets:  " + childTickets);
        System.out.println("Senior Tickets: " + seniorTickets);
        double totalSales = ((adultTickets * 10.0) + (childTickets * 5.0) + (seniorTickets * 7.50));
        System.out.println("Total Sales:    $" + String.format("%.2f", totalSales));
    }       //  End of function PrintReport()

        //  Function to find the best available seats
    public static int BestAvailable(char[][] auditoriumSeats, int totalSeats, int rowInput, int totalTickets)
    {
        double bestDistance = Integer.MAX_VALUE;
        int startingSeat = -1;
        boolean isEmpty = true;
        double distance = Integer.MAX_VALUE;

        for(int counter = 0; counter < (totalSeats - totalTickets + 1); counter++)
        {
                //  Iterating through the seats to find if the total number of seats required are available.
            for(int i = 0; i < totalTickets; i++)
            {
                if(auditoriumSeats[rowInput - 1][i + counter] != '.')
                {
                    isEmpty = false;
                    break;
                }
            }

                //  Calculating the distance.
            if(isEmpty)
            {
                distance = ((counter + (totalTickets) / 2.0) - ((totalSeats + 1) / 2.0));

                //  Check to get the shortest distance from the center of the auditorium
                if ((totalSeats + Math.abs(distance) / 2.0) < (totalSeats + Math.abs(bestDistance) / 2.0))
                {
                    bestDistance = distance;
                    startingSeat = counter;
                }
            }

            isEmpty = true;
        }

        return startingSeat;
    }       //  End of function BestAvailable()

        //  Main Function.
    public static void main(String[] args) throws FileNotFoundException
    {
            //  Defining Variables
        int adultTickets;
        int childTickets;
        int seniorTickets;
        int totalTickets;
        String firstLine;
        char repeat = 'Y';
        int totalSeats;
        int totalRows = 0;
        String userInput;
        int rowInput;
        boolean status;
        char tempAlphabet = 'A';
        int startingSeat;
        char seatInput;

            //  Prompting the user for the name of the input file.
        System.out.println("Enter input file name: ");
        Scanner input = new Scanner(System.in);
        String inputFile = input.nextLine();

            //  Creating a file object and using the file for input.
        File inFileName = new File(inputFile);
        Scanner inFile = new Scanner(inFileName);

            //  Creating the output file.
        File outFileName = new File("A1.txt");
        PrintWriter outFile = new PrintWriter(outFileName);

        if(inFileName.canRead()) {
            firstLine = inFile.nextLine();

            //  Creating a character array with the exact number of seats in each row.
            totalSeats = firstLine.length();
            char[][] auditoriumSeats = new char[10][totalSeats];

            //  Inputting information from the file into the character array.
            for (int i = 0; i < totalSeats; i++) {
                auditoriumSeats[0][i] = firstLine.charAt(i);
            }

            totalRows = totalRows + 1;

            // Validates the file to check if there is data in the file.
            while (inFile.hasNextLine())
            {
                //  Loop to iterate through the 2D array.
                for (int rowCounter = 1; rowCounter < 10; rowCounter++)
                {
                    //  Checking to see if the file has more information.
                    if (inFile.hasNextLine()) {
                        firstLine = inFile.nextLine();
                        totalRows++;

                        //  Loop to insert information about seats from file to array.
                        for (int seatCounter = 0; seatCounter < totalSeats; seatCounter++)
                        {
                            auditoriumSeats[rowCounter][seatCounter] = firstLine.charAt(seatCounter);
                        }
                    }
                }    //  For loop to iterate through the row of the character array

                while (repeat == 'Y')
                {
                    System.out.println("1. Reserve Seats");
                    System.out.println("2. Exit");

                    userInput = input.next();

                        //  Executes if the user wanted to reserve seats.
                    if (userInput.equals("1"))
                    {
                        //  Function call to print the components in the array.
                        PrintMenu(auditoriumSeats, totalRows, firstLine, totalSeats);

                        System.out.println("Enter the row to reserve the tickets.");
                        rowInput = input.nextInt();

                        //  Validating user input for desired row number.
                        while ((rowInput <= 0) | (rowInput > totalRows))
                        {
                            System.out.println("Please enter a valid row number.");
                            rowInput = input.nextInt();
                        }

                        System.out.println("Enter the seat letter that you want to reserve");
                        seatInput = input.next().charAt(0);
                        seatInput = ValidateSeat(seatInput, totalSeats, input);

                        //  Prompting the user to enter the total number of adult tickets needed.
                        System.out.println("Enter the total number of adult tickets to be reserved.");
                        adultTickets = input.nextInt();

                        while (adultTickets < 0)
                        {
                            System.out.println("Please enter a valid number of adult tickets.");
                            adultTickets = input.nextInt();
                        }

                        //  Prompting the user to enter the total number of child tickets needed.
                        System.out.println("Enter the total number of child tickets to be reserved.");
                        childTickets = input.nextInt();

                        while (childTickets < 0) {
                            System.out.println("Please enter a valid number of adult tickets.");
                            childTickets = input.nextInt();
                        }

                        //  Prompting the user to enter the total number of senior tickets needed.
                        System.out.println("Enter the total number of senior tickets to be reserved.");
                        seniorTickets = input.nextInt();

                        while (seniorTickets < 0) {
                            System.out.println("Please enter a valid number of senior tickets.");
                            seniorTickets = input.nextInt();
                        }

                        //  Calculating the total number of tickets wanted by the user.
                        totalTickets = (adultTickets + childTickets + seniorTickets);

                            //  Calculating the starting seat based on the first possible alphabet 'A'
                        startingSeat = ((seatInput - tempAlphabet) + 1);

                        //  Function call to check whether the seats are available.
                        status = CheckAvailability(auditoriumSeats, rowInput, totalTickets, startingSeat);

                        //  Prompting the user to check whether to reserve the seats.
                        if (status)
                        {
                            ReserveSeats(auditoriumSeats, rowInput, (startingSeat - 1), adultTickets, childTickets, seniorTickets);
                        }

                        else
                        {
                            int seatLetter = 'A';
                            startingSeat = BestAvailable(auditoriumSeats, totalSeats, rowInput, totalTickets);
                            char startingLetter = (char) (seatLetter + startingSeat);

                                //  Checking to see if there are any seats available
                            if(startingSeat != -1)
                            {
                                //System.out.println("Best Available Seats: ");

                                if(totalTickets != 1)
                                {
                                    System.out.println("" + rowInput + startingLetter + " - " + rowInput + ((char) (startingLetter + totalTickets - 1)));
                                    System.out.println();
                                }
                                else
                                {
                                    System.out.println("" + rowInput + startingLetter);
                                    System.out.println();
                                }

                                    //  Asks the user whether to reserve the best available seats or not.
                                System.out.println("Would you like to reserve the best available seats? (Y/N)");
                                String reserve = input.next();

                                if(reserve.equals("Y"))
                                {
                                    ReserveSeats(auditoriumSeats, rowInput, startingSeat, adultTickets, childTickets, seniorTickets);
                                }
                            }

                            else
                            {
                                System.out.println("no seats available");
                            }
                        }
                    }       //  End of if statement for userInput.

                    else
                    {
                        System.out.println("no seats available");
                        repeat = 'N';
                    }
                }       //  End of while statement
            }       //  While statement to check whether the user wants to try and reserve again.

            //  Function calls to print report and to write the information to the output file.
            PrintOutputFile(outFile, auditoriumSeats, totalRows, totalSeats);
            PrintReport(auditoriumSeats, totalSeats, totalRows);

        }       //  End of if statement to check whether the file can be read.

    }   //  End of function main.
}
