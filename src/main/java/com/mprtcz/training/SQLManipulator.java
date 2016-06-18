package com.mprtcz.training;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azet on 2016-02-28.
 */
class SQLManipulator {

    static void saveBean(ExerciseBean bean) throws Exception {
        Class.forName("org.sqlite.JDBC");

        Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\history.db");
        Statement statement = connection.createStatement();

        statement.executeUpdate("create table if not exists history (name, reps, datetime);");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into history values (?, ?, ?);");

        preparedStatement.setString(1, bean.getExerName());
        preparedStatement.setString(2, String.valueOf(bean.getReps()));
        preparedStatement.setString(3, String.valueOf(bean.getDateTime()));
        preparedStatement.addBatch();

        preparedStatement.executeBatch();

        statement.close();
        connection.close();
    }

    static List<ExerciseBean> loadBeans() throws Exception {
        Class.forName("org.sqlite.JDBC");

        List<ExerciseBean> list = new ArrayList<ExerciseBean>();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\history.db");
        Statement statement = connection.createStatement();

        statement.executeUpdate("create table if not exists history (name, reps, datetime);");

        ResultSet resultSet = statement.executeQuery(
                "select * from history;"
        );

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int reps = Integer.valueOf(resultSet.getString("reps"));
            String datetime = resultSet.getString("datetime");

            ExerciseBean bean = new ExerciseBean.Builder(name, reps).dateTime(datetime).build(); //builder pattern usage

            list.add(bean);
        }

        statement.close();
        connection.close();

        return list;
    }
}
