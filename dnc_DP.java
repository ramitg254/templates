
import java.util.*;
import java.io.*; 

public class dnc_DP {

    // in divide and conquer optimisation we limit the range of optimal splitting point for each i for particular k and runs in n*log(n) for each k and total=k*n*log(n)
    // not the log(n) factor is due to the limited range for each index
    
    // the expression for it looks like: for all valid i->( dp(i,mid)=min(dp(i−1,j−1)+C(j,mid)) for all valid j in range for particular i) where C is cost function
    // note- this expression is only applied if quadrangle inequality is satisfied: opt(i,j)<=opt(i,j+1) (in general monotonic in one direction) where opt is optimal splitting point
    // if quadrangle inequality is satisfied for a problem the range of optimal splitting point for further indices can be limitised

    // eg. below is the eg. for problem subarray squares of cses
    
    static long[] ps, a;
    static int n, k;
    static long[][] dp;
    public static void solve() {
    /** take input **/
        // n = in.nextInt();
        // k = in.nextInt();
        // a = in.nextLongArray(n);

        // calculating prefix sum
        ps = new long[n + 1];
        for (int i = 1; i <= n; i++)
            ps[i] = ps[i - 1] + a[i - 1];

        //initialising dp
        dp = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++)
            Arrays.fill(dp[i], (long) 1e18);
        dp[0][0] = 0;

        // calculating dp values for each k
        for(int i=1;i<=k;i++){
            dnc(i,1,n,0,n-1);
        }

    /** print output **/
        // out.println(dp[k][n]);
        // out.flush();
    }

    public static void dnc(int i,int l,int r,int optl,int optr){
        if(l>r)return;
        int mid=(l+r)/2;

        int opt=optl;
        long val=dp[i-1][optl]+(ps[mid]-ps[optl])*(ps[mid]-ps[optl]);

        for(int j=optl;j<=Math.min(optr,r);j++){
            long sum=ps[mid]-ps[j];
            long res=dp[i-1][j]+sum*sum;
            if(res<val){
                val=res;
                opt=j;
            }
        }

        dp[i][mid]=val;

        dnc(i,l,mid-1,optl,opt);
        dnc(i,mid+1,r,opt,optr);
    }
}
