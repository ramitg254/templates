package templates;

import java.util.*;
import java.io.*;

public class number_theory {
    // number theory
    
    public static long gcd(long x, long y) {
        if (x == 0)
            return y;
        if (y == 0)
            return x;
        return gcd(y, x % y);
    }

    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static boolean prime[] = new boolean[15000105];

    public static void sieve(int n) {
        for (int i = 0; i <= n; i++)
            prime[i] = true;
        for (int p = 2; p * p <= n; p++) {
            if (prime[p] == true) {
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
        prime[1] = prime[0] = false;
    }

    static List<Long> primelist = new ArrayList<>();
    static boolean __primes_generated__ = false;

    public static void genprimes(int n) {
        __primes_generated__ = true;
        sieve(n + 1);
        for (int i = 2; i <= n; i++)
            if (prime[i])
                primelist.add((long) i);
    }

    public static ArrayList<Long> primefactors(long n) {
        if (!__primes_generated__) {
            System.out.println("call genprimes");
            return null;
        }
        ArrayList<Long> facs = new ArrayList<>();

        for (int i = 0; primelist.get(i) * primelist.get(i) <= n && i < primelist.size(); i++) {
            if (n % primelist.get(i) == 0) {
                while (n % primelist.get(i) == 0) {
                    n /= primelist.get(i);
                    facs.add(primelist.get(i));
                }
            }
        }
        if (n > 1) {
            facs.add(n);
        }
        Collections.sort(facs);
        return facs;
    }

    public static ArrayList<Long> getdivs(long n) {
        ArrayList<Long> divs = new ArrayList<>();
        for (long i = 1; i * i < n; i++) {
            if (n % i == 0) {
                divs.add(i);
                divs.add(n / i);
            }
        }
        long sqrt = (long) Math.sqrt(n);
        if (sqrt * sqrt == n)
            divs.add(sqrt);
        Collections.sort(divs);
        return divs;
    }
}
