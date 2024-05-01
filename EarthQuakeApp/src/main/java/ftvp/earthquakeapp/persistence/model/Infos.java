package ftvp.earthquakeapp.persistence.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Infos {
    String title;
    double mag;
    String place;
    long millitime;
    String detail;
    String alert;
    int tsunami;
    Date datetime;
    public Infos(){}

    public Infos(String title, double mag, String place, long millitime, String detail, String alert, int tsunami) {
        this.title = title;
        this.mag = mag;
        this.place = place;
        this.millitime = millitime;
        this.detail = detail;
        this.alert = alert;
        this.tsunami = tsunami;
        this.datetime = new Date(millitime);
    }

    public String getTitle() {
        return title;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getMillitime() {
        return millitime;
    }

    public String getDetail() {
        return detail;
    }

    public String getAlert() {
        return alert;
    }

    public int getTsunami() {
        return tsunami;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setMillitime(long time) {
        this.millitime = millitime;
    }

    public void setDetail(String detail) {
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

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infos infos = (Infos) o;
        return Double.compare(getMag(), infos.getMag()) == 0 && getMillitime() == infos.getMillitime() && getTsunami() == infos.getTsunami() && Objects.equals(getPlace(), infos.getPlace()) && Objects.equals(getDetail(), infos.getDetail()) && Objects.equals(getAlert(), infos.getAlert()) && Objects.equals(getTitle(), infos.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mag, place, millitime, detail, alert, tsunami, title);
    }

    @Override
    public String toString() {
        return "Infos{" +
                "mag=" + mag +
                ", place='" + place + '\'' +
                ", time=" + millitime +
                ", detail=" + detail +
                ", alert='" + alert + '\'' +
                ", tsunami=" + tsunami +
                ", title='" + title + '\'' +
                '}';
    }
}
