package chapter3;

public interface 아아먹고싶다 {
    abstract int ttt(int a);
    default int ttt2(int a) {
        return a+3;
    }
}