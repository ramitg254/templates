package templates;

public class prefAndSuffsums {
    public static long[] pref(long[] a){
        long[] pref=new long[a.length+1];
        for(int i=1;i<=a.length;i++)pref[i]=pref[i-1]+a[i-1];
        return pref;
    }

    public static long[] suff(long[] a){
        long[] suff=new long[a.length+1];
        for(int i=a.length-1;i>=0;i--)suff[i]=suff[i+1]+a[i];
        return suff;
    }
}
