package upb.ea.ea01_ADT;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class EjemploBibliotecas {

    static void graficacion() {
        StdDraw.setXscale(-1, 2);
        StdDraw.setYscale(-0.1, 3);
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(0,0,2.0,0);
        StdDraw.line(0,0,0,3.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(0, 0, 0.5, 0.5);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.10, 0.05, "Hello World!");
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        StdDraw.circle(0.5, 0.5, 0.5);
    }


    static void estadisticas() {
        double[] datos = new double[100];
        for(int i=0; i<100; i++) {
            // datos[i] = StdRandom.gaussian(1.5, 0.5);
            // datos[i] = StdRandom.exp(1.5);
            datos[i] = StdRandom.uniform(2.0,10.0);
            // datos[i] = StdRandom.geometric(0.2);
            // datos[i] = StdRandom.pareto(0.2);
        }
        StdOut.println("Promedio: "+StdStats.mean(datos));
        StdOut.println("Varianza: "+StdStats.var(datos));
        StdOut.println("Desviacion estandar: "+StdStats.stddev(datos));
        StdOut.println("Mínimo: "+StdStats.min(datos));
        StdOut.println("Máximo: "+StdStats.max(datos));
    }

    public static void main(String[] args) {
        graficacion();
        estadisticas();
    }

}

// Ejercicios:
// ==========
// 1. Hacer el histograma del arreglo `datos` para distintas distribuciones

