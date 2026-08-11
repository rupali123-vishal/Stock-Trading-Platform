import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Wallet.java
 *
 * Represents a user's virtual wallet.
 * Handles deposits, withdrawals, and balance management.
 */
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    private double balance;

    /**
     * Default constructor.
     * Initial balance is ₹0.
     */
    public Wallet() {
        this.balance = 0.0;
    }

    /**
     * Constructor with initial balance.
     */
    public Wallet(double initialBalance) {

        if (initialBalance < 0)
            initialBalance = 0;

        this.balance = initialBalance;
    }

    // -----------------------------
    // Getter
    // -----------------------------

    public double getBalance() {
        return balance;
    }

    // -----------------------------
    // Deposit Money
    // -----------------------------

    /**
     * Adds money to the wallet.
     *
     * @param amount Deposit amount
     * @return true if successful
     */
    public boolean deposit(double amount) {

        if (amount <= 0)
            return false;

        balance += amount;

        balance = round(balance);

        return true;
    }

    // -----------------------------
    // Withdraw Money
    // -----------------------------

    /**
     * Withdraws money from the wallet.
     *
     * @param amount Withdrawal amount
     * @return true if successful
     */
    public boolean withdraw(double amount) {

        if (amount <= 0)
            return false;

        if (amount > balance)
            return false;

        balance -= amount;

        balance = round(balance);

        return true;
    }

    // -----------------------------
    // Balance Validation
    // -----------------------------

    /**
     * Checks if sufficient funds are available.
     */
    public boolean hasSufficientBalance(double amount) {

        return amount > 0 && balance >= amount;
    }

    /**
     * Resets wallet balance.
     */
    public void reset() {
        balance = 0;
    }

    // -----------------------------
    // Display Methods
    // -----------------------------

    /**
     * Prints current wallet balance.
     */
    public void displayBalance() {

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("--------------------------------");
        System.out.println("Wallet Balance : ₹" + df.format(balance));
        System.out.println("--------------------------------");
    }

    /**
     * Returns formatted balance.
     */
    public String getFormattedBalance() {

        DecimalFormat df = new DecimalFormat("0.00");

        return "₹" + df.format(balance);
    }

    // -----------------------------
    // Utility
    // -----------------------------

    /**
     * Rounds value to two decimal places.
     */
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {

        return "Wallet{" +
                "balance=" + balance +
                '}';
    }
}