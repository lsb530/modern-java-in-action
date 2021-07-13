package chapter3;

public class LambdaTest {

    public static void process(Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        process(() -> System.out.println("This is awesome!!"));
        String t = "kk";
        Integer k = 100;
    }

    static class FunctionalClass<T> implements Functional<T> {
        @Override
        public Integer test(String data) {
            return 3;
        }
    }

}