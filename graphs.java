package templates;

import java.util.*;
import java.io.*;

public class graphs {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;

    // dfs
    public static void dfs(int v) {
        visited[v] = true;
        ArrayList<Integer> l = adj.get(v);
        for (int u :l) {
            if (!visited[u]) {
                dfs( u);
            }
        }
    }
    
    // bfs
    public static void bfs( int v) {
        Queue<Integer> q=new LinkedList<>();
        q.add(v);
        visited[v]=true;
        while(!q.isEmpty()){
            int cur=q.poll();
            ArrayList<Integer> l = adj.get(cur);
            for(int i = 0; i <l.size() ; i++){
                if(!visited[l.get(i)]){
                    q.add(l.get(i));
                    visited[l.get(i)]=true;
                }
            }
        }
    }
    
}

