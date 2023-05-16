package Practice3;

public class App {
    static volatile int buf;
    synchronized static void increment() {
        buf++;
    }
    public static void main(String[] args) throws Exception {
        buf = 0;
        LockList<Integer> lockList = new LockList<>();
        Thread one = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                lockList.add(i);
            }
        });
        Thread two = new Thread(()->{
            for (int i = 1001; i < 2000; i++) {
                lockList.add(i);
            }
        });
        one.start();
        two.start();
        Thread.sleep(3000);
        System.out.println(lockList);
    }
}
