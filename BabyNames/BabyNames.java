
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyNames {
    void totalBirths(int year) {
        FileResource file = new FileResource("./us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int girlCount = 0;
        int boyCount = 0;
        for(CSVRecord rec: file.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boyCount ++;
            }
            else {
                totalGirls += numBorn;
                girlCount++;
            }
            totalBirths += numBorn;
        }
        
        System.out.println("Total births = " + totalBirths + "; Total girls = " + totalGirls + "; totalBoys = " + totalBoys + "boynames" + boyCount + "girlNames" + girlCount);
        
    }
    
    int getRankFromFile(File file, String name, String gender) {
        FileResource fr = new FileResource(file);
        int count = 1;
        Boolean males = false;
        for(CSVRecord rec: fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (!males && currGender.equals("M")){
                males = true;
                count = 1;
            }
            if (currName.equals(name) && gender.equals(currGender)) {
                return count;
            }
            count ++;
        }
        return -1;
    }
    
    int getRank(int year, String name, String gender) {
        FileResource file = new FileResource("./us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int count = 1;
        Boolean males = false;
        for(CSVRecord rec: file.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (!males && currGender.equals("M")){
                males = true;
                count = 1;
            }
            if (currName.equals(name) && gender.equals(currGender)) {
                return count;
            }
            count ++;
        }
        return -1;
    }
    
    String getName(int year, int rank, String gender) {
        FileResource file = new FileResource("./us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int currRank = 1;
        Boolean males = false;
        for (CSVRecord rec: file.getCSVParser(false)) {
            String currGender = rec.get(1);
            if (!males && currGender.equals("M")){
                males = true;
                currRank = 1;
            }
            if (currRank == rank && currGender.equals(gender)) {
                return rec.get(0);
            }
            currRank ++;
        }
        return "NO NAME";
    }
    
    int yearOfHighestRank(String name, String gender) {
        File highestFile = null;
        int highestRank = -1;
        DirectoryResource directory = new DirectoryResource();
        
        for (File f: directory.selectedFiles()) {
            int currRank = getRankFromFile(f, name, gender);
            if (highestRank == -1) {
                highestRank = currRank;
                highestFile = f;
            }
            else if (currRank < highestRank) {
                highestRank = currRank;
                highestFile = f;
            }
        }
        if (highestRank == -1) {
            return -1;
        }
        else {
            return Integer.parseInt(highestFile.getName().substring(3, 7));
        }
    }
    
    double getAverageRank(String name, String gender) {
        double sum = 0.0;
        double count = 0.0;
        DirectoryResource directory = new DirectoryResource();
        
        for (File f: directory.selectedFiles()) {
            int currRank = getRankFromFile(f, name, gender);
            if (currRank != -1) {
                sum += currRank;
                count ++;
            }
        }
        if (count == 0) {
            return -1;
        }
        else {
            return sum/count;
        }
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int originalRank = getRank(year, name, gender);
        String newName = getName(newYear, originalRank, gender);
        System.out.println(name + " born in " + year + " would have been " + newName + " if she had been born in " + newYear + ".");
    }
    
    int totalBirthsRankedHigher(int year, String name, String gender) {
        FileResource file = new FileResource("./us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv");
        int count = 0;
        Boolean males = false;
        for(CSVRecord rec: file.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            int currBirths = Integer.parseInt(rec.get(2));
            if (!males && currGender.equals("M")){
                males = true;
                count = 0;
            }
            if (currName.equals(name) && gender.equals(currGender)) {
                return count;
            }
            count += currBirths;
        }
        return -1;
    }
    
    void testTotalBirths() {
        totalBirths(1900);
        totalBirths(1905);
    }
    
    void testGetRank(){
        System.out.println(getRank(1960, "Emily", "F"));
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    void testGetName() {
        System.out.println(getName(1980, 350, "F"));
        System.out.println(getName(1982, 450, "M"));
    }
    
    void testWhatIsNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Genevieve", "F"));
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    void testGetAverageRank() {
        System.out.println(getAverageRank("Susan", "F"));
        System.out.println(getAverageRank("Robert", "M"));
    }
    
    void testTotalBirthsRankedHigher() {
        System.out.println(totalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println(totalBirthsRankedHigher(1990, "Drew", "M"));
    }
    
    void testAll() {
        testTotalBirths();
        testGetRank();
        testGetName();
        testWhatIsNameInYear();
        testYearOfHighestRank();
        testGetAverageRank();
        testTotalBirthsRankedHigher();
    }
}
