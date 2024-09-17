import java.io.*;
import java.util.*;

public class Catalan {

    // recursive formula =>  CAT(n)= summation k=0 to k=n-1 CAT(k) * CAT(n-1-k) => O(n^2)
    // combinatorial formula => CAT(n)= (1 / (n+1)) * (2n C n) => O(n)


    static long[] cat;
    static long mod = 1000000000 + 7;// generally it is a prime
    static int FACTORIAL_SIZE = (int) 1.1e6;
    static long fact[] = new long[FACTORIAL_SIZE];
    static long ifact[] = new long[FACTORIAL_SIZE];
    static boolean __factorials_generated__ = false;

    // call gencat to generate catalan numbers upto n

    public static void gencat(int n) {

        cat = new long[n + 1];
        cat[0] = 1;
        cat[1] = 1;
        gen_factorial(2 * n);
        for (int i = 2; i <= n; i++) {
            cat[i] = mdiv(nck(2 * i, i), i + 1);
        }
    }

    public static void gen_factorial(int n) {
        __factorials_generated__ = true;
        fact[0] = fact[1] = ifact[0] = ifact[1] = 1;
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
        if (k < 0 || n < k)
            return 0;
        long den = (ifact[k] * ifact[n - k]) % mod;
        return (den * fact[n]) % mod;
    }

    public static long mpow(long base, long exp) {
        long res = 1;
        base = base % mod;
        if (base == 0)
            return 0;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    public static long madd(long a, long b) {
        return (a + b) % mod;
    }

    public static long msub(long a, long b) {
        return (((a - b) % mod) + mod) % mod;
    }

    public static long mmul(long a, long b) {
        return ((a % mod) * (b % mod)) % mod;
    }

    /* this works only if mod is prime(Fermat's Little Theorm ) */
    public static long minv(long base) {
        return mpow(base, mod - 2);
    }

    public static long mdiv(long a, long b) {
        return mmul(a, minv(b));
    }

}
