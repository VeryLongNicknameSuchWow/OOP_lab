package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class MapDirectionTest {

    @Test
    void next() {
        assertSame(MapDirection.NORTH.next(), MapDirection.EAST);
        assertSame(MapDirection.EAST.next(), MapDirection.SOUTH);
        assertSame(MapDirection.SOUTH.next(), MapDirection.WEST);
        assertSame(MapDirection.WEST.next(), MapDirection.NORTH);
    }

    @Test
    void previous() {
        assertSame(MapDirection.NORTH.previous(), MapDirection.WEST);
        assertSame(MapDirection.EAST.previous(), MapDirection.NORTH);
        assertSame(MapDirection.SOUTH.previous(), MapDirection.EAST);
        assertSame(MapDirection.WEST.previous(), MapDirection.SOUTH);
    }
}
