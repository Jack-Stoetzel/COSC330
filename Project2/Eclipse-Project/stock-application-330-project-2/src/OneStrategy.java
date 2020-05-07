public class OneStrategy implements StockStrategy
{
    //BUY if change is greater than 5%
    //SELL if change is less than -5%
    //HOLD if otherwise

    Stock stock;
    
    //create OneStrategy object that uses given Stock
    public OneStrategy(Stock s)
    {
        stock = s;
    }

    //returns action to do, look at StockStrategy for what numbers mean
    public int determineAction()
    {
        if(stock.getChangePercent() > 5)
        {
            return BUY;
        }
        else if(stock.getChangePercent() <= -5)
        {
            return SELL;
        }
        else
        {
            return HOLD;
        }
    }
}
