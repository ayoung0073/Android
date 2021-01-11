package com.moonayoung.challenge13;

public class Person {

    int num;
    String name;
    String mobile;

    public Person(int num, String name, String mobile){
        this.num=num;
        this.name=name;
        this.mobile=mobile;
    }

    public int getNum(){ return num;}

    public String getName(){ return name;}

    public String getMobile(){ return mobile;}

    public void setNum(int num){this.num=num;}

    public void setName(String name){this.name=name;}

    public void setMobile(String mobile){this.mobile=mobile;}



}
