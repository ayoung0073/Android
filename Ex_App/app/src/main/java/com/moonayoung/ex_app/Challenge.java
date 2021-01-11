package com.moonayoung.ex_app;

import android.widget.ImageView;

public class Challenge {
    ImageView imageView;
    int challenge;
    String challenge_title;
    String challenge_content;


    public Challenge(){

    }

    public  Challenge(int challenge, String challenge_title,String challenge_content){
        this.challenge = challenge;
        this.challenge_title = challenge_title;
        this.challenge_content = challenge_content;
    }

    public  Challenge(String challenge_title,String challenge_content){
        this.challenge_title = challenge_title;
        this.challenge_content = challenge_content;
    }

    public int getChallenge(){
        return challenge;
    }

    public String getchallenge_title(){
        return challenge_title;
    }

    public void setChallenge(int num){
        this.challenge = num;
    }

    public void setchallenge_title(String name){
        this.challenge_title = name;
    }

}
