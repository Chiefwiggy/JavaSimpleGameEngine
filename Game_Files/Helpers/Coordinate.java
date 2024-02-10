package Game_Files.Helpers;

public class Coordinate<T> {

    private final T row, col;

    public Coordinate(T row, T col) { this.row = row; this.col = col; }

    public T GetRow() { return row; }

    public T GetCol() { return col; }

    @Override
    public String toString() { return "Coordinate ( Row: " + row + ", Col: " + col + " )"; }

}
