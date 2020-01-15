package hometask14.common.business;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    protected transient Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}