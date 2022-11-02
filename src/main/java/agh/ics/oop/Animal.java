package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;
    private IWorldMap map;

    public Animal() {
        this(new RectangularMap(4, 4));
    }

    public Animal(final IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void move(MoveDirection moveDirection) {
        switch (moveDirection) {
            case FORWARD -> this.move(this.direction.toUnitVector());
            case BACKWARD -> this.move(this.direction.toUnitVector().opposite());
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
        }
    }

    private void move(Vector2d moveVector) {
        Vector2d newPosition = this.position.add(moveVector);
        if (map.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return direction == animal.direction && Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, position);
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
