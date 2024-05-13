package com.lurodev.adminsgestioninspecciones.models;

public class Attachment {
    private String filename;
    private String content;

    // GETTERS AND SETTERS
    public Attachment() {
    }
    public Attachment(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    // GETTERS AND SETTERS
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
