import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsRequester {
    /**
     * Gets the content of the "GET" request
     * @param url URL to use for the request
     * @return Resulting content in a string
     * @throws IOException
     */
    public static String getPage( URL url ) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream in_stream = null;
        int returnCode = 0;

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");

        if ( ( returnCode = connection.getResponseCode() ) == HttpURLConnection.HTTP_OK)
            in_stream = connection.getInputStream();
        else
            in_stream = connection.getErrorStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in_stream));

        String line;
        while( ( line = reader.readLine() ) != null ) {
            result.append( line );
        }

        reader.close();
        return result.toString();
    }
}