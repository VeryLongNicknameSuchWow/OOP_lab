package agh.ics.oop;

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
        Arrays.stream(directions).map(direction -> switch (direction) {
            case FORWARD -> "Zwierzak idzie do przodu";
            case BACKWARD -> "Zwierzak idze do tyłu";
            case RIGHT -> "Zwierzak skręca w prawo";
            case LEFT -> "Zwierzak skręca w lewo";
        }).forEach(System.out::println);
    }
}
