package chapter5;

import static chapter5.Dish.Type.*;

import java.util.Arrays;
import java.util.List;

public class Dish {

  private final String name;
  private final boolean vegetarian;
  private final int calories;
  private final Type type;

  public Dish(String name, boolean vegetarian, int calories, Type type) {
    this.name = name;
    this.vegetarian = vegetarian;
    this.calories = calories;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public boolean isVegetarian() {
    return vegetarian;
  }

  public int getCalories() {
    return calories;
  }

  public Type getType() {
    return type;
  }

  public enum Type {
    MEAT,
    FISH,
    OTHER
  }

  @Override
  public String toString() {
    return name;
  }

  public static final List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, MEAT),
      new Dish("beef", false, 700, MEAT),
      new Dish("chicken", false, 400, MEAT),
      new Dish("french fries", true, 530, OTHER),
      new Dish("pizza", true, 550, OTHER),
      new Dish("prawns", false, 400, FISH),
      new Dish("rice", true, 350, OTHER),
      new Dish("season fruit", true, 120, OTHER),
      new Dish("salmon", false, 450, FISH)
  );

}