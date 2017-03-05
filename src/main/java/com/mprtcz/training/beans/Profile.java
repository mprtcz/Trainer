package com.mprtcz.training.beans;

import com.mprtcz.training.chartDataOperators.ChartsDataParser;
import com.mprtcz.training.sqlOperators.SQLManipulator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Azet on 2016-06-16.
 */
public class Profile {
    private ObservableList<Exercise> historyList;
    private ChartsDataParser chartsDataParser;

    public Profile() {
        initializeProfile();
    }

    public ObservableList<String> getNames() {
        List<String> names = new ArrayList<String>();
        historyList.stream().filter(bean -> !names.contains(bean.getName())).forEach(
                bean -> names.add(bean.getName()));
        return FXCollections.observableArrayList(names);
    }

    public void addExercise(Exercise exercise) {
        try {
            SQLManipulator.saveExercise(exercise);
            initializeProfile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initializeProfile() {
        try {
            historyList = FXCollections.observableArrayList(SQLManipulator.loadExercises());
            parseChartsData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<String> getHistory() {
        ObservableList<String> historyStrings = FXCollections.observableArrayList();
        historyStrings.addAll(historyList.stream().map(Exercise::toString).collect(Collectors.toList()));
        return historyStrings;
    }

    public ObservableList<PieChart.Data> getPieChartData() {
        return chartsDataParser.getPieChartData();
    }

    public List<XYChart.Series> getBarChartData() {
        return chartsDataParser.collectBarChartData();
    }

    private void parseChartsData() {
        chartsDataParser = new ChartsDataParser(historyList);
    }
}
