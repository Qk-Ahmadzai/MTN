package com.qkahmadzai.mtn.Database;

/**
 * Created by Qiyamuddin Ahmadzai on 8/3/2017.
 */

public class Packages {

    public static final String TAG = Packages.class.getSimpleName();


    int id;
    String bundleType;
    String volume;
    String validation;
    String price;
    String subMethod;

    public Packages() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBundleType() {
        return bundleType;
    }

    public void setBundleType(String bundleType) {
        this.bundleType = bundleType;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubMethod() {
        return subMethod;
    }

    public void setSubMethod(String subMethod) {
        this.subMethod = subMethod;
    }




}
