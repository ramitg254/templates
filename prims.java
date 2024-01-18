package templates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class prims {
    static long inf = (long) 4e18;
    static long[] dists;
    static boolean[] visited;
    static ArrayList<ArrayList<long[]>> adj;/* adjacency list with weight */
    static ArrayList<ArrayList<long[]>> mst;

    // initialisation
    public static void init(int n) {
        dists = new long[n];
        visited = new boolean[n];
        adj = new ArrayList<>();
        mst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            mst.add(new ArrayList<>());
        }

    }

    // addedge
    public static void addEdge(int a, int b, long wt) {
        adj.get(a).add(new long[] { wt, b });
        adj.get(b).add(new long[] { wt, a });
    }

    // prims
    public static void run(int src) {
        Arrays.fill(dists, inf);
        Arrays.fill(visited, false);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] > b[0])
                return 1;
            else if (a[0] == b[0])
                return 0;
            else
                return -1;
        });/* (dist,vertex1,vertex2) */

        dists[src] = 0;
        for (long[] x : adj.get(src))
            pq.add(new long[] { x[0], x[1], src });

        long count=0;
        while (!pq.isEmpty() && count!=visited.length-1) {
            long[] foc = pq.poll();

            int v = (int) foc[1];

            if (visited[v])
                continue;

            visited[v] = true;

            mst.get((int)foc[2]).add(new long[]{foc[1],foc[0]});
            mst.get((int)foc[1]).add(new long[]{foc[2],foc[0]});

            count++;
            for (long[] x : adj.get(v)) {
                long d = x[0];
                int u = (int) x[1];
                if (d < dists[u]) {
                    dists[u] = d;
                    pq.add(new long[] { d, u, v });
                }
            }
        }
    }

}
