package com.lxxdawn.test;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/3/27 14:52
 */
public class switchTest {
    public static void main(String[] args){
        int i = 0;
        switch(i){
            case 1:
                //第一种方法，使用XML
                System.out.println("1");
                break;
            case 2:
                //第二种方法，使用注解
                System.out.println(2);;
                break;
            default:
                System.out.println("结束");
        }
    }
}
