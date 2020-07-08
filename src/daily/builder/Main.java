package daily.builder;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/7/8 15:14
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .setName("莉哥")
                .setAge(20)
                .setHeight(162)
                .setWeight(45)
                .build();

    }
}
