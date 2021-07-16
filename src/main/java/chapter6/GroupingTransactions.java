package chapter6;

import static chapter6.GroupingTransactions.UserType.*;
import static java.util.stream.Collectors.groupingBy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.GsonUtil;

public class GroupingTransactions {

  public enum UserType {
    ADMIN, GUEST, COMPANY, LOCAL, GOOGLE, KAKAO, NAVER, DAUM
  }

  public static class LoginData {
    private final UserType userType;
    private final Long id;

    public LoginData(UserType userType, Long id) {
      this.userType = userType;
      this.id = id;
    }
  }

  private static final GsonUtil gsonUtil = new GsonUtil();

  public static List<Transaction> transactions = Arrays.asList(
      new Transaction(Currency.EUR, 1500.0),
      new Transaction(Currency.USD, 2300.0),
      new Transaction(Currency.GBP, 9900.0),
      new Transaction(Currency.EUR, 1100.0),
      new Transaction(Currency.JPY, 7800.0),
      new Transaction(Currency.CHF, 6700.0),
      new Transaction(Currency.EUR, 5600.0),
      new Transaction(Currency.USD, 4500.0),
      new Transaction(Currency.CHF, 3400.0),
      new Transaction(Currency.GBP, 3200.0),
      new Transaction(Currency.USD, 4600.0),
      new Transaction(Currency.JPY, 5700.0),
      new Transaction(Currency.EUR, 6800.0)
  );
//  Vue / React
//  List<User> dd = new ArrayList<>();
//  dd.tt();
  // 1. 비가공 데이터
  // 2. SQL 데이터 가

  // 3. 비가공 데이터를 들고 자바함수 map<> ;

  public static void main(String... args) {
    List<LoginData> dataList = Arrays.asList(
        new LoginData(ADMIN, 0L),
        new LoginData(COMPANY, 1L),
        new LoginData(GOOGLE, 2L),
        new LoginData(GUEST, 3L),
        new LoginData(KAKAO, 5L),
        new LoginData(LOCAL, 8L)
    );
    Gson gson = gsonUtil.getGson();
    groupImperatively(gson);
    groupFunctionally(gson);
  }

  private static void groupImperatively(Gson gson) {
    Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
    for (Transaction transaction : transactions) {
      Currency currency = transaction.getCurrency();
      List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
      if (transactionsForCurrency == null) {
        transactionsForCurrency = new ArrayList<>();
        transactionsByCurrencies.put(currency, transactionsForCurrency);
      }
      transactionsForCurrency.add(transaction);
    }

    System.out.println("Imperatively transactionsByCurrencies = " + transactionsByCurrencies);
    System.out.println(gson.toJson(transactionsByCurrencies));
  }

  private static void groupFunctionally(Gson gson) {
    Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
        .collect(groupingBy(Transaction::getCurrency));
    System.out.println("Functionally transactionsByCurrencies = " + transactionsByCurrencies);
    System.out.println(gson.toJson(transactionsByCurrencies));
  }

  public static class Transaction {
    private final Currency currency;

    private final double value;

    public Transaction(Currency currency, double value) {
      this.currency = currency;
      this.value = value;
    }

    public Currency getCurrency() {
      return currency;
    }

    public double getValue() {
      return value;
    }

    @Override
    public String toString() {
      return currency + " " + value;
    }

  }
  public enum Currency {
    EUR, USD, JPY, GBP, CHF;

  }
}