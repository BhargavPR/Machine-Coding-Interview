package utils;

import java.util.Random;

public class DiceUtils {

    public static int roll() {
        return Math.abs(new Random().nextInt()) % 6 + 1;
    }

}
