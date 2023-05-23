package com.example.studifyy.BTechFirstSem;

public class LoadMaterialModal {
    String bookcover;
    String bookname;
    String url;

    public LoadMaterialModal() {
    }

    public LoadMaterialModal(String bookcover, String bookname, String url) {
        this.bookcover = bookcover;
        this.bookname = bookname;
        this.url = url;
    }

    public String getBookcover() {
        return bookcover;
    }

    public void setBookcover(String bookcover) {
        this.bookcover = bookcover;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
