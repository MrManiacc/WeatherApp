package me.jraynor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.jraynor.context.Context;
import me.jraynor.context.Reflection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context.put(primaryStage, "stage");
        new Reflection(getClass().getPackage());
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Context.put(root, "root");
        primaryStage.setTitle("Weather app - by james");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 720, 220);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        Context.put(scene, "scene");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
