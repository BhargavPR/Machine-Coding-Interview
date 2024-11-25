import constants.Constants;
import service.CacheService;
import service.CacheServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        CacheService<Object> cacheService = new CacheServiceImpl<>();
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.GET -> {
                    System.out.println(cacheService.get(input[1]));
                }
                case Constants.PUT -> {
                    int length = input.length;
                    for (int index = 2; index < length; index = index + 2) {
                        cacheService.put(input[1], input[index], input[index + 1]);
                    }
                }
                case Constants.DELETE -> {
                    cacheService.delete(input[1]);
                }
                case Constants.SEARCH -> {
                    System.out.println(cacheService.search(input[1]));
                }
                case Constants.KEYS -> {
                    System.out.println(cacheService.getKeys());
                }
            }
        }
    }
}