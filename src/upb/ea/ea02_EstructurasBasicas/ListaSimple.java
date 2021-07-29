package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

public class ListaSimple<T> implements Iterable<T> {

    private class Nodo {
        T item;
        Nodo sig;
    }

    private Nodo first=null;
    private int n=0;

    public void addHead(T item) {
        Nodo x = new Nodo();
        x.item = item;
        x.sig = first;
        first = x;
        n++;
    }

    public T removeHead() throws Exception {
        if (first == null)
            throw new Exception("Lista vacia");
        T i = first.item;
        first = first.sig;
        n--;
        return i;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorLista();
    }

    private class IteradorLista implements Iterator<T> {

        private Nodo pos = first;

        @Override
        public boolean hasNext() {
            return pos!=null;
        }

        @Override
        public T next() {
            T i = pos.item;
            pos = pos.sig;
            return i;
        }

    }

    /*
     *   Ejercicios: Implementar los siguientes métodos
     */

    /** Remueve el ultimo elemento de la lista */
    public T removeLast() { return null; }

    /** Agregar un elemento al final de la lista */
    public void addLast() {  }

    /** Obtener el item en la i-ésima posición de la lista */
    public T get(int i) { return null; }

    /** Insertar un item en la i-ésima posición de la lista */
    public void insert(int i, T dato) { }

    /** remueve el item de la i-ésima posición de la lista */
    public T remove(int i) { return null; }

    /** Obtener una nueva ListaSimple con todos los items en orden inverso */
    public ListaSimple<T> invert() { return null; }

    public static void main(String[] args) throws Exception {
        
        // Implementación de algunas pruebas unitarias
        ListaSimple<Integer> l = new ListaSimple<>();
        assert(l.size()==0);
        l.addHead(1);
        assert(l.size()==1);
        int x = l.removeHead();
        assert(x==1);
        assert(l.size()==0);

    }


}