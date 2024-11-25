package database;

import model.Document;
import model.Permission;
import model.User;

import java.util.HashMap;
import java.util.Optional;

public class Database {

    private static Database INSTANCE = null;

    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<String, Document> documentHashMap = new HashMap<>();
    private HashMap<String, Permission> permissionHashMap = new HashMap<>();

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    public void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    public Optional<User> getUser(String userId) {
        if (!userHashMap.containsKey(userId)) {
            return Optional.empty();
        }
        return Optional.of(userHashMap.get(userId));
    }

    public void addDocument(Document document) {
        documentHashMap.put(document.getId(), document);
    }

    public Optional<Document> getDocument(String documentId) {
        if (!documentHashMap.containsKey(documentId)) {
            return Optional.empty();
        }
        return Optional.of(documentHashMap.get(documentId));
    }

    public void deleteDocument(String documentId) {
        documentHashMap.remove(documentId);
    }


    public void addPermission(Permission permission) {
        permissionHashMap.put(getPermissionKey(permission), permission);
    }

    public Optional<Permission> getPermission(String documentId, String userId) {
        String key = getPermissionKey(documentId, userId);
        if (!permissionHashMap.containsKey(key)) {
            return Optional.empty();
        }
        return Optional.of(permissionHashMap.get(key));
    }

    private String getPermissionKey(Permission permission) {
        return getPermissionKey(permission.getDocumentId(), permission.getUserId());
    }

    private String getPermissionKey(String documentId, String userId) {
        return documentId + "_" + userId;
    }

}
