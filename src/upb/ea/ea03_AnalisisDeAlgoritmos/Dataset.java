package upb.ea.ea03_AnalisisDeAlgoritmos;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class Dataset {

    private double[][] points;
    private int[] labels;
    private int d;  // dimensionality
    private int n;  // number of samples


    Dataset(double[][] p, int[] l) {
        d = p[0].length;
        n = p.length;
        points = p;
        labels = l;
    }


    public double[] getPoint(int i) {
        return points[i];
    }


    public int getLabel(int i) {
        return labels[i];
    }

    public double[][] getPointsArray() {
        return points;
    }

    public int[] getLabelsArray() {
        return labels;
    }

    public int size() {
        return n;
    }

    public static Dataset generateClusters(int n, int d) {
        double[][] points = new double[n][d];
        int[] labels = new int[n];
        for(int i=0; i<n; i++) {
            double mu = 1.0;
            double sigma = 0.5;
            labels[i] = 0;
            if (StdRandom.uniform() >= 0.5) { mu = -1.0; labels[i]=1; }
            double x = StdRandom.gaussian(mu, sigma);
            double y = StdRandom.gaussian(mu, sigma);
            points[i][0] = x;
            points[i][1] = y;
        }
        Dataset ds = new Dataset(points, labels);
        return ds;
    }


    public void plotPoints() {
        StdDraw.setXscale(-5, 5);
        StdDraw.setYscale(-5, 5);
        Color colors[] = { StdDraw.RED, StdDraw.BLUE };
        for(int i=0; i<points.length; i++) {
            StdDraw.setPenColor(colors[labels[i]]);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(points[i][0], points[i][1]);
        }

    }


    public static void main(String[] args) {
        Dataset ds = generateClusters(20, 2);
        ds.plotPoints();
    }


}
