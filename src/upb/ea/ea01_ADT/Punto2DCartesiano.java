package upb.ea.ea01_ADT;

/**
 * Implementacion del ADT Punto2D representado por medio de
 * coordenadas cartesianas
 *  
 * @author jmlon
 *
 */
public class Punto2DCartesiano implements Punto2D {

    private double x,y;

    public Punto2DCartesiano(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
        Punto2D p0 = new Punto2DCartesiano(0,0);
        Punto2D p1 = new Punto2DCartesiano(1,1);

        System.out.println(p0.distancia(p1));
        assert( Math.abs(p0.distancia(p1)-Math.sqrt(2))<1E-10 );


    }


}

