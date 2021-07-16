package chapter5;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteIterateStream {

    public static void main(String[] args) {
        // Stream.iterate
        Stream.iterate(0, n -> n + 2)
            .limit(10)
            .forEach(x -> System.out.printf("%d ", x));
//            .forEach(System.out::println);
        System.out.println();

        // iterate를 이용한 피보나치
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
            .limit(20)
            .forEach(t -> System.out.printf("(%d, %d)", t[0], t[1]));
        System.out.println();

        // 일반적인 피보나치 수열 출력
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
            .limit(10)
            .map(t -> t[0])
            .forEach(x -> System.out.printf("%d ", x));
//            .forEach(System.out::println);
        System.out.println();

        IntStream.iterate(0, n -> n < 100, n -> n + 4)
            .forEach(x -> System.out.printf("%d ", x));
        System.out.println();

        // filter 메소드는 언제 이 작업을 중단해야 하는지를 알 수 없기때문
//        IntStream.iterate(0, n -> n + 4)
//            .filter(n -> n < 100)
//            .forEach(System.out::println);

        IntStream.iterate(0, n -> n + 4)
            .takeWhile(n -> n < 100)
            .forEach(x -> System.out.printf("%d ", x));
        System.out.println();
    }
}