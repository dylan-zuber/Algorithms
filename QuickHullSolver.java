/**
* Name: Dylan Zuber
* Assignment: PA 3
* Course/Semester: CS371/Fall2020
* Lab Section: N/A
* Sources Consulted: N/A
* */

import java.awt.geom.Point2D;
import java.util.*;
import java.io.*;

/**
* This program takes a string representing the name of a file with 2D points in the file. Using
* the Quick Hull algorithm, this program will return a list of the points making up the convex hull
* of the points in order.
* */

public class QuickHullSolver {

    private List<Point2D.Double> points; // contains all of the points
    private int numPoints; // number of points in the data set
    private List<Integer> result; // list of the indexes making up the convex hull of points

    /**
    * Constructor for QuickHullSolver that populates the list of points and number of points
    * @param fileName string representing the name of the file containing 2D points
    * @throws IOException if file does not exist
    * */
    public QuickHullSolver(String fileName) throws IOException {
        points = new ArrayList<Point2D.Double>();
        result = new ArrayList<Integer>();
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();
        Scanner scanLine = new Scanner(line);
        numPoints = scanLine.nextInt();
        while(scan.hasNextLine()) {
            double x, y;
            line = scan.nextLine();
            scanLine = new Scanner(line);
            if(scanLine.hasNextInt()) {
                x = scanLine.nextInt();
            } else {
                x = scanLine.nextDouble();
            }
            if(scanLine.hasNextInt()) {
                y = scanLine.nextInt();
            } else {
                y = scanLine.nextDouble();
            }
            points.add(new Point2D.Double(x, y));
        }
    }

    /**
     * Returns the list of points.
     * @return points list of all points in data set
     * */
    public List<Point2D.Double> getPoints() {
        return points;
    }

    /**
     * Use quick hull and helper function to recursively solve for the convex hull of a set of points
     * @return result list of points representing the convex hull of points
     * */
    public List<Integer> solve() {
        int p1 = 0, pn = 0;
        for(int i = 0; i < points.size(); i++) {
            if(points.get(i).x < points.get(p1).x){
                p1 = i;
            }
            if(points.get(i).x > points.get(pn).x) {
                pn = i;
            }
        }

        List<Integer> upperSet = new ArrayList<Integer>();
        List<Integer> lowerSet = new ArrayList<Integer>();

        for(int i = 0; i < points.size(); i++) {
            if(points.get(p1).equals(points.get(i)) || points.get(pn).equals(points.get(i))) {
                continue;
            }
            if(lineDistance(p1, pn, i) >= 0) {
                upperSet.add(i);
            } else {
                lowerSet.add(i);
            }
        }

        result.add(p1);
        upperHull(p1, pn, upperSet);
        result.add(pn);
        upperHull(pn, p1, lowerSet);

        return result;
    }

    /**
     * Recursive function that uses divide and conquer technique to solve the quick hull algorithm
     * @param p1 min x value from data set, known point in the convex hull
     * @param pn max x value from data set, known point in the convex hull
     * @param list list of points to be processed, some in the convex hull and some not
     * */
    public void upperHull(int p1, int pn, List<Integer> list) {
        if(!list.isEmpty()){
            // after processing all points in list pmax will be max value (will be in convex hull)
            int pmax = list.get(0);
            double maxDistance = lineDistance(p1, pn, pmax);
            double d = Double.MIN_VALUE;
            for(int i : list) {
                d = lineDistance(p1, pn, i);
                if(d > maxDistance) {
                    maxDistance = d;
                    pmax = i;
                }
            }

            Set<Integer> set1 = new HashSet<Integer>();
            Set<Integer> set2 = new HashSet<Integer>();
            for(int i : list) {
                if(points.get(i).equals(points.get(p1)) || points.get(i).equals(points.get(pmax))) continue;
                double dist1 = lineDistance(p1, pmax, i);
                double dist2 = lineDistance(pmax, pn, i);
                if(dist1 > 0) {
                    set1.add(i);
                } else if(dist2 > 0) {
                    set2.add(i);
                }
            }

            if(pmax < 0) result.add(pmax);
            upperHull(p1, pmax, new ArrayList<Integer>(set1));
            if(pmax >= 0) result.add(pmax);
            upperHull(pmax, pn, new ArrayList<Integer>(set2));
        }
    }

    /**
     * Returns a positive or negative Double value. If negative, we know p3 is
     * to the right of the line made by p1 and p2, if positive we know p3 is to
     * the left of the line made by p1 and p2, if zero, p3 is on the line.
     * @param p1 minimum point, making up one of two points to create a line to compare p3 to
     * @param p2 maximum point, making up one of two points to create a line to compare p3 to
     * @param p3 point to be compared to the line of p1 and p2
     * */
    private Double lineDistance(int p1, int p2, int p3) {
        return points.get(p1).x * points.get(p2).y + points.get(p3).x * points.get(p1).y + points.get(p2).x * points.get(p3).y -
                points.get(p3).x * points.get(p2).y - points.get(p2).x * points.get(p1).y - points.get(p1).x * points.get(p3).y;
    }
}

