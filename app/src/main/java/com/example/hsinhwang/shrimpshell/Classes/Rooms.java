package com.example.hsinhwang.shrimpshell.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Rooms implements Serializable, Parcelable {
    private int roomImageId, roomId;
    private String roomName, roomiDetail;

    public Rooms(int roomImageId, int roomId, String roomName, String roomiDetail) {
        this.roomImageId = roomImageId;
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomiDetail = roomiDetail;
    }

    @Override
    public String toString() {
        String text = this.roomName + "\n" + this.roomiDetail;
        return text;
    }

    public int getRoomImageId() {
        return roomImageId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomiDetail() {
        return roomiDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(roomImageId);
        parcel.writeInt(roomId);
        parcel.writeString(roomName);
        parcel.writeString(roomiDetail);
    }
}
