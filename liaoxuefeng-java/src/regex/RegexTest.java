package regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
//        System.out.println(".匹配任意单一字符");
//        String re1 = "a..c";
//        System.out.println("abc".matches(re1));
//        System.out.println("a1c".matches(re1));
//        System.out.println("a*c".matches(re1));
//        System.out.println("ac".matches(re1));
//        System.out.println("abbc".matches(re1));
//        System.out.println("\\d匹配任意单一数字");
//        String re2 = "a\\da";
//        System.out.println("a3a".matches(re2));
//        System.out.println("\\d与\\D比较");
//        String re1 = "java\\d"; // 对应的正则是java\d
//        System.out.println("java9".matches(re1));
//        System.out.println("java10".matches(re1));
//        System.out.println("javac".matches(re1));
//
//        String re2 = "java\\D";
//        System.out.println("javax".matches(re2));
//        System.out.println("java#".matches(re2));
//        System.out.println("java5".matches(re2));

//        String re3 = "_\\d";
//        System.out.println("_1".matches(re3));
        int test = 0;
        switch (test){
            case 0:
                //常用正基础则
                test0();
                break;
            case 1:
                //复杂匹配
                test1();
                break;
            case 2:
                test2();
                break;
            case 3:
                //分组匹配
                test3();
                break;
            case 4:
                //搜索替换
                test4();
                break;
            default:
                System.out.println("执行完毕");
        }

    }
    static void test0(){
        //一、"."匹配任意单个字符
        //1.第一种方法，使用String的matches方法
        String regex0 = "dawn.";
        System.out.println(". 匹配 " + "dawnx".matches(regex0));
        //2.第二种方法，使用Pattern和Matchef类
        Pattern pattern0 = Pattern.compile("dawn.");
        Matcher matcher0 = pattern0.matcher("dawnx");
        System.out.println(". 匹配 " + matcher0.matches());
        //二、"\d"匹配任意单个数字
        regex0 = "dawn\\d";
        System.out.println("\\d 匹配 " + "dawn2".matches(regex0));
        //三、"\w"匹配一个字母、数字、下划线。word的意思。
        regex0 = "dawn\\w";
        System.out.println("\\w 匹配 " + "dawn_".matches(regex0));
        //四、"\s"匹配一个空格字符，包含空格、tab(\t)
        regex0 = "dawn\\s";
        System.out.println("\\s 匹配 " + "dawn ".matches(regex0));
        //五、\D \W \S 与小写的相反
        regex0 = "dawn\\D";
        System.out.println("\\D 匹配 " + "dawnd".matches(regex0));
        regex0 = "dawn\\W";
        System.out.println("\\W 匹配 " + "dawn-".matches(regex0));
        regex0 = "dawn\\S";
        System.out.println("\\S 匹配 " + "dawnk".matches(regex0));
        //六、修饰符*可以匹配任意个字符，包括0个字符。
        //修饰符+可以匹配至少一个字符。
        //修饰符?可以匹配0个或一个字符。
        //修饰符{n}精确指定n个字符
        //修饰符{n,m}指定匹配n~m个字符
        regex0 = "dawn.*";
        System.out.println(".* 匹配 " + "dawndawn".matches(regex0));
        regex0 = "dawn\\d+";
        System.out.println("\\d+ 匹配 " + "dawn123".matches(regex0));
        regex0 = "dawn\\d?";
        System.out.println("\\d? 匹配 " + "dawn".matches(regex0));
        regex0 = "dawn.{3}";
        System.out.println("\\.{3} 匹配 " + "dawndaw".matches(regex0));
        regex0 = "dawn.{4,6}";
        System.out.println("\\.{4,6} 匹配 " + "dawndawn".matches(regex0));

    }
    static void test4(){
        String s = "the quick brown fox jumps over the lazy dog.";
        Pattern p = Pattern.compile("\\wo\\w");
        Matcher m = p.matcher(s);
        while (m.find()) {
            String sub = s.substring(m.start(), m.end());
            System.out.println(sub);
        }
        s = "The     quick\t\t brown   fox  jumps   over the  lazy dog.";
        String r = s.replaceAll("\\s+", " ");
        System.out.println(r); // "The quick brown fox jumps over the lazy dog."
        s = "the quick brown fox jumps over the lazy dog.";
        r = s.replaceAll("\\s([a-z]{4})\\s", " <b>$1</b> ");
        System.out.println(r);
    }
    static void test3(){
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        pattern.matcher("010-12345678").matches(); // true
        pattern.matcher("021-123456").matches(); // true
        pattern.matcher("022#1234567").matches(); // false
        // 获得Matcher对象:
        Matcher matcher = pattern.matcher("010-12345678");
        if (matcher.matches()) {
            String whole = matcher.group(0); // "010-12345678", 0表示匹配的整个字符串
            String area = matcher.group(1); // "010", 1表示匹配的第1个子串
            String tel = matcher.group(2); // "12345678", 2表示匹配的第2个子串
            System.out.println(area);
            System.out.println(tel);
        }
        matcher = pattern.matcher("022#1234567");
        if(matcher.matches()) {
            String whole = matcher.group(0);
            System.out.println(whole);
        }
    }
    static void test2(){
        String re1 = "\\d{3,4}\\-\\d{6,8}";
        String re = "(\\d{3,4})\\-(\\d{6,8})";
        System.out.println("111-111111".matches(re1));
    }
    static void test1(){
        String re = "\\d{3,4}\\-\\d{7,8}";
        for (String s : List.of("010-12345678", "020-9999999", "0755-7654321")) {
            if (!s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        for (String s : List.of("010 12345678", "A20-9999999", "0755-7654.321")) {
            if (s.matches(re)) {
                System.out.println("测试失败: " + s);
                return;
            }
        }
        System.out.println("测试成功!");
    }
}
