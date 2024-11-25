import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadingEx4 {

    private static final ConcurrentHashMap<Integer, Boolean> seatMap = new ConcurrentHashMap<>();

    public static void execute() {
        seatMap.clear();
        seatMap.put(1, false);
        for (int index = 1; index <= 10; index++) {
            new Thread(() -> bookSeat(1)).start();
        }
    }

    private static void bookSeat(Integer seatNo) {
        seatMap.computeIfPresent(seatNo, (key, isBooked) -> {
            if (!isBooked) {
                System.out.println("Book seat " + Thread.currentThread().getName() + " successfully ");
                return true;
            }
            System.out.println("Book seat " + Thread.currentThread().getName() + " failed ");
            return true;
        });
    }
}
