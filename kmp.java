package templates;

import java.util.*;
import java.io.*;

public class kmp  {

    static InputReader in = new InputReader(System.in);
    static OutputWriter out = new OutputWriter(System.out);

    public int[] prefixFunction(StringBuilder s){
        int n=s.length();
        int[] pi=new int[n];
        for(int i=1;i<n;i++){
            int j=pi[i-1];
            while(j>0 && s.charAt(j)!=s.charAt(i)){
                j=pi[j-1];
            }
            if(s.charAt(j)==s.charAt(i)){
                j++;
            }
            pi[i]=j;
        }
        return pi;

    }

    public int match(StringBuilder pattern,StringBuilder text){
        int ans=-1;
        StringBuilder tot=new StringBuilder(pattern);
        tot.append('#');
        tot.append(text);
        int n=pattern.length();
        int[] pi=prefixFunction(tot);
        for(int i=0;i<tot.length();i++){
            if(pi[i]==n){
                ans=i-(n+1)-n+1;
                break;
            }
        }
        return ans;
    }
}
