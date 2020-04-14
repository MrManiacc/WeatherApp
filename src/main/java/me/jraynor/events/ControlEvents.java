package me.jraynor.events;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

/**
 * Stores some arbitrary events, related to controls
 */
public final class ControlEvents {
    private ControlEvents() {
    }

    public static class ControlEnterEvent {
        public final Control control;

        public ControlEnterEvent(Control control) {
            this.control = control;
        }
    }

    public static class ControlActionEvent {
        public final Control control;

        public ControlActionEvent(Control control) {
            this.control = control;
        }
    }

    public static class ControlExitEvent {
        public final Control control;

        public ControlExitEvent(Control control) {
            this.control = control;
        }
    }


}
