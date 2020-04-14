package me.jraynor.logic;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.jraynor.annotations.Injectable;
import me.jraynor.context.Context;

/**
 * This class allows for the window to be draggable
 */
@Injectable
public class WindowHandler {
    AnchorPane titleBar;
    private double xOff, yOff;

    /**
     * Initialize the event handlers
     */
    public void init() {
        Stage stage = Context.get(Stage.class, "stage").get();

        titleBar.setOnMousePressed(event -> {
            xOff = event.getSceneX();
            yOff = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOff);
            stage.setY(event.getScreenY() - yOff);
        });

    }
}
