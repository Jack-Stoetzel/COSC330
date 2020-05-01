import org.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class Stock
{
    private String symbol;
    private String name;
    private double current;
    private double open;
    private double high;
    private double low;
    private double close;
    private double changePercent;
    private double change;
    private int volume;
    private LocalDateTime timestamp;
    private String date;
    private String time;

    public Stock()
    {
        symbol = "null";
        name = "null";
        current = 0;
        open = 0;
        high = 0;
        low = 0;
        close = 0;
        changePercent = 0;
        change = 0;
        volume = 0;
        timestamp = LocalDateTime.now();
        date = setDate();
        time = setTime();
    }
    
    public Stock(String ticker)
    {
        JSONObject obj = getStockInfo(ticker);
        //need to check if obj is null
        if(obj == null)
        {
            symbol = "null";
            name = "null";
            current = 0;
            open = 0;
            high = 0;
            low = 0;
            close = 0;
            changePercent = 0;
            change = 0;
            volume = 0;
            timestamp = LocalDateTime.now();
            date = setDate();
            time = setTime();
        }
        else
        {
            symbol = obj.getString("symbol");
            name = obj.getString("name");
            current = obj.getDouble("price");
            open = obj.getDouble("open");
            high = obj.getDouble("dayHigh");
            low = obj.getDouble("dayLow");
            close = obj.getDouble("previousClose");
            changePercent = obj.getDouble("changesPercentage");
            change = obj.getDouble("change");
            volume = obj.getInt("volume");
            timestamp = LocalDateTime.now();
            date = setDate();
            time = setTime();
        }
    }
    
    //static methods
    public static JSONObject getStockInfo(String ticker)
    {
        try
        {
            String messageJSONStr = "";
        
            URL url = new URL("https://financialmodelingprep.com/api/v3/quote/" + ticker);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")))
            {
                for (String line; (line = reader.readLine()) != null;) 
                {
                    //System.out.println(line);
                    messageJSONStr += line;
                }
                JSONArray test = new JSONArray(messageJSONStr);
                //System.out.println(test.get(0).getClass());
                JSONObject info = (JSONObject)test.get(0);
                //System.out.println(info.keySet());
                return info;
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
    
    
    //instance methods
    public String setDate()
    {
        return getMonths() + "/" + getDays() + "/" + getYears();
    }
    public String setTime()
    {
        return String.format("%d:%02d", getHours(), getMinutes());
    }
    
    //gets for time values
    public int getHours()
    {
        return timestamp.getHour();
    }
    public int getMinutes()
    {
        return timestamp.getMinute();
    }
    public int getDays()
    {
        return timestamp.getDayOfMonth();
    }
    public int getMonths()
    {
        return timestamp.getMonthValue();
    }
    public int getYears()
    {
        return timestamp.getYear();
    }
    
    //getters
    public String getSymbol()
    {
        return symbol;
    }
    public String getName()
    {
        return name;
    }
    public double getCurrent()
    {
        return current;
    }
    public double getOpen()
    {
        return open;
    }
    public double getHigh()
    {
        return high;
    }
    public double getLow()
    {
        return low;
    }
    public double getClose()
    {
        return close;
    }
    public double getChangePercent()
    {
        return changePercent;
    }
    public double getChange()
    {
        return change;
    }
    public int getVolume()
    {
        return volume;
    }
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    
    public String toString()
    {
        return "Symbol: " + symbol + "\nName: " + name 
        + "\nCurrent price: " + current + "\nOpen: " + open
        + "\nPrevious close: " + close + "\nHigh: " + high
        + "\nLow: " + low + "\nChange percentage: " + changePercent
        + "\nChange: " + change + "\nVolume: " + volume 
        + "\nTimestamp: " + timestamp;
    }
    
    //main
    public static void main(String[] args)
    {
        Stock test = new Stock("AAPL");
        System.out.println("\n" + test.toString());
        
        Stock test2 = new Stock("APL");
        System.out.println("\n" + test2.toString());
        
        Stock test3 = new Stock("VUG");
        System.out.println("\n" + test3.toString());
        //System.out.println("\n" + test3.getHours() + ":" + test3.getMinutes()); //mins will need to be formatted
        System.out.println(test3.getTime());
        
        System.out.println("\n" + test3.getMonths() + "/" + test3.getDays() + "/" + test3.getYears()); //or use test3.getDate()
        
    }
}
