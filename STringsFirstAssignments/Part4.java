
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {
    void findYoutube() {
    URLResource links = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    for (String word: links.words()) {
        word = word.toLowerCase();
        if (word.contains("youtube.com")) {
            int firstQuote = word.indexOf("\"");
            int lastQuote = word.lastIndexOf("\"");
            System.out.println(word.substring(firstQuote +1, lastQuote));
        }
    }
    }
}
