package templates;

import java.util.ArrayList;
import java.util.LinkedList;


public class Kosaraju {

    public void dfs(int v,ArrayList<ArrayList<Integer>> adj,LinkedList<Integer> s,boolean[] vis){
        vis[v]=true;
        for(int u:adj.get(v)){
            if(!vis[u]){
                dfs(u,adj,s,vis);
            }
        }
        s.add(v);
    }

    public void dfs2(int v,ArrayList<ArrayList<Integer>> adjT,boolean[] vis){
        vis[v]=true;
        for(int u:adjT.get(v)){
            if(!vis[u]){
                dfs2(u,adjT,vis);
            }
        }
    }

    public int kosaraju(ArrayList<ArrayList<Integer>> adj){

        // arrange the elements in order of their finishing time in stack having top with max finishing time
        int n=adj.size();
        boolean [] vis=new boolean[n];
        LinkedList<Integer> s=new LinkedList<>();
        for(int i=0;i<n;i++){
            if(!vis[i]){
                dfs(i,adj,s,vis);
            }
        }

        // reverse the edges
        ArrayList<ArrayList<Integer>> adjT=new ArrayList<>();
        for(int i=0;i<n;i++)adjT.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            vis[i]=false;
            for(int u:adj.get(i)){
                adjT.get(u).add(i);
            }
        }

        // run dfs2 to find the scc's
        int cnt=0;
        while(s.size()>0){
            int x=s.remove(s.size()-1);
            if(!vis[x]){
                dfs2(x,adjT,vis);
                cnt++;
            }
        }

        return cnt;

    }
}
