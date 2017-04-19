import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class A {

    public static void main(String arg[]) throws Exception {

        try {

            URL URLObj = new URL("http://www.facebook.com/");
            URLConnection URLconnect = URLObj.openConnection();
            URLconnect.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(URLconnect.getOutputStream()));
            writer.write("username=14besenriaz&pass=nasH-1995&submit=Login");
            writer.close();

            Document doc = Jsoup.connect(URLconnect.getInputStream().toString()).get();
            Elements tableElements = doc.select("table");
            Elements bodyElements = tableElements.select("tbody");
            Elements rowElements = bodyElements.select("tr");

//        System.out.println("helo");
            for (int i=0; i<rowElements.size(); i++) {
                Element row = rowElements.get(i);
                Elements rowItems = row.select("td");
                System.out.print("\n"+(i+1)+". ");
                for (int j=0; j<3; j++) {
                    System.out.printf("%-30s",rowItems.get(j).text());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}