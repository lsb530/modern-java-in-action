package chapter5;

import static chapter5.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Finding {

    public static void main(String[] args) {
//        anyMatch();
//        allMatch();
//        noneMatch();
//        findAny();
//        findFirst();
    }

    private static void findFirst() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
            .map(n -> n * n)
            .filter(n -> n % 3 == 0)
            .findFirst()
            .ifPresent(System.out::println);
    }

    private static void findAny() {
        menu.stream()
            .filter(Dish::isVegetarian)
            .skip(1) // 1을 스킵해줌
            .findAny() // 쇼트서킷 평가
            .ifPresent(dish -> System.out.println(dish.getName()));
    }

    private static void noneMatch() {
        System.out.println("Predicate가 모든 요소와 일치하지 않는지 검사=> noneMatch");
        boolean isHealthy = menu.stream()
            .noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println("isHealthy = " + isHealthy);
    }

    private static void allMatch() {
        System.out.println("Predicate가 모든 요소와 일치하는지 검사=> allMatch");
        boolean isHealthy = menu.stream()
            .allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("isHealthy = " + isHealthy);
    }

    private static void anyMatch() {
        System.out.println("Predicate가 적어도 한 요소와 일치하는지 검사=> anyMatch");
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }
}