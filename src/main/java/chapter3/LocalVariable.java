package chapter3;

import java.util.function.Supplier;

public class LocalVariable {

    public static void main(String[] args) {
        final int portNumber = 1337;
        Runnable runnable = () -> System.out.println(portNumber);
//        portNumber = 31337;
        runnable.run();
        Transaction dd = new Transaction(); // 인스턴스화
        dd.setT(3);
        int t = dd.getT();
        Supplier<Transaction> c1 = Transaction::new;
    }
}

class Transaction {

    public Transaction() { }

    public Transaction(int t) {
        this.t = t;
    }

    private int t;

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    int getValue() {
        return t;
    }
}