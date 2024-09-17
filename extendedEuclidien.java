import java.util.*;
import java.io.*;

public class extendedEuclidien {

    // to find (x,y) such that a*x +b*y= gcd
    /*
     
     * if b*x1 +(a mod b)*y1=gcd => b*x1 +(a - floor(a/b)*b)=gcd
     
     * then for a*x +b*y =gcd
     
     * we have relation => x=y1 and y=x1-y1*(floor(a/b))
     
     */
    
    public static long gcd (long a,long b,long[] x,long[] y){
        if(b==0){
            x[0]=1;
            y[0]=0;
            return a;
        }
        long[] x1=new long[1];
        long[] y1=new long[1];
        long gcd =gcd (b,a%b,x1,y1);
        x[0]=y1[0];
        y[0]=x1[0]-y1[0]*(a/b);
        return gcd ;
    }

}
