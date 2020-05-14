import org.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class Stock
{
    private String symbol;  //ticker symbol
    private String name;    //company name
    private double current; //current price of the stock today
    private double open;    //opening price of the stock today
    private double high;    //high of the stock today
    private double low;     //low of the stock today
    private double close;   //closing price of the stock yesterday
    private double changePercent; //percentage of change between today and yesterday, can be positive or negative
    private double change;  //value of change between today and yesterday, not percentage
    private int volume;     //volume of trades today
    private LocalDateTime timestamp; //timestamp for the current price
    private String date;    //holds formatted date, avoids user knowing LocalDateTime library
    private String time;    //holds formatted time, avoids user knowing LocalDateTime library

    //create Stock object with null values
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
    
    //create Stock object with values associated with the given ticker
    //  makes an API call
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
    
    //create Stock object with given parameters, useful for testing purposes
    public Stock(String symbol, String name, double current, double open, double high, double low, double close, double changePercent, double change, int volume, LocalDateTime timestamp)
    {
        this.symbol = symbol;
        this.name = name;
        this.current = current;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.changePercent = changePercent;
        this.change = change;
        this.volume = volume;
        this.timestamp = timestamp;
        date = setDate();
        time = setTime();
    }
    
    //static methods
    
    //gets stock info for given ticker and returns the JSONObject
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
    
    //returns String of date in format
    public String setDate()
    {
        return getMonths() + "/" + getDays() + "/" + getYears();
    }
    
    //return String of time in format (24hrs)
    public String setTime()
    {
        return String.format("%d:%02d", getHours(), getMinutes());
    }
    
    //gets for time values, allows for user to not know LocalDateTime library
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
    
    //getters for the Stock object attributes
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
    
    //returns String of all of Stock objects attributes
    //  useful for human readability and testing purposes
    public String toString()
    {
        return "Symbol: " + symbol + "\nName: " + name 
        + "\nCurrent price: " + current + "\nOpen: " + open
        + "\nPrevious close: " + close + "\nHigh: " + high
        + "\nLow: " + low + "\nChange percentage: " + changePercent
        + "\nChange: " + change + "\nVolume: " + volume 
        + "\nTimestamp: " + timestamp;
    }
    
}
