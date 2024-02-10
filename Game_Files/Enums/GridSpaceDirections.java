package Game_Files.Enums;

public enum GridSpaceDirections {

    UP_LEFT(-1, -1),
    UP(0, -1),
    UP_RIGHT(1, -1),
    RIGHT(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN(0, 1),
    DOWN_LEFT(-1, 1),
    LEFT(-1, 0);

    public final int xInc, yInc;
    GridSpaceDirections(int xInc, int yInc) {
        this.xInc = xInc;
        this.yInc = yInc;
    }
}
