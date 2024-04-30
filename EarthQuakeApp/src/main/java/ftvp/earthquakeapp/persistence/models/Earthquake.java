package ftvp.earthquakeapp.persistence.models;

import java.util.Objects;

public class Earthquake {

    private String id;
    private Properties properties;
    private Geometry geometry;

    public Earthquake() {}

    public String getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
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
    public String toString() {
        return "Earthquake{" +
                "id='" + id + '\'' +
                ", properties=" + properties.toString() +
                ", geometry=" + geometry.toString() +
                '}';
    }
}
