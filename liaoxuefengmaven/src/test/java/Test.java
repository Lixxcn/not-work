public class Test {
    public static void main(String[] args) {
        String str = "120.2334234.12";
        str = str.substring(0, str.lastIndexOf("."));
        System.out.println(str);

    }
}
