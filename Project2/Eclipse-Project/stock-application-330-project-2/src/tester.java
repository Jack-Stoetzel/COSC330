import org.json.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class tester {

    public static void main(String[] args) {
        //-Xbootclasspath/a:path
        // TODO Auto-generated method stub
        String ticker = "AAPL,GOOG,VUG";
        String messageJSONStr = "";
        
        try
        {
            //URL url = new URL("https://financialmodelingprep.com/api/v3/company/profile/AAPL");
            URL url = new URL("https://financialmodelingprep.com/api/v3/quote/AAPL");
            //URL url = new URL("https://financialmodelingprep.com/api/v3/stock/real-time-price/" + ticker);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")))
            {
                for (String line; (line = reader.readLine()) != null;) 
                {
                    //System.out.println(line);
                    messageJSONStr += line;
                }
                JSONArray test = new JSONArray(messageJSONStr);
                System.out.println(test.get(0).getClass());
                JSONObject info = (JSONObject)test.get(0);
                System.out.println(info.keySet());
                //JSONObject test = new JSONObject(messageJSONStr);
                //System.out.println(test.keySet());
                //System.out.println(test.getString("symbol"));
                //System.out.println(((JSONObject)test.get("profile")).get("price"));
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
    }
}
