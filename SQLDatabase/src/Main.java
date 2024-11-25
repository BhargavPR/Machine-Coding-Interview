import database.Database;
import model.column.DataType;
import model.table.Table;
import service.DatabaseService;
import service.DatabaseServiceImpl;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        DatabaseService databaseService = new DatabaseServiceImpl(database);

        Table table = databaseService.createTable("users");
        databaseService.addColumn(table.getName(), "id", DataType.INTEGER);
        databaseService.addColumn(table.getName(), "name", DataType.STRING);
        databaseService.addColumn(table.getName(), "mobile", DataType.STRING);


        databaseService.addRecord(table.getName(), getRandomUserRecord());
        databaseService.addRecord(table.getName(), getRandomUserRecord());
        databaseService.addRecord(table.getName(), getRandomUserRecord());
        databaseService.addRecord(table.getName(), getRandomUserRecord());
        databaseService.addRecord(table.getName(), getRandomUserRecord());

        databaseService.printRecords(table.getName());

        databaseService.filterRecords(table.getName(), "id", 1);


        databaseService.filterRecords(table.getName(), "id", 3);


    }

    private static HashMap<String, Object> getRandomUserRecord() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("id", Math.abs(new Random().nextInt()) % 3);
        userMap.put("name", getRandomString(5));
        userMap.put("mobile", getRandomString(10));
        return userMap;
    }

    private static String getRandomString(int length) {
        String TEXT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * TEXT.length());
            salt.append(TEXT.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}