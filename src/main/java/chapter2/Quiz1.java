package chapter2;

import chapter2.FilteringApples.Apple;
import chapter2.FilteringApples.Color;
import java.util.Arrays;
import java.util.List;

public class Quiz1 {

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public interface AppleFormatter {

        String accept(Apple a);
    }

    static class AppleSimpleFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            return "An apple has " + apple.getColor() + " color and " +
                apple.getWeight() + "g";
        }
    }

    static class AppleFancyFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + apple.getColor() + " apple";
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED)
        );

        prettyPrintApple(inventory, new AppleFancyFormatter());

        prettyPrintApple(inventory, new AppleSimpleFormatter());

    }

}