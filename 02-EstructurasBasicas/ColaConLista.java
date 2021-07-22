import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class ColaConLista<Item> implements Iterable<Item> { 


    private class Nodo {
        Item item;
        Nodo sig;
    }
    private Nodo first;
    private Nodo last;

    private int n;

    public void enqueue(Item s) {
        Nodo tmp =new Nodo();
        tmp.item = s;
        tmp.sig = null;
        if (first==null) {
            first = tmp;
        }
        else {
            last.sig = tmp;
        }
        last = tmp;
        n++;
    }

    public Item dequeue() {
        if (first!=null) {
            Item s = first.item;
            first = first.sig;
            n--;
            if (n==0) last=null;
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
        return new IteradorDeCola();
    }


    private class IteradorDeCola implements Iterator<Item> {

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
        ColaConLista<String> c = new ColaConLista<>();

        for(String s: c) 
            StdOut.println(s);

        StdOut.println(c.size());

    }



}
    