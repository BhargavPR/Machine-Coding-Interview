import java.util.HashMap;
import java.util.Set;
import java.util.Timer;

public class MultiThreadingEx2 {

    private static final HashMap<Integer, Integer> map = new HashMap<>();

    public static void execute() {
        map.clear();
        for (int index = 1; index <= 100; index++) {
            int finalIndex = index;
            new Thread(() -> updateMapWithSynchronization(1, finalIndex)).start();
        }

        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("--------------------");
                printMap();
            }
        }, 1000L);

    }

    private static void updateMapWithSynchronization(Integer key, Integer value) {
        synchronized (map) {
            updateMap(key, value);
        }
    }

    private static void updateMap(Integer key, Integer value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
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
