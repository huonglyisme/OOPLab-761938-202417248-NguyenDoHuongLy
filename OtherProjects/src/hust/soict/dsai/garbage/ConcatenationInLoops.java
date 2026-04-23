package hust.soict.dsai.garbage;

import java.util.Random;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        final int ITERATIONS = 65536;

        Random r = new Random(123);
        long start = System.currentTimeMillis();

        String s = "";
        for (int i = 0; i < ITERATIONS; i++) {
            s += r.nextInt(2);
        }

        long end = System.currentTimeMillis();
        System.out.println("Using + operator: " + (end - start) + " ms");

        r = new Random(123);
        start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ITERATIONS; i++) {
            sb.append(r.nextInt(2));
        }
        s = sb.toString();

        end = System.currentTimeMillis();
        System.out.println("Using StringBuilder: " + (end - start) + " ms");

        r = new Random(123);
        start = System.currentTimeMillis();

        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < ITERATIONS; i++) {
            sf.append(r.nextInt(2));
        }
        s = sf.toString();

        end = System.currentTimeMillis();
        System.out.println("Using StringBuffer: " + (end - start) + " ms");
    }
}
