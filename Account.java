package ATM;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
    // Scanner object for user input
    Scanner input = new Scanner(System.in);
    
    // DecimalFormat to format monetary values
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // Private attributes for account details
    private int customerNumber;  // Unique customer ID
    private int pinNumber;       // 4-digit PIN for authentication
    private double checkingBalance = 5000; // Initial Checking Account balance
    private double savingBalance = 3000;   // Initial Savings Account balance

    // Setter for Customer Number
    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    // Getter for Customer Number
    public int getCustomerNumber() {
        return customerNumber;
    }

    // Setter for PIN Number
    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    // Getter for PIN Number
    public int getPinNumber() {
        return pinNumber;
    }

    // Getter for Checking Account Balance
    public double getCheckingBalance() {
        return checkingBalance;
    }

    // Getter for Savings Account Balance
    public double getSavingBalance() {
        return savingBalance;
    }

    /**
     * Withdraws funds from the selected account.
     * Ensures that the withdrawal amount does not exceed the account balance.
     */
    public void withdrawFunds(String accountType) {
        System.out.print("Enter amount to withdraw: ");
        double amount = input.nextDouble();

        if (accountType.equals("Checking")) {
            if (amount <= checkingBalance) {
                checkingBalance -= amount;
                System.out.println("New Checking Balance: " + moneyFormat.format(checkingBalance));
            } else {
                System.out.println("Insufficient funds.");
            }
        } else if (accountType.equals("Saving")) {
            if (amount <= savingBalance) {
                savingBalance -= amount;
                System.out.println("New Saving Balance: " + moneyFormat.format(savingBalance));
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Invalid account type.");
        }
    }

    /**
     * Deposits funds into the selected account.
     * Updates the balance after a successful deposit.
     */
    public void depositFunds(String accountType) {
        System.out.print("Enter amount to deposit: ");
        double amount = input.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }

        if (accountType.equals("Checking")) {
            checkingBalance += amount;
            System.out.println("New Checking Balance: " + moneyFormat.format(checkingBalance));
        } else if (accountType.equals("Saving")) {
            savingBalance += amount;
            System.out.println("New Saving Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid account type.");
        }
    }
}
