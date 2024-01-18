package templates;

import java.util.*;
import java.io.*;

public class dsu {

    static int[] dsupar, dsusz;
    static ArrayList<ArrayList<Integer>> adj;

    // initialise dsu;
    public static void init(int n) {
        dsupar = new int[n];
        dsusz = new int[n];
        adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            dsupar[i] = i;
            adj.add(new ArrayList<>());
        }

        Arrays.fill(dsusz, 1);

    }

    // find parent
    public static int find(int v) {
        if (v == dsupar[v])
            return v;
        return dsupar[v] = find(dsupar[v]);
    }

    // check whether in same set
    public static boolean isConnected(int u, int v) {
        return find(v) == find(u);
    };

    // merging two component
    public static boolean merge(int u, int v) {
        v = find(v);
        u = find(u);

        if (u == v)
            return false;

        // assuming rank of u is always greater than v
        if (dsusz[u] < dsusz[v]) {
            int t = v;
            v = u;
            u = t;
        }
        dsupar[v] = u;
        dsusz[u] = dsusz[u] + dsusz[v];
        return true;
    }

    public static void relabel(int v, int target) {
        if (dsupar[v] == target)
            return;
        dsupar[v] = target;
        for (int x : adj.get(v)) {
            relabel(x, target);
        }
    }

}