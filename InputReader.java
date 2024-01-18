package templates;

import java.util.*;
import java.io.*;

class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar++];
    }

    public int peek() {
        if (numChars == -1) {
            return -1;
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0) {
                return -1;
            }
        }
        return buf[curChar];
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int sum = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            sum *= 10;
            sum += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return sum * sgn;
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long sum = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            sum *= 10;
            sum += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return sum * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder sum = new StringBuilder();
        do {
            if (Character.isValidCodePoint(c)) {
                sum.appendCodePoint(c);
            }
            c = read();
        } while (!isSpaceChar(c));
        return sum.toString();
    }

    public boolean isSpaceChar(int c) {
        if (filter != null) {
            return filter.isSpaceChar(c);
        }
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private String readLine0() {
        StringBuilder buf = new StringBuilder();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r') {
                buf.appendCodePoint(c);
            }
            c = read();
        }
        return buf.toString();
    }

    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0) {
            s = readLine0();
        }
        return s;
    }

    public String readLine(boolean ignoreEmptyLines) {
        if (ignoreEmptyLines) {
            return readLine();
        } else {
            return readLine0();
        }
    }

    public String nextLine() {
        return readLine();
    }

    public char nextCharacter() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        return (char) c;
    }

    public double nextDouble() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double sum = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E') {
                return sum * Math.pow(10, nextInt());
            }
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            sum *= 10;
            sum += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E') {
                    return sum * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                m /= 10;
                sum += (c - '0') * m;
                c = read();
            }
        }
        return sum * sgn;
    }

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1) {
            read();
        }
        return value == -1;
    }

    public SpaceCharFilter getFilter() {
        return filter;
    }

    public void setFilter(SpaceCharFilter filter) {
        this.filter = filter;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }

    public int[] nextIntArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; ++i)
            array[i] = nextInt();
        return array;
    }

    public int[] nextSortedIntArray(int n) {
        int array[] = nextIntArray(n);
        Arrays.sort(array);
        return array;
    }

    public int[] nextSumIntArray(int n) {
        int[] array = new int[n];
        array[0] = nextInt();
        for (int i = 1; i < n; ++i)
            array[i] = array[i - 1] + nextInt();
        return array;
    }

    public long[] nextLongArray(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; ++i)
            array[i] = nextLong();
        return array;
    }

    public long[] nextSumLongArray(int n) {
        long[] array = new long[n];
        array[0] = nextInt();
        for (int i = 1; i < n; ++i)
            array[i] = array[i - 1] + nextInt();
        return array;
    }

    public long[] nextSortedLongArray(int n) {
        long array[] = nextLongArray(n);
        Arrays.sort(array);
        return array;
    }

}
