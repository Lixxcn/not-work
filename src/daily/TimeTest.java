package daily;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/6/12 11:14
 */
public class TimeTest {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long l = 1588521600000L;//1587830400000
        Date date = new Date(l);
        System.out.println(date);
        System.out.println(simpleDateFormat.format(date));
    }
}
