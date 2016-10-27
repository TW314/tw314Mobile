package tw314.tw314mobile.testeWS;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pedro on 26/10/2016.
 */

public class TicketHttp {

    public static final String TICKET_ROUTE = "http://localhost:3000/ticket/";

    public static HttpURLConnection setConnection(String requestURL) throws IOException{
        final int SEGUNDOS = 1000;
        URL url = new URL(requestURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10 * SEGUNDOS);
        connection.setConnectTimeout(20 * SEGUNDOS);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.connect();
        return connection;
    }

    public static boolean hasConnection(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static Ticket getTicketJson(String codigoAcesso){
        try{
            HttpURLConnection connection = setConnection(TICKET_ROUTE + codigoAcesso);

            int response = connection.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK){
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Ticket ticket = restTemplate.getForObject((TICKET_ROUTE + codigoAcesso), Ticket.class);
                return ticket;
            }
        } catch (IOException e) {
            Log.e("TicketHTTP: ", e.getMessage(), e);
        }

        return null;
    }

}
