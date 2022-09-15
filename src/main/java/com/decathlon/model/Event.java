package com.decathlon.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Event implements Serializable {

    private String name;
    private BigDecimal score;
    private String result;

    public Event() {
    }

    public Event(String name, BigDecimal score, String result) {
        this.name = name;
        this.score = score;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
