package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class ColaArreglo {

    private String[] cola;
    private int n;

    public ColaArreglo(int max) {
        // TODO: instanciar el arreglo para la pila
    }

    public void enqueue(String s) {
        // TODO: Implementar el método
    }

    public String dequeue() throws Exception {
        // TODO: Implementar el método
        return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // TODO: Dar una implementación del Iterador para la pila
    // implements Iterable<Item>
    // public Iterator<Item> iterator() {


    // TODO: Implementar el procedimiento para cambiar el tamaño del arreglo
    // @SuppressWarnings("unchecked")
    // private void resize(int m)


    public static void main(String[] args) throws Exception {
        // TODO: Implementar algunos ejemplos de uso de la pila
        StdOut.println("Ejercicio Cola");

    }



}
    

// Ejercicios
// 1. Completar la implementación de la Cola utilizando un arreglo
// 2. Hacer algunas pruebas unitarias de la implementación
// 3. Hacer la implementacion genérica utilizando un parámetro de tipo
// 4. Implementar el iterador de la cola utilizando el orden FIFO
// 5. Hacer algunos ejemplos utilizando Colas con distintos tipos de datos