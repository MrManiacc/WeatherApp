package me.jraynor.context;

import com.google.common.eventbus.EventBus;

/**
 * This class handles events with googles event bus
 */
public final class Bus {
    private static final EventBus bus = new EventBus("bus");

    /**
     * This will post an event to the event bus
     */
    public static void post(Object event) {
        bus.post(event);
    }

    /**
     * Registers an object to the bus
     */
    public static void register(Object object) {
        bus.register(object);
    }
}
