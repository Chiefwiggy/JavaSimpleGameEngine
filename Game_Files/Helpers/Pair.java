package Game_Files.Helpers;

public class Pair<E> {

    private final E a;
    private final E b;

    public Pair (E a, E b) {
        this.a = a;
        this.b = b;
    }

    public E get(int idx) {
        if (idx == 0) { return this.a; }
        else { return this.b; }
    }

    public String toString() {
        return "Pair: (" + this.a + ", " + this.b + ")";
    }

}
