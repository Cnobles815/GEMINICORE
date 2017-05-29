package base.destinyconnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nosec_000 on 5/28/2017.
 */
public class BungieAccountAccess {
    static private String stats;

    static final String apiKey = "978bafe49b1241bb9c0fb491a52176aa";

    static final private String FOX = "/1/4611686018433946292/";
    static final private String RICOCHET = "/1/4611686018433947109/";
    static final private String MAKA = "/2/4611686018433230205/";

    public String bNetAccess() throws IOException {
        return giveStats();
    }

    public String getBungieStuff() throws IOException {

        String url = "https://www.bungie.net/platform/Destiny/Stats/Account" + RICOCHET;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        //TODO THIS SETS THE HEADER. FIGURE OUT HOW THE WHY THE FUCK
        con.setRequestProperty("X-API-Key", apiKey);

        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to Bungie.Net : " + url);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        String response = "";

        while ((inputLine = in.readLine()) != null) {
            response += inputLine;
        }

        in.close();

        return response;
    }

//        System.out.println(response);
    public String parseBungieStuff(String response){
        // Uses Gson - https://github.com/google/gson
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        String parsed = json.getAsJsonObject("Response").getAsJsonObject("mergedAllCharacters")
                .getAsJsonObject("results").getAsJsonObject("allPvE").getAsJsonObject("allTime")
                .getAsJsonObject("kills").getAsJsonObject("basic").get("value").getAsString();

        stats = parsed;
//        System.out.println(stats);

        return parsed;
    }

    public String giveStats() throws IOException {
        stats = parseBungieStuff(getBungieStuff());
//        System.out.println(stats);
        return stats;
    }
}
