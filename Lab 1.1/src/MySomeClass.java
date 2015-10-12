/**
 * Created by Yegor2 on 10/8/2015.
 */
public class MySomeClass {

    @MyAnnotation(a=3,  b=2)
    int SumElements1(int a, int b) {
        System.out.println("make sum, a: " + a + " b: " + b);
        return a + b;
    }

    @MyAnnotation(a=2,  b=5)
    int SumElements2(int a, int b) {
        System.out.println("make sum, a: " + a + " b: " + b);
        return a + b;
    }

    @MyAnnotation(a=1,  b=6)
    int SumElements3(int a, int b) {
        System.out.println("make sum, a: " + a + " b: " + b);
        return a + b;
    }

    @MyAnnotation(a=5,  b=9)
    int SumElements4(int a, int b) {
        System.out.println("make sum, a: " + a + " b: " + b);
        return a + b;
    }
}
