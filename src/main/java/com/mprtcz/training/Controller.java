package com.mprtcz.training;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Created by Azet on 2016-06-16.
 */
public class Controller {


    public ListView<String> exerciseTypesListView;
    public ListView<String> historyListView;
    public TextField exerciseNameTextField, repeatsTextField;
    public Button addButton;
    public Label messagesLabel;
    public PieChart exercisesPieChart;
    public BarChart exercisesBarChart;
    private ProfileBean profileBean;

    public void onExerciseTypesListViewMouseClicked() {
        try {
            exerciseNameTextField.setText(exerciseTypesListView.getSelectionModel().getSelectedItem());

        } catch (NullPointerException nex) {
            System.out.println("Null!");
        }
    }

    public void onAddButtonClicked() {
        String exerciseName;
        int reps;

        try {
            exerciseName = exerciseNameTextField.getText();

            reps = Integer.parseInt(repeatsTextField.getText());

            ExerciseBean bean = new ExerciseBean(exerciseName, reps);

            profileBean.addExercise(bean);
            updateGUI();

        } catch (Exception ex) {
            System.out.println("Exceptions! " +ex.getCause() +" message: " +ex.getMessage());
            System.out.println(ex.getClass().toString());
            if(ex.getClass().toString().contains("NumberFormatException")){
                messagesLabel.setText("Use numeric values only in number of reps");
            } else {
                ex.printStackTrace();
            }
        }
    }

    public void initialize() {
        System.out.println("Initialized...");

        profileBean = new ProfileBean();
        updateGUI();
    }

    private void updateGUI(){
        historyListView.setItems(profileBean.getHistory());
        exerciseTypesListView.setItems(profileBean.getNames());
        exercisesPieChart.setData(profileBean.getPieChartData());
        exercisesBarChart.getData().clear();
        exercisesBarChart.getData().addAll(profileBean.getBarChartData());
    }
}
