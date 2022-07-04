package onboarding.common.observer;


public interface Subscriber<T> {
    void update(T object);
}