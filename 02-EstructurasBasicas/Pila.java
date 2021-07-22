import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Pila<Item> implements Iterable<Item> {

    private Item[] pila;
    private int n;

    @SuppressWarnings("unchecked")
    public Pila(int max) {
        pila = (Item[]) new Object[max];
    }

    public void push(Item s) {
        if (n == pila.length)
            resize(2*n);
        pila[n++] = s;
    }

    public Item pop() throws Exception {
        if (n == 0)
            throw new Exception("La pila esta vacia");
        Item s = pila[--n];
        pila[n] = null;
        if (n<pila.length/2) resize(pila.length/2);
        return s;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new IteradorDePila();
    }


    private class IteradorDePila implements Iterator<Item> {

        private int pos = n;

        @Override
        public boolean hasNext() {
            return pos>0;
        }

        @Override
        public Item next() {
            Item x = pila[--pos];
            return x;
        }

    }

    @SuppressWarnings("unchecked")
    private void resize(int m) {
        Item[] nuevo = (Item[]) new Object[m];
        for(int i=0; i<n; i++)
            nuevo[i] = pila[i];
        pila = nuevo;
    }


    public static void main(String[] args) throws Exception {
        Pila<String> p = new Pila<>(1);
        p.push("Hola");
        p.push("Mundo");
        p.push("El");
        p.push("Fin");
        p.push("se");
        p.push("acerca");

        for(Iterator<String> i = p.iterator(); i.hasNext(); ) {
            String x = i.next();
            StdOut.println(x);
        }

    }



}
    