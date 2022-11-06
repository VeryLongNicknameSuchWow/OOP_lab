package agh.ics.oop.map;

import agh.ics.oop.map.element.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void testCanMoveTo_outOfBounds() {
        RectangularMap rectangularMap = new RectangularMap(10, 20);

        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(0, 0)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(5, 5)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(10, 20)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(-1, 0)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(0, -1)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(11, 0)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(0, 21)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(11, 21)));
    }

    @Test
    public void testCanMoveTo_occupied() {
        RectangularMap rectangularMap = new RectangularMap(10, 20);
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(5, 5)));
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(10, 10)));

        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(5, 5)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(10, 10)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(5, 10)));
    }

    @Test
    public void testPlace_outOfBounds() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        boolean res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(2, 2)));
        Assertions.assertTrue(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(5, 5)));
        Assertions.assertTrue(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(6, 5)));
        Assertions.assertFalse(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(5, 6)));
        Assertions.assertFalse(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(10, 10)));
        Assertions.assertFalse(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(-1, 5)));
        Assertions.assertFalse(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(-1, -5)));
        Assertions.assertFalse(res);

        Assertions.assertEquals(2, rectangularMap.getElements().size());
    }

    @Test
    public void testPlace_occupied() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        boolean res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(2, 2)));
        Assertions.assertTrue(res);

        res = rectangularMap.place(new Animal(rectangularMap, new Vector2d(2, 2)));
        Assertions.assertFalse(res);

        Assertions.assertEquals(1, rectangularMap.getElements().size());
    }

    @Test
    public void testIsOccupied() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(2, 2)));
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(2, 2)));
        Assertions.assertTrue(rectangularMap.isOccupied(new Vector2d(2, 2)));

        Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(-2, 2)));
    }

    @Test
    public void testObjectAt() {
        RectangularMap rectangularMap = new RectangularMap(5, 5);

        Vector2d vector2d = new Vector2d(2, 2);
        Assertions.assertNull(rectangularMap.objectAt(vector2d));
        Animal animal = new Animal(rectangularMap, vector2d);
        rectangularMap.place(animal);
        Assertions.assertEquals(animal, rectangularMap.objectAt(vector2d));

        Assertions.assertNull(rectangularMap.objectAt(new Vector2d(-2, 2)));
    }
}
