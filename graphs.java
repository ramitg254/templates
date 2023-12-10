package templates;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class graphs {
    static ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
    static boolean[] visited=new boolean[100005];

    public static void dfs(int v) {
        visited[v] = true;
        ArrayList<Integer> l = adj.get(v);
        for (int u :l) {
            if (!visited[u]) {
                dfs( u);
            }
        }
    }
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

