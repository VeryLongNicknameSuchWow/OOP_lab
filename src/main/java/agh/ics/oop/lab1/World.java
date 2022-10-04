package agh.ics.oop.lab1;

import java.util.Arrays;
import java.util.Objects;

public class World {

    public static void main(String[] args) {
        System.out.println("system wystartował");
        Direction[] directions = Arrays.stream(args).map(arg -> switch (arg) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> null;
        }).filter(Objects::nonNull).toArray(Direction[]::new);
        run(directions);
        System.out.println("system zakończył działanie");
    }

    public static void run(Direction[] directions) {
        Arrays.stream(directions).forEach(direction -> System.out.println(direction.message));
    }
}
