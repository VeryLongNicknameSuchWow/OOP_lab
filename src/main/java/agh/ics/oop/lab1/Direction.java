package agh.ics.oop.lab1;

enum Direction {
    FORWARD("Zwierzak idzie do przodu"),
    BACKWARD("Zwierzak idze do tyłu"),
    RIGHT("Zwierzak skręca w prawo"),
    LEFT("Zwierzak skręca w lewo");

    final String message;

    Direction(String message) {
        this.message = message;
    }
}
