package com.example.passangerapp;

public class SessionRequestData {

    private String type;
    private String request;

    public SessionRequestData() {
    }

    public String getSessionname() {
        return type;
    }

    public void setSessionname(String sessionname) {
        this.type = sessionname;
    }

    public String getSessdescription() {
        return request;
    }

    public void setSessdescription(String sessdescription) {
        this.request = sessdescription;
    }

}
