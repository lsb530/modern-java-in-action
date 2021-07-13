package chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class ManyTypePredicate {

    public static void main(String[] args) {
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000));
        Predicate<Integer> oddNumbers = (Integer i) -> i% 2 != 0;
        System.out.println(oddNumbers.test(1000));
        List<String> listOfString = new ArrayList<>(); // deprecated( 자바 3 )
    }
}