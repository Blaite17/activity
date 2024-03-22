import java.util.Scanner;

class BankAccount {
    private String userName;
    private String accountNumber; // Changed type to String to support 10 digits
    private double savingsBalance;
    private double checkingBalance;

    // Constructor to initialize user details
    public BankAccount(String userName, String accountNumber) {
        this.userName = userName;
        this.accountNumber = accountNumber;
    }

    // Method to deposit money into the specified account type
    public void deposit(double amount, String accountType) {
        if (accountType.equalsIgnoreCase("savings")) {
            savingsBalance += amount;
            System.out.println("Deposited ₱" + amount + " into savings account.");
        } else if (accountType.equalsIgnoreCase("checking")) {
            checkingBalance += amount;
            System.out.println("Deposited ₱" + amount + " into checking account.");
        } else {
            System.out.println("Invalid account type.");
        }
    }

    // Method to display the balances of both accounts
    public void displayBalance() {
        System.out.println("User: " + userName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Savings Account Balance: ₱" + savingsBalance);
        System.out.println("Checking Account Balance: ₱" + checkingBalance);
    }

    // Method to withdraw money from the specified account type
    public void withdraw(double amount, String accountType) {
        if (accountType.equalsIgnoreCase("savings")) {
            if (amount <= savingsBalance) {
                savingsBalance -= amount;
                System.out.println("Withdrawn ₱" + amount + " from savings account.");
            } else {
                System.out.println("Insufficient funds in savings account.");
            }
        } else if (accountType.equalsIgnoreCase("checking")) {
            if (amount <= checkingBalance) {
                checkingBalance -= amount;
                System.out.println("Withdrawn ₱" + amount + " from checking account.");
            } else {
                System.out.println("Insufficient funds in checking account.");
            }
        } else {
            System.out.println("Invalid account type.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for name and account number
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        String accountNumber;
        do {
            System.out.print("Enter your 10-digit account number: ");
            accountNumber = scanner.nextLine();
            if (accountNumber.length() != 10) {
                System.out.println("Account number must be 10 digits long. Please try again.");
            }
        } while (accountNumber.length() != 10);

        // Create BankAccount object
        BankAccount account = new BankAccount(userName, accountNumber);

        // Display menu
        System.out.println("\nBank Account Management System\n1. Deposit Money\n2. Display Balance\n3. Withdraw Money\n4. Exit");

        // Menu loop
        boolean running = true;
        while (running) {
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Deposit Money
                    System.out.print("Enter account type (savings/checking): ");
                    String accountType = scanner.next();
                    System.out.print("Enter amount to deposit: ₱");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount, accountType);
                    break;
                case 2: // Display Balance
                    account.displayBalance();
                    break;
                case 3: // Withdraw Money
                    System.out.print("Enter account type (savings/checking): ");
                    String withdrawalAccountType = scanner.next();
                    System.out.print("Enter amount to withdraw: ₱");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount, withdrawalAccountType);
                    break;
                case 4: // Exit
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default: // Invalid choice
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        // Close scanner
        scanner.close();
    }
}
