package com.study.nodejsappchat.entities;

public class Message {
    private String name, mesage;

    public Message(String name, String mesage) {
        this.name = name;
        this.mesage = mesage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }
}
