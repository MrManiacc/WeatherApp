package me.jraynor.logic;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.jraynor.annotations.Injectable;
import me.jraynor.api.Weather;
import me.jraynor.api.events.CurrentWeatherSuccessEvent;
import me.jraynor.api.events.LocationSelectedEvent;
import me.jraynor.api.events.LocationSuccessEvent;
import me.jraynor.api.events.WeatherSuccessEvent;
import me.jraynor.api.models.LocationSearchModel;
import me.jraynor.api.requests.LocationRequest;
import me.jraynor.context.Context;
import me.jraynor.events.ControlEvents;

import java.util.List;
import java.util.Optional;

@Injectable
public class InputHandler {
    TextField searchBar;
    Button clearButton;
    HBox contentPane;
    ListView<String> locationList;
    Stage stage;

    /**
     * We want to get a reference to the stage here
     */
    public void init() {
        Context.get(Stage.class, "stage").ifPresent(value -> this.stage = value);
    }

    /**
     * This will be called antime theres an action, so we check to see if the aciton is search type
     */
    @Subscribe
    public void onAction(ControlEvents.ControlActionEvent event) {
        if (event.control.equals(searchBar)) {
            Weather.getLocation(searchBar.getText());
            contentPane.setVisible(false);
        } else if (event.control.equals(clearButton)) {
            contentPane.setVisible(false);
            stage.setHeight(80);
        }
    }

    /**
     * We want to make the panes visble here
     */
    @Subscribe
    public void onWeatherSuccess(WeatherSuccessEvent event) {
        contentPane.setVisible(true);
        stage.setHeight(220);//Reset to default height
    }


    /**
     * This will be called when a location is successfully found, and will be added to the location list
     */
    @Subscribe
    public void onLocationSuccess(LocationSuccessEvent event) {
        Platform.runLater(() -> {
            locationList.getItems().clear();
            List<LocationSearchModel> locations = event.models;
            locations.forEach(model -> locationList.getItems().add("[" + model.getKey() + "]: " + model.getLocalizedName() + ", " + model.getCountry().getLocalizedName()));
            //Limit the size to a maximum of 7 elements
            int size = Math.min(locationList.getItems().size() * 26, 7 * 26);
            locationList.setPrefHeight(size);
            locationList.setVisible(true);
            stage.setHeight(80 + size);
        });
    }

    /**
     * This method will be called when a location is selected from the drop down
     */
    @Subscribe
    public void onLocationSelected(LocationSelectedEvent event) {
        Weather.getWeatherForecast(event.locationKey);
        Weather.getCurrentWeather(event.locationKey);
        locationList.setVisible(false);
        locationList.getItems().clear();
        searchBar.setText("");//reset the text box
    }

}
