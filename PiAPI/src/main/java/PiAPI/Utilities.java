package PiAPI;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

/**
 * <h1>Utilities</h1>
 * Utilities for Pi such as
 * <ul>
 * <li>HTTP GET response</li>
 * <li>HTTP POST response</li>
 * <li>JSON helpers</li>
 * </ul>
 */
public class Utilities {
    private static Gson gson = new Gson();

    /**
     * Turns given object into a JSON formatted String
     * @param object Object to be formatted to JSON
     * @return JSON formatted String
     */
    public static String toJSON(Object object) {
        return gson.toJson(object);
    }

    /**
     * Gets response from HttpURLConnection
     * @param connection The HttpURLConnection
     * @return The connection response
     */
    private static String getHttpResponse(HttpURLConnection connection) {  
        try {
            int status = connection.getResponseCode();

            InputStreamReader reader;
            BufferedReader in;
            StringBuffer res = new StringBuffer();
            String response;

                
            if (status > 299) {
                reader = new InputStreamReader(connection.getErrorStream());
            } else {
                reader = new InputStreamReader(connection.getInputStream());
            }
    
            in = new BufferedReader(reader);

            while ((response = in.readLine()) != null) {
                res.append(response);
            }

            in.close();

            return res.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds a new HttpURLConnection
     * @param url The URL to connect to
     * @param requestType The HTTP request type
     * @param timeout Connection timeout in miliseconds
     * @return New HttpURLConnection
     */
    private static HttpURLConnection buildHttpRequest(String url, String requestType, int timeout) {
        try {
            URL reqUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) reqUrl.openConnection();
            con.setRequestMethod(requestType);
            con.setConnectTimeout(timeout);
            con.setReadTimeout(5000);
            con.setDoOutput(true);

            return con;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds a new HttpURLConnection
     * @param url The URL to connect to
     * @param requestType The HTTP Request Type
     * @return New HttpURLConnection
     */
    private static HttpURLConnection buildHttpRequest(String url, String requestType) {
        return buildHttpRequest(url, requestType, 5000);
    }

    /**
     * Posts a given String to a URL
     * @param url The URL to post to
     * @param content The String to send to the URL
     * @return HTTP Response
     */
    public static String post(String url, String content) {
        HttpURLConnection connection = null;
        try {
            connection = buildHttpRequest(url, "POST");
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close();

            String res = getHttpResponse(connection);
            connection.disconnect();

            return res;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
        }
    }

    /**
     * Gets response from given URL
     * @param url The URL to get response from
     * @return The HTTP response
     */
    public static String get(String url) {
        HttpURLConnection connection = null;
        try {
            connection = buildHttpRequest(url, "GET");
            String res = getHttpResponse(connection);
            connection.disconnect();

            return res;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            connection.disconnect();
        }
    }
}