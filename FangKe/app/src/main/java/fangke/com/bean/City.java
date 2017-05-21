package fangke.com.bean;

/**
 * Created by ChongZi007 on 2017/5/21.
 */

public class City {
    private String cityName;
    private int num;
    private int level;
    private double longitude;//经度
    private double latitude;//纬度

    public City() {
    }

    public City(String cityName, int num, int level) {
        this.cityName = cityName;
        this.num = num;
        this.level = level;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
