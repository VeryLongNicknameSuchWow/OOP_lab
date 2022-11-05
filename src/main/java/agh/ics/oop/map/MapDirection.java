package agh.ics.oop.map;

public enum MapDirection {
    NORTH(0, 1, "Północ", 'N'),
    EAST(1, 0, "Wschód", 'E'),
    SOUTH(0, -1, "Południe", 'S'),
    WEST(-1, 0, "Zachód", 'W');

    private static final MapDirection[] vals = values();

    public final int x;
    public final int y;
    private final String text;
    private final char letter;

    MapDirection(int x, int y, String text, char letter) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.letter = letter;
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

    public char getLetter() {
        return letter;
    }
}
