package upb.ea.ea07_Grafos;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Digraph;

public class DigrafoConNombres {
    
    private Map<String,Integer> nombres = new HashMap<>();
    private Map<Integer,String> numeros = new HashMap<>();
    private Digraph graph;

    public DigrafoConNombres(String[] nodos) {
        graph = new Digraph(nodos.length);
        int i=0;
        for(String nombre: nodos) {
            nombres.put(nombre, i);
            numeros.put(i, nombre);
        }
    }


    public void addEdge(String a, String b) {
        int u = nombres.get(a);
        int v = nombres.get(b);
        graph.addEdge(u, v);
    }


    // public Iterable<String> adj() { … }
    // public int indegree(String v) { … }
    // public int outdegree(String v) { … }
    // public String toString() { … }

     public static void main(String[] args) {
        String[] nombres = { "turbo", "pasto", "pereira", "medellin", "bogota" };
        DigrafoConNombres g = new DigrafoConNombres(nombres);
        g.addEdge("medellin", "pereira");
     }

}
