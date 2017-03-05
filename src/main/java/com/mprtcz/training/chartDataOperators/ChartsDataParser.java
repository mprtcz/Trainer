package com.mprtcz.training.chartDataOperators;

import com.mprtcz.training.Exercises;
import com.mprtcz.training.beans.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Azet on 2016-06-16.
 */
public class ChartsDataParser {
    private Exercises exerciseTypes;

    public ChartsDataParser(ObservableList<Exercise> historyList) {
        this.exerciseTypes = new Exercises(historyList);
    }

    public ObservableList<PieChart.Data> getPieChartData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.addAll(sumExerciseReps().stream().map(exercise ->
                new PieChart.Data(exercise.getName(), exercise.getReps())).collect(Collectors.toList()));
        return data;
    }

    private Collection<Exercise> sumExerciseReps(){
        return exerciseTypes.sumExerciseReps();
    }

    public List<XYChart.Series> collectBarChartData() {
        LinkedHashMap<LocalDate, Collection<Exercise>> summedExercisesInDay = exerciseTypes.sumMappedExercisesInDates();
        List<XYChart.Series> chartData = new ArrayList<>();
        for (String name : exerciseTypes.getAllExercisesNames()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(name);
            for (Map.Entry<LocalDate, Collection<Exercise>> entry : summedExercisesInDay.entrySet()) {
                for (Exercise exercise : entry.getValue()) {
                    if(exercise.getName().equals(name)) {
                        series.getData().add(new XYChart.Data<>(entry.getKey().toString(), exercise.getReps()));
                    }
                }
            }
            chartData.add(series);
        }
        return chartData;
    }
}
