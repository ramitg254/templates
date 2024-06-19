package templates;

public class Zfunction {
    
    int[] zvalues(StringBuilder s){
        int n=s.length();
        int[] zvals=new int[n];
        int l=0,r=0;
        for(int i=1;i<n;i++){
            if(i<r){
                zvals[i]=Math.min(zvals[i-l],r-i);
            }

            while(i+zvals[i]<n && s.charAt(i+zvals[i])==s.charAt(zvals[i]))zvals[i]++;

            if(i+zvals[i]>r){
                l=i;
                r=i+zvals[i];
            }
        }
        return zvals;
    }

    public int match(StringBuilder pattern,StringBuilder text){
        int ans=-1;
        StringBuilder tot=new StringBuilder(pattern);
        tot.append('#');
        tot.append(text);
        int n=pattern.length();
        int[] z=zvalues(tot);
        for(int i=0;i<tot.length();i++){
            if(z[i]==n){
                ans=i-(n+1);
                break;
            }
        }
        return ans;
    }
}
