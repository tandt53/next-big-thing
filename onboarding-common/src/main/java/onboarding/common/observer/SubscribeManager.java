package onboarding.common.observer;


/**
 * @author tandt53
 * @param <T> the type parameter of EventType
 * @param <O> the Object that will be used to notify
 */
public interface SubscribeManager<T extends EventType, O> {
    void attach(T type, Subscriber<O> subscriber);

    void detach(T type);

    void notifyUpdate(T type, O object);

    void notifyUpdate(O object);
}