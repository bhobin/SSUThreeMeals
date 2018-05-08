package com.example.user.androidproject;

import android.os.Parcel;
import android.os.Parcelable;

public class StoreData implements Parcelable {

    private int id;
    private String category;
    private String title;
    private String time;
    private String information;
    private String number;
    private int like_count;
    private boolean like=false;
    private String location;
    private int report;
    private String image;

    protected StoreData(Parcel in) {
        id = in.readInt();
        category = in.readString();
        title = in.readString();
        time = in.readString();
        information = in.readString();
        number = in.readString();
        like_count = in.readInt();
        like = in.readByte() != 0;
        location = in.readString();
        report = in.readInt();
        image = in.readString();
    }

    public static final Creator<StoreData> CREATOR = new Creator<StoreData>() {
        @Override
        public StoreData createFromParcel(Parcel in) {
            return new StoreData(in);
        }

        @Override
        public StoreData[] newArray(int size) {
            return new StoreData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public StoreData(int id, String category, String title, String time, String information, String number, int like_count, boolean like, String location, int report, String image) {

        this.id = id;
        this.category = category;
        this.title = title;
        this.time = time;
        this.information = information;
        this.number = number;
        this.like_count = like_count;
        this.like = like;
        this.location = location;
        this.report = report;
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(category);
        parcel.writeString(title);
        parcel.writeString(time);
        parcel.writeString(information);
        parcel.writeString(number);
        parcel.writeInt(like_count);
        parcel.writeByte((byte) (like ? 1 : 0));
        parcel.writeString(location);
        parcel.writeInt(report);
        parcel.writeString(image);
    }
}