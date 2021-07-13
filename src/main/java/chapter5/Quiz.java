package chapter5;

import static chapter5.Dish.menu;

import chapter5.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz {

    public static void main(String[] args) {
//        Quiz1();
//        Quiz2_1();
//        Quiz2_2();
//        Quiz2_3();
    }

    private static void Quiz2_3() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> paris = numbers1.stream()
            .flatMap(i -> numbers2.stream()
                .filter(j -> (i + j) % 3 == 0)
                .map(j -> new int[]{i, j})
            )
            .collect(Collectors.toList());
        paris.forEach(ints -> System.out.println(ints[0] + ", " +ints[1]));
    }

    private static void Quiz2_2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> paris = numbers1.stream()
            .flatMap(i -> numbers2.stream()
                .map(j -> new int[]{i, j})
            )
            .collect(Collectors.toList());
        paris.forEach(ints -> System.out.println(ints[0] + ", " +ints[1]));
    }

    private static void Quiz2_1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
            .map(integer -> integer * integer)
            .collect(Collectors.toList());
        squares.forEach(System.out::println);
    }

    private static void Quiz1() {
        List<Dish> firstTwoMeats = menu.stream()
            .filter(dish -> dish.getType() == Type.MEAT)
            .limit(2)
            .collect(Collectors.toList());
        firstTwoMeats.forEach(System.out::println);
    }

}