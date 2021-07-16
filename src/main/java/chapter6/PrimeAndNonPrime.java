package chapter6;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeAndNonPrime {

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> primes = partitionPrimes(5);
        System.out.println("primes = " + primes);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.range(2, n).boxed()
            .collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate) // 2부터 candidate미만 사이의 자연수를 생성한다.
            .noneMatch(i -> candidate % i == 0); // 스트림의 모든 정수로 candidate를 나눌 수 없으면 참을 반환한다.
    }

    public static boolean isPrimeSquareRoot(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.range(2, candidateRoot)
            .noneMatch(i -> candidate % i == 0);
    }
}