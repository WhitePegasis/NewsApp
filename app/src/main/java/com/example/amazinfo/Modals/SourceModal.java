package com.example.amazinfo.Modals;

import java.io.Serializable;

public class SourceModal implements Serializable {
    String id="",name="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
