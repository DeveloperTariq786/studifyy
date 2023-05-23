package com.example.studifyy;

public class ChatGPTModalClass {
    public static String send_by_me="me";
    public static String getSend_by_bot="bot";
    String massage;
    String sendby;

    public ChatGPTModalClass(String massage, String sendby) {
        this.massage = massage;
        this.sendby = sendby;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSendby() {
        return sendby;
    }

    public void setSendby(String sendby) {
        this.sendby = sendby;
    }
}
