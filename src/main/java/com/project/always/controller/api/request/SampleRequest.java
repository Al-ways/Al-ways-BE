package com.project.always.controller.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SampleRequest {
    private String name;
    private int age;

    public SampleRequest() {
    }

    public SampleRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
