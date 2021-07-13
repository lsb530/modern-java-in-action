package chapter3;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationTest {
    public static void main(String[] args) {
        int[] array = new int[3];
        input(array);
        Arrays.sort(array);
        System.out.println(array[1]);
    }

    private static void input(int[] array) {
        Scanner in = new Scanner(System.in);
        try {
            for (int i = 0; i < 3; i++) {
                array[i] = in.nextInt();
            }
        } catch (InputMismatchException ex) {
            throw new NumberFormatException();
        }
    }
}