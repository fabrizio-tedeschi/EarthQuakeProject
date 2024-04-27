package entities;

import java.net.URI;

public class Properties {
    private double mag;
    private String place;
    private long time;
    private URI detail;
    private String alert;
    private int tsunami;
    private String title;

    public Properties(){}

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }

    public URI getDetail() {
        return detail;
    }

    public String getAlert() {
        return alert;
    }

    public int getTsunami() {
        return tsunami;
    }

    public String getTitle() {
        return title;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setDetail(URI detail) {
        this.detail = detail;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
