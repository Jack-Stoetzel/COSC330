import org.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class OwnStrategy implements StockStrategy
{
    //BUY if in biggest losers
    //https://financialmodelingprep.com/api/v3/stock/losers
    //SELL if in biggest gainers
    //https://financialmodelingprep.com/api/v3/stock/gainers
    //HOLD if neither
    
    Stock stock;
    
    //create OwnStrategy object that uses given Stock
    public OwnStrategy(Stock s)
    {
        stock = s;
    }
    
    //returns action to do, look at StockStrategy for what numbers mean
    public int determineAction()
    {
        //assumes that losers and gainers is the same length, looks like it should always be 10
        String[] losers = getBiggestLosers();
        String[] gainers = getBiggestGainers();
        
        for(int i = 0; i < gainers.length; i++)
        {
            if(stock.getSymbol().equals(losers[i]))
            {
                return BUY; //is loser
            }
            else if(stock.getSymbol().equals(gainers[i]))
            {
                return SELL; //is gainer
            }
        }
        return HOLD; //is neither
    }
    
    //returns a String[] of the biggest gainers based on API call
    public String[] getBiggestGainers()
    {
        String urlGainer = "https://financialmodelingprep.com/api/v3/stock/gainers";
        return dataToStringArr(useAPICall(urlGainer));
    }
    
    //returns a String[] of biggest losers based on API call
    public String[] getBiggestLosers()
    {
        String urlLoser = "https://financialmodelingprep.com/api/v3/stock/losers";
        return dataToStringArr(useAPICall(urlLoser));
    }
    
    //sends API request and returns JSONObject with the data from API call
    public JSONObject useAPICall(String u)
    {
        try
        {
            String messageJSONStr = "";
        
            URL url = new URL(u);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")))
            {
                for (String line; (line = reader.readLine()) != null;) 
                {
                    //System.out.println(line);
                    messageJSONStr += line;
                }
                JSONObject data = new JSONObject(messageJSONStr);
                return data;
            }
            catch(Exception e)
            {
                System.out.println("ERROR 8998 - Failed to return BufferedReader");
            }
        }
        catch(MalformedURLException e)
        {
            System.out.println("ERROR 404 - Bad URL");
        }
        return null;
    }
    
    //turns the JSONObject into a String[]
    //  requires the JSONObject to hold a JSONArray
    public String[] dataToStringArr(JSONObject data)
    {
        JSONArray dataArr = (JSONArray)data.get(data.keySet().iterator().next());
                
        String[] tickers = new String[dataArr.length()];
        for(int i = 0; i < dataArr.length(); i++)
        {
            JSONObject indObj = (JSONObject)dataArr.get(i);
            tickers[i] = indObj.getString("ticker");
        }
                
        return tickers;
    }
    
    //prints a String[] to console
    //  useful to know which stocks are gainers/losers for testing
    public static void printTicker(String[] tickers)
    {
        for(int i = 0; i < tickers.length; i++)
        {
            System.out.print(tickers[i] + " ");
        }
        System.out.println();
    }
    
    //main for testing purposes only
    public static void main(String[] args)
    {
        LocalDateTime time1 = LocalDateTime.of(2020, 4, 12, 10, 15);
        Stock s1 = new Stock("AMZN", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time1);
        OwnStrategy test1 = new OwnStrategy(s1);
        System.out.println("Test1: " + test1.determineAction());
        
        Stock s2 = new Stock("TVIX", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time1);
        OwnStrategy test2 = new OwnStrategy(s2);
        System.out.println("Test2: " + test2.determineAction());
        
        Stock s3 = new Stock("TEST", "TEST", 0, 0, 0, 0, 0, 5.5, 0, 0, time1);
        OwnStrategy test3 = new OwnStrategy(s3);
        System.out.println("Test3: " + test3.determineAction());
    }
}
