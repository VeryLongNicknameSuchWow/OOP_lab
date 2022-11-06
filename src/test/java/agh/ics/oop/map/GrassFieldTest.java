package agh.ics.oop.map;

import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.Grass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {

    @Test
    public void testCanMoveTo_occupied() {
        GrassField grassField = new GrassField(1);
        Vector2d grassPosition = grassField.getElements(Grass.class).get(0).getPosition();
        Vector2d animalPosition = grassPosition.add(new Vector2d(2, 2));
        grassField.place(new Animal(grassField, animalPosition));

        Assertions.assertTrue(grassField.canMoveTo(grassPosition));
        Assertions.assertFalse(grassField.canMoveTo(animalPosition));
    }

    @Test
    public void testPlace_occupied() {
        GrassField grassField = new GrassField(1);
        Vector2d grassPosition = grassField.getElements(Grass.class).get(0).getPosition();
        Vector2d animalPosition = grassPosition.add(new Vector2d(2, 2));

        Animal animal1 = new Animal(grassField, animalPosition);
        Assertions.assertTrue(grassField.place(animal1));
        Animal animal2 = new Animal(grassField, grassPosition);
        Assertions.assertTrue(grassField.place(animal2));
        Animal animal3 = new Animal(grassField, grassPosition);
        Assertions.assertFalse(grassField.place(animal3));

        Assertions.assertEquals(3, grassField.getElements().size());
        Assertions.assertEquals(animal1, grassField.objectAt(animalPosition));
        Assertions.assertEquals(animal2, grassField.objectAt(grassPosition));
    }

    @Test
    public void testIsOccupied() {
        GrassField grassField = new GrassField(1);
        Vector2d grassPosition = grassField.getElements(Grass.class).get(0).getPosition();
        Vector2d animalPosition = grassPosition.add(new Vector2d(2, 2));
        grassField.place(new Animal(grassField, animalPosition));

        Assertions.assertTrue(grassField.isOccupied(grassPosition));
        Assertions.assertTrue(grassField.isOccupied(animalPosition));

        grassField.place(new Animal(grassField, grassPosition));
        Assertions.assertTrue(grassField.isOccupied(grassPosition));
    }

    @Test
    public void testObjectAt() {
        GrassField grassField = new GrassField(1);
        Grass grass = grassField.getElements(Grass.class).get(0);
        Vector2d position = grass.getPosition();
        Assertions.assertEquals(grass, grassField.objectAt(position));

        Animal animal = new Animal(grassField, position);
        grassField.place(animal);
        Assertions.assertEquals(animal, grassField.objectAt(position));
    }
}
