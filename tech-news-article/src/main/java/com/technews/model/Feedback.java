package com.technews.model;

public class Feedback {
    private int id;
    private String name, email, subject, message;
    private int rating;

    public Feedback(int id, String name, String email, String subject, String message, int rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }
    public String getMessage() { return message; }
    public int getRating() { return rating; }
}
