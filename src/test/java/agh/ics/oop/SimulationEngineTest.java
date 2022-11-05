package agh.ics.oop;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.Vector2d;
import agh.ics.oop.map.element.MoveDirection;
import agh.ics.oop.util.OptionsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SimulationEngineTest {

    @Test
    void testCollision() {
        // two duplicate starting positions, only one of them should create an Animal
        Vector2d[] startingPositions = {new Vector2d(1, 1), new Vector2d(3, 4), new Vector2d(3, 4)};
        // last move is impossible, because the position will be occupied
        MoveDirection[] directions = OptionsParser.parse("f,l,f,f,f,f".split(","));
        IWorldMap map = new RectangularMap(3, 4);
        IEngine engine = new SimulationEngine(directions, map, startingPositions);

        Assertions.assertEquals(2, map.getAnimals().length, "map should contain 2 animals");
        engine.run();

        Vector2d[] finalPositions = new Vector2d[]{new Vector2d(1, 4), new Vector2d(2, 4)};
        for (Vector2d position : finalPositions) {
            Assertions.assertNotNull(map.objectAt(position), "there should be an object at " + position);
        }
    }

}
