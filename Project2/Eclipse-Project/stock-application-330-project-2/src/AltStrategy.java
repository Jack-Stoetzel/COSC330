public class AltStrategy implements StockStrategy
{
    //BUY if stock price is 5% lower than previous close
    //SELL if the change is greater than 5%
    //HOLD is otherwise
    
    Stock stock;
    
    //create AltStrategy object that uses given Stock
    public AltStrategy(Stock s)
    {
        stock = s;
    }
    
    //returns action to do, look at StockStrategy for what numbers mean
    public int determineAction()
    {
        if(stock.getChangePercent() > 5)
        {
            return SELL;
        }
        else if(stock.getChangePercent() <= -5)
        {
            return BUY;
        }
        else
        {
            return HOLD;
        }
    }
}
