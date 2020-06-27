import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        
        for (Point currPt: s.getPoints()) {
            count ++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double sum = 0;
        int count = 0;
        Point prevPoint = s.getLastPoint();
        for (Point p : s.getPoints()) {
            double currDist = prevPoint.distance(p);
            sum += currDist;
            prevPoint = p;
            count++;
        }
        
       return sum/count;
    }

    public double getLargestSide(Shape s) {
        double largest = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point p : s.getPoints()) {
            double currDist = prevPoint.distance(p);
            if (currDist > largest) {
            largest = currDist;
        }
            prevPoint = p;
        }
        
       return largest;
    }

    public double getLargestX(Shape s) {
        double largest = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point p : s.getPoints()) {
            if (p.getX() > largest) {
            largest = p.getX();
        }
            prevPoint = p;
        }
        
       return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largest = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource newFile = new FileResource(f);
            Shape s = new Shape(newFile);
            double perimeter = getPerimeter(s);
            if (perimeter > largest) {
                largest = perimeter;
            }
        }
        return largest;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largest = 0.0;
        File largestFile = null;
        for (File f : dr.selectedFiles()) {
            FileResource newFile = new FileResource(f);
            Shape s = new Shape(newFile);
            double perimeter = getPerimeter(s);
            if (perimeter > largest) {
                largest = perimeter;
                largestFile = f;
            }
        }
        return largestFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("number of points = " + numPoints);
        double average = getAverageLength(s);
        System.out.println("average side length = " + average);
        double largest = getLargestSide(s);
        System.out.println("largest side length = " + largest);
        double largestX = getLargestX(s);
        System.out.println("largest x = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimFile = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter = " + largestPerimFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
