package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class PilaConLista<Item> implements Iterable<Item> {


    private class Nodo {
        Item item;
        Nodo sig;
    }
    private Nodo first;

    private int n;

    public void push(Item s) {
        // TODO: Implementar
    }

    public Item pop() {
        // TODO: Implementar
        return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }


    // TODO: Implementar el iterador de la pila
    public Iterator<Item> iterator() { return null; }


    public static void main(String[] args) throws Exception {
        // TODO: Implementar algunos ejemplos de uso de la PilaConLista

    }



}
    

// Ejercicios:
// 1. Completar la implementación del API de la Pila
// 2. Hacer pruebas unitarias
// 3. Implementar la interfacez Iterable
// 4. Hacer algunos ejemplos con iteración

