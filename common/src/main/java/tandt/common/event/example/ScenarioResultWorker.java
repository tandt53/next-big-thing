package tandt.common.event.example;

import tandt.common.event.EventDispatcher;
import tandt.common.event.type.EventType;
import tandt.common.event.type.ResultEvent;
import tandt.common.event.type.ResultEventType;

public class ScenarioResultWorker extends EventDispatcher<ResultEvent> {

    public void pass(){
        dispatchEvent(ResultEventType.PASS);
    }

    public void fail(){
        dispatchEvent(ResultEventType.FAIL);
    }

    @Override
    protected ResultEvent buildEvent(EventType eventType, Object... eventData) {
        return null;
    }
}
