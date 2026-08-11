import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * StockMarket.java
 *
 * Manages all stocks available in the market.
 */
public class StockMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    // List of available stocks
    private ArrayList<Stock> stocks;

    /**
     * Constructor
     */
    public StockMarket() {

        stocks = new ArrayList<>();

        initializeDefaultStocks();
    }

    // ------------------------------------------------
    // Default Market Data
    // ------------------------------------------------

    /**
     * Creates sample stocks.
     */
    private void initializeDefaultStocks() {

        if (!stocks.isEmpty())
            return;

        stocks.add(new Stock("TCS",
                "Tata Consultancy Services",
                3850.00,
                5000));

        stocks.add(new Stock("INFY",
                "Infosys Ltd.",
                1575.50,
                7000));

        stocks.add(new Stock("RELIANCE",
                "Reliance Industries",
                2920.75,
                4500));

        stocks.add(new Stock("HDFCBANK",
                "HDFC Bank",
                1742.40,
                6000));

        stocks.add(new Stock("ICICIBANK",
                "ICICI Bank",
                1210.60,
                5500));

        stocks.add(new Stock("SBIN",
                "State Bank of India",
                890.25,
                9000));

        stocks.add(new Stock("LT",
                "Larsen & Toubro",
                3625.10,
                2500));

        stocks.add(new Stock("WIPRO",
                "Wipro Ltd.",
                542.80,
                8500));

        stocks.add(new Stock("HCLTECH",
                "HCL Technologies",
                1645.25,
                4200));

        stocks.add(new Stock("TATAMOTORS",
                "Tata Motors",
                982.45,
                10000));
    }

    // ------------------------------------------------
    // Getters
    // ------------------------------------------------

    /**
     * Returns all stocks.
     */
    public List<Stock> getStocks() {
        return stocks;
    }

    /**
     * Returns internal ArrayList.
     */
    public ArrayList<Stock> getStockList() {
        return stocks;
    }

    /**
     * Returns total listed stocks.
     */
    public int getStockCount() {
        return stocks.size();
    }

    /**
     * Returns true if market is empty.
     */
    public boolean isEmpty() {
        return stocks.isEmpty();
    }

    // ------------------------------------------------
    // Search Methods
    // ------------------------------------------------

    /**
     * Find stock using symbol.
     */
    public Stock findStock(String symbol) {

        if (symbol == null)
            return null;

        for (Stock stock : stocks) {

            if (stock.getSymbol()
                    .equalsIgnoreCase(symbol)) {

                return stock;
            }
        }

        return null;
    }

    /**
     * Search by company name.
     */
    public Stock findCompany(String company) {

        if (company == null)
            return null;

        for (Stock stock : stocks) {

            if (stock.getCompanyName()
                    .equalsIgnoreCase(company)) {

                return stock;
            }
        }

        return null;
    }

    /**
     * Returns true if stock exists.
     */
    public boolean containsStock(String symbol) {
        return findStock(symbol) != null;
    }

    // ------------------------------------------------
    // Stock Management
    // ------------------------------------------------

    /**
     * Adds stock.
     */
    public boolean addStock(Stock stock) {

        if (stock == null)
            return false;

        if (containsStock(stock.getSymbol()))
            return false;

        stocks.add(stock);

        return true;
    }

    /**
     * Removes stock.
     */
    public boolean removeStock(String symbol) {

        Stock stock = findStock(symbol);

        if (stock == null)
            return false;

        stocks.remove(stock);

        return true;
    }

    /**
     * Clears all stocks.
     */
    public void clearMarket() {
        stocks.clear();
    }

    // ------------------------------------------------
    // Sorting
    // ------------------------------------------------

    /**
     * Sort alphabetically.
     */
    public void sortBySymbol() {

        Collections.sort(
                stocks,
                Comparator.comparing(
                        Stock::getSymbol,
                        String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Sort by company name.
     */
    public void sortByCompany() {

        Collections.sort(
                stocks,
                Comparator.comparing(
                        Stock::getCompanyName,
                        String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Sort by current price.
     */
    public void sortByPrice() {

        Collections.sort(
                stocks,
                Comparator.comparingDouble(
                        Stock::getCurrentPrice));
    }

    // -------- PART 2 CONTINUES BELOW --------

        // ------------------------------------------------
    // Market Display
    // ------------------------------------------------

    /**
     * Displays all stocks in the market.
     */
    public void displayMarket() {

        if (stocks.isEmpty()) {

            System.out.println();
            System.out.println("No stocks available.");
            return;
        }

        System.out.println();
        System.out.println("==================== STOCK MARKET ====================");

        System.out.printf(
                "%-10s %-28s %-12s %-10s %-10s%n",
                "SYMBOL",
                "COMPANY",
                "PRICE",
                "SHARES",
                "CHANGE");

        System.out.println(
                "--------------------------------------------------------------------------");

        for (Stock stock : stocks) {
            stock.displayStock();
        }

        System.out.println(
                "--------------------------------------------------------------------------");
    }

    // ------------------------------------------------
    // Price Simulation
    // ------------------------------------------------

    /**
     * Updates the price of every stock.
     */
    public void updateAllPrices() {

        for (Stock stock : stocks) {
            stock.updateMarketPrice();
        }
    }

    // ------------------------------------------------
    // Statistics
    // ------------------------------------------------

    /**
     * Returns total available shares.
     */
    public int getTotalAvailableShares() {

        int total = 0;

        for (Stock stock : stocks) {
            total += stock.getAvailableShares();
        }

        return total;
    }

    /**
     * Returns total market value.
     */
    public double getTotalMarketValue() {

        double total = 0;

        for (Stock stock : stocks) {

            total += stock.getCurrentPrice()
                    * stock.getAvailableShares();
        }

        return total;
    }

    /**
     * Highest-priced stock.
     */
    public Stock getHighestPricedStock() {

        if (stocks.isEmpty())
            return null;

        Stock highest = stocks.get(0);

        for (Stock stock : stocks) {

            if (stock.getCurrentPrice()
                    > highest.getCurrentPrice()) {

                highest = stock;
            }
        }

        return highest;
    }

    /**
     * Lowest-priced stock.
     */
    public Stock getLowestPricedStock() {

        if (stocks.isEmpty())
            return null;

        Stock lowest = stocks.get(0);

        for (Stock stock : stocks) {

            if (stock.getCurrentPrice()
                    < lowest.getCurrentPrice()) {

                lowest = stock;
            }
        }

        return lowest;
    }

    /**
     * Displays market statistics.
     */
    public void displayStatistics() {

        System.out.println();
        System.out.println("============== MARKET STATISTICS ==============");

        System.out.println("Listed Stocks : " + getStockCount());

        System.out.println("Available Shares : "
                + getTotalAvailableShares());

        System.out.printf("Market Value : ₹%.2f%n",
                getTotalMarketValue());

        Stock highest = getHighestPricedStock();

        if (highest != null) {

            System.out.printf(
                    "Highest Price : %s (₹%.2f)%n",
                    highest.getSymbol(),
                    highest.getCurrentPrice());
        }

        Stock lowest = getLowestPricedStock();

        if (lowest != null) {

            System.out.printf(
                    "Lowest Price : %s (₹%.2f)%n",
                    lowest.getSymbol(),
                    lowest.getCurrentPrice());
        }

        System.out.println("==============================================");
    }

    // ------------------------------------------------
    // FileManager Support
    // ------------------------------------------------

    /**
     * Replace stock list after loading from file.
     */
    public void setStocks(ArrayList<Stock> stocks) {

        if (stocks == null) {
            this.stocks = new ArrayList<>();
        } else {
            this.stocks = stocks;
        }
    }

    /**
     * Reload default market if empty.
     */
    public void reloadDefaults() {

        if (stocks.isEmpty()) {
            initializeDefaultStocks();
        }
    }

    // ------------------------------------------------
    // Utility Methods
    // ------------------------------------------------

    /**
     * Returns stock by index.
     */
    public Stock getStock(int index) {

        if (index < 0 || index >= stocks.size())
            return null;

        return stocks.get(index);
    }

    /**
     * Prints a summary of the market.
     */
    public void printSummary() {

        System.out.println();
        System.out.println("============= STOCK MARKET SUMMARY =============");

        System.out.println("Stocks Listed : "
                + getStockCount());

        System.out.println("Total Shares  : "
                + getTotalAvailableShares());

        System.out.printf("Market Value  : ₹%.2f%n",
                getTotalMarketValue());

        System.out.println("===============================================");
    }

    @Override
    public String toString() {

        return "StockMarket{" +
                "stocks=" + stocks.size() +
                '}';
    }
}