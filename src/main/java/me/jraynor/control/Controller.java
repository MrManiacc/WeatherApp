package me.jraynor.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import me.jraynor.api.events.LocationSelectedEvent;
import me.jraynor.context.Bus;
import me.jraynor.context.Injector;
import me.jraynor.events.ControlEvents;
import me.jraynor.events.InitializationEvent;


/**
 * This class will wire everything up so they can be injected to other locations
 */
public class Controller {
    @FXML
    private Pane firstDayPane;
    @FXML
    private Pane secondDayPane;
    @FXML
    private Pane thirdDayPane;
    @FXML
    private AnchorPane titleBar;
    @FXML
    private HBox contentPane;
    @FXML
    private TextField searchBar;
    @FXML
    private Button clearButton;
    @FXML
    private Label firstDayLabel;
    @FXML
    private Label firstHighLabel;
    @FXML
    private Label firstLowLabel;
    @FXML
    private Label firstDayTemp;
    @FXML
    private Label secondDayLabel;
    @FXML
    private Label secondHighLabel;
    @FXML
    private Label secondLowLabel;
    @FXML
    private Label thirdDayLabel;
    @FXML
    private Label thirdHighLabel;
    @FXML
    private Label thirdLowLabel;

    @FXML
    private ListView<String> locationList;

    @FXML
    public void initialize() {
        Injector.inject(this);
        Bus.post(new InitializationEvent());
    }

    @FXML
    public void clearButtonEnter() {
        Bus.post(new ControlEvents.ControlEnterEvent(clearButton));
    }

    @FXML
    public void clearButtonExit() {
        Bus.post(new ControlEvents.ControlExitEvent(clearButton));
    }


    @FXML
    public void searchBarTyped() {
        Bus.post(new ControlEvents.ControlActionEvent(searchBar));
    }

    @FXML
    public void clearButtonAction() {
        Bus.post(new ControlEvents.ControlActionEvent(clearButton));
    }

    /**
     * This method will parse out the location key based upon the location list value string,
     * it will find texture between the [].
     */
    @FXML
    public void locationClicked() {
        String selected = locationList.getSelectionModel().getSelectedItem();
        String index = selected.substring(selected.indexOf("[") + 1, selected.lastIndexOf("]"));
        Bus.post(new LocationSelectedEvent(index));
    }
}
