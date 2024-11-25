import database.Database;
import model.AccessLevel;
import model.Document;
import model.User;
import service.DocumentService;
import service.DocumentServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        Database database = Database.getInstance();
        UserService userService = new UserServiceImpl(database);
        DocumentService documentService = new DocumentServiceImpl(database);

        User userA = userService.addUser("UserA");
        User userB = userService.addUser("UserB");
        User userC = userService.addUser("UserC");

        Document doc1 = documentService.createDocument(userA.getId(), "Doc1", "Doc1 content");
        Document doc2 = documentService.createDocument(userB.getId(), "Doc2", "Doc2 content");

        documentService.grantPermission(userB.getId(), doc1.getId(), AccessLevel.READER);

        documentService.readDocument(userB.getId(), doc1.getId());
        documentService.readDocument(userC.getId(), doc1.getId());

        documentService.deleteDocument(userB.getId(), doc1.getId());
        documentService.deleteDocument(userA.getId(), doc1.getId());

    }
}