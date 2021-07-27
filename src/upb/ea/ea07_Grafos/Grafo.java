package upb.ea.ea07_Grafos;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class Grafo {


    interface Visitor<T> {
        void visit(T n);
    }


    static class Visitante implements Visitor<Integer> {
        public void visit(Integer n) {
            StdOut.println("Nodo: "+n);
        }

    }


    public static class DepthFirstSearch {
        private boolean[] marked; // marked[v] = is there an s-v path?
        private int count; // number of vertices connected to s
        private Visitor<Integer> visitor;

        public DepthFirstSearch(Graph G, int s, Visitor<Integer> v) {
            marked = new boolean[G.V()];
            visitor = v;
            dfs(G, s);
        }

        private void dfs(Graph G, int v) {
            count++;
            marked[v] = true;
            if (visitor!=null) visitor.visit(v);
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }
    }


    public static void main(String[] args) {

        Graph vuelos = new Graph(6);
        vuelos.addEdge(1, 2);
        vuelos.addEdge(2, 3);
        vuelos.addEdge(2, 4);
        vuelos.addEdge(3, 5);
        vuelos.addEdge(3, 0);
        // StdOut.println(vuelos.degree(3));
        // for (int vecino : vuelos.adj(2)) {
        //     StdOut.print(vecino + ", ");
        // }
        // StdOut.println();
        // StdOut.println(vuelos);

        DepthFirstSearch x = new DepthFirstSearch(vuelos, 1, new Visitante());

    }






}
