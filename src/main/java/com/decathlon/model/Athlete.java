package com.decathlon.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "position", "totalScore", "event"})
public class Athlete implements Serializable {


    private String name;
    private BigDecimal totalScore;
    private String position;
    private List<Event> event;

    public Athlete() {

    }

    /**
     * @param name String
     * @param eventList List<Event>
     * @param totalScore BigDecimal
     * @param position String
     */
    public Athlete(String name, List<Event> eventList, BigDecimal totalScore, String position) {
        super();
        this.name = name;
        this.event = eventList;
        this.totalScore = totalScore;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> eventList) {
        this.event = eventList;
    }
}
