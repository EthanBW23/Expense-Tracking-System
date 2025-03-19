import java.util.ArrayList;
import java.util.Scanner;
// Import necessary libraries

class Expense // Class that'll be used for creating expense objects for storing the expense's information
{
	// Setting class attributes
	String name; // Name of the expense
	String date; // Date of when the expense occurred
	String category; // What type of expense it was, i.e. food, utilities, transport etc.
	double amount; // Cost of the expense 
	
	// Constructor
	public Expense(String name, String date, String category, double amount) {
		this.name = name;
		this.date = date;
		this.category = category;
		this.amount = amount;
	}
}

public class ExpenseTrackingSystem
{
	private static ArrayList<Expense> expenses = new ArrayList<>(); // Creating an array list for storing the expense objects
    private static Scanner userInput = new Scanner(System.in); // Scanner that will be used for user inputs
	
	public static void main(String[] args) 
	{
        System.out.println("Welcome to the Expense Tracker!");
        
		while (true) { // Loops through expense tracker function options list until user exits the program
            System.out.println("\nSelect an option:");
            System.out.println("1. Add an Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Filter Expenses by Category");
            System.out.println("5. Exit \n");
            System.out.print("Enter your choice: ");
            
            int choice = userInput.nextInt(); // User's choice of function
            userInput.nextLine(); 
            
            switch (choice) { // Switch case to select which function the user selected, from their input
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    calculateTotal();
                    break;
                case 4:
                    filterByCategory();
                    break;
                case 5:
                    System.out.println("\nExiting Expense Tracker."); // Exit expense tracker
                    return;
                default:
                    System.out.println("\nInvalid choice. Enter a number between 1 and 5."); // User's input was invalid
            }
		}
	}
	
	private static void addExpense() 
	{
		String name = ""; // Create and set name to nothing
		while (name.isEmpty()) { // Loops until a valid input has been input
			System.out.print("\nEnter expense name: ");
			name = userInput.nextLine();
			if (name.isEmpty()) { // If the user entered nothing
				System.out.print("\nName cannot be nothing.\n");
			}
		}
        
		String date = ""; // Create and set date to nothing
		int day = 0;
		int month = 0;
		int year = 0;
		while (true) { // Loops until a valid date has been input
			System.out.print("\nEnter year (YYYY): ");
			if (userInput.hasNextInt()) { // Checks if the input is an integer
				year = userInput.nextInt();
				if (year < 1945 || year > 2025) { // If the year input is earlier than 1945 (or negative) or higher than the current year...
					System.out.print("\nInvalid Year. Enter a valid year.\n");
					continue;
				}
			}
			else { // User entered an invalid input
				System.out.print("\nInvalid input. Enter a valid input.\n");
				continue;
			}
			
			System.out.print("\nEnter month (MM): ");
			if (userInput.hasNextInt()) {
				month = userInput.nextInt();
				if (month < 1 || month > 12) { // If the month input is earlier than 1 (or negative) or higher than than 12...
					System.out.print("\nInvalid month. Enter a valid month.\n");
					continue;
				}
			}
			else {
				System.out.print("\nInvalid input. Enter a valid input.\n");
				continue;
			}
			
			System.out.print("\nEnter day (DD): ");
			if (userInput.hasNextInt()) {
				day = userInput.nextInt();
				if (day < 1 || day > 31) { // If the day input is earlier than 1 (or negative) or higher than than 31...
					System.out.print("\nInvalid day. Enter a valid day.\n");
					continue;
				}
			}
			else {
				System.out.print("\nInvalid input. Enter a valid input.\n");
				continue;
			}
			break;
		}
		date = day + "-" + month + "-" + year; // Formats the day, month and year into one string
        
		String category = ""; // Create and set category to nothing
		while (category.isEmpty()) {
			System.out.print("\nEnter category name: ");
			category = userInput.next().toLowerCase(); // Lower cases the user's input, so that different casing for inputs isn't an issue
			if (category.isEmpty()) {
				System.out.print("\nCategory name cannot be nothing.\n");
			}
			else if (stringNumberChecker(category)) { // If the string input contains a number...
				System.out.print("\nCategory must be a specific name."); // The category name needs to be generic like food or utilities, not something too specific
				category = ""; // Resets the string
			}
		}
        
		double amount = 0; // Create and set amount to nothing
		while (true) {
			System.out.print("\nEnter amount: ");
			if (userInput.hasNextDouble()) { // checks if the user's input is a number...
				amount = userInput.nextDouble();
				if (amount <= 0) { // If the number input is less than or is 0...
					System.out.print("\nAmount must be positive (greater than 0).");
					break;
				}
			}
			else {
				System.out.print("\nInvalid input. Please enter an amount of money.");				
			}
		}
        
		// When all values are collected and checked...
        expenses.add(new Expense(name, date, category, amount)); // Creates a new expense instance with the input values and stores them in the array list
        System.out.println("\nExpense added.");
    }

    private static void viewExpenses() 
    {
        if (expenses.isEmpty()) { // If the array list has no entries
            System.out.println("\nNo expenses recorded yet.");
            return;
        }
        System.out.println("\nRecorded Expenses:\n");
        for (Expense expense:expenses) { // Loops through every entry in the array list
            System.out.println("Name: " + expense.name);
            System.out.println("Date: " + expense.date);
            System.out.println("Category: " + expense.category);
            System.out.println("Amount: £" + expense.amount);
            System.out.println("");
            // Outputs all the entries and their associated data back to the user
        }
    }

    private static void calculateTotal() 
    {
        double total = 0;
        for (Expense expense:expenses) { // loops through every entry in the array list
            total += expense.amount; // Adds every entry's amount together to find the total
        }
        System.out.println("\nTotal Expenses: £" + total);
    }

    private static void filterByCategory() 
    {
        System.out.print("\nEnter category to filter by: ");
        String category = userInput.nextLine().toLowerCase(); // Lower cases the user's input to remove problems with case sensitivity 
        
        boolean found = false;
        System.out.println("\nFiltered Expenses:\n");
        for (Expense expense:expenses) { // loops through every entry in the array list
            if (expense.category.equals(category)) { // If an entry matches the name of the category input by the user...
                System.out.println("Name: " + expense.name);
                System.out.println("Date: " + expense.date);
                System.out.println("Amount: £" + expense.amount);
                System.out.println("");
                found = true;
                // Outputs all the entries, and their information, with that same category name
            }
        }
        if (!found) { // If no entry has a matching category name...
            System.out.println("\nNo expenses found in this category.");
        }
    }
    
    private static boolean stringNumberChecker(String input) { // Function to check if a string has any numbers
        for (char c : input.toCharArray()) { // Loops through every character in the string
          if (Character.isDigit(c)) { // Checks if that character is a number
            return true; // String contains a number
          }
        }
        return false; // No number
      }
    // Source of function: // https://www.delftstack.com/howto/java/check-if-string-contains-numbers-in-java/#:~:text=Utilizing%20the%20Character.isDigit%28%29%20method%20in%20Java%20to%20check,the%20need%20for%20complex%20iterations%20or%20regular%20expressions.
}
