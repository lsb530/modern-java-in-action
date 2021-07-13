package chapter5;

import static chapter5.Dish.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoopDiff {

    public static void main(String[] args) {
        System.out.println("외부 반복");
        List<Dish> external = externalLoop();
        for (Dish dish : external) {
            System.out.println("dish.getName() = " + dish.getName());
        }
        System.out.println("내부 반복");
        List<Dish> internal = internalLoop();
        for (Dish dish : internal) {
            System.out.println("dish.getName() = " + dish.getName());
        }
        // 좀 더 짧은 코드
        externalLoop().forEach(System.out::println);
        externalLoop().forEach(dish -> System.out.println("음식 이름 : " + dish.getName()));
        internalLoop().forEach(System.out::println);
        internalLoop().forEach(dish -> System.out.println("음식 이름 : " + dish.getName()));
    }

    public static <T> List<Dish> externalLoop() {
        List<Dish> vegetarianDishes = new ArrayList<>();
        for (Dish d : menu) {
            if(d.isVegetarian()){
                vegetarianDishes.add(d);
            }
        }
        return vegetarianDishes;
    }

    public static <T> List<Dish> internalLoop() {
        return menu.stream()
        .filter(Dish::isVegetarian)
        .collect(Collectors.toList());
    }

}