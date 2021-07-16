package chapter5;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteGenerateStream {

    public static void main(String[] args) {
        // Stream.generate를 이용한 임의의 double 스트림
        Stream.generate(Math::random)
            .limit(10)
            .forEach(x -> System.out.printf("%f ", x));
//            .forEach(System.out::println);
        System.out.println();

        // Stream.generate을 이용한 요소 1을 갖는 스트림
        IntStream.generate(() -> 1)
            .limit(5)
            .forEach(x -> System.out.printf("%d ", x));
//            .forEach(System.out::println);
        System.out.println();

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        })
            .limit(5)
            .forEach(x -> System.out.printf("%d ", x));
//            .forEach(System.out::println);
        System.out.println();

        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }

        };
        IntStream.generate(fib)
            .limit(10)
            .forEach(x -> System.out.printf("%d ", x));
//            .forEach(System.out::println);
//        Paths.get("/data.txt")
//        long uniqueWords = Files.lines(Paths.get("../data.txt"), Charset.defaultCharset())
//            .flatMap(line -> Arrays.stream(line.split(" ")))
//            .distinct()
//            .count();
//
//        System.out.println("There are " + uniqueWords + " unique words in data.txt");
    }
}