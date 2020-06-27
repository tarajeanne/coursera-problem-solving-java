
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    int howMany(String stringa, String stringb) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            System.out.println("startIndex " + startIndex);
            int index = stringb.indexOf(stringa, startIndex);
            System.out.println("index " + index);
            if (index == -1) {
                break;
            }
            count ++;
            startIndex = index + stringa.length();
        }
        return count;
    }
    
    void testHowMany() {
        System.out.println(howMany("a", "banana"));
    }
}
