import java.util.HashMap;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ContadorPalabras {

    public static HashMap<String, Integer> wordCount(int minLen) {
        HashMap<String, Integer> conteos = new HashMap<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() >= minLen) {
                if (!conteos.containsKey(word))
                    conteos.put(word, 0);
                conteos.put(word, conteos.get(word) + 1);
            }
        }
        return conteos;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> ts = wordCount(3);
        for(String k: ts.keySet()) {
            StdOut.println(k+"  :  "+ts.get(k));
        }
    }

}
