package chapter6;

import static chapter6.Dish.menu;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NestedPartition {

    public static void main(String[] args) {
        Map<Boolean, Map<Boolean, List<Dish>>> 채소and칼로리500이상 = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian,
                partitioningBy(dish -> dish.getCalories() > 500)));
        System.out.println("채소and칼로리500이상 = " + 채소and칼로리500이상);
//        menu.stream()
//            .collect(
//                partitioningBy(Dish::isVegetarian,
//                    partitioningBy(Dish::getType)) // <- partitioningBy 안에는 boolean만 가능
//            );
        Map<Boolean, Long> 채소개수 = menu.stream().collect(
            partitioningBy(Dish::isVegetarian, counting())
        );
        System.out.println("채소개수 = " + 채소개수);
    }
}