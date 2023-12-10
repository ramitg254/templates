package templates;

public class modop {
    // modop

    static long mod=1000000000 +7;//generally it is a prime
    public static long mpow(long base ,long exp){
        long res=1;
        base =base%mod;
        if(base==0)return 0;
        while(exp>0){
            if((exp & 1)==1){
                res=(res*base)%mod;
            }
            base=(base*base)%mod;
            exp>>=1;
        }
        return res;
    }
    public static long madd(long a,long b){
        return (a+b)%mod;
    }
    public static long msub(long a,long b){
        return (((a-b)%mod)+mod)%mod;
    }
    public static long mmul(long a,long b){
        return ((a%mod)*(b%mod))%mod;
    }

    /* this works only if mod is prime(Fermat's Little Theorm )*/ 
    public static long minv(long base){
        return mpow(base,mod-2);
    }

    /*use below inverse one if mod is not prime(Extended Euclid Theorm) */
    
    // public static long minv(long base, long M) 
    // {
    //     // here M is mod
    //     long m0 = M;
    //     long y = 0, x = 1;
 
    //     if (M == 1)
    //         return 0;
 
    //     while (base > 1) {
            
    //         long q = base / M;
    //         long t = M;
    //         M = base % M;
    //         base = t;
    //         t = y;
 
           
    //         y = x - q * y;
    //         x = t;
    //     }
 
        
    //     if (x < 0)
    //         x += m0;
 
    //     return x;
    // }

    public static long mdiv(long a,long b){
        return mmul(a,minv(b));
    }

    static int FACTORIAL_SIZE=(int)1.1e6;
    static long fact[]=new long[FACTORIAL_SIZE];
    static long ifact[]=new long[FACTORIAL_SIZE];
    static boolean __factorials_generated__=false;

    public static void gen_factorial(int n){
        __factorials_generated__=true;
        fact[0]=fact[1]=ifact[0]=ifact[1]=1;
        for (int i = 2; i <= n; i++) {
			fact[i] = (i * fact[i - 1]) % mod;
		}
		ifact[n] = minv(fact[n]);
		for (int i = n - 1; i >= 2; i--) {
			ifact[i] = ((i + 1) * ifact[i + 1]) % mod;
		}
    }

    public static long nck(int n, int k) {
		if (!__factorials_generated__) {
            System.out.println("call gen_factorial");
			return -1;
		}
		if (k < 0 || n < k) return 0;
		long den = (ifact[k] * ifact[n - k]) % mod;
		return (den * fact[n]) % mod;
	}
}
