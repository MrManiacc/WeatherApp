package me.jraynor.logic;

import com.google.common.collect.Maps;
import com.google.common.eventbus.Subscribe;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import me.jraynor.annotations.Injectable;
import me.jraynor.events.ControlEvents;

import java.util.Map;

/**
 * This class will do the styling for the inputs
 */
@Injectable
public class StyleHandle {
    TextField searchBar;
    Button clearButton;
    ListView<String> locationList;
    HBox contentPane;
    AnchorPane titleBar;
    private final Map<Control, String> originalStyles = Maps.newHashMap();

    /**
     * Here we initialize the default styling
     */
    public void init() {
        searchBar.setStyle(searchBar.getStyle() + ";-fx-text-fill: white;");
        originalStyles.put(searchBar, searchBar.getStyle());
        originalStyles.put(clearButton, clearButton.getStyle());
        locationList.setVisible(false);
        contentPane.setVisible(false);
        searchBar.setFocusTraversable(false);
    }

    /**
     * This will enable hovering of our clear button
     */
    @Subscribe
    public void onControlHover(ControlEvents.ControlEnterEvent e) {
        if (e.control.equals(clearButton))
            startHover(clearButton, "#2c3e50");
        else if (e.control.equals(searchBar))
            startHover(searchBar, "#34495e");
    }

    /**
     * This will disable hovering of our
     */
    @Subscribe
    public void onControlStopHover(ControlEvents.ControlExitEvent e) {
        stopHover(e.control);
    }


    /**
     * Starts the hovering for the control
     */
    private void startHover(Control control, String color) {
        String style = originalStyles.get(control) + ";-fx-background-color: " + color + ";";
        control.setStyle(style);
    }

    /**
     * Stops the hovering for the control
     */
    private void stopHover(Control control) {
        control.setStyle(originalStyles.get(control));
    }
}
