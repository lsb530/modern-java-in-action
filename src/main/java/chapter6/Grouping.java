package chapter6;

import static chapter6.Dish.dishTags;
import static chapter6.Dish.menu;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import chapter6.Dish.Type;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.java.Log;
import util.GsonUtil;

@Log
public class Grouping {

    static GsonUtil gsonUtil = new GsonUtil();

    private static final String useLess = "-------------------------------------------";

    enum CaloricLevel {DIET, NORMAL, FAT};

    public static void main(String[] args) {
        Gson gson = gsonUtil.getGson();
//        basicGroupingByMenu(gson);
//        calorieLevelDishes(gson);
//        misUseDeletedKey(gson);
//        goodClassification(gson);
//        elementsMapping(gson);
        dishesNamesByTagType(gson);
    }

    private static void dishesNamesByTagType(Gson gson) {
        Map<Type, Set<String>> dishNamesByType = menu.stream().collect(groupingBy(Dish::getType,
            flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println("dishTags = " + gson.toJson(dishTags));
        System.out.println("dishNamesByType = " + gson.toJson(dishNamesByType));
        log.info(dishNamesByType.toString());
    }

    private static void elementsMapping(Gson gson) {
        Map<Type, List<String>> dishNamesByType = menu.stream()
            .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("dishNamesByType = " + dishNamesByType);
        System.out.println("dishNamesByType(gson) = " + gson.toJson(dishNamesByType));
    }

    private static void goodClassification(Gson gson) {
        final String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(useLess + methodName + useLess + "\n");
        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
            .collect(
                groupingBy(Dish::getType,
                    filtering(dish -> dish.getCalories() > 500, toList())
                )
            );
        System.out.println("caloricDishesByType = " + caloricDishesByType);
        String methodName1 = new Exception().getStackTrace()[0].getMethodName();
        System.out.println("methodName1 = " + methodName1);
    }

    private static void misUseDeletedKey(Gson gson) {
        final String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(useLess + methodName + useLess);
        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
            .filter(dish -> dish.getCalories() > 500)
            .collect(groupingBy(Dish::getType));
        System.out.println("misUseDeletedKey = " + caloricDishesByType);
    }

    private static void calorieLevelDishes(Gson gson) {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
            groupingBy(dish -> {
                if (dish.getCalories() <= 400) {
                    return CaloricLevel.DIET;
                } else if (dish.getCalories() <= 700) {
                    return CaloricLevel.NORMAL;
                } else {
                    return CaloricLevel.FAT;
                }
            })
        );
        System.out.println("dishesByCaloricLevel = " + gson.toJson(dishesByCaloricLevel));
    }

    private static void basicGroupingByMenu(Gson gson) {
        Map<Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("dishesByType = " + gson.toJson(dishesByType));
    }
}