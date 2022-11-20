package agh.ics.oop.map;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    // Not ordering by map element type, because map boundary can be determined without it.
    // If necessary this could be easily done with SortedSet<AbstractWorldMapElement> and
    // positionChanged(AbstractWorldMapElement element) which rehashes given animal according to class and new position.

    private final SortedSet<Vector2d> orderedByX = new TreeSet<>(Comparator.comparingInt(o -> o.x));
    private final SortedSet<Vector2d> orderedByY = new TreeSet<>(Comparator.comparingInt(o -> o.y));

    public boolean hasBoundary() {
        return !orderedByX.isEmpty() && !orderedByY.isEmpty();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removePosition(oldPosition);
        addPosition(newPosition);
    }

    public void removePosition(Vector2d position) {
        orderedByX.remove(position);
        orderedByY.remove(position);
    }

    public void addPosition(Vector2d position) {
        orderedByX.add(position);
        orderedByY.add(position);
    }

    public Vector2d getMaxByX() {
        return orderedByX.last();
    }

    public Vector2d getMinByX() {
        return orderedByX.first();
    }

    public Vector2d getMaxByY() {
        return orderedByY.last();
    }

    public Vector2d getMinByY() {
        return orderedByY.first();
    }
}
