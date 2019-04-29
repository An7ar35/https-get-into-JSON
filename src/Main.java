import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static int countWikipediaTopicOccurrences(String topic ) throws IOException {
            URL url  = new URL( "https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=" + topic );
            String result = HttpsRequester.getPage( url );

            //Count occurrences of topic word in page (case sensitive)
            Pattern regex = Pattern.compile( topic );
            Matcher matcher = regex.matcher( result.toString() );

            int count = 0;
            while( matcher.find() ) {
                count++;
            }

            return count;
    }

    public static void main( String[] args ) {
        String topic = "pizza";
        try {
            System.out.println( "Result: " + countWikipediaTopicOccurrences( topic ) + " mentions." );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
