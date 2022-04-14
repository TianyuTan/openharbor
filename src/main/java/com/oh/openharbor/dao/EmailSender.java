package com.oh.openharbor.dao;

public interface EmailSender {
    void send(String to, String email);
}
