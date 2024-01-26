package templates;

public class bitmanipulation {
    
    // counting of no. of set ith bits in an array
    public static int[][] pref(int[] a) {
        int[][] pref = new int[a.length + 1][32];
        for (int i = 0; i < 32; i++) {
            int val = (1 << i);
            for (int j = 1; j <= a.length; j++) {
                pref[j][i] = pref[j - 1][i];
                if ((val & a[j - 1]) == val) {
                    pref[j][i]++;
                }
            }
        }

        return pref;
    }
}
