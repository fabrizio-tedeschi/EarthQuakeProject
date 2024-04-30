package ftvp.earthquakeapp.persistence.models;

import java.net.URI;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties properties = (Properties) o;
        return Double.compare(getMag(), properties.getMag()) == 0 && getTime() == properties.getTime() && getTsunami() == properties.getTsunami() && Objects.equals(getPlace(), properties.getPlace()) && Objects.equals(getDetail(), properties.getDetail()) && Objects.equals(getAlert(), properties.getAlert()) && Objects.equals(getTitle(), properties.getTitle());
    }

    @Override
    public String toString() {
        return "Properties{" +
                "mag=" + mag +
                ", place='" + place + '\'' +
                ", time=" + time +
                ", detail=" + detail +
                ", alert='" + alert + '\'' +
                ", tsunami=" + tsunami +
                ", title='" + title + '\'' +
                '}';
    }
}
