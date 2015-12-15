package com.ryan.java.gson;

import java.util.Date;

public class DateTime {

    Date date;
    
    public DateTime(Date d){
        date = d;
    }
    
    public DateTime(String str) {
        date = new Date(str);
    }
    
    @Override
    public String toString() {
        return date.toGMTString();
    }
}
