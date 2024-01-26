package templates;

import java.util.ArrayList;

public class binarySearch {

    public static int bs(long[] a, long target, int s, int e) { // e is inclusive
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (a[mid] < target)
                s = mid + 1;
            else
                e = mid;
        }
        if (a[s] != target)
            return -1;
        return s;
    }

    public static int bs(ArrayList<Long> a, long target, int s, int e) { // e is inclusive
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (a.get(mid) < target)
                s = mid + 1;
            else
                e = mid;
        }
        if (a.get(s) != target)
            return -1;
        return s;
    }
}
