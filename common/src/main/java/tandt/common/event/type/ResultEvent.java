package tandt.common.event.type;

import tandt.common.event.Event;
import tandt.common.event.EventDispatcher;

public class ResultEvent extends Event {
    public ResultEvent(EventType type, EventDispatcher<?> source, Object... eventData) {
        super(type, source, eventData);
    }
}
