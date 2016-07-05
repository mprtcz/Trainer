package com.mprtcz.training.beans;


import java.time.LocalDateTime;

/**
 * Created by Azet on 2016-02-28.
 */
public class ExerciseBean {

    private int reps;
    private String exerName;
    private java.time.LocalDateTime dateTime;
    private String stringRepresentation;
    private String dateTimeString;

    public ExerciseBean(String name, int reps) {
        this.reps = reps;
        this.exerName = name;
        this.dateTime = LocalDateTime.now();
        //dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss")).replace("T", " ");
    }

    public static class Builder{
        private final int reps;
        private final String exerName;

        private String dateTimeString = "";

        public Builder(String exerciseName, int reps){
            this.exerName = exerciseName;
            this.reps = reps;
        }

        public Builder dateTime(String newDateTime){
            dateTimeString = newDateTime;
            return this;
        }

        public ExerciseBean build(){
            return new ExerciseBean(this);
        }

    }

    private ExerciseBean(Builder builder){
        reps = builder.reps;
        exerName = builder.exerName;
        stringRepresentation = "Name: " +exerName +" reps: " +reps +" on: " +builder.dateTimeString;
    }

    @Override
    public String toString(){
        if(stringRepresentation!=null) {
            return stringRepresentation;
        } else {
            return "Name: " +exerName +" reps: " +reps;
        }
    }

    public String toDayString(){
        if(stringRepresentation!=null) {
            String[] parts = stringRepresentation.split("on: ");
            return parts[1].substring(0, 10);
        } else {
            return "?";
        }
    }

    public int getReps() {
        return reps;
    }

    public String getExerName() {
        return exerName;
    }

    public String getDateTime() {
        return dateTime.toString().replace("T", " ");
    }

    public void addRepsToBean(int reps){
        this.reps += reps;
    }
}