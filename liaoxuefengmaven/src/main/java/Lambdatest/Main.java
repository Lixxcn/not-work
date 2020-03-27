package Lambdatest;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arrays = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        switch(2){
            case 0:
                Arrays.sort(arrays, (s1,s2) -> {
                    return s1.compareTo(s2);
                });
                System.out.println(String.join(",", arrays));
                break;
            case 1:
                Arrays.sort(arrays, (s1,s2) -> s1.compareTo(s2));
                System.out.println(String.join(",", arrays));
                break;
            case 2:
                Pair<String> p1 = new Pair<>("Hello", "world");
                Pair<Integer> p2 = new Pair<>(123, 456);
                Class c1 = p1.getClass();
                Class c2 = p2.getClass();
                System.out.println(c1==c2); // true
                System.out.println(c1); //
                System.out.println(c2); //
                System.out.println(Pair.class);
                System.out.println(c1==Pair.class); // true

        }
    }
}
class Pair<T> {
    private T first;
    private T last;
    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
}