package agh.ics.oop.map;


import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.Animal;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final Map<Vector2d, AbstractWorldMapElement> elementMap;
    protected final List<AbstractWorldMapElement> elements;
    protected final MapBoundary mapBoundary;

    public AbstractWorldMap() {
        this.elementMap = new HashMap<>();
        this.elements = new LinkedList<>();
        this.mapBoundary = new MapBoundary();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement element = elementMap.remove(oldPosition);
        elementMap.put(newPosition, element);
        mapBoundary.positionChanged(oldPosition, newPosition);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        AbstractWorldMapElement element = elementMap.get(position);
        if (element == null) {
            return true;
        }
        return !element.hasCollision();
    }

    @Override
    public boolean place(Animal animal) {
        return placeElement(animal);
    }

    public boolean placeElement(AbstractWorldMapElement element) {
        Vector2d position = element.getPosition();
        if (canMoveTo(position)) {
            AbstractWorldMapElement previous = elementMap.remove(position);
            removeElement(previous);
            element.addObserver(this);
            elements.add(element);
            elementMap.put(position, element);
            mapBoundary.addPosition(position);
            return true;
        }
        throw new IllegalArgumentException(position + " is already occupied");
    }

    public void removeElement(AbstractWorldMapElement element) {
        if (element == null) {
            return;
        }
        element.removeObserver(this);
        Vector2d position = element.getPosition();
        this.elements.remove(element);
        if (element.equals(this.elementMap.get(position))) {
            elementMap.remove(position);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return elementMap.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elementMap.get(position);
    }

    public List<AbstractWorldMapElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public <T extends AbstractWorldMapElement> List<T> getElements(Class<T> clazz) {
        return elements.stream()
                .filter(element -> element.getClass().equals(clazz))
                .map(clazz::cast)
                .toList();
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        if (!mapBoundary.hasBoundary()) {
            throw new IllegalStateException("unable to draw map");
        }

        Vector2d ll = mapBoundary.getMinByX().lowerLeft(mapBoundary.getMinByY());
        Vector2d ur = mapBoundary.getMaxByX().upperRight(mapBoundary.getMaxByY());

        return mapVisualizer.draw(ll, ur);
    }
}
