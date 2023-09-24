package com.project.always.controller.api.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.Getter;

@Getter
public class SampleResponse {
    private String name;
    private int age;


    public SampleResponse() {
    }

    public SampleResponse(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
