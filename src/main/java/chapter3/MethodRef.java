package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class MethodRef {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println();
        runnable.run();
    }
}