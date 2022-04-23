package light.mobile.gesture;

import light.ui.element.Element;

public interface JsGesture {

    /**
     * ios, android
     * Swipe's parameters:
     * - direction: "left", "right", "up", "down"
     * - location: start(x,y) to end(x,y)
     * - duration: time in milliseconds
     */
    void swipe(); // swipeGesture

    void swipe(int startX, int startY, int endX, int endY);

    void swipe(int startX, int startY, int endX, int endY, int duration);

    void swipe(Element element, int endX, int endY);

    void swipe(int startX, int startY, int direction);

    /**
     * ios, android
     * scroll's parameters:
     * - direction:"up", "down"
     * - location: start(x,y) to end(x,y)
     * - duration: time in milliseconds
     * - element: element to scroll
     */
    void scroll(); // scrollGesture

    // ios, android
    void pinch(); // pinchOpenGesture, pinchCloseGesture

    // ios/android
    void doubleTap(); // double click on android

    // ios
    void touchAndHold();

    // ios
    void twoFinderTap();

    // ios, android
    void tap(); // click on android

    // ios, android
    void dragAndDrop();

    // ios
    void selectPickleWheelValue();

    // ios
    void rotateElement();

    // ios
    void tapMultipleTimes(int numberOfTaps);

    // android
    void longClick();

    // android
    void fling();
}
