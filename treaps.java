package templates;

import java.util.*;
import java.io.*;
import templates.OutputWriter;


public class treaps {

    static OutputWriter out = new OutputWriter(System.out);
    // these are not bst treaps, these are treaps sustaining the order of elements of array

    // all the elements or indices occured before a particular index or element is either on its left child subtree or are present in nodes on ancestor levels which are to the left of that node and vice-versa we can say for elements occuring after that particular element.

    // first make an individual treap node of each element
    // initialise a treap node t as null
    // then call merge one by one between t and individual treap nodes 

    static final Random rand = new Random(5);

    static class Treap {
        long data;
        int priority;
        Treap[] kids = new Treap[2];
        long sum, toProp;
        int subtreeSize;

        public Treap(long data) {
            this.data = data;
            priority = rand.nextInt();
            recalc(this);
        }

        static Treap init(long[] ar){
            if(ar.length==0)return null;
            Treap t=new Treap(ar[0]);
            for(int i=1;i<ar.length;i++)t=Treap.merge(t, new Treap(ar[i]));
            return t;
        }

        static void print(Treap t,int arraylength){
            for(int i=0;i<arraylength;i++){
                Treap[] part=Treap.split(t, 1);
                t=part[1];
                out.print(part[0].data+" ");
            }
        }

        static Treap[] split(Treap me, int nInLeft) {
            if (me == null)
                return new Treap[] { null, null };
            if (size(me.kids[0]) >= nInLeft) {
                Treap[] LeftRes = split(me.kids[0], nInLeft);
                me.kids[0] = LeftRes[1];
                recalc(me);
                return new Treap[] { LeftRes[0], me };
            } else {
                nInLeft = nInLeft - size(me.kids[0]) - 1;
                Treap[] RightRes = split(me.kids[1], nInLeft);
                me.kids[1] = RightRes[0];
                recalc(me);
                return new Treap[] { me, RightRes[1] };
            }
        }

        static Treap merge(Treap l, Treap r) {
            if (l == null)
                return r;
            if (r == null)
                return l;
            prop(l);
            prop(r);
            if (l.priority < r.priority) {
                l.kids[1] = merge(l.kids[1], r);
                recalc(l);
                return l;
            } else {
                r.kids[0] = merge(l, r.kids[0]);
                recalc(r);
                return r;
            }
        }

        static void recalc(Treap me) {
            if (me == null)
                return;
            me.subtreeSize = 1;
            me.sum = me.data + (long)(me.toProp * size(me));
            for (Treap t : me.kids)
                if (t != null)
                    me.subtreeSize += t.subtreeSize;
            for (Treap t : me.kids)
                if (t != null)
                    me.sum += t.sum + t.toProp * size(t);
        }

        static void prop(Treap me) {
            if (me == null)
                return;
            if (me.toProp == 0)
                return;
            for (Treap t : me.kids)
                if (t != null)
                    t.toProp += me.toProp;
            me.data += me.toProp;
            me.toProp = 0;
            recalc(me);
        }

        static Treap rangeAdd(Treap t, int l, int r, long toAdd) {
            Treap[] a = split(t, l);
            Treap[] b = split(a[1], r - l + 1);
            b[0].toProp += toAdd;
            return merge(a[0], merge(b[0], b[1]));
        }

        static int size(Treap me) {
            return (me == null) ? 0 : me.subtreeSize;
        }
    }
}
