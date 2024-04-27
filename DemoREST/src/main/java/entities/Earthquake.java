package entities;

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
}
