package upb.ea.ea06_MétodosDeBúsqueda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ContadorPalabras {
       

    public static BinarySearchST<String, Integer> wordCount(int minLen, In in) {

        // SequentialSearchST<String,Integer> st = new SequentialSearchST<>();
        BinarySearchST<String,Integer> st = new BinarySearchST<>();
        // ST<String,Integer> st = new ST<>(); // Basada en arbol binario
        while (! in.isEmpty() ) {
            String word = in.readString().toLowerCase();
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

        // String fname = "../gabriel_garcia_marquez_cien_annos_soledad.txt";
        String fname = "../elRenacuajoPaseador.txt";

        In fileInput = new In(fname);
        
        Stopwatch sw = new Stopwatch();
        BinarySearchST<String, Integer> wc = wordCount(3, fileInput);
        double tiempo = sw.elapsedTime();

        for(String w: wc.keys()) {
            StdOut.println(w+" : "+wc.get(w));
        }

        StdOut.println();
        StdOut.println("Tiempo: "+tiempo);

        // TODO: Mostrar los conteos por cada una de las palabras en orden descendiente por conteo
        // TODO: Obtener la M palabras de mayor frecuencia


        // Varios intentos para leer correctamente el archivo en formato UTF-8:
        // StdOut.println(readTextFile1("../gabriel_garcia_marquez_cien_annos_soledad.txt"));
        // StdOut.println(readTextFile2("../gabriel_garcia_marquez_cien_annos_soledad.txt"));


    }


    public static String readTextFile1(String filename) {
        Path path = FileSystems.getDefault().getPath(filename);
        try {
            BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8")); 
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            return str.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String readTextFile2(String filename) {
        Path path = FileSystems.getDefault().getPath(filename);
        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            return String.join(System.lineSeparator(), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
