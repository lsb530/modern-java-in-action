package chapter4;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import chapter4.Dish.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class HighCaloriesNames {

    public static void main(String[] args) {
        // 초기화
        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 400, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
        );
//        List<String> names = menu.stream()
//            .filter(dish -> {
//                System.out.println("filtering " + dish.getName());
//                return dish.getCalories() > 300;
//            })
//            .map(dish -> {
//                System.out.println("mapping " + dish.getName());
//                return dish.getName();
//            })
//            .limit(3)
//            .collect(toList());
//        System.out.println(names);
        menu.stream().forEach(dish -> System.out.println(dish.getCalories()));
        List<String> collect1 = menu.stream().map(Dish::getName).collect(toList());
        System.out.println("collect1 = " + collect1);
        menu.stream().map(dish -> dish.getCalories());
        List<String> lowCaloricDishesName = menu.stream()
            .filter(d -> d.getCalories() < 400) // Predicate
            .sorted(comparing(Dish::getCalories))
//            .map(Dish::getType)
            .map(Dish::getName) //
            .collect(toList());
        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
        Map<Type, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("collect = " + collect);
    }

}