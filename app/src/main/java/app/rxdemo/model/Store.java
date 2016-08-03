package app.rxdemo.model;

/**
 * Created by atempa on 26/07/16.
 */
public class Store implements Comparable<Store> {
    private int store_id;
    private String name;
    private String address;
    private String city;
    private String postal_code;
    private String phone;
    private float latitude;
    private float longitude;
    private String monday_open;
    private String monday_close;
    private String tuesday_open;
    private String tuesday_close;
    private String wednesday_open;
    private String wednesday_close;
    private String thursday_open;
    private String thursday_close;
    private String friday_open;
    private String friday_close;
    private String saturday_open;
    private String saturday_close;
    private String sunday_open;
    private String sunday_close;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getMonday_open() {
        return monday_open;
    }

    public void setMonday_open(String monday_open) {
        this.monday_open = monday_open;
    }

    public String getMonday_close() {
        return monday_close;
    }

    public void setMonday_close(String monday_close) {
        this.monday_close = monday_close;
    }

    public String getTuesday_open() {
        return tuesday_open;
    }

    public void setTuesday_open(String tuesday_open) {
        this.tuesday_open = tuesday_open;
    }

    public String getTuesday_close() {
        return tuesday_close;
    }

    public void setTuesday_close(String tuesday_close) {
        this.tuesday_close = tuesday_close;
    }

    public String getWednesday_open() {
        return wednesday_open;
    }

    public void setWednesday_open(String wednesday_open) {
        this.wednesday_open = wednesday_open;
    }

    public String getWednesday_close() {
        return wednesday_close;
    }

    public void setWednesday_close(String wednesday_close) {
        this.wednesday_close = wednesday_close;
    }

    public String getThursday_open() {
        return thursday_open;
    }

    public void setThursday_open(String thursday_open) {
        this.thursday_open = thursday_open;
    }

    public String getThursday_close() {
        return thursday_close;
    }

    public void setThursday_close(String thursday_close) {
        this.thursday_close = thursday_close;
    }

    public String getFriday_open() {
        return friday_open;
    }

    public void setFriday_open(String friday_open) {
        this.friday_open = friday_open;
    }

    public String getFriday_close() {
        return friday_close;
    }

    public void setFriday_close(String friday_close) {
        this.friday_close = friday_close;
    }

    public String getSaturday_open() {
        return saturday_open;
    }

    public void setSaturday_open(String saturday_open) {
        this.saturday_open = saturday_open;
    }

    public String getSaturday_close() {
        return saturday_close;
    }

    public void setSaturday_close(String saturday_close) {
        this.saturday_close = saturday_close;
    }

    public String getSunday_open() {
        return sunday_open;
    }

    public void setSunday_open(String sunday_open) {
        this.sunday_open = sunday_open;
    }

    public String getSunday_close() {
        return sunday_close;
    }

    public void setSunday_close(String sunday_close) {
        this.sunday_close = sunday_close;
    }

    @Override
    public int compareTo(Store store) {
        return this.getName().compareTo(store.getName());
    }
}