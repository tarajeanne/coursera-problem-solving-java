import edu.duke.*;

/**
 * Write a description of PerimeterRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PerimeterRunner {

        public double getPerimeter(Shape s) {
            double totalPerim = 0;
            Point prevPt = s.getLastPoint();
            for(Point currPt: s.getPoints()) {
                double currDist = prevPt.distance(currPt);
                totalPerim += currDist;
                prevPt = currPt;
            }
            
            return totalPerim;
        }
        public void testPerimeter() {
        }
        public static void main(String args[]) {
            Point a = new Point(1, 2);
            Point b = new Point(5, 7);
            Point c = new Point( -1, 4);
            Shape s = new Shape()
        }
}
