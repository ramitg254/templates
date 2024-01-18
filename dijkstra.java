package templates;

import java.util.*;
import java.io.*;

public class dijkstra {
    static long inf = (long) 4e18;
    static long[] dists;
    static int[] par;
    static boolean[] visited;
    static ArrayList<ArrayList<long[]>> adj;/* adjacency list with weight */

    // initialisation
    public static void init(int n) {
        dists = new long[n];
        par = new int[n];
        visited = new boolean[n];
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // addedge
    public static void addedge(int a, int b, long wt) {
        adj.get(a).add(new long[] { wt, b });
        adj.get(b).add(new long[] { wt, a });
    }

    // dijkstra
    public static void run(int src) {
        Arrays.fill(dists, inf);
        Arrays.fill(visited, false);
        Arrays.fill(par, -1);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] > b[0])
                return 1;
            else if (a[0] == b[0])
                return 0;
            else
                return -1;
        });/* (dist,vertex) */

        dists[src] = 0;
        pq.add(new long[] { 0, src });

        while (!pq.isEmpty()) {
            long[] foc = pq.poll();

            int v = (int) foc[1];

            if (visited[v])
                continue;
            visited[v] = true;

            for (long[] x : adj.get(v)) {
                long d = dists[v] + x[0];
                int u = (int) x[1];
                if (d < dists[u]) {
                    dists[u] = d;
                    par[u] = v;
                    pq.add(new long[] { d, u });
                }
            }
        }
    }

}
