package upb.ea.ea06_MétodosDeBúsqueda;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ContadorPalabras {

    public static SequentialSearchST<String, Integer> wordCount(int minLen, In in) {

        SequentialSearchST<String,Integer> st = new SequentialSearchST<>();
        // BinarySearchST<String,Integer> st = new BinarySearchST<>();
        // ST<String,Integer> st = new ST<>(); // Basada en arbol binario
        while (! in.isEmpty() ) {
            String word = in.readString();
            if (st.contains(word)) {
                st.put(word, st.get(word)+1);
            }
            else {
                st.put(word, 1);
            }
        }
        return st;
    }

    public static void main(String[] args) {
        In fileInput = new In("../gabriel_garcia_marquez_cien_annos_soledad.txt");
        
        Stopwatch sw = new Stopwatch();
        SequentialSearchST<String, Integer> wc = wordCount(3, fileInput);
        double tiempo = sw.elapsedTime();

        for(String w: wc.keys()) {
            StdOut.println(w+" : "+wc.get(w));
        }
        StdOut.println();
        StdOut.println("Tiempo: "+tiempo);

        // TODO: Mostrar los conteos por cada una de las palabras en orden descendiente por conteo
        // TODO: Obtener la M palabras de mayor frecuencia


    }

}
