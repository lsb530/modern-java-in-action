package chapter5;

import static chapter5.Dish.menu;
import static java.util.stream.Collectors.toList;

import chapter5.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Filtering {

    public static void main(String[] args) {
        // 프레디케이트로 거름
        System.out.println("Filtering with a predicate");
        List<Dish> vegetarianMenu = menu.stream()
            .filter(Dish::isVegetarian)
            .collect(toList());
        vegetarianMenu.forEach(System.out::println); // 함수 디스크럽터

        // 고유 요소로 거름
        System.out.println("Filtering unique elements:");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);

        menu.stream()
            .filter(dish -> dish.getType() == Type.MEAT)
            .collect(toList())
            .forEach(System.out::println);
    }
}