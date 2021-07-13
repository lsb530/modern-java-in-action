package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("하하하","히히히","","냠냠냠","");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println("nonEmpty = " + nonEmpty);
    }

}

class 뜨아는싫다 {

    public 뜨아는싫다(int a) {
        this.a = a;
    }

    int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}