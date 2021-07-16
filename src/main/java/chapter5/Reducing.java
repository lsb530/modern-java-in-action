package chapter5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 5);
        List<Integer> emptyList = Collections.emptyList();
        System.out.println("basicSum = " + basicSum(numbers)); // 1+2+3+5 = 11
        System.out.println("reduceSum = " + reduceSum(numbers));
        System.out.println("reduceProduct = " + reduceProduct(numbers)); // 1*2*3*5 = 30
//        Optional<Integer> reduceWithoutIdentity = reduceWithoutIdentity(numbers);
        Optional<Integer> reduceWithoutIdentity = reduceWithoutIdentity(emptyList);
        int setData = 0;
//        if (reduceWithoutIdentity.isPresent()) {
//            Integer integer = reduceWithoutIdentity.get();
//            System.out.println("integer = " + integer);
//        }
        // Error Exception
        Integer result = reduceWithoutIdentity
            .orElseThrow(() -> new IllegalArgumentException("리스트가 비어있어서 에러가 났습니다"));
//            .orElseGet(() -> setData);
        reduceWithoutIdentity.ifPresent(System.out::println);
        System.out.println("result = " + result);
        System.out.println("reduceMax = " + reduceMax(numbers));
//        System.out.println("reduceMax = " + reduceMax(emptyList));
        System.out.println("reduceMin = " + reduceMin(numbers));
    }

    public static int basicSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static int reduceSum(List<Integer> numbers) {
//        return numbers.stream().reduce(0, (a, b) -> a + b);
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static int reduceProduct(List<Integer> numbers) {
        return numbers.stream().reduce(1, (a, b) -> a * b);
    }

    public static Optional<Integer> reduceWithoutIdentity(List<Integer> numbers) {
        return numbers.stream().reduce((a, b) -> a + b);
    }

    public static int reduceMax(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::max).get();
    }

    public static int reduceMin(List<Integer> numbers) throws NoSuchElementException {
        return numbers.stream().reduce(Integer::min).get();
    }


}