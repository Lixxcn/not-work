package daily;

/**
 * @author Li-Xiaoxu
 * @version 1.0
 * @date 2020/7/10 14:14
 */
public class DoubleTest {
    public static void main(String[] args) {
        System.out.println(Double.isNaN(Double.NaN));
        double d1 = 0.0;
        double d2 = 0;
        NotInit notInit = new NotInit();
        System.out.println(d1 == d2);
        System.out.println(notInit.getD3() == 0);
        System.out.println(Double.NaN == 1/0);
    }
}

class NotInit{
    double d3;

    public double getD3() {
        return d3;
    }

    public void setD3(double d3) {
        this.d3 = d3;
    }
}
