package templates;

public class lazyProp {
    long[] ar;
    long[][] seg;

    public lazyProp(int n) {
        seg =new long[4*n][2];
        ar=new long[n];
    }

    public  void build(int node,int low,int high){
        if(low==high){
            seg[node][0]=ar[low];
            return;
        }
        int mid=(low+high)/2;
        build(2*node+1,low,mid);
        build(2*node+2,mid+1,high);
        seg[node][0]=seg[2*node+1][0]+seg[2*node+2][0];
    }

    public void Lazyupdate(int node,int low,int high,int l,int r,int val){
        if(seg[node][1]!=0){
            seg[node][0]+=(high-low+1)*seg[node][1];
            if(low!=high){
                seg[2*node+1][1]+=seg[node][1];
                seg[2*node+2][1]+=seg[node][1];
            }
            seg[node][1]=0;
        }
        if(low>r || high<l || low>high)return;
        if(low>=l && high<=r){
            seg[node][0]+=(high-low+1)*val;
            if(low!=high){
                seg[2*node+2][1]+=val;
                seg[2*node+1][1]+=val;
            }
            return;
        }
        int mid=(low+high)/2;
        Lazyupdate(2*node+1,low,mid,l,r,val);
        Lazyupdate(2*node+2,mid+1,high,l,r,val);
        seg[node][0]=seg[2*node+1][0]+seg[2*node+2][0];
    }

    public  long query(int node,int low ,int high,int l,int r){
        if(seg[node][1]!=0){
            seg[node][0]+=(high-low+1)*seg[node][1];
            if(low!=high){
                seg[2*node+1][1]+=seg[node][1];
                seg[2*node+2][1]+=seg[node][1];
            }
            seg[node][1]=0;
        }
        if(low>r || high<l || low>high)return 0;

        else if (low>=l && high<=r) {
            return seg[node][0];
        }else{
            int mid=(low+high)/2;
            long a=query(2*node+1,low,mid,l,r);
            long b=query(2*node+2,mid+1,high,l,r);
            return a+b;
        }
    }
}
