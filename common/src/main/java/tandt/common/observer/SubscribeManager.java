package tandt.common.observer;

public interface SubscribeManager<T extends EventType, O> {
    void attach(T type, Subscriber<O> subscriber);

    void detach(T type);

    void notifyUpdate(T type, O object);

    void notifyUpdate(O object);
}