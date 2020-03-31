package daily;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/10 15:13
 */
public class IntTest {
    public static void main(String[] args) {
//        DomainTest domainTest = new DomainTest();
//        domainTest.setB("test");
//        String intnum = domainTest.getA() + "";
//
//        System.out.println(intnum);
//
////        if(domainTest.getA() == null){
////            System.out.println("默认为0");
////        }

        String n = new String();
        n = "a";
        String n1 = new String();
        n1 = "a";
        System.out.println(n == n1);
        System.out.println(n1);
        if(n == n1){
            System.out.println("n == n1");
        }

        if(n1 == ""){
            System.out.println("n1相等");
        }

        if(n == ""){
            System.out.println("n相等");
        }

    }
}
