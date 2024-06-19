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

    static Set<Integer> par=new HashSet<>();

    public static boolean isCycleDirected(int v){
        visited[v] = true;
        ArrayList<Integer> l = adj.get(v);
        boolean check=false;
        par.add(v);
        for (int u :l) {
            if (!visited[u]) {
                check|=isCycleDirected(u);
            }
            if(par.contains(u))return true;
        }
        par.remove(v);
        return check;
    }

    public static boolean isCycleUnDirected(int v,int p){
        visited[v] = true;
        ArrayList<Integer> l = adj.get(v);
        boolean check=false;
        for (int u :l) {
            if (!visited[u]) {
                check|=isCycleUnDirected(u,v);
            }else if(u!=p) return true;
        }
        return check;
    }
    
}

