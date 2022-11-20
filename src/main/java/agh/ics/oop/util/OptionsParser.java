package agh.ics.oop.util;

import agh.ics.oop.map.element.MoveDirection;

import java.util.Arrays;

public class OptionsParser {

    public static MoveDirection[] parse(String[] directions) {
        return Arrays.stream(directions)
                .map(OptionsParser::parse)
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection parse(String direction) {
        return switch (direction) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException("\"" + direction + "\" is not legal move specification");
        };
    }
}
