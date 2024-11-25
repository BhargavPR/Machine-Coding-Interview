import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TokenBucketRateLimiter {

    private static final List<String> tokens = new ArrayList<>();

    public static void execute() {
        generateTokens();
        for (int index = 0; index < 20; index++) {
            new Thread(TokenBucketRateLimiter::assignToken).start();
        }
    }

    private static void assignToken() {
        String token = getToken();
        System.out.println("Assign Token " + Thread.currentThread().getName() + " " + token);
    }

    private static String getToken() {
        synchronized (tokens) {
            if (tokens.isEmpty()) {
                return null;
            }
            String token = tokens.get(tokens.size() - 1);
            tokens.remove(token);
            return token;
        }
    }

    private static void generateTokens() {
        for (int index = 0; index < 10; index++) {
            tokens.add(UUID.randomUUID().toString());
        }
    }


}
