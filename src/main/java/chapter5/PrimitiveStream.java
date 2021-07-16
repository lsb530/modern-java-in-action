package chapter5;

import static chapter5.Dish.menu;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStream {

    public static void main(String[] args) {
//        WrapperSum();
//        PrimitiveSum();
//        OptionalIntMax();
        IntStream intStream = numberStream();
        Stream<Integer> stream = intStream.boxed();
//        utilsError(intStream);
//        utilsError2(intStream);
//        utilsMiddleError(intStream);
    }

    // 스트림은 1번 처리되고 파이프라인이 닫히기때문에 연속해서 쓸 수 없다!!!
    private static void utilsError(IntStream intStream) {
        int sum = intStream.sum();
        int max = intStream.max().getAsInt();
        int min = intStream.min().getAsInt();
        double average = intStream.average().getAsDouble();
        System.out.println("sum = " + sum);
        System.out.println("max = " + max);
        System.out.println("min = " + min);
        System.out.println("average = " + average);
    }

    // 오호... 이런 방식으로도 안되는구나
    private static void utilsError2(IntStream intStream) {
        Sum(intStream);
        IntStream test = intStream;
        Max(test);
    }

    // 중간연산은 파이프라인을 소비하지 않는다는게 아닌듯??
    private static void utilsMiddleError(IntStream intStream) {
        IntStream minus = intStream.filter(value -> value < 0);
        IntStream plus = intStream.filter(value -> value > 0);
        System.out.println("minus = " + minus);
        System.out.println("plus = " + plus);
    }

    private static void Max(IntStream intStream) {
        int max = intStream.max().getAsInt();
        System.out.println("max = " + max);
    }

    private static void Sum(IntStream intStream) {
        int sum = intStream.sum();
        System.out.println("sum = " + sum);
    }


    private static IntStream numberStream() {
        return menu.stream()
            .mapToInt(Dish::getCalories);
    }

    private static void OptionalIntMax() {
        OptionalInt max = menu.stream()
            .mapToInt(Dish::getCalories)
            .max();
        System.out.println("max = " + max);
        System.out.println("max(int) = " + max.getAsInt());
    }

    private static void PrimitiveSum() {
        int primitiveSum = menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();
        System.out.println("primitiveSum = " + primitiveSum);
    }

    private static void WrapperSum() {
        Integer wrapperSum = menu.stream()
            .map(Dish::getCalories)
            .reduce(0, Integer::sum);
        System.out.println("wrapperSum = " + wrapperSum);
    }
}