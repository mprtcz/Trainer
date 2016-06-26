package com.mprtcz.training;

import com.mprtcz.training.beans.ExerciseBean;
import com.mprtcz.training.chartDataOperators.ExercisesWithSameDateSorter;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-06-16.
 */
public class ExercisesList {
    private List<ExerciseBean> exercisesRepsSumList = new ArrayList<>();

    private ObservableList<ExerciseBean> historyList;

    public ExercisesList(ObservableList<ExerciseBean> historyList) {
        this.historyList = historyList;
    }

    private static void addRepsToExercise(ExerciseBean exerciseBean, List<ExerciseBean> list) {
        if (!list.toString().contains(exerciseBean.getExerName())) {
            list.add(new ExerciseBean(exerciseBean.getExerName(), exerciseBean.getReps()));
        } else {
            for (ExerciseBean exercise : list) {
                if (exerciseBean.getExerName().equals(exercise.getExerName())) {
                    exercise.addRepsToBean(exerciseBean.getReps());
                }
            }
        }
    }

    public List<ExerciseBean> sumExerciseReps(){
        for(ExerciseBean exerciseBean : historyList){
            addRepsToExercise(exerciseBean, exercisesRepsSumList);
        }
        return exercisesRepsSumList;
    }

    private List<ExercisesWithSameDate> groupExercisesByDate(){
        List<ExercisesWithSameDate> exercisesListGroupedByDate = new ArrayList<>();
        for(ExerciseBean exerciseBean : historyList){
            if(!containsObjectWithName(exercisesListGroupedByDate, exerciseBean)){
                exercisesListGroupedByDate.add(new ExercisesWithSameDate(exerciseBean.toDayString(), exerciseBean));
            } else {
                for(ExercisesWithSameDate exercisesWithSameDate : exercisesListGroupedByDate){
                    if(exerciseBean.toDayString().equals(exercisesWithSameDate.listDate)){
                        exercisesWithSameDate.add(exerciseBean);
                    }
                }
            }
        }
        return exercisesListGroupedByDate;
    }

    public List<ExercisesWithSameDate> sumExercisesInDates(){
        List<ExercisesWithSameDate> days = groupExercisesByDate();
        for(ExercisesWithSameDate date : days){
            date.sumExercises();
        }
        days.sort(new ExercisesWithSameDateSorter());
        return days;
    }

    public List<String> getAllExercisesNames(){
        List<String> names = new ArrayList<>();
        for(ExerciseBean exerciseBean: historyList){
            if(!names.contains(exerciseBean.getExerName())){
                names.add(exerciseBean.getExerName());
            }
        }
        return names;
    }

    public class ExercisesWithSameDate{
        List<ExerciseBean> exercisesWithTheSameDateList = new ArrayList<>();
        String listDate;

        ExercisesWithSameDate(String listDate, ExerciseBean exerciseBean) {
            this.listDate = listDate;
            this.exercisesWithTheSameDateList.add(exerciseBean);
        }

        boolean add(ExerciseBean exerciseBean){
            if(exerciseBean.toDayString().equals(listDate)){
                exercisesWithTheSameDateList.add(exerciseBean);
                return true;
            } else {
                return false;
            }
        }

        void sumExercises(){
            List<ExerciseBean> summedBeans = new ArrayList<>();
            for(ExerciseBean exerciseBean : exercisesWithTheSameDateList){
                addRepsToExercise(exerciseBean, summedBeans);
            }
            exercisesWithTheSameDateList = summedBeans;
        }

        public List<ExerciseBean> getExercisesWithTheSameDateList() {
            return exercisesWithTheSameDateList;
        }

        public String getListDate() {
            return listDate;
        }

        @Override
        public String toString(){
            return "Date: " +listDate +" elements: " + exercisesWithTheSameDateList.size();
        }
    }

    private static boolean containsObjectWithName(List<ExercisesWithSameDate> list, ExerciseBean exerciseBean){
        for(ExercisesWithSameDate exercisesWithSameDate : list){
            if(exerciseBean.toDayString().equals(exercisesWithSameDate.listDate)){
                return true;
            }
        }
        return false;
    }
}
