package agh.ics.oop.map.element;


import agh.ics.oop.util.OptionsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

class AnimalTest {

    @ParameterizedTest(name = "{index}")
    @CsvSource({
            // expectedDirection, expectedX, expectedY, instructions...
            "Północ, 2, 3, forward",

            // direction tests (2 - 8)
            "Północ, 2, 2, l, r, l, l, r, r",
            "Wschód, 2, 2, r, r, r, r, r",
            "Południe, 2, 2, r, right",
            "Zachód, 2, 2, r, right, r",
            "Wschód, 2, 2, l, left, l",
            "Południe, 2, 2, l, left",
            "Zachód, 2, 2, l",

            // map bounds tests (9 - 16)
            "Północ, 2, 4, f, forward, f, f",
            "Wschód, 4, 2, r, f, f, f, f",
            "Południe, 2, 0, r, right, f, f, f, f",
            "Zachód, 0, 2, r, r, r, f, f, f",
            "Północ, 2, 0, b, backward, b, b",
            "Wschód, 0, 2, r, b, b, b, b",
            "Południe, 2, 4, r, right, b, b, b, b",
            "Zachód, 4, 2, r, r, r, b, b, b",

            // coordinates tests (17 - 23)
            "Zachód, 2, 2, f, b, b, f, l, f, f, b, b",
            "Wschód, 3, 3, f, r, f",
            "Wschód, 3, 4, f, f, r, f",
            "Wschód, 4, 3, f, r, f, f",
            "Wschód, 1, 1, b, r, b",
            "Wschód, 1, 0, b, b, r, b",
            "Wschód, 0, 1, b, r, b, b",
    })
    void testAnimal(ArgumentsAccessor argumentsAccessor) {
        String vectorStr = argumentsAccessor.getString(0);
        int x = argumentsAccessor.getInteger(1);
        int y = argumentsAccessor.getInteger(2);
        String expected = String.format("Animal{direction=%s, position=(%d,%d)}", vectorStr, x, y);

        String[] textDirections = IntStream.range(3, argumentsAccessor.size())
                .mapToObj(argumentsAccessor::getString)
                .toArray(String[]::new);

        MoveDirection[] directions = OptionsParser.parse(textDirections);
        Animal animal = new Animal();
        for (MoveDirection direction : directions) {
            animal.move(direction);
        }

        Assertions.assertEquals(expected, animal.serialize());
    }
}
