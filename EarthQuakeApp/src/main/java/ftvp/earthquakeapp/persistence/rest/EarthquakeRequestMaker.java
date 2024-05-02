package ftvp.earthquakeapp.persistence.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import ftvp.earthquakeapp.persistence.model.Earthquake;
import ftvp.earthquakeapp.persistence.model.Geometry;
import ftvp.earthquakeapp.persistence.model.Metadata;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeRequestMaker implements RequestMaker<Earthquake> {

    public List<Earthquake> getDefault() {

        //Generating a new OkHttpClient - client class for Http requests
        OkHttpClient client = new OkHttpClient();

        //URL data
        String protocol = "https";
        String host = "earthquake.usgs.gov";
        String path = "fdsnws/event/1";
        String method = "query";
        String auth = null;
        String fragment = null;

        //Building URL
        URL myurl = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .addPathSegments(path)
                .addPathSegments(method)
                .addQueryParameter("format", "geojson")
                .addQueryParameter("starttime", LocalDate.now().toString())
                .build().url();

        //System.out.println(myurl);

        //Setting the request parameters (defaults to GET)
        Request request = new Request.Builder()
                .url(myurl)
                .build();

        //Generating a new call to obtain the response
        Call call = client.newCall(request);

        //Trying to execute the call
        try (Response response = call.execute()){

            //Saving body data before closing
            ObjectMapper mapper = new ObjectMapper();
            ResponseBody responseBody = response.body();

            JsonNode bodyNode = mapper.readTree(responseBody.string());

            Metadata metadata = new Metadata(
                    bodyNode.get("metadata").get("generated").asText(),
                    bodyNode.get("metadata").get("url").asText(),
                    bodyNode.get("metadata").get("title").asText(),
                    bodyNode.get("metadata").get("status").asInt(),
                    bodyNode.get("metadata").get("api").asText(),
                    bodyNode.get("metadata").get("count").asInt()
            );

            List<Earthquake> earthquakes = new ArrayList<>();

            for(JsonNode feature : bodyNode.get("features")){

                Geometry geom = new Geometry(
                        feature.get("geometry").get("coordinates").get(0).asDouble(),
                        feature.get("geometry").get("coordinates").get(1).asDouble(),
                        feature.get("geometry").get("coordinates").get(2).asDouble()
                );

                Earthquake tmp = new Earthquake(
                        feature.get("id").asText(),
                        feature.get("properties").get("title").asText(),
                        feature.get("properties").get("mag").asDouble(),
                        feature.get("properties").get("place").asText(),
                        feature.get("properties").get("time").asLong(),
                        feature.get("properties").get("detail").asText(),
                        feature.get("properties").get("alert").asText(),
                        feature.get("properties").get("tsunami").asInt(),
                        geom
                );

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
