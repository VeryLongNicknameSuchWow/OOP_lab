package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Vector2dTest {

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), expected={4}")
    @CsvSource({
            "0, 0, 0, 0, true",
            "4, 5, 4, 6, true",
            "3, 5, 4, 5, true",
            "-7, 6, -6, 6, true",
            "7, -6, 8, -6, true",
            "1, 1, 0, 0, false",
            "-4, 5, 4, -5, false",
            "4, 5, 3, 5, false",
            "-7, 8, -7, 6, false",
            "8, -6, -8, -6, false",
    })
    void testAPrecedesB(int xA, int yA, int xB, int yB, boolean expected) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Assertions.assertEquals(expected, vA.precedes(vB));
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), expected={4}")
    @CsvSource({
            "0, 0, 0, 0, true",
            "4, 5, 4, 6, false",
            "3, 5, 4, 5, false",
            "-7, 6, -6, 6, false",
            "7, -6, 8, -6, false",
            "1, 1, 0, 0, true",
            "4, 5, -4, -5, true",
            "4, 5, 3, 5, true",
            "-7, 8, -7, 6, true",
            "8, -6, -8, -6, true",
    })
    void testAFollowsB(int xA, int yA, int xB, int yB, boolean expected) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Assertions.assertEquals(expected, vA.follows(vB));
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), vE=({4}, {5})")
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "4, 5, 6, 7, 6, 7",
            "-1, -2, -3, -4, -1, -2",
            "-10, 20, 20, -30, 20, 20"
    })
    void upperRight(int xA, int yA, int xB, int yB, int xE, int yE) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Vector2d vE = new Vector2d(xE, yE);
        Assertions.assertEquals(vE, vA.upperRight(vB));
        Assertions.assertEquals(vE, vB.upperRight(vA));
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), vE=({4}, {5})")
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "4, 5, 6, 7, 4, 5",
            "-1, -2, -3, -4, -3, -4",
            "-10, 20, 20, -30, -10, -30"
    })
    void lowerLeft(int xA, int yA, int xB, int yB, int xE, int yE) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Vector2d vE = new Vector2d(xE, yE);
        Assertions.assertEquals(vE, vA.lowerLeft(vB));
        Assertions.assertEquals(vE, vB.lowerLeft(vA));
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), vE=({4}, {5})")
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "0, 0, 5, 6, 5, 6",
            "-3, -4, 3, 4, 0, 0",
            "-8, 0, 0, -9, -8, -9",
            "1, 2, 3, 4, 4, 6"
    })
    void add(int xA, int yA, int xB, int yB, int xE, int yE) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Vector2d vE = new Vector2d(xE, yE);
        Assertions.assertEquals(vE, vA.add(vB));
        Assertions.assertEquals(vE, vB.add(vA));
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), vE=({4}, {5})")
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "0, 0, 5, 6, -5, -6",
            "-3, -4, 3, 4, -6, -8",
            "-8, 0, 0, -9, -8, 9",
            "1, 2, 3, 4, -2, -2",
            "10, -20, 10, -20, 0, 0"
    })
    void subtract(int xA, int yA, int xB, int yB, int xE, int yE) {
        Vector2d vA = new Vector2d(xA, yA);
        Vector2d vB = new Vector2d(xB, yB);
        Vector2d vE = new Vector2d(xE, yE);
        Assertions.assertEquals(vE, vA.subtract(vB));
    }

    @ParameterizedTest(name = "{index} => v=({0}, {1})")
    @CsvSource({
            "1, 2",
            "3, 4",
            "-5, 6",
            "7, -8",
            "-9, -10"
    })
    void opposite(int x, int y) {
        Vector2d vA = new Vector2d(x, y);
        Vector2d vE = new Vector2d(-x, -y);
        Assertions.assertEquals(vE, vA.opposite());
    }

    @ParameterizedTest(name = "{index} => vA=({0}, {1}), vB=({2}, {3}), expected={4}")
    @CsvSource({
            "0, 0, 0, 0, true",
            "1, 2, 1, 2, true",
            "-5, 6, -5, 6, true",
            "7, -8, 7, -8, true",
            "-9, -10, -9, -10, true",
            "1, 2, 2, 1, false",
            "-1, 2, 1, -2, false",
            "10, 11, 12, 13, false"
    })
    void testEquals(int x, int y) {
        Vector2d vA = new Vector2d(x, y);
        Vector2d vE = new Vector2d(x, y);
        Assertions.assertEquals(vE, vA);
    }

    @ParameterizedTest(name = "{index} => v=({0}, {1})")
    @CsvSource({
            "1, 2",
            "-3, 4",
            "5, -6",
            "-7, -8"
    })
    void testToString(int x, int y) {
        Vector2d v = new Vector2d(x, y);
        Assertions.assertEquals(String.format("(%s,%s)", x, y), v.toString());
    }
}
