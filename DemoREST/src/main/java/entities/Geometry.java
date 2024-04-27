package entities;

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
    public String toString(){
        return "GEOMETRY: ["+coordinates[0]+","+coordinates[1]+","+coordinates[2]+"]";
    }
}
