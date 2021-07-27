package upb.ea.ea01_ADT;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StringDemo {
    
    public static void main(String[] args) {
        StdOut.println("Ejemplo equals, hash");
        String a = "Hola";
        String b = StdIn.readString();

        StdOut.println( a==b );

    }

}


// Ejercicios:
// ==========
// 1. Porque == responde 'false' incluso si los Strings son iguales?
// 2. Cual es la diferencia entre == y .equals ?
// 3. Como son los hashCode de Strings? Considerar casos iguales y diferentes.

