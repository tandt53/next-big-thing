package onboarding.mobile.gesture;


public interface JsGesture {

    void swipeLeft();

    void swipeRight();

    void swipe(String elementId, Direction direction);

    boolean scrollDown();

    boolean scrollUp();

    void dragAndDrop(int startX, int startY, int endX, int endY);

    void tap(int x, int y);

    void tap(String elementId);
}
