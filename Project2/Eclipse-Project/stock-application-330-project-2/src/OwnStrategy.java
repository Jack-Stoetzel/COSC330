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
    String[] losers;
    String[] gainers;
    
    //create OwnStrategy object that uses given Stock
    public OwnStrategy(Stock s)
    {
        stock = s;
    }
    
    //returns action to do, look at StockStrategy for what numbers mean
    public int determineAction()
    {
        //assumes that losers and gainers is the same length, looks like it should always be 10
        losers = getBiggestLosers();
        gainers = getBiggestGainers();
        
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
    
    //returns the top 5 losers.
    public String getLosers()
    {
    	String loser_list = "";
    	for(int count = 0; count < 5; count++) 
    	{
    		loser_list = loser_list + losers[count] + " ";
    	}
    	return loser_list;
    }
    
    //returns the top 5 gainers.
    public String getGainers()
    {
    	String gainer_list = "";
    	for(int count = 0; count < 5; count++)
    	{
    		gainer_list = gainer_list + gainers[count] + " ";
    	}
    	return gainer_list;
    }
    
}
