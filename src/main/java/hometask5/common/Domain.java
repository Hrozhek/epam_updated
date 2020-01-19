package hometask5.common;

import hometask5.IdGenerator;

public abstract class Domain {
    protected Long id;
    public void setId() {
        if (this.id == null) {
            this.id = IdGenerator.generateId();
        }
    }
}
