package upb.ea.ea03_AnalisisDeAlgoritmos;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSum;

public class runThreeSum {

    public static int[] randomArray(int n) {
        int[] a = new int[n];
        for(int i=0; i<n; i++)
            a[i] = StdRandom.uniform(-1000, 1000);
        return a;
    }


    public static void main(String[] args) {
        // TODO medir el tiempo que se toma el conteo de ThreeSum

        int[] a = randomArray(100);
        int c = ThreeSum.count(a);

        StdOut.println("Conteo: "+c);
    }

}
