package com.moonayoung.challenge13;

import android.media.TimedText;
import android.widget.TextView;

public class Diary {
    String timeStamp;
    String content;

    public Diary(String timeStamp, String content){
        this.timeStamp=timeStamp;
        this.content=content;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getContent(){
        return content;
    }

    public void setTimeStamp(String timeStamp){
        this.timeStamp=timeStamp;
    }

    public void setContent(String content){
        this.content=content;
    }
}
