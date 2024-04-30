package ftvp.earthquakeapp.persistence.models;

import java.util.Arrays;
import java.util.Objects;

public class Geometry {    private String type;

    private double[] coordinates;

    public Geometry(){
        coordinates = new double[3];
    };

    public String getType() {
        return type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geometry geometry = (Geometry) o;
        return Arrays.equals(coordinates, geometry.getCoordinates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), Arrays.hashCode(getCoordinates()));
    }

    @Override
    public String toString(){
        return "Geometry{"+coordinates[0]+","+coordinates[1]+","+coordinates[2]+"}";
    }
}

