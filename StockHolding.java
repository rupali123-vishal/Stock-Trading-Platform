import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * StockHolding.java
 *
 * Represents a user's holding of a particular stock.
 */
public class StockHolding implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stockSymbol;
    private String companyName;

    private int quantity;
    private double averageBuyPrice;

    /**
     * Constructor
     */
    public StockHolding(String stockSymbol,
                        String companyName,
                        int quantity,
                        double averageBuyPrice) {

        this.stockSymbol = stockSymbol.toUpperCase();
        this.companyName = companyName;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }

    // -----------------------------
    // Getters
    // -----------------------------

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    // -----------------------------
    // Setters
    // -----------------------------

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    // -----------------------------
    // Buy Shares
    // -----------------------------

    /**
     * Updates quantity and average buy price after purchasing
     * additional shares.
     */
    public void buyShares(int newQuantity, double buyPrice) {

        if (newQuantity <= 0)
            return;

        double totalInvestment =
                (averageBuyPrice * quantity)
                + (buyPrice * newQuantity);

        quantity += newQuantity;

        averageBuyPrice = totalInvestment / quantity;
    }

    // -----------------------------
    // Sell Shares
    // -----------------------------

    /**
     * Returns true if shares are sold successfully.
     */
    public boolean sellShares(int sellQuantity) {

        if (sellQuantity <= 0)
            return false;

        if (sellQuantity > quantity)
            return false;

        quantity -= sellQuantity;

        return true;
    }

    /**
     * Returns true if all shares are sold.
     */
    public boolean isEmpty() {
        return quantity == 0;
    }

    // -----------------------------
    // Calculations
    // -----------------------------

    /**
     * Total investment made.
     */
    public double getInvestmentValue() {
        return quantity * averageBuyPrice;
    }

    /**
     * Current market value.
     */
    public double getCurrentValue(double currentPrice) {
        return quantity * currentPrice;
    }

    /**
     * Profit/Loss amount.
     */
    public double getProfitLoss(double currentPrice) {
        return getCurrentValue(currentPrice)
                - getInvestmentValue();
    }

    /**
     * Profit/Loss percentage.
     */
    public double getProfitLossPercentage(double currentPrice) {

        if (averageBuyPrice == 0)
            return 0;

        return ((currentPrice - averageBuyPrice)
                / averageBuyPrice) * 100;
    }

    // -----------------------------
    // Display
    // -----------------------------

    public void display(double currentPrice) {

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.printf(
                "%-8s %-20s %-8d ₹%-12s ₹%-12s ₹%-12s %.2f%%%n",
                stockSymbol,
                companyName,
                quantity,
                df.format(averageBuyPrice),
                df.format(currentPrice),
                df.format(getProfitLoss(currentPrice)),
                getProfitLossPercentage(currentPrice)
        );
    }

    @Override
    public String toString() {

        return "StockHolding{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", quantity=" + quantity +
                ", averageBuyPrice=" + averageBuyPrice +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof StockHolding))
            return false;

        StockHolding other = (StockHolding) obj;

        return stockSymbol.equalsIgnoreCase(other.stockSymbol);
    }

    @Override
    public int hashCode() {
        return stockSymbol.toUpperCase().hashCode();
    }
}