package chapter6;

import static chapter6.Dish.menu;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;
import util.GsonUtil;

public class Collect {

    static GsonUtil gsonUtil = new GsonUtil();

    public static void main(String[] args) {
        Gson gson = gsonUtil.getGson();
//        howManyDishes();
//        maxDish(gson);
//        totalCalories();
//        avgCalories();
        summarizingStastics(gson);
//        menuJoining();
//        totalCaloriesAndMostCalorieDish(gson);
//        misUseReduce();
//        collectSum();
//        longCount();
    }

    private static void longCount() {
        List<Double> doubleList = Arrays.asList(1.0, 3.4, 2.7, 3.8);
        Long longSum = doubleList.stream().collect(longCounting());
        System.out.println("longSum = " + longSum);
    }

    public static <T> Collector<T, ?, Long> longCounting() {
        return reducing(0L, e -> 1L, Long::sum);
    }

    private static void collectSum() {
        Integer totalCalories = menu.stream().collect(
            reducing(0,
                Dish::getCalories,
                Integer::sum));
        System.out.println("totalCalories = " + totalCalories);
    }

    private static void misUseReduce() { // collect
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
            new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
                l.add(e);
                return l;
            },
            (List<Integer> l1, List<Integer> l2) -> {
                l1.addAll(l2);
                return l1;
            }
        );
        System.out.println("numbers = " + numbers);
    }

    private static void totalCaloriesAndMostCalorieDish(Gson gson) {
        Integer totalCalories = menu.stream()
            .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("totalCalories = " + totalCalories);
        Dish mostCalorieDish = menu.stream()
            .collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
        System.out.println(gson.toJson(mostCalorieDish));
    }

    private static void menuJoining() {
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println("shortMenu = " + shortMenu);
        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("shortMenu2 = " + shortMenu2);
    }

    private static void summarizingStastics(Gson gson) {
        IntSummaryStatistics menuStatistics = menu.stream()
            .collect(summarizingInt(Dish::getCalories));
        System.out.println("menuStatistics = " + menuStatistics);
        System.out.println("gson.toJson(menuStatistics) = " + gson.toJson(menuStatistics));
    }

    private static void avgCalories() {
        Double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("avgCalories = " + avgCalories);
    }

    private static void totalCalories() {
        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories = " + totalCalories);
    }

    private static void maxDish(Gson gson) {
        Dish dish = menu.stream().collect(maxBy(Comparator.comparing(Dish::getCalories))).get();
        System.out.println("dish = " + gson.toJson(dish));
    }

    private static void howManyDishes() {
        Long howManyDishes = menu.stream().collect(counting());
        System.out.println("howManyDishes = " + howManyDishes);
        long howManyDishes2 = menu.stream().count();
        System.out.println("howManyDishes2 = " + howManyDishes2);
    }
}