package ftvp.earthquakeapp.persistence.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import ftvp.earthquakeapp.persistence.model.Earthquake;
import ftvp.earthquakeapp.persistence.model.Geometry;
import ftvp.earthquakeapp.persistence.model.Metadata;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EarthquakeRequestMaker extends RequestMaker {

    String protocol = "https";
    String host = "earthquake.usgs.gov";
    String path = "fdsnws/event/1/query";

    String place;
    Date date;
    double minmag;
    double maxmag;

    private List<Earthquake> earthquakes = new ArrayList<>();

    public EarthquakeRequestMaker() {
        super();
    }

    public EarthquakeRequestMaker(String protocol, String host, String path, String place, Date date, double minmag, double maxmag) {
        super(protocol, host, path);
        this.place = place;
        this.date = date;
        this.minmag = minmag;
        this.maxmag = maxmag;
    }

    public String getPlace() {
        return place;
    }

    public Date getDate() {
        return date;
    }

    public double getMinmag() {
        return minmag;
    }

    public double getMaxmag() {
        return maxmag;
    }

    public void setMaxmag(double maxmag) {
        this.maxmag = maxmag;
    }

    public void setMinmag(double minmag) {
        this.minmag = minmag;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Earthquake> getDefault() {

        URL myurl = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .addPathSegments(path)
                .addQueryParameter("format", "geojson")
                .addQueryParameter("starttime", LocalDate.now().toString())
                .build().url();

        Request request = new Request.Builder()
                .url(myurl)
                .build();

        Call call = client.newCall(request);

        try (Response response = call.execute()){

            if(!response.isSuccessful()){
                throw new RuntimeException("Unsuccessful response: code = " + response.code());
            }

            ResponseBody responseBody = response.body();
            JsonNode bodyNode = mapper.readTree(responseBody.string());

            List<Earthquake> earthquakes = new ArrayList<>();

            for(JsonNode feature : bodyNode.get("features")){

                Geometry geom = new Geometry(
                        feature.get("geometry").get("coordinates").get(0).asDouble(),
                        feature.get("geometry").get("coordinates").get(1).asDouble(),
                        feature.get("geometry").get("coordinates").get(2).asDouble()
                );

                Earthquake tmp = mapper.readValue(feature.get("properties").toString(), Earthquake.class);
                tmp.setId(feature.get("id").asText());
                tmp.setGeometry(geom);
                tmp.setDatetime();

                earthquakes.add(tmp);
            }

            return earthquakes;
        }
        catch (IOException e) {
            //Catching errors and throwing exceptions
            throw new RuntimeException(e);
        }
    }

    public List<Earthquake> getByParams() {

        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme(protocol);
        builder.host(host);
        builder.addPathSegments(path);
        builder.addQueryParameter("format", "geojson");

        if(place != null && !place.isEmpty()){
            GeometryRequestMaker geometryRequestMaker = new GeometryRequestMaker();
            Geometry coords = geometryRequestMaker.geocode(place);

            builder.addQueryParameter("latitude", String.valueOf(coords.getLatitude()));
            builder.addQueryParameter("longitude", String.valueOf(coords.getLongitude()));
            builder.addQueryParameter("maxradiuskm", "500");
        }

        if(date != null){
            builder.addQueryParameter("starttime", date.toString());
            builder.addQueryParameter("endtime", date.toString());
        }
        else{
            builder.addQueryParameter("starttime", LocalDate.now().toString());
        }

        if(minmag != 0.0){
            builder.addQueryParameter("minmagnitude", String.valueOf(minmag));
        }

        if(maxmag != 0.0){
            builder.addQueryParameter("maxmagnitude", String.valueOf(maxmag));
        }

        URL myurl = builder.build().url();

        Request request = new Request.Builder()
                .url(myurl)
                .build();

        Call call = client.newCall(request);

        try (Response response = call.execute()){

            if(!response.isSuccessful()){
                throw new RuntimeException("Unsuccessful response: code = " + response.code());
            }

            ResponseBody responseBody = response.body();
            JsonNode bodyNode = mapper.readTree(responseBody.string());

            for(JsonNode feature : bodyNode.get("features")){

                Geometry geom = new Geometry(
                        feature.get("geometry").get("coordinates").get(0).asDouble(),
                        feature.get("geometry").get("coordinates").get(1).asDouble(),
                        feature.get("geometry").get("coordinates").get(2).asDouble()
                );

                Earthquake tmp = mapper.readValue(feature.get("properties").toString(), Earthquake.class);
                tmp.setId(feature.get("id").asText());
                tmp.setGeometry(geom);
                tmp.setDatetime();

                earthquakes.add(tmp);
            }

            return earthquakes;
        }
        catch (IOException e) {
            //Catching errors and throwing exceptions
            throw new RuntimeException(e);
        }
    }
}
