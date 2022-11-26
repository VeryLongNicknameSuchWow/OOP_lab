package agh.ics.oop.map.element;

import agh.ics.oop.map.IPositionChangeObserver;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractWorldMapElement {
    protected final IWorldMap map;
    protected final List<IPositionChangeObserver> observers;
    protected final boolean collision;

    protected Vector2d position;

    public AbstractWorldMapElement(IWorldMap map, boolean collision, Vector2d position) {
        this.map = map;
        this.observers = new LinkedList<>();
        this.collision = collision;
        this.position = position;
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    protected void move(Vector2d newPosition) {
        if (map.canMoveTo(newPosition)) {
            this.positionChanged(newPosition);
            this.position = newPosition;
        }
    }

    private void positionChanged(Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(this.position, newPosition);
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean hasCollision() {
        return collision;
    }

    public abstract Path getTexture();

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
