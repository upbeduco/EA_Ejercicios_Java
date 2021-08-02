package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class ColaConLista<Item> { 


    private class Nodo {
        Item item;
        Nodo sig;
    }
    private Nodo first;
    private Nodo last;

    private int n;

    public void enqueue(Item s) {
        // TODO: Implementar el método
    }

    public Item dequeue() {
        // TODO: Implementar el método
        return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    // TODO: Implementar el Iterador para la ColaConLista
    // implements Iterable<Item>
    // public Iterator<Item> iterator()
    // private class IteradorDeCola implements Iterator<Item>



    public static void main(String[] args) throws Exception {
        // TODO: Instanciar una Cola y hacer algunas pruebas de sus métodos
    }



}
    

// Ejercicios:
// 1. Completar la implementación del API de la Cola
// 2. Hacer pruebas unitarias
// 3. Implementar la interfacez Iterable
// 4. Hacer algunos ejemplos con iteración
