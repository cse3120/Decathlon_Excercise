package com.decathlon;

import com.decathlon.enumeration.GameOrder;
import com.decathlon.enumeration.ScoringTables;
import com.decathlon.model.Athlete;
import com.decathlon.model.Event;
import com.decathlon.utils.DecathlonUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DecathlonResult {
    private Integer order;
    private BigDecimal totalScore;
    private Integer position;
    private List<Athlete> athletesWithPositions;

    /**
     * Move inside of arrays to send one row.
     *
     * @return List<Athlete>
     */
    public List<Athlete> resultEvent(List<String> rows) {
        List<Athlete> athletes = new ArrayList<>();
        rows.forEach(row -> athletes.add(this.registerOneAthlete(row)));
        athletes.sort((a1, a2) -> a2.getTotalScore().compareTo(a1.getTotalScore()));
        this.position = 1;
        this.athletesWithPositions = new ArrayList<>();
        this.definePositionPerEachAthlete(athletes);
        return this.athletesWithPositions;
    }

    /**
     * Final position to the athlete.
     *
     */
    private void definePositionPerEachAthlete(List<Athlete> athleteOrigin) {
        List<Athlete> athlete = athleteOrigin.stream().filter(athleteOne -> athleteOne.getPosition().compareToIgnoreCase("0") == 0 && !this.athletesWithPositions.contains(athleteOne)).collect(Collectors.toList());
        if (athlete != null && athlete.size() > 0) {
            List<Athlete> duplicate = athleteOrigin.stream().filter(athleteOne -> athleteOne.getTotalScore().compareTo(athlete.get(0).getTotalScore()) == 0).collect(Collectors.toList());
            if (duplicate.size() > 0) {
                String position1 = this.similarPosition(this.position,duplicate.size());
                duplicate.forEach(a -> a.setPosition(position1));
                this.position = this.position + duplicate.size();
                this.athletesWithPositions.addAll(duplicate);
            } else {
                athlete.get(0).setPosition(this.position.toString());
                this.athletesWithPositions.add(athlete.get(0));
                this.position++;
            }
            this.definePositionPerEachAthlete(athleteOrigin);
        }
    }

    /**
     * Group by the position and return this value
     */
    String similarPosition(Integer begin, Integer end) {
        String response = "";
        for (Integer i = begin; i < (begin + end); i++)
            response = response + i.toString() + " - ";
        return response.substring(0, response.length() - 3);

        /*StringBuilder response = new StringBuilder();
        for (Integer i = begin; i < (begin + end); i++)
             response.append(i.toString()).append(" - ");
        return response.substring(0, response.length() - 3);*/
    }

    public Athlete registerOneAthlete(String register) {
        if (register == null || register.trim().length() < 1 || register.split(DecathlonUtils.SEPARATOR_FILE).length < DecathlonUtils.MAX_FIELDS_FILE) {
            throw new IllegalArgumentException("The parameters [" + register + "] were incorrect, please check them and try again");
        }
        String name = register.split(DecathlonUtils.SEPARATOR_FILE)[0];
        this.order = 1;
        this.totalScore = new BigDecimal("0");
        String fields = register.substring(register.indexOf(DecathlonUtils.SEPARATOR_FILE) + 1);
        List<Event> eventList = Stream.of(fields.split(DecathlonUtils.SEPARATOR_FILE)).map(this::createEvent).collect(Collectors.toList());
        return new Athlete(name, eventList, this.totalScore, "0");
    }

    /**
     * Create an event and calculate the score of each athlete
     * @param athleteScore String
     * @return Event
     */
    public Event createEvent(String athleteScore) {
        String eventName = "";
        BigDecimal score = new BigDecimal("0");
        if (this.order == GameOrder.HUNDRED_MTS.getOrder()) {
            eventName = GameOrder.HUNDRED_MTS.getScoringTable().getName();
            score = GameOrder.HUNDRED_MTS.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.LONGJUMP.getOrder()) {
            eventName = GameOrder.LONGJUMP.getScoringTable().getName();
            score = GameOrder.LONGJUMP.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.SHOTPUT.getOrder()) {
            eventName = GameOrder.SHOTPUT.getScoringTable().getName();
            score = GameOrder.SHOTPUT.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.HIGHJUMP.getOrder()) {
            eventName = GameOrder.HIGHJUMP.getScoringTable().getName();
            score = GameOrder.HIGHJUMP.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.FOUR100MTS.getOrder()) {
            eventName = GameOrder.FOUR100MTS.getScoringTable().getName();
            score = GameOrder.FOUR100MTS.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.HURDLESMTS110.getOrder()) {
            eventName = GameOrder.HURDLESMTS110.getScoringTable().getName();
            score = GameOrder.HURDLESMTS110.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.DISCUSTHROW.getOrder()) {
            eventName = GameOrder.DISCUSTHROW.getScoringTable().getName();
            score = GameOrder.DISCUSTHROW.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.POLEVAULT.getOrder()) {
            eventName = GameOrder.POLEVAULT.getScoringTable().getName();
            score = GameOrder.POLEVAULT.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.JAVELINTHROW.getOrder()) {
            eventName = GameOrder.JAVELINTHROW.getScoringTable().getName();
            score = GameOrder.JAVELINTHROW.getScoringTable().calculatePoints(athleteScore);
        } else if (this.order == GameOrder.THOUSAN500MTS.getOrder()) {
            eventName = GameOrder.THOUSAN500MTS.getScoringTable().getName();
            score = GameOrder.THOUSAN500MTS.getScoringTable().calculatePoints(athleteScore);
        }
        this.order++;
        this.totalScore = this.totalScore.add(score).setScale(2, RoundingMode.DOWN);
        return new Event(eventName, score, athleteScore);
    }

    /**
     * Validate if result is major to Zero. Check this -> (P — B)
     * @return BigDecimal
     */
    private BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b).compareTo(BigDecimal.ZERO) >= 0 ? a.subtract(b) : BigDecimal.ZERO;
    }

    /**
     * Apply the formula INT(A(B — P)^C) for track events.
     * @param bigDecimal  Athlete value
     * @param scoringTables ScoringTables.
     * @return BigDecimal
     */
    public BigDecimal trackEvents(BigDecimal bigDecimal, ScoringTables scoringTables) {
        return scoringTables.getValueA().multiply(BigDecimal.valueOf(Math.pow(this.subtract(scoringTables.getValueB(), bigDecimal).doubleValue(), scoringTables.getValueC().doubleValue()))).setScale(0, BigDecimal.ROUND_DOWN);
    }

    /**
     * Apply the formula INT(A(P — B)^C) for field events.
     * @param bigDecimal  Athlete value
     * @param scoringTables ScoringTables.
     * @return BigDecimal
     */
    public BigDecimal fieldEvents(BigDecimal bigDecimal, ScoringTables scoringTables) {
        return scoringTables.getValueA().multiply(BigDecimal.valueOf(Math.pow(this.subtract(bigDecimal, scoringTables.getValueB()).doubleValue(), scoringTables.getValueC().doubleValue()))).setScale(0, BigDecimal.ROUND_DOWN);
    }

}
