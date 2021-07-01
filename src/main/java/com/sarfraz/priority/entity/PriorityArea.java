package com.sarfraz.priority.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PriorityAreaCollection")
public class PriorityArea {

    @Id
    String name;

    public PriorityArea(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PriorityArea{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
