package upb.ea.ea04_MétodosDeOrdenación;

import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;

public class OrdenarADTs {

    public static void main(String[] args) {

        List<Person> personas = GeneradorADTs.generar(10);
        for (Person p : personas)
            StdOut.println(p);  // TODO: Arreglar para que imprima correctamente caracteres UTF-8
        
        // Uso de la interfaz Comparable
        StdOut.println("persona[0] < persona[1] ? " + personas.get(0).compareTo(personas.get(1)));

        // Uso de la interfaz Comparator        
        Comparator<Person> compEdad = (p1, p2) -> { return p1.getEdad()-p2.getEdad(); };
        StdOut.println( compEdad.compare(personas.get(0), personas.get(1)) );


        // Ejercicios
        // 1. Ordenar por apellido y nombre ascendiente
        // 2. Ordenar por edad ascendiente
        // 3. Ordenar por peso descendiente
        // 4. Ordenar por edad y peso
    
        // Medición de tiempos y comparación, para N grandes
        // 1. Generar un arreglo de tamaño N
        // 2. Hacer tres copias del arreglo
        // 3. Ordenar una copia por un método cuadratico
        // 4. Ordenar por un método linearitmético
        // 5. Ordenar por la biblioteca del lenguaje
        // 6. Comparar los tiempos obtenidos
        // 7. Hace diferencia en el tiempo si se ordenan ascendente/descendente ?
    
                
    }

}
