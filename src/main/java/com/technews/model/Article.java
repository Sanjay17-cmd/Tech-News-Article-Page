package com.technews.model;

public class Article {
    private int id;
    private String title;
    private String content;
    private String author;

    public Article(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }
}
