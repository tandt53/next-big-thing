package tandt.mobile.gesture;

public enum Direction {
    LEFT("left"), RIGHT("right"), UP("up"), DOWN("down");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
