package demorequest;

import entities.Earthquake;
import entities.Geometry;
import entities.Metadata;
import entities.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OkHttpDemo {
    public static void main(String[] args) {

        //Generating a new OkHttpClient - client class for Http requests
        OkHttpClient client = new OkHttpClient();

        String alert = "yellow";
        int magnitude = 6;

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
                .addQueryParameter("minmagnitude", "7")
                .build().url();

        System.out.println(myurl);

        //Setting the request parameters
        Request request = new Request.Builder()
                .url(myurl)
                .build(); // defaults to GET

        //Generating a new call to obtain the response
        Call call = client.newCall(request);

        //Trying to execute the call
        try (Response response = call.execute()){

            //Saving body data before closing
            ResponseBody responseBody = response.body();
            JsonObject jsonBody = JsonParser.parseString(responseBody.string()).getAsJsonObject();

            //Printing test
            System.out.println(jsonBody.get("metadata"));
            System.out.println(jsonBody.get("features"));

            //Parsing metadata form Json to class
            Gson gson = new Gson();
            Metadata metadata = gson.fromJson(jsonBody.get("metadata"), Metadata.class);

            //Parsing features from JsonArray to classes
            JsonArray features = jsonBody.get("features").getAsJsonArray();

            //Generating and filling an earthquake list
            List<Earthquake> earthquakes = new ArrayList<>();
            for(int i = 0; i < metadata.getCount(); i++){

                Earthquake tmp = new Earthquake();

                String id = features.get(i).getAsJsonObject().get("id").getAsString();
                tmp.setId(id);


                Properties prop = gson.fromJson(features.get(i).getAsJsonObject().get("properties"), Properties.class);
                tmp.setProperties(prop);

                Geometry geom = gson.fromJson(features.get(i).getAsJsonObject().get("geometry"), Geometry.class);
                tmp.setGeometry(geom);

                earthquakes.add(tmp);
            }

            System.out.println("------------- My first Earthquake! -------------");
            System.out.println("Id: " + earthquakes.getFirst().getId());
            System.out.println("Id: " + earthquakes.getFirst().getProperties().getTitle());

        } catch (IOException e) {
            //Catching errors and throwing exceptions
            throw new RuntimeException(e);
        }
    }
}
