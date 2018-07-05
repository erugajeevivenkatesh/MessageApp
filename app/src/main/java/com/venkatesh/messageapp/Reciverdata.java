package com.venkatesh.messageapp;

public class Reciverdata {
    String Name,Body,Date;
    public Reciverdata(String Name,String Body,String Date)
    {
        this.Name=Name;
        this.Body=Body;
        this.Date=Date;

    }

    public String getBody() {
        return Body;
    }

    public String getDate() {
        return Date;
    }

    public String getName() {
        return Name;
    }

    public void setBody(String body) {
        Body = body;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setName(String name) {
        Name = name;
    }
}
