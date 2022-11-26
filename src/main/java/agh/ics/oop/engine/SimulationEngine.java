package agh.ics.oop.engine;

import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.IPositionChangeObserver;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers;
    private int moveDelay;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.directions = directions;
        this.map = map;
        this.observers = new LinkedList<>();
        this.moveDelay = 0;
        for (Vector2d initialPosition : initialPositions) {
            map.place(new Animal(map, initialPosition));
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public int getMoveDelay() {
        return moveDelay;
    }

    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

    @Override
    public void run() {
        if (!(map instanceof AbstractWorldMap abstractWorldMap)) {
            return;
        }

        Iterator<MoveDirection> directionIterator = Arrays.stream(directions).iterator();
        while (directionIterator.hasNext()) {
            boolean madeMove = false;
            for (Animal animal : abstractWorldMap.getElements(Animal.class)) {
                Vector2d oldPos = animal.getPosition();
                animal.move(directionIterator.next());
                Vector2d newPos = animal.getPosition();
                if (!oldPos.equals(newPos)) {
                    for (IPositionChangeObserver observer : observers) {
                        Platform.runLater(() -> observer.positionChanged(oldPos, newPos));
                    }
                    try {
                        Thread.sleep(moveDelay);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                madeMove = true;
            }
            if (!madeMove) {
                return;
            }
        }
    }
}
