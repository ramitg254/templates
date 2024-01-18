package templates;

import java.util.ArrayList;
import java.util.Collections;
import templates.dsu;

public class kruskal {
    static ArrayList<long[]> elist = new ArrayList<>();
    static ArrayList<ArrayList<long[]>> mst;

    // add edge
    public static void addEdge(int u, int v, long w) {
        elist.add(new long[] { u, v, w });
    }

    // kruskal algo
    public static void mst(int n) {
        mst = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            mst.add(new ArrayList<>());

        }
        Collections.sort(elist, (x, y) -> {
            if (x[2] == y[2])
                return 0;
            else if (x[2] > y[2])
                return 1;
            else
                return -1;
        });
        dsu.init(n);
        int count = 0;
        for (long[] x : elist) {
            if (count == n - 1)
                break;
            if (dsu.merge((int) x[0], (int) x[1])) {
                mst.get((int) x[0]).add(new long[] { x[1], x[2] });
                mst.get((int) x[1]).add(new long[] { x[0], x[2] });
                count++;
            }
        }
    }
}
