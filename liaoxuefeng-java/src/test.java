public class test {
    public static void main(String[] args) {
        StaticTest.num = 3;
        StaticTest staticTest = new StaticTest();

        System.out.println(StaticTest.num);
    }
}

class StaticTest{
    public static int num;
    public int count;
}