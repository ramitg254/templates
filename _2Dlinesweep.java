package templates;

import java.util.*;
import java.io.*;

// time complexity - O( n^2 * ( log(n) ))

public class _2Dlinesweep {
    // calculating union of area of rectangles whose vertical edges is parrallel to
    // y-axis and horizontal edges is parallel to x-axis

    static long mod = 1000000000 + 7;

    public static long area(ArrayList<ArrayList<Long>> rect) {
        ArrayList<ArrayList<Long>> events = new ArrayList<>();

        for (ArrayList<Long> x : rect) {
            events.add(new ArrayList<>());
            events.add(new ArrayList<>());
            Collections.addAll(events.get(events.size() - 2), x.get(0), 0l, x.get(1), x.get(3));
            Collections.addAll(events.get(events.size() - 1), x.get(2), 1l, x.get(1), x.get(3));
        }

        Collections.sort(events, (a, b) -> {
            if (a.get(0) == b.get(0))
                return (int) (a.get(1) - b.get(1));
            return (int) (a.get(0) - b.get(0));
        });

        long area = 0;
        long prev = Long.MIN_VALUE;
        TreeMap<long[], Integer> yline = new TreeMap<>((a, b) -> {
            if (a[0] == b[0])
                return (int) (a[1] - b[1]);
            return (int) (a[0] - b[0]);
        });

        for (ArrayList<Long> e : events) {
            if (prev != Long.MIN_VALUE) {
                area = (area + get_area(e.get(0) - prev, yline)) % mod;
            }

            if (e.get(1) != 0) {
                long[] a = { e.get(2), 1l };
                long[] b = { e.get(3), -1l };
                yline.put(a, yline.get(a) - 1);
                yline.put(b, yline.get(b) - 1);
                if (yline.get(a) == 0)
                    yline.remove(a);
                if (yline.get(b) == 0)
                    yline.remove(b);
            } else {
                long[] a = { e.get(2), 1l };
                long[] b = { e.get(3), -1l };
                yline.put(a, yline.getOrDefault(a, 0) + 1);
                yline.put(b, yline.getOrDefault(b, 0) + 1);
            }
            prev = e.get(0);
        }

        return area;
    }

    public static long get_area(long x, TreeMap<long[], Integer> yline) {
        long area = 0;
        long prev = Long.MIN_VALUE;
        long s = 0;

        for (long[] y : yline.keySet()) {
            int freq = yline.get(y);
            for (int i = 0; i < freq; i++) {
                s += y[1];
                if (s == y[1]) {
                    prev = y[0];
                }

                if (s == 0) {
                    area = (area + mmul(y[0] - prev, x)) % mod;
                }
            }
        }
        return area;
    }

    public static long mmul(long a, long b) {
        return ((a % mod) * (b % mod)) % mod;
    }
}
