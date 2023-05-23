package com.example.studifyy;

public class GetUserDetail {
    public String universityattend,programenrolled,number;

    public GetUserDetail() {
    }

    public GetUserDetail(String universityattend, String programenrolled, String number) {
        this.universityattend = universityattend;
        this.programenrolled = programenrolled;
        this.number=number;
    }

    public String getUniversityattend() {
        return universityattend;
    }

    public void setUniversityattend(String universityattend) {
        this.universityattend = universityattend;
    }

    public String getProgramenrolled() {
        return programenrolled;
    }

    public void setProgramenrolled(String programenrolled) {
        this.programenrolled = programenrolled;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
