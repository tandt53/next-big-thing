package tandt.common.event;


import tandt.common.Log;
import tandt.common.event.type.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * source from post by Petre Popescu
 * Designing and Coding Event Management in Java
 * @param <T>
 */
public abstract class EventDispatcher<T extends Event> {
    private Log evenLog = new Log(EventDispatcher.class);
    private Map<EventType, List<EventListener>> listeners;

    public EventDispatcher() {
        this.listeners = new HashMap<>();
    }

    public void addEventListener(EventType eventType, EventListener listener) {
        if (listener == null) return;

        List<EventListener> handlersForEventType = listeners.get(eventType);
        if (handlersForEventType == null) {
            handlersForEventType = new ArrayList<>();
            listeners.put(eventType, handlersForEventType);
        }

        if (handlersForEventType.contains(listener)) return;
        handlersForEventType.add(listener);
    }

    protected void dispatchEvent(EventType eventType, Object... eventData) {
        List<EventListener> listenersForEvent = listeners.get(eventType);
        if (listenersForEvent == null || listenersForEvent.isEmpty()) return;

        listenersForEvent.forEach(listener -> {
            try {
                listener.onEvent(buildEvent(eventType, eventData));
            } catch (Exception e) {
                evenLog.error("Listener threw exception for event " + eventType);
            }
        });
    }

    protected abstract T buildEvent(EventType eventType, Object... eventData);
}
