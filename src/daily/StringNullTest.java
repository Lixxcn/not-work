package daily;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/5/18 9:46
 */
public class StringNullTest {
    public static void main(String[] args) {

        Map object = new HashMap<String,Object>();
        object.put("interval","null");
        Object  blockDemo=object.get("interval");
        long blockInterval =Long.parseLong(String.valueOf(((blockDemo==null)||"null".equals(blockDemo)||"".equals(blockDemo))?"0":blockDemo));
        System.out.println(blockInterval);


    }
}
