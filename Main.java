import java.util.Scanner;

/**
 * Main.java
 *
 * Entry point for the Stock Trading Platform.
 */
public class Main {

    private Scanner scanner;

    private LoginManager loginManager;
    private StockMarket stockMarket;
    private TradingService tradingService;
    private FileManager fileManager;
    private Admin admin;

    /**
     * Constructor
     */
    public Main() {

        scanner = new Scanner(System.in);

        loginManager = new LoginManager();
        stockMarket = new StockMarket();
        tradingService = new TradingService(stockMarket);
        fileManager = new FileManager();
        admin = new Admin();

        loadData();
    }

    /**
     * Load saved data.
     */
    private void loadData() {

        try {

            fileManager.loadUsers(loginManager);
            fileManager.loadStocks(stockMarket);

        } catch (Exception e) {

            System.out.println(
                    "No previous data found. Starting with default data.");
        }
    }

    /**
     * Save application data.
     */
    private void saveData() {

        try {

            fileManager.saveUsers(loginManager);
            fileManager.saveStocks(stockMarket);

        } catch (Exception e) {

            System.out.println(
                    "Unable to save data.");
        }
    }

    /**
     * Starts the application.
     */
    public void start() {

        Utils.printApplicationHeader();

        boolean running = true;

        while (running) {

            Utils.printMainMenu();

            System.out.print("Enter your choice: ");

            int choice = Validation.readInt(scanner);

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    loginUser();
                    break;

                case 3:
                    adminLogin();
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        saveData();

        Utils.printGoodbye();

        scanner.close();
    }

    // ------------------------------------------------
    // Main Menu Operations
    // ------------------------------------------------
    // ------------------------------------------------
// Main Menu Operations
// ------------------------------------------------

/**
 * Register a new user.
 */
private void registerUser() {
    // Read full name
    // Read username
    // Read password
    // Validate input
    // Register through LoginManager
    // Display success/error message
}

/**
 * Login as user.
 */
private void loginUser() {
    // Read username
    // Read password
    // Authenticate
    // Open user menu on success
}

/**
 * Login as administrator.
 */
private void adminLogin() {
    // Read admin username
    // Read admin password
    // Authenticate using Admin
    // Open admin menu on success
}

/**
 * User menu loop.
 */
private void userMenu() {
    // Will be completed in Part 2
}

/**
 * Admin menu loop.
 */
private void adminMenu() {
    // Will be completed in Part 3
}
// ------------------------------------------------
// User Menu
// ------------------------------------------------

/**
 * Displays the user menu and handles user operations.
 */
private void userMenu() {

    while (loginManager.isLoggedIn()) {

        Utils.printUserMenu();

        System.out.print("Enter your choice: ");

        int choice = Validation.readInt(scanner);

        switch (choice) {

            case 1:
                stockMarket.displayMarket();
                break;

            case 2:
                searchStock();
                break;

            case 3:
                buyStock();
                break;

            case 4:
                sellStock();
                break;

            case 5:
                // Continues in Part 2B
                showPortfolio();
                break;

            case 6:
                // Continues in Part 2B
                showWallet();
                break;

            case 7:
                // Continues in Part 2B
                depositMoney();
                break;

            case 8:
                // Continues in Part 2B
                showTransactionHistory();
                break;

            case 9:
                // Continues in Part 2B
                showProfile();
                break;

            case 10:

                loginManager.logout();

                Utils.success("Logged out successfully.");

                return;

            default:

                Utils.error("Invalid choice.");
        }

        Utils.pause(scanner);
    }
}

/**
 * Search stock by symbol.
 */
private void searchStock() {

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    Stock stock = stockMarket.findStock(symbol);

    if (stock == null) {

        Utils.error("Stock not found.");
        return;
    }

    System.out.println();

    System.out.println("============= STOCK DETAILS =============");

    stock.displayStock();

    System.out.println("=========================================");
}

/**
 * Buy stock.
 */
private void buyStock() {

    User user = loginManager.getCurrentUser();

    if (user == null) {

        Utils.error("Please login first.");
        return;
    }

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    System.out.print("Enter Quantity: ");

    int quantity = Validation.readPositiveInt(scanner);

    tradingService.buyStock(user, symbol, quantity);
}

/**
 * Sell stock.
 */
private void sellStock() {

    User user = loginManager.getCurrentUser();

    if (user == null) {

        Utils.error("Please login first.");
        return;
    }

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    System.out.print("Enter Quantity: ");

    int quantity = Validation.readPositiveInt(scanner);

    tradingService.sellStock(user, symbol, quantity);
}

// ------------------------------------------------
// Main.java Part 2B continues below
// ------------------------------------------------

// ------------------------------------------------
// User Menu Operations (Part 2B)
// ------------------------------------------------

/**
 * Display current user's portfolio.
 */
private void showPortfolio() {

    User user = loginManager.getCurrentUser();

    if (user == null) {
        Utils.error("Please login first.");
        return;
    }

    tradingService.displayPortfolio(user);
}

/**
 * Display wallet balance.
 */
private void showWallet() {

    User user = loginManager.getCurrentUser();

    if (user == null) {
        Utils.error("Please login first.");
        return;
    }

    user.getWallet().displayBalance();
}

/**
 * Deposit money into wallet.
 */
private void depositMoney() {

    User user = loginManager.getCurrentUser();

    if (user == null) {
        Utils.error("Please login first.");
        return;
    }

    System.out.print("Enter amount to deposit: ₹");

    double amount = Validation.readPositiveDouble(scanner);

    if (user.deposit(amount)) {

        Utils.success("₹" + String.format("%.2f", amount)
                + " deposited successfully.");

        System.out.println("Current Balance : "
                + user.getWallet().getFormattedBalance());

    } else {

        Utils.error("Deposit failed.");
    }
}

/**
 * Display transaction history.
 */
private void showTransactionHistory() {

    User user = loginManager.getCurrentUser();

    if (user == null) {
        Utils.error("Please login first.");
        return;
    }

    tradingService.displayTransactionHistory(user);
}

/**
 * Display logged-in user's profile.
 */
private void showProfile() {

    User user = loginManager.getCurrentUser();

    if (user == null) {
        Utils.error("Please login first.");
        return;
    }

    user.displayProfile();
}

// ------------------------------------------------
// Main.java Part 3A starts below
// ------------------------------------------------

// ------------------------------------------------
// Admin Menu (Part 3A)
// ------------------------------------------------

/**
 * Displays the admin menu.
 */
private void adminMenu() {

    boolean running = true;

    while (running) {

        Utils.printAdminMenu();

        System.out.print("Enter your choice: ");

        int choice = Validation.readInt(scanner);

        switch (choice) {

            case 1:
                admin.viewAllStocks(stockMarket);
                break;

            case 2:
                addStock();
                break;

            case 3:
                removeStock();
                break;

            case 4:
                updateStockPrice();
                break;

            case 5:
                addStockShares();
                break;

            case 6:
                // Continues in Part 3B
                viewUsers();
                break;

            case 7:
                // Continues in Part 3B
                refreshMarket();
                break;

            case 8:
                // Continues in Part 3B
                showMarketStatistics();
                break;

            case 9:

                Utils.success("Admin logged out.");

                running = false;
                break;

            default:
                Utils.error("Invalid choice.");
        }

        if (running) {
            Utils.pause(scanner);
        }
    }
}

/**
 * Add a new stock.
 */
private void addStock() {

    System.out.print("Enter Stock Symbol: ");
    String symbol = scanner.nextLine().trim().toUpperCase();

    System.out.print("Enter Company Name: ");
    String company = scanner.nextLine().trim();

    System.out.print("Enter Current Price: ₹");
    double price = Validation.readPositiveDouble(scanner);

    System.out.print("Enter Available Shares: ");
    int shares = Validation.readPositiveInt(scanner);

    if (admin.addStock(
            stockMarket,
            symbol,
            company,
            price,
            shares)) {

        Utils.success("Stock added successfully.");

    } else {

        Utils.error("Unable to add stock.");
    }
}

/**
 * Remove an existing stock.
 */
private void removeStock() {

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    if (admin.removeStock(stockMarket, symbol)) {

        Utils.success("Stock removed successfully.");

    } else {

        Utils.error("Stock not found.");
    }
}

/**
 * Update stock price.
 */
private void updateStockPrice() {

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    System.out.print("Enter New Price: ₹");

    double price = Validation.readPositiveDouble(scanner);

    if (admin.updatePrice(
            stockMarket,
            symbol,
            price)) {

        Utils.success("Stock price updated.");

    } else {

        Utils.error("Unable to update stock.");
    }
}

/**
 * Add shares to an existing stock.
 */
private void addStockShares() {

    System.out.print("Enter Stock Symbol: ");

    String symbol = scanner.nextLine().trim().toUpperCase();

    System.out.print("Enter Shares to Add: ");

    int quantity = Validation.readPositiveInt(scanner);

    if (admin.addShares(
            stockMarket,
            symbol,
            quantity)) {

        Utils.success("Shares added successfully.");

    } else {

        Utils.error("Unable to add shares.");
    }
}

// ------------------------------------------------
// Main.java Part 3B continues below
// ------------------------------------------------

 // ------------------------------------------------
// Admin Menu Operations (Part 3B)
// ------------------------------------------------

/**
 * Display all registered users.
 */
private void viewUsers() {

    admin.viewUsers(loginManager.getUsers());
}

/**
 * Refresh all stock market prices.
 */
private void refreshMarket() {

    admin.refreshMarketPrices(stockMarket);

    Utils.success(
            "Market prices updated successfully.");
}

/**
 * Display market statistics.
 */
private void showMarketStatistics() {

    admin.displayStatistics(
            stockMarket,
            loginManager.getUsers());

    stockMarket.displayStatistics();
}

// ------------------------------------------------
// Application Entry Point
// ------------------------------------------------

/**
 * Main method.
 */
public static void main(String[] args) {

    Main application = new Main();

    application.start();
}

}