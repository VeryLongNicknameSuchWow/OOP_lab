package agh.ics.oop.map.element;

import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.Vector2d;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;

    public Animal() {
        this(new RectangularMap(4, 4));
    }

    public Animal(final IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(map, true, initialPosition);
    }

    public void move(MoveDirection moveDirection) {
        switch (moveDirection) {
            case FORWARD -> super.move(position.add(direction.toUnitVector()));
            case BACKWARD -> super.move(position.add(direction.toUnitVector().opposite()));
            case RIGHT -> direction = direction.next();
            case LEFT -> direction = direction.previous();
        }
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        if (!super.equals(o)) return false;
        return direction == animal.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), direction);
    }

    @Override
    public Path getTexture() {
        return switch (direction) {
            case NORTH -> Paths.get("src/main/resources/up.png");
            case EAST -> Paths.get("src/main/resources/right.png");
            case SOUTH -> Paths.get("src/main/resources/down.png");
            case WEST -> Paths.get("src/main/resources/left.png");
        };
    }

    @Override
    public String toString() {
        return Character.toString(direction.getLetter());
    }

    public String serialize() {
        return "Animal{" +
                "direction=" + direction +
                ", position=" + position +
                '}';
    }
}
