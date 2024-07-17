package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

/**
 * ListaDoble : Implementacion basica de la lista doblemente enlazada
 * Se proponen como ejercicios operaciones adicionales de la 
 * la lista.
 * 
 * @author: Jorge Londoño
 * 
 */
public class ListaDoble<T> implements Iterable<T> {

    /**
     * Nodo representa un nodo de la lista simplemente enlazada
     */
    private class Nodo {
        T item;
        Nodo sig;
        Nodo ant;
    }

    private Nodo first=null;
    private Nodo last=null;
    private int n=0;

    /**
     * Agregar un item a la cabeza de la lista
     * @param item
     */
    public void addHead(T item) {
        Nodo x = new Nodo();
        x.item = item;
        x.sig = first;
        if (first!=null) first.ant = x;
        first = x;
        if (last==null) last=first;
        n++;
    }

    /**
     * Remover el primer nodo de la lista
     * @return item contenido en el nodo eliminado
     * @throws Exception
     */
    public T removeHead() throws Exception {
        if (first == null)
            throw new Exception("Lista vacia");
        T i = first.item;
        if (last==first) last=null;
        first = first.sig;
        n--;
        return i;
    }

    /**
     * True si la lista esta vacia
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return Longitud de la lista
     */
    public int size() {
        return n;
    }

    /**
     * Obtener un iterador para la lista
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorLista();
    }

    /**
     * Implementacion del iterador para la lista simple
     */
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

    /** Obtener una nueva ListaDoble con todos los items en orden inverso */
    public ListaDoble<T> invert() { return null; }

    /** Dividir una lista en dos mitades */
    public ListaDoble<T>[] splitList() { return null; }

    public static void main(String[] args) throws Exception {
        
        // Implementación de algunas pruebas unitarias
        ListaDoble<Integer> l = new ListaDoble<>();
        assert(l.size()==0);
        l.addHead(1);
        assert(l.size()==1);
        int x = l.removeHead();
        assert(x==1);
        assert(l.size()==0);

    }


}