package templates;

import java.util.*;
import java.io.*;

public class convexhull {
    static ArrayList<long[]> points;
    static ArrayList<long[]> upper;
    static ArrayList<long[]> down;
    static ArrayList<long[]> tot;

    static void init() {
        points = upper = down = tot = new ArrayList<>();
    }

    static void addPoint(long a, long b) {
        points.add(new long[] { a, b });
    }

    static int orient(long[] a, long[] b, long[] c) {
        long val = (b[0] - a[0]) * (c[1] - b[1]) - (c[0] - b[0]) * (b[1] - a[1]);

        if (val < 0)
            return -1; // clockwise
        else if (val > 0)
            return 1; // counterclockwise
        else
            return 0; // collinear
    }

    static void find_hull() {

        if (points.size() == 1) {
            upper = down = tot = points;
            return;
        }

        Collections.sort(points, (a, b) -> {
            if (a[0] < b[0])
                return -1;
            else if (a[0] > b[0])
                return 1;
            else {
                if (a[1] < b[1])
                    return -1;
                if (a[1] > b[1])
                    return 1;
                return 0;
            }
        });
        long[] f = points.get(0);
        long[] l = points.get(points.size() - 1);
        upper.add(f);
        down.add(f);

        int sz = points.size();
        for (int i = 1; i < sz; i++) {

            // cw

            // to include collinear points replace ==-1 with !=1 below
            if (i == points.size() - 1 || orient(f, points.get(i), l) == -1) {

                // to include collinear points replace !=-1 with ==1 below
                while (upper.size() >= 2
                        && orient(upper.get(upper.size() - 2), upper.get(upper.size() - 1), points.get(i)) != -1)
                    upper.remove(upper.size() - 1);
                upper.add(points.get(i));
            }

            // ccw

            // to include collinear points replace ==1 with !=-1 below
            if (i == points.size() - 1 || orient(f, points.get(i), l) == 1) {

                // to include collinear points replace !=1 with ==-1 below
                while (down.size() >= 2
                        && orient(down.get(down.size() - 2), down.get(down.size() - 1), points.get(i)) != 1)
                    down.remove(down.size() - 1);
                down.add(points.get(i));
            }
        }

        for (int i = 0; i < (int) upper.size(); i++)
            tot.add(upper.get(i));
        for (int i = down.size() - 2; i > 0; i--)
            tot.add(down.get(i));
    }

}
