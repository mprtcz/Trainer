package com.mprtcz.training;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Azet on 2016-02-28.
 */
public class App extends javafx.application.Application {

    @Override
    public void start(Stage window) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainerMainMenu.fxml"));
        System.out.println(loader.getClass());
        Parent root = loader.load();

        Scene scene = new Scene(root, 1024, 768);

        window.setTitle("Training Logger");
        window.setScene(scene);
        window.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
