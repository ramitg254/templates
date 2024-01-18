package templates;

import java.io.*;
import java.util.*;

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(objects[i]);
        }
    }

    public void println(Object... objects) {
        print(objects);
        writer.println();
    }

    public void println() {
        writer.println();
    }

    public void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(array[i]);
        }
    }

    public void println(int[] array) {
        print(array);
        writer.println();
    }

    public void print(long[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(array[i]);
        }
    }

    public void println(long[] array) {
        print(array);
        writer.println();
    }

    public <T> void print(List<T> array) {
        for (int i = 0; i < array.size(); i++) {
            if (i != 0) {
                writer.print(' ');
            }
            writer.print(array.get(i));
        }
    }

    public <T> void println(List<T> array) {
        print(array);
        writer.println();
    }

    public void separateLines(int[] array) {
        for (int i : array) {
            println(i);
        }
    }

    public void close() {
        writer.close();
    }

    public void flush() {
        writer.flush();
    }

}
