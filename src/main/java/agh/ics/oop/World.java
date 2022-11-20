package agh.ics.oop;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.GrassField;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.Vector2d;
import agh.ics.oop.map.element.MoveDirection;
import agh.ics.oop.util.OptionsParser;

public class World {

    public static void main(String[] args) {
        try {
            System.out.println("system wystartował");
            MoveDirection[] directions = OptionsParser.parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
            System.out.println("system zakończył działanie");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
