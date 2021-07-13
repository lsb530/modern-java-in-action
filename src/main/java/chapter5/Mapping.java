package chapter5;

import static chapter5.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {

    public static void main(String[] args) {
//        dishNames();
//        wordCount();
        dishNamesCount();
    }

    private static void dishNames() {
        List<String> dishNames = menu.stream()
            .map(Dish::getName)
            .collect(Collectors.toList());
        dishNames.forEach(System.out::println);
    }

    private static void dishNamesCount() {
        List<Integer> dishNameLengths = menu.stream()
            .map(Dish::getName)
            .map(String::length)
            .collect(Collectors.toList());
        dishNameLengths.forEach(System.out::println);
    }

    private static void wordCount() {
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());
        wordLengths.forEach(System.out::println);
    }
}