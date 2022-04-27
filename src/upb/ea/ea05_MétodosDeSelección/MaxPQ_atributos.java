package upb.ea.ea05_MétodosDeSelección;

import java.util.Comparator;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;


class Mascota {
    private String nombre;
    private float peso;
    private int edad;
    public Mascota(String n, float p, int e) {
        nombre = n;
        peso = p;
        edad = e;
    }
    public String getNombre() { return nombre; }
    public float getPeso()    { return peso; }
    public int getEdad()      { return edad; }
}


public class MaxPQ_atributos {
 
    static class ComparatorEdad implements Comparator<Mascota> {

        @Override
        public int compare(Mascota m1, Mascota m2) {
            return m1.getEdad()-m2.getEdad();
        }
        
    }

    static void maxPorEdad(Mascota[] pets) {
        MaxPQ<Mascota> pq = new MaxPQ<>(new ComparatorEdad());
        for(Mascota m: pets)
            pq.insert(m);
        StdOut.println("Mascota de mas edad: "+pq.max().getNombre());
    }

    
    static void maxPorPeso(Mascota[] pets) {
        Comparator<Mascota> comparadorPeso = (m1,m2) -> { 
            float diff = m1.getPeso()-m2.getPeso();
            if (diff<0) return -1;
            else if (diff>0) return 1;
            else return 0;
        }; // Interfaz funcional
        MaxPQ<Mascota> pq = new MaxPQ<>(comparadorPeso);
        for(Mascota m: pets)
            pq.insert(m);
        StdOut.println("Mascota de mas peso: "+pq.max().getNombre());
    }


    public static void main(String[] args) {
        Mascota[] mascotas = {
            new Mascota("Firulais", 15.3f, 2),
            new Mascota("Santa's little helper", 5.1f, 3),
            new Mascota("Gardfield", 8.4f, 5),
            new Mascota("Snoopy", 3.8f, 4)
        };
        maxPorPeso(mascotas);
        maxPorEdad(mascotas);
    }

}

