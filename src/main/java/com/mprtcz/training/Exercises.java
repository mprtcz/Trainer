package com.mprtcz.training;

import com.mprtcz.training.beans.Exercise;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Azet on 2016-06-16.
 */
public class Exercises {
    private ObservableList<Exercise> historyList;

    public Exercises(ObservableList<Exercise> historyList) {
        this.historyList = historyList;
    }

    private static void addRepsToExercise(Exercise exercise, Map<String, Exercise> nameObjectMap) {
        if(nameObjectMap.containsKey(exercise.getName())) {
            Exercise updatedExercise = nameObjectMap.get(exercise.getName());
            updatedExercise.addRepsToBean(exercise.getReps());
            nameObjectMap.put(exercise.getName(), updatedExercise);
        } else {
            nameObjectMap.put(exercise.getName(), exercise);
        }
    }

    public Collection<Exercise> sumExerciseReps() {
        return sumExerciseReps(historyList);
    }

    private Collection<Exercise> sumExerciseReps(List<Exercise> exercisesToSumUp) {
        Map<String, Exercise> nameObjectMap = new HashMap<>();
        for (Exercise exercise : exercisesToSumUp) {
            addRepsToExercise(exercise, nameObjectMap);
        }
        return nameObjectMap.values();
    }

    private Map<LocalDate, List<Exercise>> collectExercisesByDates() {
        LinkedHashMap<LocalDate, List<Exercise>> groupedExercisesByDate = new LinkedHashMap<>();
        for (Exercise exercise: historyList) {
            if(groupedExercisesByDate.containsKey(exercise.getDate())) {
                groupedExercisesByDate.get(exercise.getDate()).add(exercise);
            } else {
                groupedExercisesByDate.put(exercise.getDate(), new ArrayList<>());
            }
        }
        return groupedExercisesByDate;
    }

    public LinkedHashMap<LocalDate, Collection<Exercise>> sumMappedExercisesInDates() {
        Map<LocalDate, List<Exercise>> exercisesInDate = collectExercisesByDates();
        LinkedHashMap<LocalDate, Collection<Exercise>> summedExercisesInDay = new LinkedHashMap<>();
        for (Map.Entry<LocalDate, List<Exercise>> day : exercisesInDate.entrySet()) {
            Collection<Exercise> summedExercises = sumExerciseReps(day.getValue());
            summedExercisesInDay.put(day.getKey(), summedExercises);
        }
        return sortDaysHashMap(summedExercisesInDay);
    }

    private LinkedHashMap<LocalDate, Collection<Exercise>> sortDaysHashMap(LinkedHashMap<LocalDate, Collection<Exercise>> summedExercisesInDay) {
        LinkedHashMap<LocalDate, Collection<Exercise>> orderedHashMap = new LinkedHashMap<>();
        summedExercisesInDay.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> orderedHashMap.put(x.getKey(), x.getValue()));
        return orderedHashMap;
    }

    public Set<String> getAllExercisesNames() {
        return historyList.stream().map(Exercise::getName).collect(Collectors.toSet());
    }
}
