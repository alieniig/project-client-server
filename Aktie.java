import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Aktie {
    private String name;
    private double preis;
    private double einstandspreis;

    public Aktie(String name, double preis, double einstandspreis) {
        this.name = name;
        this.preis = preis;
        this.einstandspreis = einstandspreis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getEinstandspreis() {
        return einstandspreis;
    }

    public void setEinstandspreis(double einstandspreis) {
        this.einstandspreis = einstandspreis;
    }

    public static double getAktienpreis(String symbol) {
        try {
            String apiKey = "MAR7YEK8ELI9TYHV";
            @SuppressWarnings("deprecation")
            URL url = new URL(
                    "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString());
            double price = json.getJSONObject("Global Quote").getDouble("05. price");

            return price;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static void main(String[] args) {
        // Existing code...

        double price = getAktienpreis("AAPL");
        System.err.println(price);

        // Rest of the code...
    }
}