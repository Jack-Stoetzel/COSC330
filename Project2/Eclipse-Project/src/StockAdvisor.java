import java.time.LocalDateTime;
//import only needed for testing main method

public class StockAdvisor
{
    //private Stock stock;
    private StockStrategy strategy;
    
    //create StockAdvisor object that uses given StockStrategy
    public StockAdvisor(StockStrategy ss)
    {
        //stock = s;
        strategy = ss;
    }
    
    //uses the chosen strategy to determine what action to take
    public int determineAction()
    {
        return strategy.determineAction();
    }
    
    //turns the value from determineAction into a String for what action to take
    //  only needed for human readability
    public String toString()
    {
        String message = "";
        int value = strategy.determineAction();
        if(value == 0)
        {
            message += "Buy";
        }
        else if(value == 1)
        {
            message += "Sell";
        }
        else if(value == 2)
        {
            message += "Hold";
        }
        else
        {
            message += "Error";
        }
        return message;
    }
    
    //main for testing purposes only
    public static void main(String[] args)
    {
        //StockAdvisor test = new StockAdvisor(new OneStrategy(new Stock("AAPL")));
        //System.out.println(test.determineAction());
        
        //TESTING ONESTRATEGY, buy/sell/hold
        System.out.println("\nOne Strategy");
        LocalDateTime time1 = LocalDateTime.of(2020, 4, 12, 10, 15);
        Stock s1 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time1);
        StockAdvisor test1 = new StockAdvisor(new OneStrategy(s1));
        System.out.println(test1.toString());
        
        //LocalDateTime time = LocalDateTime.of(2020, 4, 12, 10, 15);
        Stock s2 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, -5, 0, 0, time1);
        StockAdvisor test2 = new StockAdvisor(new OneStrategy(s2));
        System.out.println(test2.toString());
        
        //LocalDateTime time = LocalDateTime.of(2020, 4, 12, 10, 15);
        Stock s3 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5, 0, 0, time1);
        StockAdvisor test3 = new StockAdvisor(new OneStrategy(s3));
        System.out.println(test3.toString());
        
        //TESTING ALTSTRATEGY, buy/sell/hold
        System.out.println("\nAlt Strategy");
        //LocalDateTime time2 = LocalDateTime.of(2020, 4, 12, 10, 15);
        s1 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, -5, 0, 0, time1);
        test1 = new StockAdvisor(new AltStrategy(s1));
        System.out.println(test1.toString());
        
        //LocalDateTime time = LocalDateTime.of(2020, 4, 12, 10, 15);
        s2 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time1);
        test2 = new StockAdvisor(new AltStrategy(s2));
        System.out.println(test2.toString());
        
        //LocalDateTime time = LocalDateTime.of(2020, 4, 12, 10, 15);
        s3 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5, 0, 0, time1);
        test3 = new StockAdvisor(new AltStrategy(s3));
        System.out.println(test3.toString());
        
        //TESTING RANDOMSTRATEGY, buy/sell/hold
        System.out.println("\nRandom Strategy");
        LocalDateTime time4 = LocalDateTime.of(2020, 4, 12, 10, 15);
        s1 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5, 0, 0, time4);
        test1 = new StockAdvisor(new RandomStrategy(s1));
        System.out.println(test1.toString());
        
        LocalDateTime time2 = LocalDateTime.of(2020, 4, 12, 15, 22);
        s2 = new Stock("TEST1", "TEST", 0, 0, 0, 0, 0, 5, 0, 0, time2);
        test2 = new StockAdvisor(new RandomStrategy(s2));
        System.out.println(test2.toString());
        
        LocalDateTime time3 = LocalDateTime.of(2020, 4, 12, 13, 0);
        s3 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5, 0, 0, time3);
        test3 = new StockAdvisor(new RandomStrategy(s3));
        System.out.println(test3.toString());
        
        //TESTING OWNSTRATEGY
        System.out.println("\nOwn Strategy");
        s1 = new Stock("AMZN", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time4);
        test1 = new StockAdvisor(new OwnStrategy(s1));
        System.out.println(test1.toString());
        
        s2 = new Stock("TVIX", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time4);
        test2 = new StockAdvisor(new OwnStrategy(s2));
        System.out.println(test2.toString());
        
        s3 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time4);
        test3 = new StockAdvisor(new OwnStrategy(s3));
        System.out.println(test3.toString());
    }
}
