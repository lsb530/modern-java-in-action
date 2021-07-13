package chapter5;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
            .map(String::length)
            .collect(toList());
        System.out.println(wordLengths);
        List<String[]> failure1 = words.stream()
            .map(word -> word.split(""))
            .distinct()
            .collect(toList());
        failure1.forEach(System.out::println);
        List<Stream<String>> failure2 = words.stream()
            .map(word -> word.split(""))
            .map(Arrays::stream)
            .distinct()
            .collect(toList());
        failure2.forEach(System.out::println);
        List<String> uniqueCharacters = words.stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(toList());
        uniqueCharacters.forEach(System.out::println);
//        words.stream()
//            .flatMap((String line) -> Arrays.stream(line.split("")))
//            .distinct()
//            .forEach(System.out::println);
    }

}