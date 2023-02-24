package upb.ea.ea03_AnalisisDeAlgoritmos;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;

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

    public static Dataset generateClusters2D(int n) {
        double[][] points = new double[n][2];
        int[] labels = new int[n];
        for(int i=0; i<n; i++) {
            double mu = 1.0;
            double sigma = 0.5;
            labels[i] = 0;
            if (StdRandom.uniformDouble() >= 0.5) { mu = -1.0; labels[i]=1; }
            double x = StdRandom.gaussian(mu, sigma);
            double y = StdRandom.gaussian(mu, sigma);
            points[i][0] = x;
            points[i][1] = y;
        }
        Dataset ds = new Dataset(points, labels);
        return ds;
    }

    private static double[] clusterCenter(int label) {
        String s = Integer.toBinaryString(label);
        StdOut.println(label+" : "+s);
        return null;
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


    private static double[][] hypercube(int d) {
        int n = 1<<d;
        double[][] vertices = new double[n][d];
        int[] powers = new int[d];
        for(int i=0,j=1; i<d; i++,j<<=1) powers[i] = j;
        // StdOut.println(Arrays.toString(powers));
        for(int i=0; i<n; i++)
            for(int j=0; j<d; j++)
                vertices[i][j] = (i&powers[j])!=0 ? -1 : 1;
        // for(double[] a : vertices)
        //     StdOut.println(Arrays.toString(a));
        return vertices;
    }


    public static double[] gaussianPoint(double[] centroid, double sigma) {
        double[] point = new double[centroid.length];
        for(int i=0; i<centroid.length; i++)
            point[i] = StdRandom.gaussian(centroid[i], sigma);
        return point;
    }

    public static Dataset generateClusters(int n, int dim, int lab) {
        double[][] points = new double[n][dim];
        int[] labels = new int[n];
        double sigma = 0.5;
        double[][] centroids = hypercube(dim);
        for(int i=0; i<n; i++) {
            labels[i] = StdRandom.uniformInt(lab);
            points[i] = gaussianPoint(centroids[labels[i]], sigma);
        }
        return new Dataset(points, labels);
    }


    public static void main(String[] args) {

        int n=20;

        // Generar un dataset de n puntos en 2D
        Dataset ds1 = generateClusters2D(n);
        ds1.plotPoints();

        // Generar un dataset de n puntos en 3D
        Dataset ds2 = generateClusters(n, 3, 8);
        double[][] points = ds2.getPointsArray();
        for(double[] x: points)
            StdOut.println(Arrays.toString(x));

    }


}
