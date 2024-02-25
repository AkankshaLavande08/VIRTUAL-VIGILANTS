import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String pin;
    private double balance;

    public Account(String pin, double initialBalance) {
        this.pin = pin;
        this.balance = initialBalance;
    }

    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: $" + balance);
    }
}

public class ATM {
    private static final Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        
        accounts.put("4521", new Account("4521", 8000)); 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM Simulator");

        while (true) {
            System.out.print("Enter your PIN (or 'exit' to quit): ");
            String pinInput = scanner.nextLine();

            if (pinInput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            }

            Account currentAccount = accounts.get(pinInput);
            if (currentAccount == null) {
                System.out.println("Invalid PIN. Please try again.");
                continue;
            }

            System.out.println("Welcome, user with PIN " + pinInput);

            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: $" + currentAccount.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); 
                        try {
                            currentAccount.deposit(depositAmount);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); 
                        try {
                            currentAccount.withdraw(withdrawAmount);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                if (choice == 4) {
                    break;
                }
            }
        }
        scanner.close();
    }
}

