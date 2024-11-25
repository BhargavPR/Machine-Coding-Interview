package model;

import java.util.UUID;

public class Document {

    private String id;
    private String name;
    private String content;

    public Document(String name, String content) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
