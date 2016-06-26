package com.mprtcz.training.chartDataOperators;

import com.mprtcz.training.ExercisesList;
import com.mprtcz.training.beans.ExerciseBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-06-16.
 */
public class ChartsDataParser {
    ObservableList<ExerciseBean> historyList;
    ExercisesList exerciseTypes;

    public ChartsDataParser(ObservableList<ExerciseBean> historyList) {
        this.historyList = historyList;
        exerciseTypes = new ExercisesList(historyList);
    }

    public ObservableList<PieChart.Data> getPieChartData(){
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for(ExerciseBean exerciseBean : sumExerciseReps()){
            data.add(new PieChart.Data(exerciseBean.getExerName(), exerciseBean.getReps()));
        }
        System.out.println(data.toString());
        return data;
    }

    private List<ExerciseBean> sumExerciseReps(){
        return exerciseTypes.sumExerciseReps();
    }

    public List<XYChart.Series> parseBarData(){
        List<ExercisesList.ExercisesWithSameDate> summedExercisesByDates = exerciseTypes.sumExercisesInDates();
        List<XYChart.Series> chartData = new ArrayList<>();
        for(String name : exerciseTypes.getAllExercisesNames()){
            XYChart.Series series = new XYChart.Series();
            series.setName(name);
            for(ExercisesList.ExercisesWithSameDate exercisesWithSameDate : summedExercisesByDates){
                for(ExerciseBean exerciseBean : exercisesWithSameDate.getExercisesWithTheSameDateList()){
                    if(exerciseBean.getExerName().equals(name)){
                        series.getData().add(new XYChart.Data<>(exercisesWithSameDate.getListDate(), exerciseBean.getReps()));
                    }
                }
            }
            chartData.add(series);
        }
        return chartData;
    }
}
