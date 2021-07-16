package chapter6;

import static chapter6.Dish.menu;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;

import chapter6.Dish.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Partition {

    public static void main(String[] args) {
//        basicPartitioning();
//        vegetarianDishesByType();
        mostCaloricPartitionedByVegetarian();
    }

    private static void basicPartitioning() {
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(
            partitioningBy(Dish::isVegetarian)
        );
        System.out.println("partitionedMenu = " + partitionedMenu);
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println("vegetarianDishes = " + vegetarianDishes);
        List<Dish> notVegetarianDishes = partitionedMenu.get(false);
        System.out.println("notVegetarianDishes = " + notVegetarianDishes);
        List<Dish> vegeDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println("vegeDishes = " + vegeDishes);
    }

    private static void vegetarianDishesByType() {
        Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream()
            .collect(
                partitioningBy(Dish::isVegetarian,
                    groupingBy(Dish::getType))
            );
        System.out.println("vegetarianDishesByType = " + vegetarianDishesByType);
    }

    private static void mostCaloricPartitionedByVegetarian() {
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(
            partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))
        );
        System.out
            .println("mostCaloricPartitionedByVegetarian = " + mostCaloricPartitionedByVegetarian);
    }
}