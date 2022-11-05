package agh.ics.oop.map.element;

import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;

import java.util.Objects;

public abstract class AbstractWorldMapElement {
    protected final IWorldMap map;
    protected final boolean collision;

    protected Vector2d position;

    public AbstractWorldMapElement(IWorldMap map, boolean collision, Vector2d position) {
        this.map = map;
        this.collision = collision;
        this.position = position;
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    protected void move(Vector2d newPosition) {
        if (map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean hasCollision() {
        return collision;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractWorldMapElement that = (AbstractWorldMapElement) o;
        return collision == that.collision && Objects.equals(map, that.map) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, collision, position);
    }
}
