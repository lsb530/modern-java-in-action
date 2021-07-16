package chapter5;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

//        Q1(transactions);
//        Q2(transactions);
//        Q3(transactions);
//        Q4(transactions);
//        Q5(transactions);
//        Q6(transactions);
//        Q7(transactions);
        Q8(transactions);
    }

    // 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
    public static void Q1(List<Transaction> transactions) {
        transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(comparing(Transaction::getValue))
            .forEach(System.out::println);
    }

    // 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
    public static void Q2(List<Transaction> transactions) {
        // 방법1
        transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .forEach(System.out::println);
        System.out.println("---------");
        // 방법2
        transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .collect(toSet())
            .forEach(System.out::println);
    }

    // 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
    public static void Q3(List<Transaction> transactions) {
        transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity() == "Cambridge")
            .distinct()
            .sorted(comparing(Trader::getName))
            .forEach(System.out::println);
    }

    // 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
    public static void Q4(List<Transaction> transactions) {
        // 방법1
        String traderStr = transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (n1, n2) -> n1 + n2); // 이름을 알파벳 순으로 정렬
        System.out.println("traderStr = " + traderStr);
        // 방법 2
        String traderStr2 = transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .distinct()
            .sorted()
            .collect(joining());
        System.out.println("traderStr2 = " + traderStr2);
    }

    // 밀라노에 거래자가 있는가?
    public static void Q5(List<Transaction> transactions) {
        boolean milanBased = transactions.stream()
            .anyMatch(transaction -> transaction.getTrader().getCity() == "Milan");
        System.out.println("milanBased = " + milanBased);
    }

    // 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
    public static void Q6(List<Transaction> transactions) {
        transactions.stream()
            .filter(transaction -> transaction.getTrader().getCity() == "Cambridge")
            .map(Transaction::getValue)
            .forEach(System.out::println);
    }

    // 전체 트랜잭션 중 최댓값은 얼마인가?
    public static void Q7(List<Transaction> transactions) {
        // 방법1
        Integer maxValue1 = transactions.stream()
            .map(Transaction::getValue)
            .reduce((t1, t2) -> t1 > t2 ? t1 : t2)
            .get();
        System.out.println("maxValue1 = " + maxValue1);
        // 방법2
        Integer maxValue2 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max)
            .get();
        System.out.println("maxValue2 = " + maxValue2);
        // 방법3
        Integer maxValue3 = transactions.stream()
            .map(Transaction::getValue)
            .max(Integer::compareTo)
            .get();
        System.out.println("maxValue3 = " + maxValue3);
    }

    // 전체 트랜잭션 중 최솟값은 얼마인가?
    public static void Q8(List<Transaction> transactions) {
        // 방법1
        Integer minValue1 = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min)
            .get();
        System.out.println("minValue1 = " + minValue1);
        // 방법2
        int minValue2 = transactions.stream()
            .min(comparing(Transaction::getValue))
            .get()
            .getValue();
        System.out.println("minValue2 = " + minValue2);
    }
}