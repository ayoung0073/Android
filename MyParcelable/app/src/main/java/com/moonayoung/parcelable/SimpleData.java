package com.moonayoung.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    int code;
    String msg;

    public SimpleData(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public SimpleData(Parcel src){
        code=src.readInt();
        msg=src.readString();
    }

    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){
        public SimpleData createFromParcel(Parcel in){
            return new SimpleData(in);
        }
        public SimpleData[] newArray(int size){
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(code);
        parcel.writeString(msg);
    }
}
