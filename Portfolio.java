import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Portfolio.java
 *
 * Manages all stock holdings owned by a user.
 */
public class Portfolio implements Serializable {


    private static final long serialVersionUID = 1L;


    private ArrayList<StockHolding> holdings;



    /**
     * Constructor
     */
    public Portfolio() {

        holdings = new ArrayList<>();
    }



    /**
     * Returns all holdings.
     */
    public List<StockHolding> getHoldings() {

        return holdings;
    }



    /**
     * Returns portfolio size.
     */
    public int size() {

        return holdings.size();
    }



    /**
     * Check empty portfolio.
     */
    public boolean isEmpty() {

        return holdings.isEmpty();
    }



    /**
     * Find holding by symbol.
     */
    public StockHolding findHolding(String symbol) {


        if(symbol == null)
            return null;


        for(StockHolding holding : holdings) {


            if(holding.getStockSymbol()
                    .equalsIgnoreCase(symbol)) {

                return holding;
            }
        }


        return null;
    }




    /**
     * Check stock exists.
     */
    public boolean contains(String symbol) {

        return findHolding(symbol)!=null;
    }




    /**
     * Buy stock.
     */
    public void buyStock(Stock stock,
                         int quantity) {



        if(stock == null || quantity<=0)
            return;



        StockHolding holding =
                findHolding(stock.getSymbol());



        if(holding==null) {


            holding = new StockHolding(
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    quantity,
                    stock.getCurrentPrice()
            );


            holdings.add(holding);


        } else {


            holding.buyShares(
                    quantity,
                    stock.getCurrentPrice()
            );
        }
    }




    /**
     * Sell stock.
     */
    public boolean sellStock(String symbol,
                             int quantity) {



        if(quantity<=0)
            return false;



        StockHolding holding =
                findHolding(symbol);



        if(holding==null)
            return false;



        boolean result =
                holding.sellShares(quantity);



        if(!result)
            return false;



        if(holding.isEmpty()) {

            holdings.remove(holding);
        }



        return true;
    }




    /**
     * Total investment amount.
     */
    public double getTotalInvestment() {


        double total=0;


        for(StockHolding holding: holdings) {


            total += holding.getInvestmentValue();
        }


        return total;
    }




    /**
     * Current portfolio value.
     */
    public double getCurrentValue(
            StockMarket market) {


        if(market==null)
            return 0;



        double total=0;



        for(StockHolding holding: holdings) {


            Stock stock =
                    market.findStock(
                            holding.getStockSymbol()
                    );



            if(stock!=null) {


                total += holding.getCurrentValue(
                        stock.getCurrentPrice()
                );
            }
        }



        return total;
    }




    /**
     * Profit or Loss.
     */
    public double getProfitLoss(
            StockMarket market) {


        return getCurrentValue(market)
                -
                getTotalInvestment();
    }




    /**
     * Profit percentage.
     */
    public double getProfitLossPercentage(
            StockMarket market) {


        double investment =
                getTotalInvestment();



        if(investment==0)
            return 0;



        return
        (getProfitLoss(market)/investment)*100;
    }




    /**
     * Display portfolio.
     */
    public void displayPortfolio(
            StockMarket market) {



        if(holdings.isEmpty()) {


            System.out.println(
                    "Portfolio is empty.");

            return;
        }



        DecimalFormat df =
                new DecimalFormat("0.00");



        System.out.println();
        System.out.println(
        "============== MY PORTFOLIO ==============");



        for(StockHolding holding: holdings) {


            Stock stock =
                    market.findStock(
                            holding.getStockSymbol()
                    );



            if(stock!=null) {


                holding.display(
                        stock.getCurrentPrice()
                );
            }
        }



        System.out.println("-------------------------------------------");



        System.out.println(
                "Investment : ₹"+
                df.format(
                getTotalInvestment()));



        System.out.println(
                "Current    : ₹"+
                df.format(
                getCurrentValue(market)));



        System.out.println(
                "Profit/Loss : ₹"+
                df.format(
                getProfitLoss(market)));



        System.out.println(
                "Return     : "+
                df.format(
                getProfitLossPercentage(market))
                +"%");



        System.out.println(
        "===========================================");
    }





    /**
     * Remove all stocks.
     */
    public void clear(){

        holdings.clear();
    }





    @Override
    public String toString(){

        return "Portfolio{" +
                "holdings=" + holdings +
                '}';
    }
}