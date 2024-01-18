package templates;

import java.util.*;
import java.io.*;

public class prefx {

    // no. of elements less than an element x in its prefix of array

    long[] a;
    long[] ga;
    SegmentTree st;
    int n;

    public prefx(int[] x) {
        this.n = x.length;
        st = new SegmentTree(n);
        a = new long[n];
        ga = new long[n];
        for (int i = 0; i < n; i++) {
            ga[i] = x[i];
            a[i] = x[i];
        }
        Arrays.sort(a);
    }

    public int findInd(long val) {
        int ind = Arrays.binarySearch(a, val);
        if (ind < 0)
            ind = Math.abs(ind) - 1;
        return ind;
    }

    public ArrayList<Long> run() {
        ArrayList<Long> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ind = findInd(ga[i]);
            long count = st.query(0, 0, n - 1, 0, ind - 1);
            st.update(0, 0, n - 1, ind, 1);
            ans.add(count);
        }
        return ans;
    }
}