package agh.ics.oop.engine;

import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;

import java.util.Arrays;
import java.util.Iterator;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d initialPosition : initialPositions) {
            map.place(new Animal(map, initialPosition));
        }
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
                animal.move(directionIterator.next());
                madeMove = true;
            }
            if (!madeMove) {
                return;
            }
        }
    }
}
