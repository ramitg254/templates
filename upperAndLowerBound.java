package templates;

import java.util.ArrayList;

public class upperAndLowerBound {

    // strictly greater than key
    public static int upperBound(long[] ar, long key,int s,int e) {
        int ae=e;
        while (s < e && s != ae) {
            int mid = s + (e - s) / 2;
            if (ar[mid] <= key)
                s = mid + 1;
            else
                e = mid;
        }
        if (s == ae)
            return -1;
        else
            return s;
    }

    public static int upperBound(ArrayList<Long> ar, long key,int s,int e) {
        int ae=e;
        while (s < e && s != ae) {
            int mid = s + (e - s) / 2;
            if (ar.get(mid) <= key)
                s = mid + 1;
            else
                e = mid;
        }
        if (s == ae)
            return -1;
        else
            return s;
    }

    // greater than or equal to key
    public static int lowerBound(long[] ar, long key,int s,int e) {
        int ae=e;
        while (s < e && s != ae) {
            int mid = s + (e - s) / 2;
            if (ar[mid] < key)
                s = mid + 1;
            else
                e = mid;
        }
        if (s == ae)
            return -1;
        else
            return s;
    }

    public static int lowerBound(ArrayList<Long> ar, long key,int s,int e) {
        int ae=e;
        while (s < e && s != ae) {
            int mid = s + (e - s) / 2;
            if (ar.get(mid) < key)
                s = mid + 1;
            else
                e = mid;
        }
        if (s == ae)
            return -1;
        else
            return s;
    }
}
