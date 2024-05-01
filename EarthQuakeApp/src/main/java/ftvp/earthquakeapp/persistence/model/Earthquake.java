package ftvp.earthquakeapp.persistence.model;

import java.util.Objects;

public class Earthquake {

    public String id;
    Infos infos;
    Geometry geometry;

    public Earthquake() {}

    public String getId() {
        return id;
    }

    public Infos getProperties() {
        return infos;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProperties(Infos infos) {
        this.infos = infos;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProperties(), that.getProperties()) && Objects.equals(getGeometry(), that.getGeometry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, geometry, infos);
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "id='" + id + '\'' +
                ", infos=" + infos.toString() +
                ", geometry=" + geometry.toString() +
                '}';
    }
}
