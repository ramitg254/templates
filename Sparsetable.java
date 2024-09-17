import java.util.*;
import java.io.*;

public class Sparsetable {

   // in sparse table original array should not be updated

    static long[][] st;
    static int LOG;

    public static void init(int n) {
        LOG = (int) (Math.log(n) / Math.log(2));
        st = new long[n][LOG + 1];
    }

    public static void fill(long[] a, int n) {

        for (int i = 0; i < n; i++)
            st[i][0] = a[i];

        for (int j = 1; j <= LOG; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // here we have used overlapping intervals to query minimum in O(1) time 
    public static  long query(int L, int R) {

        int length = R - L + 1;
        int log = (int) (Math.log(length) / Math.log(2));

        long res= Math.min(st[L][log], st[R - (1 << log) + 1][log]);
        return res;
    }

    //in case of sum queries it gives query value in O(log(length of range)) as we have add up ans of the bits of which length is composed of
}
