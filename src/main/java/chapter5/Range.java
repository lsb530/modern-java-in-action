package chapter5;

import java.util.stream.IntStream;

public class Range {

    public static void main(String[] args) {
        IntStream evenNumbers2To99 = IntStream.range(1, 100)
            .filter(n -> n % 2 == 0);
        IntStream evenNumbers1To100 = IntStream.rangeClosed(1, 100)
            .filter(n -> n % 2 == 0);
        System.out.println("evenNumbers2To99 = " + evenNumbers2To99);
        evenNumbers2To99.forEach(num -> System.out.printf("%d ", num));
        System.out.println();
        evenNumbers1To100.forEach(num -> System.out.printf("%d ", num));
    }
}