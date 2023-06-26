package com.example.Product;

public class SmsRequest {
    private String to;
    private String body;
    public SmsRequest() {
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
