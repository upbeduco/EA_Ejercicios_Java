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
        Nodo tmp  =new Nodo();
        tmp.item = s;
        tmp.sig = first;
        first = tmp;
        n++;
    }

    public Item pop() {
        if (first!=null) {
            Item s = first.item;
            first = first.sig;
            n--;
            return s;
        }
        return null;
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

        private Nodo pos = first;

        @Override
        public boolean hasNext() {
            return pos!=null;
        }

        @Override
        public Item next() {
            Item x = pos.item;
            pos = pos.sig;
            return x;
        }

    }


    public static void main(String[] args) throws Exception {
        PilaConLista<String> p = new PilaConLista<>();
        p.push("Hola");
        p.push("Mundo");

        for(String s: p) {
            StdOut.println(s);
        }
        StdOut.println(p.size());

    }



}
    