package tandt.common.event;

import tandt.common.event.type.EventType;

/**
 * source from post by Petre Popescu
 * Designing and Coding Event Management in Java
 */

public class Event {
    private EventType type;
    private EventDispatcher<?> source;
    private Object[] eventData;

    public Event(EventType type, EventDispatcher<?> source, Object... eventData) {
        this.type = type;
        this.source = source;
        this.eventData = eventData;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventDispatcher<?> getSource() {
        return source;
    }

    public void setSource(EventDispatcher<?> source) {
        this.source = source;
    }

    public Object[] getEventData() {
        return eventData;
    }

    public void setEventData(Object[] eventData) {
        this.eventData = eventData;
    }
}

