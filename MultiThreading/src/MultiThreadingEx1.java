public class MultiThreadingEx1 {

    public static int count = 0;

    public static void execute() {
        new Thread(MultiThreadingEx1::updateCount).start();
        new Thread(MultiThreadingEx1::updateCount).start();
        new Thread(MultiThreadingEx1::updateCount).start();
        System.out.println("Count is " + count);
    }

    private static void updateCount() {
        synchronized (MultiThreadingEx1.class) {
            count++;
            System.out.println("update count " + count);
        }
    }

}
