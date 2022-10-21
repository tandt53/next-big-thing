package onboarding.mobile.gesture;

import onboarding.ui.element.Element;

public interface JsGesture {

    /**
     * ios, android
     * Swipe's parameters:
     * - direction: "left", "right", "up", "down"
     * - location: start(x,y) to end(x,y)
     * - duration: time in milliseconds
     */
    // swipeGesture
    default void swipe(Direction direction) {}

    default void swipe(Direction direction, int top, int left, int width, int height) {}

    default void swipe(Direction direction, int top, int left, int width, int height, long speed) {}

    default void swipe(Element element, int width, int height){}

    default void swipe(int startX, int startY, Direction direction){}

    /**
     * ios, android
     * scroll's parameters:
     * - direction:"up", "down"
     * - location: start(x,y) to end(x,y)
     * - duration: time in milliseconds
     * - element: element to scroll
     */
    default boolean scroll(){return false;} // scrollGesture

    // ios, android
    default void pinch(){} // pinchOpenGesture, pinchCloseGesture

    // ios/android
    default void doubleTap(){} // double click on android

    // ios
    default void touchAndHold(){}

    // ios
    default void twoFinderTap(){}

    // ios, android
    default void tap(){} // click on android

    // ios, android
    default void dragAndDrop(){}

    // ios
    default void selectPickleWheelValue(){}

    // ios
    default void rotateElement(){}

    // ios
    default void tapMultipleTimes(int numberOfTaps){}

    // android
    default void longClick(){}

    // android
    default void fling(){}
}
