package Game_Files.Helpers;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Coordinate
{

    private final Number x, y;

    public Coordinate(Number x, Number y) { this.x = x; this.y = y; }

    public Number GetX() { return x; }

    public Number GetY() { return y; }

    @Override
    public String toString() { return "Coordinate (" + x + ", " + y + ')'; }

}
