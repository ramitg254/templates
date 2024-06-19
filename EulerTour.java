package templates;

import java.io.*;
import java.util.*;

// ************************ FOR TREES ************************** 

public class EulerTour { 
    int[] start, end;
    ArrayList<ArrayList<Integer>> adj;
    int timer;

    public EulerTour(int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        start = new int[n];
        end = new int[n];
        timer = 0;
    }

    public void addEdge(int a, int b) {
        adj.get(a).add(b);
        adj.get(b).add(a);
    }

    // Euler tour
    public void tour(int v, int par) {
        start[v] = timer++;
        for (int u : adj.get(v)) {
            if (u != par)
                tour(u, v);
        }
        end[v] = timer;
    }

    // Euler tour using arraydeque in place of recursive dfs calls
    public void tour2(int v) {
        boolean[] vis = new boolean[start.length];
        ArrayDeque<Integer> l = new ArrayDeque<>();
        l.add(v);
        while (l.size() > 0) {
            int cur = l.getLast();

            if (vis[cur]) {
                end[cur] = timer;
                l.removeLast();
                continue;
            }
            start[cur] = timer++;
            vis[cur] = true;
            for (int u : adj.get(cur)) {
                if (!vis[u]) {
                    l.add(u);
                }
            }

        }
    }
}