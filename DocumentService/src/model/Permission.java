package model;

public class Permission {

    private String documentId;
    private String userId;
    private AccessLevel accessLevel;

    public Permission(String documentId, String userId, AccessLevel accessLevel) {
        this.documentId = documentId;
        this.userId = userId;
        this.accessLevel = accessLevel;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}
