import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestExample {

    public static void main(String[] args) {

        //Creating a new OkHttpClient class to make requests
        OkHttpClient client = new OkHttpClient();

        //URL data
        String protocol = "https";
        String host = "jsonplaceholder.typicode.com";
        String path = "users";

        //Building URL
        URL myurl = new HttpUrl.Builder()
                .scheme(protocol)
                .host(host)
                .addPathSegments(path)
                .build().url();

        //Printing the generated URL
        System.out.println("Generated URL: " + myurl);

        //Setting the request parameters (defaults to GET method)
        Request request = new Request.Builder()
                .url(myurl)
                .build();

        //Generating a new call to obtain the response
        Call call = client.newCall(request);

        //Trying to execute the call
        try (Response response = call.execute()){

            //Saving body data before OkHttpClient closes the request
            ResponseBody responseBody = response.body();

            //Creating a new ObjcetMapper from JACKSON library
            ObjectMapper mapper = new ObjectMapper();

            //Parsing the body string to JsonNode format (from JACKSON)
            JsonNode bodyNode = mapper.readTree(responseBody.string());

            //Printing some information from Json
            System.out.println("------- Printing first user infos -------");
            System.out.println(bodyNode.get(0).get("id").asInt());
            System.out.println(bodyNode.get(0).get("name").asText());
            System.out.println(bodyNode.get(0).get("email").asText());
        }
        catch (IOException e) {
            //Catching errors and throwing exceptions
            throw new RuntimeException(e);
        }
    }
}
