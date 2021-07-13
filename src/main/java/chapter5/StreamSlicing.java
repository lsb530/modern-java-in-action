package chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.StopWatch;

public class StreamSlicing {
    public static final List<Dish> specialMenu = Arrays.asList(
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER));

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        int calories = 320;
        stopWatch.reset();
        stopWatch.start();
        specialMenu.stream()
            .filter(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList())
            .forEach(System.out::println);
        stopWatch.stop();
        System.out.println("수행시간 : " + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        take_while(calories);
        stopWatch.stop();
        System.out.println("수행시간 : " + stopWatch.getTime());
        stopWatch.reset();
        drop_while(calories);
        int count = 2;
        limit(count);
        skip(count);
    }

    private static void take_while(int calories) {
        System.out.println("\nJava9 takeWhile 사용");
        specialMenu.stream()
            .takeWhile(dish -> dish.getCalories() < calories)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }

    private static void drop_while(int calories) {
        System.out.println("\nJava9 dropWhile 사용");
        specialMenu.stream()
            .dropWhile(dish -> dish.getCalories() < calories)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }

    private static void limit(int count) {
        System.out.println("\nJava8 limit 사용");
        specialMenu.stream()
            .dropWhile(dish -> dish.getCalories() > 300)
            .limit(count)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }

    private static void skip(int count) {
        System.out.println("\nSkip 사용");
        specialMenu.stream()
            .filter(d -> d.getCalories() > 300)
            .skip(count)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }
}