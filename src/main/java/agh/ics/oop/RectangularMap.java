package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    private final int width;
    private final int height;

    private final List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.x <= width && position.y >= 0 && position.y <= height;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (isOccupied(position)) {
            return false;
        }
        return animals.add(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch(animal -> animal.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream().filter(animal -> animal.isAt(position)).findAny().orElse(null);
    }

    @Override
    public Animal[] getAnimals() {
        return animals.toArray(Animal[]::new);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
