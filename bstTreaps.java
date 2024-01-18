package templates;

import java.util.*;
import java.io.*;
import templates.OutputWriter;

public class bstTreaps {
    static OutputWriter out = new OutputWriter(System.out);
    

    static final Random rand = new Random(5);

    static class Treap {
        long data;
        int priority;
        Treap[] kids = new Treap[2];
        int subtreeSize;

        public Treap(long data) {
            this.data = data;
            priority = rand.nextInt();
            recalc(this);
        }


        static Treap[] split(Treap me,long val) {
            if(me==null){
                return new Treap[]{null,null};
            }
            if(me.data<=val){
                Treap[] RightRes=split(me.kids[1], val);
                me.kids[1]=RightRes[0];
                recalc(me);
                return new Treap[]{me,RightRes[1]};
            }else{
                Treap[] LeftRes=split(me.kids[0], val);
                me.kids[0]=LeftRes[1];
                recalc(me);
                return new Treap[]{LeftRes[0],me};
            }
        }

    
        static Treap merge(Treap l, Treap r) {
            if(l==null)return r;
            if(r==null)return l;

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

        // to insert a node in treap
        static Treap insert(Treap me,Treap newNode){
            if(me==null)return newNode;
            if(newNode.priority<me.priority){
                Treap[] res=split(me,newNode.data);
                newNode.kids[0]=res[0];
                newNode.kids[1]=res[1];
                recalc(newNode);
                return newNode;
            }
            if(me.data>=newNode.data){
                me.kids[0]=insert(me.kids[0], newNode);
                recalc(me);
                return me;
            }else{
                me.kids[1]=insert(me.kids[1], newNode);
                recalc(me);
                return me;
            }
        }

        // to erase a node from treap
        static Treap erase(Treap me, long val){
            if(me==null) return null;
            if(val==me.data){
                Treap res=merge(me.kids[0],me.kids[1]);
                return res;
            }else if(val<me.data){
                me.kids[0]=erase(me.kids[0], val);
                recalc(me);
                return me;
            }else{
                me.kids[1]=erase(me.kids[1],val);
                recalc(me);
                return me;
            }
        }

        // to search a value in the treap
        static Treap search(Treap me ,long val){
            if(me==null) return null;
            if(val==me.data)return me;
            else if(val<me.data){
                Treap res=search(me.kids[0],val);
                return res;
            }else{
                Treap res=search(me.kids[1], val);
                return res;
            }
        }

        // no. of nodes value less than value in the treap
        static int query(Treap me ,long val){
            if(me==null)return 0;
            int res=0;
            if(me.data<=val){
                res+=size(me.kids[0])+1;
                res+=query(me.kids[1], val);
            }else{
                res=query(me.kids[0], val);
            }
            return res;
        }

        static void recalc(Treap me) {
            if (me == null)
                return;
            me.subtreeSize = 1;

            for (Treap t : me.kids)
                if (t != null)
                    me.subtreeSize += t.subtreeSize;
        }

        
        static int size(Treap me) {
            return (me == null) ? 0 : me.subtreeSize;
        }
    }
}
