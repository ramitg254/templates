package templates;

public class ternarySearch {
    public static int ternary(long[] ar,int s,int e) {
        while (s < e) {
            int m1 = s + (e - s) / 3;
            int m2= e-(e-s)/3;

            if (ar[m1]<ar[m2])
                s = m1 + 1;
            else
                e = m2-1;
        }
        return s; // return maxima in range [s,e] indices inclusive
    }
    
}
