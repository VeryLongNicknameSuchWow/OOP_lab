package agh.ics.oop.map;


import agh.ics.oop.map.element.AbstractWorldMapElement;
import agh.ics.oop.map.element.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {

    protected final List<AbstractWorldMapElement> elements;

    public AbstractWorldMap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return elements.stream().noneMatch(element -> element.isAt(position) && element.hasCollision());
    }

    @Override
    public boolean place(Animal animal) {
        return placeElement(animal);
    }

    public boolean placeElement(AbstractWorldMapElement element) {
        Vector2d position = element.getPosition();
        if (element.hasCollision()) {
            if (canMoveTo(position)) {
                return elements.add(element);
            }
        } else {
            if (!isOccupied(position)) {
                return elements.add(element);
            }
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return elements.stream().anyMatch(element -> element.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elements.stream().filter(element -> element.isAt(position)).findAny().orElse(null);
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
        Optional<Vector2d> baseVector = elements.stream().map(AbstractWorldMapElement::getPosition).findAny();

        // map contains no elements
        if (baseVector.isEmpty()) {
            Vector2d zeroVector = new Vector2d(0, 0);
            return mapVisualizer.draw(zeroVector, zeroVector);
        }

        Vector2d ll = baseVector.get();
        Vector2d ur = ll;

        for (AbstractWorldMapElement element : elements) {
            Vector2d position = element.getPosition();
            ll = ll.lowerLeft(position);
            ur = ur.upperRight(position);
        }

        return mapVisualizer.draw(ll, ur);
    }
}
