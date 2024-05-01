package ftvp.earthquakeapp.persistence.dao;

import java.util.List;

public interface Repository<T> {
    List<T> getDefault();
}
