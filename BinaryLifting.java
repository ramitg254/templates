package templates;

import java.util.*;

public class BinaryLifting {
    static int n, l, t;
    static int[][] up;
    static ArrayList<Integer> tin, tout;
    static ArrayList<ArrayList<Integer>> adj;

    // precomputation in n*log(n)

    public void precompute(int root) {
        l = (int) Math.ceil((Math.log(n) / Math.log(2)));
        up = new int[n][l + 1];
        tin = new ArrayList<>();
        tout = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tin.add(0);
            tout.add(0);
        }
        t = 0;
        dfs(root, root);
    }

    public void dfs(int v, int p) {
        tin.set(v, t++);
        up[v][0] = p;
        for (int i = 1; i <= l; i++)
            up[v][i] = up[up[v][i - 1]][i - 1];
        for (int u : adj.get(v)) {
            if (u != p)
                dfs(u, v);
        }
        tout.set(v, t);
    }

    // to find lca query in log(n)

    public int lca(int u, int v) {
        if (isAncestor(u, v))
            return u;
        if (isAncestor(v, u))
            return v;
        for (int i = l; i >= 0; --i) {
            if (!isAncestor(up[u][i], v))
                u = up[u][i];
        }
        return up[u][0];
    }

    public boolean isAncestor(int u, int v) {
        return tin.get(u) <= tin.get(v) && tout.get(u) >= tout.get(v);
    }
}
