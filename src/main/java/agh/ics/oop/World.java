package agh.ics.oop;

public class World {

    public static void main(String[] args) {
        System.out.println("system wystartował");
        Animal animal = new Animal();
        MoveDirection[] directions = OptionsParser.parse(args);
        System.out.println(animal);
        for (MoveDirection direction : directions) {
            animal.move(direction);
            System.out.println(animal);
        }
        System.out.println("system zakończył działanie");
    }
}
