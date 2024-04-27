package entities;

import java.net.URL;

public class Metadata {

    private String generated;
    private URL url;
    private String title;
    private int status;
    private String api;
    private long count;

    public Metadata() {}

    public Metadata(String generated, URL url, String title, int status, String api, long count) {
        this.generated = generated;
        this.url = url;
        this.title = title;
        this.status = status;
        this.api = api;
        this.count = count;
    }

    public String getGenerated() {
        return generated;
    }

    public URL getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getApi() {
        return api;
    }

    public long getCount() {
        return count;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
