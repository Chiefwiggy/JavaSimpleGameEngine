package Game_Files.Helpers;

import Game_Files.Interfaces.BehaviorParameter;

public class Coordinate<T> implements BehaviorParameter
{

    private final T x, y;

    public Coordinate(T y, T x) { this.x = x; this.y = y; }

    public T GetX() { return x; }

    public T GetY() { return y; }

    @Override
    public String toString() { return "Coordinate (" + x + ", " + y + ')'; }

}
