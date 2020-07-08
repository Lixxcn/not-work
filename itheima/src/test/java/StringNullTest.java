import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/5/18 9:46
 */
public class StringNullTest {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();

//        Map object = new HashMap<String,Object>();
//        Map object = new HashMap();
//        object.put("interval","null");
        object.put("interval","");
//        object.put("interval","");
//        String  blockDemo= (String) object.get("interval");
        Object  blockDemo= object.get("interval");
        String s = null;
//        if(((blockDemo==null)||"null".equals(blockDemo)||"".equals(blockDemo))){
        if(((blockDemo==null)||blockDemo.equals("null")||"".equals(blockDemo.toString().trim()))){
            s = String.valueOf("0");
        }else {
            s = String.valueOf(blockDemo);
        }
        long l = Long.parseLong(s);
        System.out.println(l);

//        long blockInterval =Long.parseLong(String.valueOf(((blockDemo==null)||"null".equals(blockDemo)||"".equals(blockDemo))?"0":blockDemo));
//        System.out.println(blockInterval);
    }
}
