package templates;

public class SegmentTree {

    long[] seg;
    long[] ar;

    public SegmentTree(int n) {
        seg = new long[4 * n];
        ar = new long[n];
    }

    public void build(int node, int low, int high) {
        if (low == high) {
            seg[node] = ar[low];
            return;
        }
        int mid = (low + high) / 2;
        build(2 * node + 1, low, mid);
        build(2 * node + 2, mid + 1, high);
        seg[node] = seg[2 * node + 1] + seg[2 * node + 2];
    }

    public void update(int node, int low, int high, int ind, int val) {
        if (low == high) {
            ar[ind] = val;
            seg[node] = val;
            return;
        }
        int mid = (low + high) / 2;
        if (ind <= mid && ind >= low)
            update(2 * node + 1, low, mid, ind, val);
        else
            update(2 * node + 2, mid + 1, high, ind, val);

        seg[node] = seg[2 * node + 1] + seg[2 * node + 2];
    }

    public long query(int node, int low, int high, int l, int r) {
        if (low > r || high < l)
            return 0;
        else if (low >= l && high <= r) {
            return seg[node];
        } else {
            int mid = (low + high) / 2;
            long a = query(2 * node + 1, low, mid, l, r);
            long b = query(2 * node + 2, mid + 1, high, l, r);
            return (long) (a + b);
        }
    }

}
