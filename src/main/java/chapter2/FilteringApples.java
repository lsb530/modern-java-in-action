package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FilteringApples {

  public static void main(String... args) {
    List<Apple> inventory = Arrays.asList(
        new Apple(80, Color.GREEN),
        new Apple(155, Color.GREEN),
        new Apple(120, Color.RED));

    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
    System.out.println(greenApples);

    // [Apple{color=RED, weight=120}]
    List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    System.out.println(redApples);

    // [Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
    System.out.println(greenApples2);

    // [Apple{color=GREEN, weight=155}]
    List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
    System.out.println(heavyApples);

    // []
    List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
    System.out.println(redAndHeavyApples);

    // [Apple{color=RED, weight=120}]
    List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
      @Override
      public boolean test(Apple a) {
        return a.getColor() == Color.RED;
      }
    });
    System.out.println(redApples2);

    // 정렬
//    inventory.sort(new Comparator<Apple>() {
//      @Override
//      public int compare(Apple o1, Apple o2) {
//        return o1.getWeight().compareTo(o2.getWeight());
//      }
//    });
    // 정렬 람다표현식
//    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

    // 쓰레드
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello world");
      }
    });
    t1.start();

    // 쓰레드 람다표현식
    Thread t2 = new Thread(() -> System.out.println("Hello World"));
    t2.start();

    // 쓰레드의 이름을 반환하는 Callable
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<String> threadName1 = executorService.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        return Thread.currentThread().getName();
      }
    });
    System.out.println("threadName1 = " + threadName1);

    // Callable 람다표현식
    Future<String> threadName2 = executorService.submit(
        () -> Thread.currentThread().getName()
    );
    System.out.println("threadName2 = " + threadName2);
  }

  /**
  * 녹색 사과 필터링
   * @param inventory 사과(Apple)가 담겨있는 List입니다
   * @return static으로 Apple이 담겨있는 List를 반환합니다
  */
  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor() == Color.GREEN) {
        result.add(apple);
      }
    }
    return result;
  }

  // 색을 파라미터화
  public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor() == color) {
        result.add(apple);
      }
    }
    return result;
  }

  // 무게 파라미터화
  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  // 동작 파라미터화(추상적 조건으로 필터링)
  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  enum Color {
    RED,
    GREEN
  }

  public static class Apple {

    private int weight = 0;
    private Color color;

    public Apple(int weight, Color color) {
      this.weight = weight;
      this.color = color;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public Color getColor() {
      return color;
    }

    public void setColor(Color color) {
      this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
      return String.format("Apple{color=%s, weight=%d}", color, weight);
    }

  }

  interface ApplePredicate {

    boolean test(Apple a);

  }

  static class AppleWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }

  }

  static class AppleColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.GREEN;
    }

  }

  static class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }

  }

}