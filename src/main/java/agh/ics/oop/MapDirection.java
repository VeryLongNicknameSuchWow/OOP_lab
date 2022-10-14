package agh.ics.oop;

public enum MapDirection {
    NORTH(0, 1, "Północ"),
    EAST(1, 0, "Wschód"),
    SOUTH(0, -1, "Południe"),
    WEST(-1, 0, "Zachód");

    private static final MapDirection[] vals = values();

    public final int x;
    public final int y;
    private final String text;

    MapDirection(int x, int y, String text) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public MapDirection next() {
        return MapDirection.vals[(this.ordinal() + 1) % vals.length];
    }

    public MapDirection previous() {
        return MapDirection.vals[(this.ordinal() + vals.length - 1) % vals.length];
    }

    public Vector2d toUnitVector() {
        return new Vector2d(this.x, this.y);
    }

    @Override
    public String toString() {
        return this.text;
    }
}
