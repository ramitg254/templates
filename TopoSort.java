package templates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSort {

    /*************************
     * topological sort- using kahn's algorithm
     ***************************/

    static ArrayList<ArrayList<Integer>> adj;
    static ArrayList<Integer> tsort;
    static int[] indeg;
    static Queue<Integer> q;

    static InputReader in = new InputReader(System.in);

    public static void tsort(int n, int m) {
        indeg = new int[n];
        tsort = new ArrayList<>();
        q = new LinkedList<>();
        adj=new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            adj.get(x).add(y);
            indeg[y]++;
        }

        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int u : adj.get(v)) {
                indeg[u]--;
                if (indeg[u] == 0)
                    q.add(u);
            }
            tsort.add(v);
        }
    }

    // cycle detection for directed (disconnected or connected) graphs

    public static boolean isCycle(int n) {
        if (tsort == null) {
            System.out.println("first call tsort");
        }
        return tsort.size() < n;
    }
}
