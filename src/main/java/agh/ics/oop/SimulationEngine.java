package agh.ics.oop;

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
        Animal[] animals = map.getAnimals();
        Iterator<MoveDirection> directionIterator = Arrays.stream(directions).iterator();
        while (directionIterator.hasNext()) {
            for (Animal animal : animals) {
                animal.move(directionIterator.next());
            }
        }
    }
}
