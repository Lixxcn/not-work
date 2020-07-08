package daily;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/4/28 9:52
 */
public class NoMThread {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("start task " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("end task " + i);
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间  24093ms
    }
}
