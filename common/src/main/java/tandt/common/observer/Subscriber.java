package tandt.common.observer;


public interface Subscriber<T> {
    public void update(T object);
}