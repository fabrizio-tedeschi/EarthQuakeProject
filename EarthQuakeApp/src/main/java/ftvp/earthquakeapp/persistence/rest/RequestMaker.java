package ftvp.earthquakeapp.persistence.rest;

import java.util.List;

public interface RequestMaker<T> {
    List<T> getDefault();
}
