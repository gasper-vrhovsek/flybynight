package org.gv.flybynight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by gasper on 11/8/16.
 */
public class TestDto implements Serializable{
    private int i;
    private String a;

    private TestDto(){}

    public TestDto(int i, String a) {
        this.i = i;
        this.a = a;
    }

    @JsonProperty
    public int getI() {
        return i;
    }

    @JsonProperty
    public void setI(int i) {
        this.i = i;
    }

    @JsonProperty
    public String getA() {
        return a;
    }

    @JsonProperty
    public void setA(String a) {
        this.a = a;
    }
}
