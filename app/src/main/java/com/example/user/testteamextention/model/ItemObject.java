package com.example.user.testteamextention.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemObject implements Parcelable {

    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    private ItemObject(Parcel in) {
        sku = in.readString();
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readDouble();
        }
        currency = in.readString();
    }

    public static final Creator<ItemObject> CREATOR = new Creator<ItemObject>() {
        @Override
        public ItemObject createFromParcel(Parcel in) {
            return new ItemObject(in);
        }

        @Override
        public ItemObject[] newArray(int size) {
            return new ItemObject[size];
        }
    };

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sku);

        dest.writeDouble(amount);

        dest.writeString(currency);
    }
}
