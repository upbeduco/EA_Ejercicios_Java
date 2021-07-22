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
    // public Iterator<Item> iterator() {


    public static void main(String[] args) throws Exception {
        // TODO: Implementar algunos ejemplos de uso de la PilaConLista

    }



}
    