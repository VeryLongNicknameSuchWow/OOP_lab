package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {

    public static MoveDirection[] parse(String[] directions) {
        return Arrays.stream(directions)
                .map(OptionsParser::parse)
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection parse(String direction) {
        return switch (direction) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "l", "left" -> MoveDirection.LEFT;
            default -> null;
        };
    }
}
