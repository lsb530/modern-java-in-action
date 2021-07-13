package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionTest {

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public static void main(String[] args) {
        int k = 100;
        Integer a = 100;
//        int t = null; // 값 ( 기본형 - Primitive)
        Integer z = null; // 참조형, 객체( Wrapper )

        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length()
        );
        System.out.println(l);
        List<String> list = map(Arrays.asList("ttt", "aaaaa", "zzzz"), (String s) -> s + 3);
        System.out.println("list = " + list);
        List<Integer> integers = map(Arrays.asList("3", "100", "333333"),
            s -> Integer.parseInt(s)+3);
        System.out.println("integers = " + integers);
    }
}