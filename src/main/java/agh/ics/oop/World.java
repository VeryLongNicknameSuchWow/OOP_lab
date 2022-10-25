package agh.ics.oop;

public class World {

    public static void main(String[] args) {
        System.out.println("system wystartował");
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        System.out.println(mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(10, 5)));
        System.out.println("system zakończył działanie");
    }
}
