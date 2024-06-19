package templates;

public class BIT {
    long[] bit;
    long[] ar;
    int size;

    public BIT(int n) {
        bit = new long[n + 1];
        ar = new long[n];
        size = n;
    }

    public void update(int ind, long val) {
        add(ind, val - ar[ind]);
    }

    public void add(int ind, long val) {
        ar[ind] += val;
        ind++;
        for (int i = ind; i <= size; i += (i & (-i))) {
            bit[i] += val;
        }
    }

    public long query(int ind) {
        ind++;
        long sum = 0;
        for (int i = ind; i > 0; i -= (i & (-i))) {
            sum += bit[i];
        }
        return sum;
    }
}
