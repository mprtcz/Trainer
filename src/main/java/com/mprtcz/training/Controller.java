package com.mprtcz.training;

import com.mprtcz.training.beans.Exercise;
import com.mprtcz.training.beans.Profile;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    public BorderPane borderPane;

    private Profile profile;

    public void onExerciseTypesListViewMouseClicked() {
        try {
            exerciseNameTextField.setText(exerciseTypesListView.getSelectionModel().getSelectedItem());

        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }
    }

    public void onAddButtonClicked() {
        Exercise exercise = buildExerciseFromGUIFields();
        validateNewExercise(exercise);
    }

    private Exercise buildExerciseFromGUIFields() {
        String exerciseName = exerciseNameTextField.getText();
        int reps = parseNumberOfReps();
        return new Exercise(exerciseName, reps);
    }

    private int parseNumberOfReps() {
        int reps = -1;
        try {
            reps = Integer.parseInt(repeatsTextField.getText());
        } catch (NumberFormatException e) {
            messagesLabel.setText("Use positive numeric values only in number of reps");
        }
        return reps;
    }

    private void validateNewExercise(Exercise exercise) {
        if (exercise.getReps() > 0) {
            profile.addExercise(exercise);
            updateGUI();
        } else if (exercise.getReps() == 0) {
            messagesLabel.setText("Use only positive numbers");
        }
    }

    @FXML
    public void initialize() {
        profile = new Profile();
        updateGUI();
    }

    private void updateGUI() {
        historyListView.setItems(profile.getHistory());
        exerciseTypesListView.setItems(profile.getNames());
        exercisesPieChart.setData(profile.getPieChartData());
        exercisesBarChart.getData().clear();
        exercisesBarChart.getData().addAll(profile.getBarChartData());
    }
}
