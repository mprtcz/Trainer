package com.mprtcz.training.chartDataOperators;

import com.mprtcz.training.ExercisesList;

import java.util.Comparator;

/**
 * Created by Azet on 2016-06-17.
 */
public class ExercisesWithSameDateSorter implements Comparator<ExercisesList.ExercisesWithSameDate> {
    @Override
    public int compare(ExercisesList.ExercisesWithSameDate o, ExercisesList.ExercisesWithSameDate t1) {

        String stringDateFirst = o.getListDate();
        String stringDateSecond = t1.getListDate();

        int firstNumber = parseIntFromDateString(stringDateFirst);
        int secondNumber = parseIntFromDateString(stringDateSecond);

        if (firstNumber > secondNumber) {
            return 1;
        } else if (firstNumber < secondNumber) {
            return -1;
        } else {
            return 0;
        }
    }

    private static int parseIntFromDateString(String dateString) {
        String temp = dateString.replaceAll("-", "");
        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(temp);
        } catch (NumberFormatException ex) {
            return -1;
        }
        return parsedNumber;
    }
}
