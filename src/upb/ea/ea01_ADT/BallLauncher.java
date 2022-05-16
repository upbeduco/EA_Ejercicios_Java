package upb.ea.ea01_ADT;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Simulador de lanzamiento parabólico
 *
 * @author Jorge Londoño
 */
public class BallLauncher {

    public static void main(String[] args) throws InterruptedException {

        Ball ball = new Ball(30, 60);

        StdDraw.setXscale(0, 100.0);
        StdDraw.setYscale(0, 100.0);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        for (double t = 0; t < 10; t += DELAY / 1000.0) {
            ball.updatePosition(t);
            StdOut.println(ball);
            ball.paint();
            StdDraw.pause(DELAY);
        }
        System.exit(0);
    }

    public static final int DELAY = 16; // en msec

}

class Ball {

    private double x, y; // Posicion de la bala
    private double vel; // Velocidad inicial
    private double angle; // Angulo de lanzamiento

    Ball(double vel, double angle) {
        this.vel = vel;
        this.angle = Math.toRadians(angle);
    }

    public void updatePosition(double time) {
        x = (double) (vel * Math.cos(angle) * time);
        y = (double) (vel * Math.sin(angle) * time - g * time * time / 2.0);
    }

    public void paint() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        StdDraw.point(x, y);
        StdDraw.show();
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static double g = 9.8;

}

// Ejercicios:
// 1. Hacer que cuando la bala caiga en el piso el programa termine
// 2. Hacer que cada que al oprimir espacio se lance una nueva bala
// 3. Hacer que cada lanzamiento ocurra con una velocidad y angulo inicial
// aleatorio
