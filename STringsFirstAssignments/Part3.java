
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    Boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex != -1) {
            if (stringb.indexOf(stringa, firstIndex + stringa.length()) != -1) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    String lastPart(String stringa, String stringb) {
        int index = stringb.lastIndexOf(stringa);
        if (index != -1) {
            return stringb.substring(index);
        }
        return stringb;
    }
    void testing() {
        System.out.println(twoOccurrences("atg", "ctgtatga"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}
