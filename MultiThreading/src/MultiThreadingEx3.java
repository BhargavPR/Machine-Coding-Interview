import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadingEx3 {

    private static final ConcurrentHashMap<Integer, AtomicInteger> map = new ConcurrentHashMap<>();

    public static void execute() {
        map.clear();
        for (int index = 1; index <= 100; index++) {
            int finalIndex = index;
            new Thread(() -> updateMap(1, finalIndex)).start();
        }

        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("--------------------");
                printMap();
            }
        }, 1000L);

    }

    private static void updateMap(Integer key, Integer value) {
        map.computeIfAbsent(key, k -> new AtomicInteger()).addAndGet(value);
        printMap();
    }

    private static void printMap() {
        System.out.println("printMap " + Thread.currentThread().getName());
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println(key + " " + map.get(key));
        }
    }

}
