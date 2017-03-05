package com.mprtcz.training.sqlOperators;

import com.mprtcz.training.beans.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-02-28.
 */
public class SQLManipulator {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "jdbc:sqlite:history.db";

    public static void saveExercise(Exercise bean) {
        loadJDBCDriver();
        try (Connection connection = DriverManager.getConnection(DATABASE);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists history (name, reps, datetime);");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into history values (?, ?, ?);");
            setSaveStatementStrings(preparedStatement, bean);
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadJDBCDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void setSaveStatementStrings(PreparedStatement preparedStatement, Exercise bean) throws SQLException {
        preparedStatement.setString(1, bean.getName());
        preparedStatement.setString(2, String.valueOf(bean.getReps()));
        preparedStatement.setString(3, bean.getDateTimeString());
        preparedStatement.addBatch();
    }

    public static List<Exercise> loadExercises() {
        loadJDBCDriver();
        List<Exercise> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists history (name, reps, datetime);");
            ResultSet resultSet = statement.executeQuery("select * from history;");
            while (resultSet.next()) {
                list.add(buildExerciseFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Exercise buildExerciseFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int reps = Integer.valueOf(resultSet.getString("reps"));
        String datetime = resultSet.getString("datetime");

        return new Exercise.Builder(name, reps).dateTime(datetime).build();
    }
}
