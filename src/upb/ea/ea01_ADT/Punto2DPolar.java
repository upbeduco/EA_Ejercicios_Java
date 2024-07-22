package upb.ea.ea01_ADT;

/**
 * Implementacion del ADT Punto2D representado por medio de
 * coordenadas polares
 *  
 * @author jmlon
 *
 */
public class Punto2DPolar implements Punto2D {

    private double radio, theta;

    public Punto2DPolar(double r, double t) {
        radio = r;
        theta = t;
    }

    @Override
    public double getX() {
        return radio*Math.cos(theta);
    }

    @Override
    public double getY() {
        return radio*Math.sin(theta);
    }

    @Override
    public double distancia(Punto2D punto) {
        return Math.sqrt( (getX()-punto.getX())*(getX()-punto.getX()) + (getY()-punto.getY())*(getY()-punto.getY()) );
    }

    // TODO: Hacer la sobre-escritura del metodo equals
    // public boolean equals(Object f) {}

    // TODO: Hacer la sobre-escritura del metodo toString
    // public String toString() {}


    public static void main(String[] args) {
        Punto2D p0 = new Punto2DPolar(0, 0);
        Punto2D p1 = new Punto2DPolar(Math.sqrt(2), Math.toRadians(45));

        System.out.println(p0.distancia(p1));
        assert( Math.abs(p0.distancia(p1)-Math.sqrt(2))<1E-10 );
        
    }

}
