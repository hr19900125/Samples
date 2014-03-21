package com.ryan.java.basic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    
    //双色球
    public static void main(String[] args){
        
        long time = 1392279723000L;
        Date data = new Date(time);
        System.out.println(data.toLocaleString());
    }
}
