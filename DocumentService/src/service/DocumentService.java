package service;

import model.AccessLevel;
import model.Document;

public interface DocumentService {

    Document createDocument(String userId, String name, String content);

    void updateDocument(String userId, String documentId, String content);

    void readDocument(String userId, String documentId);

    void deleteDocument(String userId, String documentId);

    void grantPermission(String userId, String documentId, AccessLevel accessLevel);


}
