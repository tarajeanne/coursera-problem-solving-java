
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Weather {
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        double coldestTemp = -10000;
        for (CSVRecord record: parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currentTemp == -9999) {
                continue;
            }
            if (coldest == null || currentTemp < coldestTemp) {
                coldest = record;
                coldestTemp = currentTemp;
            }
        }
        return coldest;
    }
    
    String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        double coldestTemp = -10000;
        String coldest = "";
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestInFile = coldestHourInFile(parser);
            double currentTemp = Double.parseDouble(coldestInFile.get("TemperatureF"));
            if (coldest.equals("") || currentTemp < coldestTemp) {
                coldest = f.getPath();
                coldestTemp = currentTemp;
            }
        }
        return coldest;
    }
    
    CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord leastHumid = null;
        double lowestHumidity = -10000;
        for (CSVRecord record: parser) {
            System.out.println(record.get("Humidity"));
            if (record.get("Humidity") == "N/A") {
                continue;
            }
            else {
                double currentHumidity = Double.parseDouble(record.get("Humidity"));
                if (leastHumid == null || currentHumidity < lowestHumidity) {
                    leastHumid = record;
                    lowestHumidity = currentHumidity;
                }
            }
            
            
        }
        return leastHumid;
    }
    
    CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        double lowestHumidity = -10000;
        CSVRecord leastHumid = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord leastHumidInFile = lowestHumidityInFile(parser);
            double currentHumidity = Double.parseDouble(leastHumidInFile.get("Humidity"));
            if (leastHumid == null || currentHumidity < lowestHumidity) {
                leastHumid = leastHumidInFile;
                lowestHumidity = currentHumidity;
            }
        }
        return leastHumid;
    }
    
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;
        for (CSVRecord record: parser) {
            if (Double.parseDouble(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No temperatures with that humidity");
            return 0;
        }
        else {
            return sum / count;
        }
    }
    
    void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double humid = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temp when high Humidity is " + humid);
    }
    
    void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();
        System.out.println("The coldest temperature was in file " + coldestFile);
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldestRow.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }
    
    void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temperature was " + record.get("TemperatureF") + " at " + record.get("DateUTC"));
    }
}
