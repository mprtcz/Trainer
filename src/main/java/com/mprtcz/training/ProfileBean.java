package com.mprtcz.training;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-06-16.
 */
public class ProfileBean {
    ObservableList namesList;
    ObservableList<ExerciseBean> historyList;
    ChartsDataParser chartsDataParser;

    public ProfileBean(){
        initializeProfileBean();
    }

    public ObservableList<String> getNames(){
        List<String> names = new ArrayList<String>();
        for(ExerciseBean bean: historyList){
            if(!names.contains(bean.getExerName())){
                names.add(bean.getExerName());
            }
        }

        return FXCollections.observableArrayList(names);
    }

    void addExercise(ExerciseBean exerciseBean) {
        try {
            SQLManipulator.saveBean(exerciseBean);
            initializeProfileBean();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void initializeProfileBean(){
        try {
            historyList = FXCollections.observableArrayList(SQLManipulator.loadBeans());
            namesList = getNames();
            parseChartsData();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    ObservableList<String> getHistory(){
        ObservableList<String> historyStrings = FXCollections.observableArrayList();
        for(ExerciseBean exerciseBean : historyList){
            historyStrings.add(exerciseBean.toString());
        }
        return historyStrings;
    }


    ObservableList<PieChart.Data> getPieChartData(){
        return chartsDataParser.getPieChartData();
    }

    List<XYChart.Series> getBarChartData(){
        return chartsDataParser.parseBarData();
    }

    void parseChartsData(){
        chartsDataParser = new ChartsDataParser(historyList);
    }





}
