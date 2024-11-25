package service;

import database.Database;
import model.AccessLevel;
import model.Document;
import model.Permission;
import model.User;

import java.util.Optional;

public class DocumentServiceImpl implements DocumentService {

    private final Database database;

    public DocumentServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Document createDocument(String userId, String name, String content) {
        Optional<User> userOptional = database.getUser(userId);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return null;
        }

        Document document = new Document(name, content);
        database.addDocument(document);
        database.addPermission(new Permission(document.getId(), userId, AccessLevel.OWNER));
        return document;
    }

    @Override
    public void updateDocument(String userId, String documentId, String content) {
        Optional<User> userOptional = database.getUser(userId);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        Optional<Document> documentOptional = database.getDocument(documentId);
        if (documentOptional.isEmpty()) {
            System.out.println("Document not found");
            return;
        }

        Optional<Permission> permissionOptional = database.getPermission(documentId, userId);
        if (permissionOptional.isEmpty() || permissionOptional.get().getAccessLevel().equals(AccessLevel.READER)) {
            System.out.println("You don't have access for update");
            return;
        }

        Document document = documentOptional.get();
        document.setContent(content);
    }

    @Override
    public void readDocument(String userId, String documentId) {
        Optional<User> userOptional = database.getUser(userId);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        Optional<Document> documentOptional = database.getDocument(documentId);
        if (documentOptional.isEmpty()) {
            System.out.println("Document not found");
            return;
        }

        Optional<Permission> permissionOptional = database.getPermission(documentId, userId);
        if (permissionOptional.isEmpty()) {
            System.out.println("User " + userOptional.get().getName() + " don't have read access for " + documentOptional.get().getName());
            return;
        }

        System.out.println("Document content is:- " + documentOptional.get().getContent());
    }

    @Override
    public void deleteDocument(String userId, String documentId) {
        Optional<User> userOptional = database.getUser(userId);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        Optional<Document> documentOptional = database.getDocument(documentId);
        if (documentOptional.isEmpty()) {
            System.out.println("Document not found");
            return;
        }

        Optional<Permission> permissionOptional = database.getPermission(documentId, userId);
        if (permissionOptional.isEmpty() || !permissionOptional.get().getAccessLevel().equals(AccessLevel.OWNER)) {
            System.out.println("User " + userOptional.get().getName() + " don't have delete access for " + documentOptional.get().getName());
            return;
        }

        System.out.println("Document " + documentOptional.get().getName() + " is deleted by user " + userOptional.get().getName());
        database.deleteDocument(documentId);
    }

    @Override
    public void grantPermission(String userId, String documentId, AccessLevel accessLevel) {
        Optional<User> userOptional = database.getUser(userId);
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        Optional<Document> documentOptional = database.getDocument(documentId);
        if (documentOptional.isEmpty()) {
            System.out.println("Document not found");
            return;
        }

        database.addPermission(new Permission(documentId, userId, accessLevel));

    }
}
