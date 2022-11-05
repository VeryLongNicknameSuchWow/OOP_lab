package agh.ics.oop.map;


public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInBounds(position) && super.canMoveTo(position);
    }

    public boolean isInBounds(Vector2d position) {
        return position.x >= 0 && position.x <= width && position.y >= 0 && position.y <= height;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (!isInBounds(position)) {
            return false;
        }
        return super.isOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (!isInBounds(position)) {
            return null;
        }
        return super.objectAt(position);
    }
}
