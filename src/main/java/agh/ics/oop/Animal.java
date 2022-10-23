package agh.ics.oop;

import java.util.Objects;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    private static final Vector2d RightUpperBound = new Vector2d(4, 4);
    private static final Vector2d LeftLowerBound = new Vector2d(0, 0);

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
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
        newPosition = newPosition.lowerLeft(RightUpperBound);
        newPosition = newPosition.upperRight(LeftLowerBound);
        this.position = newPosition;
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
        return "Animal{" +
                "direction=" + direction +
                ", position=" + position +
                '}';
    }
}
