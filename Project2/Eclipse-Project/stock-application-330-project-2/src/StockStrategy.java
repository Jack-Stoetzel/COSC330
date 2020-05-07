public interface StockStrategy
{
    final int BUY = 0;  //recommend BUY stock
    final int SELL = 1; //recommend SELL stock
    final int HOLD = 2; //recommend HOLD stock
    
    //will return 0, 1, or 2 that corresponds to the above action
    public int determineAction();
}
