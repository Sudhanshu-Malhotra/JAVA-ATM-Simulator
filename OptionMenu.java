package ATM;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu extends Account {
    // Scanner for user input and format for money display
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // A HashMap to store account numbers and corresponding pin numbers
    HashMap<Integer, Integer> data = new HashMap<>();

    /**
     * Initiates the login process.
     * Verifies the account number and pin entered by the user.
     */
    public void getLogin() throws IOException {
        // Sample data for account number and pin.
        data.put(9876, 5432);
        data.put(12345, 6789);

        // Display welcome message
        System.out.println("Welcome to the ATM Project");

        // Prompt user for account number and pin
        System.out.print("Enter Your Account Number: ");
        setCustomerNumber(menuInput.nextInt());

        System.out.print("Enter Your Pin Number: ");
        setPinNumber(menuInput.nextInt());

        boolean validUser = false; // To track if user is valid

        // Iterate over stored data to verify account number and pin
        for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
            if (entry.getKey().equals(getCustomerNumber()) && entry.getValue().equals(getPinNumber())) {
                validUser = true; // Valid user found
                getAccountType(); // Proceed to account selection
                break;
            }
        }

        // If no valid user is found, display an error and restart login
        if (!validUser) {
            System.out.println("\nWrong Account Number or Pin Number.\n");
            getLogin(); // Restart login process
        }
    }

    /**
     * Displays the options for account type selection (Checking, Saving, or Exit).
     */
    public void getAccountType() {
        System.out.println("Select the account you want to access.");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Saving Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");

        // User input for account selection
        int selection = menuInput.nextInt();

        // Handle different account types based on user choice
        switch (selection) {
            case 1:
                getChecking(); // Go to checking account operations
                break;

            case 2:
                getSaving(); // Go to saving account operations
                break;

            case 3:
                // Exit the ATM program
                System.out.println("\nThanks for using this ATM.\n");
                System.exit(0); // Exit program
                break;

            default:
                // Handle invalid choices
                System.out.println("\nInvalid choice.\n");
                getAccountType(); // Retry account type selection
                break;
        }
    }

    /**
     * If the user selects Checking account, this method handles Checking account operations.
     */
    public void getChecking() {
        System.out.println("\nChecking Account selected.");
        performTransactions("Checking"); // Call to perform transactions on Checking account
    }

    /**
     * If the user selects Saving account, this method handles Saving account operations.
     */
    public void getSaving() {
        System.out.println("\nSaving Account selected.");
        performTransactions("Saving"); // Call to perform transactions on Saving account
    }

    /**
     * This method handles the user's transaction choices (view balance, withdraw, deposit, or exit).
     * @param accountType Specifies the type of account (Checking or Saving).
     */
    public void performTransactions(String accountType) {
        while (true) {
            // Display transaction options
            System.out.println("\nSelect an option:");
            System.out.println("1 - View Balance");
            System.out.println("2 - Withdraw Funds");
            System.out.println("3 - Deposit Funds");
            System.out.println("4 - Exit to Main Menu");
            System.out.print("Choice: ");

            // User input for transaction selection
            int choice = menuInput.nextInt();

            // Handle the transaction choices
            switch (choice) {
                case 1:
                    // Display balance based on account type (Checking or Saving)
                    System.out.println(accountType + " Balance: " + moneyFormat.format(
                            accountType.equals("Checking") ? getCheckingBalance() : getSavingBalance()));
                    break;

                case 2:
                    // Perform withdrawal operation
                    withdrawFunds(accountType);
                    break;

                case 3:
                    // Perform deposit operation
                    depositFunds(accountType); 
                    break;

                case 4:
                    // Return to the main account selection menu
                    getAccountType(); 
                    return;

                default:
                    // Handle invalid choice
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
    }
}
