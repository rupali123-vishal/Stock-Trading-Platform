import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;


/**
 * Stock.java
 *
 * Represents a stock available in the stock market.
 */
public class Stock implements Serializable {


    private static final long serialVersionUID = 1L;


    private String symbol;
    private String companyName;
    private double currentPrice;
    private double previousPrice;
    private int availableShares;



    /**
     * Constructor
     */
    public Stock(String symbol,
                 String companyName,
                 double currentPrice,
                 int availableShares) {


        this.symbol = symbol.toUpperCase();
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.previousPrice = currentPrice;
        this.availableShares = availableShares;
    }



    // -------------------------
    // Getters
    // -------------------------


    public String getSymbol() {

        return symbol;
    }


    public String getCompanyName() {

        return companyName;
    }


    public double getCurrentPrice() {

        return currentPrice;
    }


    public double getPreviousPrice() {

        return previousPrice;
    }


    public int getAvailableShares() {

        return availableShares;
    }




    // -------------------------
    // Setters
    // -------------------------


    public void setCurrentPrice(double price) {


        if(price > 0) {

            previousPrice = currentPrice;
            currentPrice = price;
        }
    }



    public void setAvailableShares(int shares) {


        if(shares >= 0) {

            availableShares = shares;
        }
    }





    // -------------------------
    // Share Operations
    // -------------------------


    public void addShares(int quantity) {


        if(quantity > 0) {

            availableShares += quantity;
        }
    }




    public boolean removeShares(int quantity) {


        if(quantity <= 0)
            return false;


        if(quantity > availableShares)
            return false;



        availableShares -= quantity;


        return true;
    }






    // -------------------------
    // Price Calculation
    // -------------------------


    public double getPriceDifference() {


        return currentPrice - previousPrice;
    }




    public double getPercentageChange() {


        if(previousPrice == 0)
            return 0;



        return ((currentPrice - previousPrice)
                / previousPrice) * 100;
    }






    /**
     * Simulates stock price movement.
     */
    public void updateMarketPrice() {


        Random random = new Random();


        previousPrice = currentPrice;



        double changePercent =
                (random.nextDouble() * 10) - 5;



        currentPrice =
                currentPrice +
                (currentPrice * changePercent / 100);



        if(currentPrice < 1) {

            currentPrice = 1;
        }



        currentPrice =
                Math.round(currentPrice * 100.0)
                / 100.0;
    }






    /**
     * Display stock details.
     */
    public void displayStock() {


        DecimalFormat df =
                new DecimalFormat("0.00");



        String movement = "-";



        if(currentPrice > previousPrice)

            movement = "UP";


        else if(currentPrice < previousPrice)

            movement = "DOWN";




        System.out.printf(
                "%-10s %-25s ₹%-10s %-10d %-8s %.2f%%%n",
                symbol,
                companyName,
                df.format(currentPrice),
                availableShares,
                movement,
                getPercentageChange()
        );
    }







    @Override
    public String toString() {


        return "Stock{" +

                "symbol='" + symbol + '\'' +

                ", companyName='" + companyName + '\'' +

                ", currentPrice=" + currentPrice +

                ", previousPrice=" + previousPrice +

                ", availableShares=" + availableShares +

                '}';
    }







    @Override
    public boolean equals(Object obj) {


        if(this == obj)

            return true;



        if(!(obj instanceof Stock))

            return false;



        Stock stock = (Stock)obj;



        return symbol.equalsIgnoreCase(
                stock.symbol);
    }






    @Override
    public int hashCode() {


        return symbol.toUpperCase()
                .hashCode();
    }
}