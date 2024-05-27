package com.blogapp1.payload;

import java.util.Date;

public class ErrorDetails {

    private Date date;
    private String message;
    private String details;

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails(Date date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }
}
