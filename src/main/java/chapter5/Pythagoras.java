package chapter5;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pythagoras {

    public static void main(String[] args) {
        int a = 3;
        Stream<int[]> notGood = IntStream.rangeClosed(1, 100)
            .filter(b -> Math.sqrt(a * a + b + b) % 1 == 0)
            .boxed()
            .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        Stream<int[]> good = IntStream.rangeClosed(1, 100)
            .filter(b -> Math.sqrt(a * a + b + b) % 1 == 0)
            .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
            .flatMap(x ->
                IntStream.rangeClosed(x, 100)
                    .filter(y -> Math.sqrt(x * x + y * y) % 1 == 0)
                    .mapToObj(y -> new int[]{x, y, (int) Math.sqrt(x * x + y * y)})
            );
        pythagoreanTriples.limit(5)
            .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
            .flatMap(x -> IntStream.rangeClosed(x, 100)
                .mapToObj(y -> new double[]{x, y, Math.sqrt(x * x + y * y)})
                .filter(t -> t[2] % 1 == 0));
        pythagoreanTriples2.limit(5)
            .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}