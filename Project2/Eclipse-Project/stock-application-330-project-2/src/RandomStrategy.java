public class RandomStrategy implements StockStrategy
{
    //BUY if ticker symbol is 4 characters
    //SELL if time is between 1:00PM and 3:22PM (13:00 and 15:22)
    //HOLD if above are true
    //defaults to hold if none are true

    Stock stock;

    //create RandomStrategy object that uses given Stock
    public RandomStrategy(Stock s)
    {
        stock = s;
    }
    
    //returns action to do, look at StockStrategy for what numbers mean
    public int determineAction()
    {
        final boolean isTickerLength = stock.getSymbol().length() == 4;
        final boolean isBetweenTime = checkTimeRange();
        
        if(isTickerLength && isBetweenTime)
        {
            return HOLD;
        }
        else if(isTickerLength)
        {
            return BUY;
        }
        else if(isBetweenTime)
        {
            return SELL;
        }
        else
        {
            return HOLD;
        }
    }
    
    //returns true if the time is in between 1:00pm and 3:22pm, inclusive
    public boolean checkTimeRange()
    {
        final boolean isBetweenHours = stock.getHours() >= 13 && stock.getHours() <= 15;
        final boolean isBetweenMinutes = stock.getMinutes() <= 22;
        
        if(stock.getHours() == 15)
        {
            return isBetweenMinutes;
        }
        else
        {
            return isBetweenHours;
        }
    }
}
