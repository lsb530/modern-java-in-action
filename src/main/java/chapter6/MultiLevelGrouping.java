package chapter6;

import static chapter6.Dish.menu;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

import chapter6.Dish.Type;
import chapter6.Grouping.CaloricLevel;
import com.google.gson.Gson;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import util.GsonUtil;

public class MultiLevelGrouping {

    public static void main(String[] args) {
        GsonUtil gsonUtil = new GsonUtil();
        Gson gson = gsonUtil.getGson();
//        multiLevelGrouping(gson);
//        subGroupDataGrouping();
//        maxDishGrouping();
//        totalCalorie();
//        collectMappingGrouping();
        collectMappingGrouping2();
    }

    private static void multiLevelGrouping(Gson gson) {
        Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
            groupingBy(Dish::getType, // 첫번째 수준의 분류 함수
                groupingBy(dish -> { // 두번째 수준의 분류 함수
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }))
        );
        System.out.println("dishesByTypeCaloricLevel = " + gson.toJson(dishesByTypeCaloricLevel));
    }

    private static void subGroupDataGrouping() {
        Map<Type, Long> typesCount = menu.stream().collect(
            groupingBy(Dish::getType, counting())
        );
        System.out.println("typesCount = " + typesCount);
    }

    private static void maxDishGrouping() {
        Map<Type, Optional<Dish>> mostCaloricByTypeOptional = menu.stream()
            .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("mostCaloricByTypeOptional = " + mostCaloricByTypeOptional);
        Map<Type, Dish> mostCaloricByType = menu.stream()
            .collect(groupingBy(Dish::getType, // 분류 함수
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), // 감싼 컬렉터
                    Optional::get))); // 변환 함수
        System.out.println("mostCaloricByType = " + mostCaloricByType);
    }

    private static void totalCalorie() {
        Map<Type, Integer> totalCaloriesByType = menu.stream().collect(
            groupingBy(Dish::getType,
                summingInt(Dish::getCalories)
            )
        );
        System.out.println("totalCaloriesByType = " + totalCaloriesByType);
    }

    private static void collectMappingGrouping() {
        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
            groupingBy(Dish::getType, mapping(dish -> {
                if (dish.getCalories() <= 400)
                    return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700)
                    return CaloricLevel.NORMAL;
                else
                    return CaloricLevel.FAT;
            }, toSet()))
        );
        System.out.println("caloricLevelsByType = " + caloricLevelsByType);
    }

    private static void collectMappingGrouping2() {
        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
            groupingBy(Dish::getType, mapping(dish -> {
                if (dish.getCalories() <= 400)
                    return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700)
                    return CaloricLevel.NORMAL;
                else
                    return CaloricLevel.FAT;
            }, toCollection(HashSet::new)))
        );
        System.out.println("caloricLevelsByType = " + caloricLevelsByType);
    }
}